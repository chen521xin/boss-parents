/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.ecargo.email;

import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.util.ArrayList;
import java.util.Date;
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

import org.springframework.beans.factory.annotation.Value;

public class MailTools {
	@Value("${email.account}")
	private String emailAccount;

	@Value("${email.password}")
	private String emailPassword;
	/**
	 * 发送邮件的工具类
	 * 
	 * @param model
	 *            邮件模型类
	 * @throws Exception
	 */
	/*
	 * public static void sendMail(MailModel mail) throws Exception { //
	 * 设置JavaMail的属性 Properties props = new Properties(); Session session =
	 * Session.getInstance(props);
	 * 
	 * // 指定邮件的发送协议 //props.setProperty("mail.transport.protocol", "smtp"); //
	 * 根据已配置的JavaMail属性来创建Session实例
	 * 
	 * // 输出跟踪日志 session.setDebug(true); // 创建邮件 Message msg = new
	 * MimeMessage(session);
	 * 
	 * // 设置文件的主题 msg.setSubject(mail.getTitle()); // 设置邮件的发送者 msg.setFrom(new
	 * InternetAddress(mail.getSender()));
	 * //msg.setRecipients(Message.RecipientType.TO, mail.getSender());
	 * 
	 * Multipart mp = new MimeMultipart();
	 * 
	 * //邮件body MimeBodyPart mbp = new MimeBodyPart();
	 * 
	 * //设置邮件正文及字符集 mbp.setContent(mail.getContent(), "text/html;charset=" +
	 * mail.getCharSet()); mp.addBodyPart(mbp); msg.setContent(mp);
	 * 
	 * msg.setSentDate(mail.getSendDate()); //设置信件头的发送日期
	 * 
	 * //发信 msg.saveChanges(); // 确保通过认证 // props.setProperty("mail.smtp.auth",
	 * "true"); if (mail.isIfAuth()) { //服务器需要身份认证 props.put("mail.smtp.auth",
	 * "true");
	 * 
	 * } else { props.put("mail.smtp.auth", "false"); }
	 * 
	 * // 取得transport对象 Transport transport =
	 * session.getTransport(mail.getSendAgreement()); // 指定邮件的发送服务器
	 * //transport.connect("smtp.163.com", mail.getSendPort(), "awfidx-12",
	 * //"1234568");
	 * 
	 * //设置用户密码 transport.connect(mail.getSmtpServer(), mail.getSendPort(), mail
	 * .getSender(), mail.getSenderPwd());
	 * 
	 * // 将消息发送到指定地址，可以同时发送多个地址 if (mail.getRecipients().length == 0) { throw
	 * new NullPointerException("至少设置一个邮件接收者"); } for (String s :
	 * mail.getRecipients()) { transport .sendMessage(msg, new Address[] { new
	 * InternetAddress(s) }); }
	 * 
	 * // 关闭资源 transport.close(); }
	 */

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

	public static void main(String[] args) {
		MailModel md = new MailModel();

		md.setCharSet("UTF-8");
		md.setTitle("全省付款标准仍是调整，养）层面的执按照新的最高最");
		md.setContent(
				"明确、财政部印发《关于2016年调016年调整退休人员基本行方案尚未明确、财政部印发《关于2016年调整退休人员基本养老金的通知》，2015年12月前已退休并按月领取养老金的企业和机关事业单位退休人员 ......百度知道");
		// md.setSenderUsername("hong.lei@5610086.cn");//发送者邮件登录名
		md.setSenderUsername("464344739@qq.com");
		// md.setSenderPwd("Babyching1107");//发送者密码
		md.setSenderPwd("mhqwosbrhqzubiai");
		// md.setSender("hong.lei@5610086.cn");//发送者邮件完整地址
		md.setSender("464344739@qq.com");
		// md.setRecipients("52624135@qq.com");//邮件接收者
		md.setRecipients("1967846206@qq.com");
		md.setSmtpServer("smtp.exmail.qq.com");// 发送服务器
		md.setIfAuth(true);
		md.setSendPort(465);
		md.setDebug(true);

		// md.setSenderUsername("awfidx-12");
		// md.setSenderPwd("504548446xsz");
		// md.setSender("awfidx-12@163.com");
		// md.setRecipients("52624135@qq.com");
		// md.setSmtpServer("smtp.163.com");

		try {
			MailTools.sendMail(md, true);
		} catch (AddressException e) { 
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) { 
			e.printStackTrace();
		} catch (MessagingException e) { 
			e.printStackTrace();
		}
	}
}
