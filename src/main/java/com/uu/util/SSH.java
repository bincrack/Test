package com.uu.util;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;

/**
 * SSH 服务
 * 
 * @author guangyu@66money.com
 */
public class SSH
{
	/**
	 * 连接SSH服务器
	 * 
	 * @param config 连接配置
	 * @return 打开的连接通道
	 * @throws JSchException
	 */
	public static Channel connect(SSHConfig config) throws JSchException
	{
		JSch jsch = new JSch();
		Session session = jsch.getSession(config.getUsername(), config.getHost(), config.getPort());
		session.setPassword(config.getPassword());
		session.setConfig("StrictHostKeyChecking", "no");
		session.connect();
		Channel channel = session.openChannel("sftp");
		channel.connect();

		return channel;
	}
	
	public static boolean exists(Channel channel, String path) throws SftpException, JSchException
	{
		return ((ChannelSftp) channel).ls(path) != null;
	}

	/**
	 * 建立远程文件夹
	 * 
	 * @param channel 打开的连接SSH通道
	 * @param path 要创建的文件路径
	 * @return 是否创建成功
	 * @throws JSchException
	 * @throws SftpException
	 */
	public static boolean mkdir(Channel channel, String path) throws JSchException, SftpException
	{
		if (!channel.isConnected())
			channel.connect();
		if (path == null)
			return false;

		ChannelSftp sftp = (ChannelSftp) channel;
		path = new File(path).getPath();
		String[] s = path.split("\\\\");
		for (int i = 1; i < s.length; i++)
		{
			String u = "";
			for (int j = 0; j <= i; j++)
				u += s[j] + "/";

			if ("/".equals(u))
				continue;

			try
			{
				SftpATTRS v = sftp.lstat(u);
				if (v == null)
					sftp.mkdir(u);
			}
			catch (SftpException e)
			{
				sftp.mkdir(u);
			}
		}

		return true;
	}

	/**
	 * 上传文件
	 * 
	 * @param channel 打开的连接SSH通道
	 * @param src 本地的文件路径
	 * @param dst 远程的文件路径
	 * @throws JSchException
	 * @throws SftpException
	 */
	public static void upload(Channel channel, String src, String dst) throws JSchException, SftpException
	{
		if (!channel.isConnected())
			channel.connect();
		ChannelSftp sftp = (ChannelSftp) channel;
		sftp.put(src.replaceAll("\\\\", "/"), dst.replaceAll("\\\\", "/"));
	}
	
	/**
	 * 上传文件
	 * 
	 * @param channel
	 * @param src
	 * @param dst
	 * @throws JSchException
	 * @throws SftpException
	 */
	public static void upload(Channel channel, InputStream src, String dst) throws JSchException, SftpException
	{
		if (!channel.isConnected())
			channel.connect();
		ChannelSftp sftp = (ChannelSftp) channel;
		sftp.put(src, dst.replaceAll("\\\\", "/"));
	}

	/**
	 * 下载文件
	 * 
	 * @param channel 打开的连接SSH通道
	 * @param src 远程的文件路径
	 * @param dst 本地的路径
	 * @throws JSchException
	 * @throws SftpException
	 */
	public static void download(Channel channel, String src, String dst) throws JSchException, SftpException
	{
		if (!channel.isConnected())
			channel.connect();

		ChannelSftp sftp = (ChannelSftp) channel;
		sftp.get(src.replaceAll("\\\\", "/"), dst.replaceAll("\\\\", "/"));
	}

	/**
	 * 下载文件
	 * 
	 * @param channel 打开的连接SSH通道
	 * @param src 远程的文件路径
	 * @param outStream 输出流
	 * @throws JSchException
	 * @throws SftpException
	 */
	public static void download(Channel channel, String src, OutputStream outStream) throws JSchException, SftpException
	{
		if (!channel.isConnected())
			channel.connect();

		ChannelSftp sftp = (ChannelSftp) channel;
		sftp.get(src.replaceAll("\\\\", "/"), outStream);
	}

	/**
	 * 获取文件列表
	 * 
	 * @param channel 打开的连接SSH通道
	 * @param directory 文件夹
	 * @return 文件名列表
	 * @throws Exception
	 */
	public static List<String> listFiles(Channel channel, String directory) throws Exception
	{
		if (!channel.isConnected())
			channel.connect();

		List<String> fileNameList = new ArrayList<String>();
		ChannelSftp sftp = (ChannelSftp) channel;
		@SuppressWarnings("rawtypes")
		Vector fileList = sftp.ls(directory);
		@SuppressWarnings("rawtypes")
		Iterator it = fileList.iterator();

		while (it.hasNext())
		{
			String fileName = ((LsEntry) it.next()).getFilename();
			if (".".equals(fileName) || "..".equals(fileName))
			{
				continue;
			}
			fileNameList.add(fileName);

		}

		return fileNameList;
	}

	/**
	 * 检查文件是否存在
	 * 
	 * @param channel 打开的连接SSH通道
	 * @param directory 文件夹
	 * @param filename 文件名
	 * @return 是否存在
	 * @throws Exception
	 */
	public static boolean existFile(Channel channel, String directory, String filename) throws Exception
	{
		if (filename == null)
			return false;

		if (!channel.isConnected())
			channel.connect();

		List<String> fileNameList = new ArrayList<String>();
		ChannelSftp sftp = (ChannelSftp) channel;
		@SuppressWarnings("rawtypes")
		Vector fileList = sftp.ls(directory);
		@SuppressWarnings("rawtypes")
		Iterator it = fileList.iterator();

		while (it.hasNext())
		{
			String fileName = ((LsEntry) it.next()).getFilename();
			if (".".equals(fileName) || "..".equals(fileName))
			{
				continue;
			}
			fileNameList.add(fileName);

		}
		return fileNameList.contains(filename);
	}

	/**
	 * 关闭通道与连接
	 * 
	 * @param channel 连接SSH的通道
	 * @throws JSchException
	 */
	public static void disconnect(Channel channel) throws JSchException
	{
		if (channel != null)
		{
			Session session = channel.getSession();
			channel.disconnect();
			if (session != null)
				session.disconnect();
		}
	}
}
