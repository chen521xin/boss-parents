package com.boss.data.email;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

/**
 * 邮件发送模型
 * @author Administrator 
 */
public class MailModel
{
	/**
	 * 邮件标题
	 */
	private String title;

	/**
	 * 邮件内容
	 */
	private String content;

	/**
	 * 是否输出跟踪日志
	 */
	private boolean isDebug;

	/**
	 * 发送者邮件完整地址
	 */
	private String sender;

	/**
	 * 发送者邮件登陆名
	 */
	private String senderUsername;

	/**
	 * 发送者邮箱密码
	 */
	private String senderPwd;
	/**
	 * 发送协议一般默认smtp
	 */
	private String sendAgreement;

	/**
	 * 发送服务器
	 */
	private String smtpServer;

	/**
	 * 字符 集utf-8，gbk等
	 * 默认utf-8
	 */
	private String charSet;

	/**
	 * 发送者邮件登陆名
	 */
	public String getSenderUsername()
	{
		if (this.ifAuth == true)
		{
			if (this.senderUsername == null || this.senderUsername.equals(""))
			{
				throw new NullPointerException("邮箱登陆名称不能为空！");
			}
		}

		return senderUsername;
	}

	/**
	 * 发送者邮件登陆名
	 */
	public void setSenderUsername(String senderUsername)
	{
		this.senderUsername = senderUsername;
	}

	/**
	 * 字符 集utf-8，gbk等
	 * 默认utf-8
	 */
	public String getCharSet()
	{
		if (this.charSet == null || "".equals(charSet))
		{
			this.charSet = "UTF-8";
		}
		return charSet;
	}

	/**
	 * 字符 集utf-8，gbk等
	 * 默认utf-8
	 */
	public void setCharSet(String charSet)
	{
		this.charSet = charSet;
	}

	/**
	 * 发送服务器
	 */
	public String getSmtpServer()
	{
		return smtpServer;
	}

	/**
	 * 发送服务器
	 */
	public void setSmtpServer(String smtpServer)
	{
		this.smtpServer = smtpServer;
	}

	/**
	 * 发件服务器端口，一般默认25
	 */
	private int sendPort;
	/**
	 * 邮件接收者
	 */
	private String[] recipients;

	private ArrayList<String> filepath;

	/**
	 * 服务器是否需要身份认证
	 */
	private boolean ifAuth;

	/**
	 * 服务器是否需要身份认证
	 */
	public boolean isIfAuth()
	{
		return ifAuth;
	}

	/**
	 * 服务器是否需要身份认证
	 */
	public void setIfAuth(boolean ifAuth)
	{
		this.ifAuth = ifAuth;
	}

	/**
	 * 邮件发送时间，如果不设置，默认为当前时间
	 */
	public Date sendDate;

	/**
	 * 邮件发送时间，如果不设置，默认为当前时间
	 */
	public Date getSendDate()
	{
		if (this.sendDate == null)
		{
			return new Date();
		}
		return sendDate;
	}

	/**
	 * 邮件发送时间，如果不设置，默认为当前时间
	 */
	public void setSendDate(Date sendDate)
	{
		this.sendDate = sendDate;
	}

	/**
	 * 邮件标题
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * 邮件标题
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * 邮件内容
	 */
	public String getContent()
	{
		return content;
	}

	/**
	 * 邮件内容
	 */
	public void setContent(String content)
	{
		this.content = content;
	}

	/**
	 * 是否输出跟踪日志
	 */
	public boolean isDebug()
	{
		return isDebug;
	}

	/**
	 * 是否输出跟踪日志
	 */
	public void setDebug(boolean isDebug)
	{
		this.isDebug = isDebug;
	}

	/**
	 * 发送者邮件完整地址
	 * @return
	 */
	public String getSender()
	{
		return sender;
	}

	/**
	 * 发送者邮件完整地址
	 * @return
	 */
	public void setSender(String sender)
	{
		this.sender = sender;
	}

	/**
	 * 发送者邮箱密码
	 * @return
	 */
	public String getSenderPwd()
	{
		if (this.ifAuth == true)
		{
			if (this.senderPwd == null || this.senderPwd.equals(""))
			{
				throw new NullPointerException("发送者邮箱密码登陆密码不能为空！");
			}
		}
		return senderPwd;
	}

	/**
	 * 发送者邮箱密码
	 * @param senderPwd
	 */
	public void setSenderPwd(String senderPwd)
	{
		this.senderPwd = senderPwd;
	}

	/**
	 * 发送协议一般默认smtp
	 * @return
	 */
	public String getSendAgreement()
	{
		if (sendAgreement == null || "".equals(sendAgreement))
		{
			sendAgreement = "smtp";
			return sendAgreement;
		}
		return sendAgreement;
	}

	/**
	 * 发送协议一般默认smtp
	 * @param sendAgreement
	 */
	public void setSendAgreement(String sendAgreement)
	{
		this.sendAgreement = sendAgreement;
	}

	/**
	 * 发件服务器端口，一般默认25
	 * @return
	 */
	public String getSendPort()
	{
		if (sendPort == 0)
		{
			sendPort = 25;
			return sendPort + "";
		}
		return sendPort + "";
	}

	/**
	 * 发件服务器端口，默认25
	 * @return
	 */
	public void setSendPort(int sendPort)
	{
		this.sendPort = sendPort;
	}

	/**
	 * 邮件接收者
	 * @return
	 */
	public String[] getRecipients()
	{
		return recipients;
	}

	/**
	 * 邮件接收者
	 * @param recipients
	 */
	public void setRecipients(String... recipients)
	{
		if (recipients.length == 0)
		{
			throw new NullPointerException("邮件地址为空");
		}
		for (String mailAdd : recipients)
		{
			//System.out.println(mailAdd);
			if (null == mailAdd || "".equals(mailAdd))
			{
				//System.out.println("一个邮件地址为空");
				throw new NullPointerException("一个邮件地址为空");
			}
			if (!MathDbTools.strIsEmail(mailAdd))
			{
				throw new IllegalArgumentException("邮件格式错误：" + mailAdd);
			}
		}
		this.recipients = recipients;
	}

	public ArrayList<String> getFilepath()
	{
		return filepath;
	}

	public void setFilepath(ArrayList<String> filepath)
	{
		for (String file : filepath)
		{
			File fl = new File(file);
			if (!fl.isFile())
			{
				throw new IllegalArgumentException("文件路径错误：" + file);
			}
		}
		this.filepath = filepath;
	}

	/**
	 * 邮件发送模型
	 */
	public MailModel()
	{

	}

	/**
	 * 邮件发送模型
	 * @param charSet 字符 集utf-8，gbk等，默认utf-8
	 * @param title  邮件标题
	 * @param content 邮件正文
	 * @param senderUsername 发送者邮箱用户名
	 * @param senderPwd  发产达者邮箱密码
	 * @param sender 发送者完整邮箱地址如: xxx@xx.xx
	 * @param smtpServer 发送服务器如：smtp.163.com
	 * @param recipients String类型数组，接收者邮箱地址，多个
	 */
	public MailModel(String charSet, String title, String content,
			String senderUsername, String senderPwd, String sender,
			String smtpServer, String... recipients)
	{
		this.charSet = charSet;
		this.title = title;
		this.content = content;
		this.senderUsername = senderUsername;
		this.senderPwd = senderPwd;
		this.sender = sender;
		this.smtpServer = smtpServer;
		this.recipients = recipients;

	}
}
