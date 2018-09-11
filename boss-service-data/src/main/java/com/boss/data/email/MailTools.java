package com.boss.data.email;

import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.boss.core.db.MailInfo;
import com.boss.core.db.User;
import com.boss.core.value.BizCode;
import com.boss.core.value.BizException;


public class MailTools {


	public static void sendMail(List<User> userList,String title,String content,boolean isAuth,MailInfo mailInfo){
			MailModel mail = new MailModel();
			mail.setIfAuth(isAuth);
			mail.setCharSet("UTF-8");
			mail.setTitle(title);
			mail.setContent(content);
			//mail.setSender("hong.lei@5610086.cn");
			mail.setSender(mailInfo.getEmailAccount());
			
			mail.setDebug(mailInfo.getDebug());

			mail.setSendPort(mailInfo.getSendPort());

			mail.setSmtpServer(mailInfo.getSmtpHost());

			mail.setSenderUsername(mailInfo.getEmailAccount());

			mail.setSenderPwd(mailInfo.getEmailPassword());


			//设置邮件接收者
			ArrayList<String> mais = new ArrayList<String>();
			for (int i = 0; i < userList.size(); i++)
			{
				String us = (String) userList.get(i).getUserMail();
				mais.add(us);
			}
			String mas[] = new String[mais.size()];
			mas = mais.toArray(mas);
			mail.setRecipients(mas);

			//就否为ssl发送
			boolean isSSL = true;

			//发送邮件执行
			try
			{

				MailTools.sendMail(mail, isSSL);
				//打印发送的邮件标题************
				System.out.println(mail.getTitle());
				//打印邮件接收者
				String[] mms = mail.getRecipients();
				for (String mm : mms)
				{
					System.out.print(mm + ";");
				}
				System.out.println();
				//System.out.println("user:" + user.getAccount()
					//	+ "mail send ok!");
				//打印发送的邮件标题************

			} catch (Exception e)
			{
				e.printStackTrace();
				throw new BizException(BizCode.INSURE_AGAINST_FAILURE_MAIL);
			}
		
	}
	/**
	 * 发送邮件
	 * 
	 * @param to
	 *            收件人列表，以","分割
	 * @param subject
	 *            标题
	 * @param body
	 *            内容
	 * @param filepath
	 *            附件列表,无附件传递null
	 * @return
	 * @throws MessagingException
	 * @throws AddressException
	 * @throws UnsupportedEncodingException
	 */
	public static boolean sendMail(MailModel mail)
			throws AddressException, MessagingException, UnsupportedEncodingException {
		String nick = mail.getSender();
		ArrayList<String> filepath = mail.getFilepath();

		// 参数修饰
		if (mail.getContent() == null) {
			mail.setContent("");
		}
		if (mail.getTitle() == null) {
			mail.setTitle("无主题");
		}
		// 创建Properties对象
		Properties props = System.getProperties();
		// 创建信件服务器
		props.put("mail.smtp.host", mail.getSmtpServer());

		props.put("mail.smtp.port", mail.getSendPort());
		if (mail.isIfAuth()) { // 服务器需要身份认证
			props.put("mail.smtp.auth", "true");

		} else {
			props.put("mail.smtp.auth", "false");
		}
		// props.put("mail.smtp.auth", "true"); // 通过验证

		// 得到默认的对话对象
		Session session = Session.getDefaultInstance(props, null);
		session.setDebug(mail.isDebug());
		// 创建一个消息，并初始化该消息的各项元素
		MimeMessage msg = new MimeMessage(session);
		nick = MimeUtility.encodeText(nick);
		msg.setFrom(new InternetAddress(nick + "<" + mail.getSender() + ">"));
		// 创建收件人列表

		String[] arr = mail.getRecipients();
		int receiverCount = arr.length;
		if (receiverCount > 0) {
			InternetAddress[] address = new InternetAddress[receiverCount];
			for (int i = 0; i < receiverCount; i++) {
				address[i] = new InternetAddress(arr[i]);
			}
			msg.addRecipients(Message.RecipientType.TO, address);
			msg.setSubject(mail.getTitle());
			// 后面的BodyPart将加入到此处创建的Multipart中
			Multipart mp = new MimeMultipart();
			// 附件操作
			if (filepath != null && filepath.size() > 0) {
				for (String filename : filepath) {
					MimeBodyPart mbp = new MimeBodyPart();
					// 得到数据源
					FileDataSource fds = new FileDataSource(filename);
					// 得到附件本身并至入BodyPart
					mbp.setDataHandler(new DataHandler(fds));
					// 得到文件名同样至入BodyPart
					mbp.setFileName(fds.getName());
					mp.addBodyPart(mbp);
				}
				MimeBodyPart mbp = new MimeBodyPart();
				mbp.setText(mail.getContent(), mail.getCharSet());
				mp.addBodyPart(mbp);
				// 移走集合中的所有元素
				filepath.clear();
				// Multipart加入到信件
				msg.setContent(mp);
			} else {
				// 设置邮件正文
				msg.setText(mail.getContent(), mail.getCharSet());
			}
			// 设置信件头的发送日期
			msg.setSentDate(new Date());
			msg.saveChanges();
			// 发送信件
			Transport transport = session.getTransport("smtp");
			transport.connect(mail.getSmtpServer(), mail.getSenderUsername(), mail.getSenderPwd());
			transport.sendMessage(msg, msg.getRecipients(Message.RecipientType.TO));
			transport.close();
			return true;
		} else {
			System.out.println("None receiver!");
			return false;
		}
	}

	/**
	 * ssl发送，或者普通发送， 当 isSSL等于true时，端口设置为465, auth设置为true
	 * 
	 * @param mail
	 * @param isSSL
	 * @return
	 * @throws AddressException
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("restriction")
	public static boolean sendMail(MailModel mail, boolean isSSL)
			throws AddressException, MessagingException, UnsupportedEncodingException {
		if (!isSSL) {
			return sendMail(mail);
		} else {
			Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
			final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
			// Get a Properties object
			Properties props = new Properties();
			props.setProperty("mail.smtp.host", mail.getSmtpServer());
			props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
			props.setProperty("mail.smtp.socketFactory.fallback", "false");
			props.setProperty("mail.smtp.port", mail.getSendPort());
			props.setProperty("mail.smtp.socketFactory.port", mail.getSendPort());
			if (mail.isIfAuth()) { // 服务器需要身份认证
				props.put("mail.smtp.auth", "true");

			} else {
				props.put("mail.smtp.auth", "false");
			}

			final String username = mail.getSenderUsername();
			final String password = mail.getSenderPwd();
			Session session = Session.getDefaultInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});
			Message msg = new MimeMessage(session);

			// 设置发件人和收件人
			msg.setFrom(new InternetAddress(mail.getSender()));
			int length = mail.getRecipients().length;
			Address to[] = new InternetAddress[length];
			for (int i = 0; i < length; i++) {
				to[i] = new InternetAddress(mail.getRecipients()[i]);
			}

			// 多个收件人地址
			msg.setRecipients(Message.RecipientType.TO, to);
			msg.setSubject(mail.getTitle()); // 标题
			msg.setText(mail.getContent());// 内容
			msg.setSentDate(mail.getSendDate());

			Transport.send(msg);
			System.out.println("EmailUtil ssl协议邮件发送打印" + msg.toString());

			return true;
		}
	}


}
