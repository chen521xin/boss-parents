/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2018 by                   #
 *#              Shanghai stock Exchange(SSE),shanghai,China        #
 *#                          All rights reserved.                   #
 *#                                                                 #
 *###################################################################
 */
package com.boss.core.db;

import java.io.Serializable;

/**
 * @description
 * @data 2018年8月21日下午9:47:14
 * @author guoyi
 * @version v1.0
 * @since v1.0
 *
 **/
public class MailInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8904330563183281819L;
	private String emailAccount;

	private String emailPassword;

	private String smtpHost;

	private int sendPort;

	private boolean isAuth;

	private boolean debug;

	public String getEmailAccount() {
		return emailAccount;
	}

	public void setEmailAccount(String emailAccount) {
		this.emailAccount = emailAccount;
	}

	public String getEmailPassword() {
		return emailPassword;
	}

	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}

	public String getSmtpHost() {
		return smtpHost;
	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	public int getSendPort() {
		return sendPort;
	}

	public void setSendPort(int sendPort) {
		this.sendPort = sendPort;
	}

	public boolean isAuth() {
		return isAuth;
	}

	public void setAuth(boolean isAuth) {
		this.isAuth = isAuth;
	}

	public boolean getDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}
	
	
}
