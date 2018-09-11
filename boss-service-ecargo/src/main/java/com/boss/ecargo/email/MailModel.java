/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#                            shanghai,China                       #
 *#                                                                 #
 *#                                                                 #
 *###################################################################
 */
package com.boss.ecargo.email;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

/**
 * 閭欢鍙戦�佹ā鍨�
 * @author Administrator 
 */
public class MailModel
{
	/**
	 * 閭欢鏍囬
	 */
	private String title;

	/**
	 * 閭欢鍐呭
	 */
	private String content;

	/**
	 * 鏄惁杈撳嚭璺熻釜鏃ュ織
	 */
	private boolean isDebug;

	/**
	 * 鍙戦�佽�呴偖浠跺畬鏁村湴鍧�
	 */
	private String sender;

	/**
	 * 鍙戦�佽�呴偖浠剁櫥闄嗗悕
	 */
	private String senderUsername;

	/**
	 * 鍙戦�佽�呴偖绠卞瘑鐮�
	 */
	private String senderPwd;
	/**
	 * 鍙戦�佸崗璁竴鑸粯璁mtp
	 */
	private String sendAgreement;

	/**
	 * 鍙戦�佹湇鍔″櫒
	 */
	private String smtpServer;

	/**
	 * 瀛楃 闆唘tf-8锛実bk绛�
	 * 榛樿utf-8
	 */
	private String charSet;

	/**
	 * 鍙戦�佽�呴偖浠剁櫥闄嗗悕
	 */
	public String getSenderUsername()
	{
		if (this.ifAuth == true)
		{
			if (this.senderUsername == null || this.senderUsername.equals(""))
			{
				throw new NullPointerException("閭鐧婚檰鍚嶇О涓嶈兘涓虹┖锛�");
			}
		}

		return senderUsername;
	}

	/**
	 * 鍙戦�佽�呴偖浠剁櫥闄嗗悕
	 */
	public void setSenderUsername(String senderUsername)
	{
		this.senderUsername = senderUsername;
	}

	/**
	 * 瀛楃 闆唘tf-8锛実bk绛�
	 * 榛樿utf-8
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
	 * 瀛楃 闆唘tf-8锛実bk绛�
	 * 榛樿utf-8
	 */
	public void setCharSet(String charSet)
	{
		this.charSet = charSet;
	}

	/**
	 * 鍙戦�佹湇鍔″櫒
	 */
	public String getSmtpServer()
	{
		return smtpServer;
	}

	/**
	 * 鍙戦�佹湇鍔″櫒
	 */
	public void setSmtpServer(String smtpServer)
	{
		this.smtpServer = smtpServer;
	}

	/**
	 * 鍙戜欢鏈嶅姟鍣ㄧ鍙ｏ紝涓�鑸粯璁�25
	 */
	private int sendPort;
	/**
	 * 閭欢鎺ユ敹鑰�
	 */
	private String[] recipients;

	private ArrayList<String> filepath;

	/**
	 * 鏈嶅姟鍣ㄦ槸鍚﹂渶瑕佽韩浠借璇�
	 */
	private boolean ifAuth;

	/**
	 * 鏈嶅姟鍣ㄦ槸鍚﹂渶瑕佽韩浠借璇�
	 */
	public boolean isIfAuth()
	{
		return ifAuth;
	}

	/**
	 * 鏈嶅姟鍣ㄦ槸鍚﹂渶瑕佽韩浠借璇�
	 */
	public void setIfAuth(boolean ifAuth)
	{
		this.ifAuth = ifAuth;
	}

	/**
	 * 閭欢鍙戦�佹椂闂达紝濡傛灉涓嶈缃紝榛樿涓哄綋鍓嶆椂闂�
	 */
	public Date sendDate;

	/**
	 * 閭欢鍙戦�佹椂闂达紝濡傛灉涓嶈缃紝榛樿涓哄綋鍓嶆椂闂�
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
	 * 閭欢鍙戦�佹椂闂达紝濡傛灉涓嶈缃紝榛樿涓哄綋鍓嶆椂闂�
	 */
	public void setSendDate(Date sendDate)
	{
		this.sendDate = sendDate;
	}

	/**
	 * 閭欢鏍囬
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * 閭欢鏍囬
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * 閭欢鍐呭
	 */
	public String getContent()
	{
		return content;
	}

	/**
	 * 閭欢鍐呭
	 */
	public void setContent(String content)
	{
		this.content = content;
	}

	/**
	 * 鏄惁杈撳嚭璺熻釜鏃ュ織
	 */
	public boolean isDebug()
	{
		return isDebug;
	}

	/**
	 * 鏄惁杈撳嚭璺熻釜鏃ュ織
	 */
	public void setDebug(boolean isDebug)
	{
		this.isDebug = isDebug;
	}

	/**
	 * 鍙戦�佽�呴偖浠跺畬鏁村湴鍧�
	 * @return
	 */
	public String getSender()
	{
		return sender;
	}

	/**
	 * 鍙戦�佽�呴偖浠跺畬鏁村湴鍧�
	 * @return
	 */
	public void setSender(String sender)
	{
		this.sender = sender;
	}

	/**
	 * 鍙戦�佽�呴偖绠卞瘑鐮�
	 * @return
	 */
	public String getSenderPwd()
	{
		if (this.ifAuth == true)
		{
			if (this.senderPwd == null || this.senderPwd.equals(""))
			{
				throw new NullPointerException("鍙戦�佽�呴偖绠卞瘑鐮佺櫥闄嗗瘑鐮佷笉鑳戒负绌猴紒");
			}
		}
		return senderPwd;
	}

	/**
	 * 鍙戦�佽�呴偖绠卞瘑鐮�
	 * @param senderPwd
	 */
	public void setSenderPwd(String senderPwd)
	{
		this.senderPwd = senderPwd;
	}

	/**
	 * 鍙戦�佸崗璁竴鑸粯璁mtp
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
	 * 鍙戦�佸崗璁竴鑸粯璁mtp
	 * @param sendAgreement
	 */
	public void setSendAgreement(String sendAgreement)
	{
		this.sendAgreement = sendAgreement;
	}

	/**
	 * 鍙戜欢鏈嶅姟鍣ㄧ鍙ｏ紝涓�鑸粯璁�25
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
	 * 鍙戜欢鏈嶅姟鍣ㄧ鍙ｏ紝榛樿25
	 * @return
	 */
	public void setSendPort(int sendPort)
	{
		this.sendPort = sendPort;
	}

	/**
	 * 閭欢鎺ユ敹鑰�
	 * @return
	 */
	public String[] getRecipients()
	{
		return recipients;
	}

	/**
	 * 閭欢鎺ユ敹鑰�
	 * @param recipients
	 */
	public void setRecipients(String... recipients)
	{
		if (recipients.length == 0)
		{
			throw new NullPointerException("閭欢鍦板潃涓虹┖");
		}
		for (String mailAdd : recipients)
		{
			//System.out.println(mailAdd);
			if (null == mailAdd || "".equals(mailAdd))
			{
				//System.out.println("涓�涓偖浠跺湴鍧�涓虹┖");
				throw new NullPointerException("涓�涓偖浠跺湴鍧�涓虹┖");
			}
			if (!MathDbTools.strIsEmail(mailAdd))
			{
				throw new IllegalArgumentException("閭欢鏍煎紡閿欒锛�" + mailAdd);
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
				throw new IllegalArgumentException("鏂囦欢璺緞閿欒锛�" + file);
			}
		}
		this.filepath = filepath;
	}

	/**
	 * 閭欢鍙戦�佹ā鍨�
	 */
	public MailModel()
	{

	}

	/**
	 * 閭欢鍙戦�佹ā鍨�
	 * @param charSet 瀛楃 闆唘tf-8锛実bk绛夛紝榛樿utf-8
	 * @param title  閭欢鏍囬
	 * @param content 閭欢姝ｆ枃
	 * @param senderUsername 鍙戦�佽�呴偖绠辩敤鎴峰悕
	 * @param senderPwd  鍙戜骇杈捐�呴偖绠卞瘑鐮�
	 * @param sender 鍙戦�佽�呭畬鏁撮偖绠卞湴鍧�濡�: xxx@xx.xx
	 * @param smtpServer 鍙戦�佹湇鍔″櫒濡傦細smtp.163.com
	 * @param recipients String绫诲瀷鏁扮粍锛屾帴鏀惰�呴偖绠卞湴鍧�锛屽涓�
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
