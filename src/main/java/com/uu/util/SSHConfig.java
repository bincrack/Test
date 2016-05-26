package com.uu.util;

import java.io.Serializable;

/**
 * SSH配置信息
 * 
 * @author guangyu@66money.com
 */
public class SSHConfig implements Serializable
{
	private static final long serialVersionUID = -8280224731538116904L;

	/**
	 * 主机
	 */
	private String host = "114.215.137.144";
	/**
	 * 端口
	 */
	private int port = 22;
	/**
	 * 用户名
	 */
	private String username = "root";
	/**
	 * 密码
	 */
	private String password = "36715840";

	public SSHConfig()
	{
	}

	public SSHConfig(String host, String username, String passwd, int port)
	{
		this.host = host;
		this.username = username;
		this.password = passwd;
		this.port = port;
	}
	
	public SSHConfig(String host, String username, String passwd)
	{
		this.host = host;
		this.username = username;
		this.password = passwd;
	}

	/**
	 * @return the host
	 */
	public String getHost()
	{
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public void setHost(String host)
	{
		this.host = host;
	}

	/**
	 * @return the port
	 */
	public int getPort()
	{
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(int port)
	{
		this.port = port;
	}

	/**
	 * @return the username
	 */
	public String getUsername()
	{
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username)
	{
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}

}
