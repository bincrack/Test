package com.uu.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

public class Test3
{
	public static void download(String u, String o) throws Exception
	{
		HttpURLConnection con = null;
		OutputStream out = null;
		InputStream in = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try
		{
			URL url = new URL(u);
			con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			con.setConnectTimeout(30000);
			con.setReadTimeout(30000);
			con.setRequestProperty("Pragma:", "no-cache");
			con.setRequestProperty("Cache-Control", "no-cache");
			con.setRequestProperty("Content-Type", "text/xml");

			in = con.getInputStream();
			byte[] buffer = new byte[1024];
			int len = -1;
			while ((len = in.read(buffer)) != -1)
				baos.write(buffer, 0, len);
			baos.flush();
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			try
			{
				if (in != null)
					in.close();
				if (out != null)
					out.close();
				if (con != null)
					con.disconnect();
				if (baos != null)
					baos.close();
			}
			catch (IOException e)
			{
			}
		}
		
		FileUtils.writeByteArrayToFile(new File(o), baos.toByteArray());
	}
	
	public static void main(String[] args) throws Exception
	{
		String text = FileUtils.readFileToString(new File("C:\\Users\\Administrator\\Desktop\\d"));
		Matcher matcher = Pattern.compile("<li[^>]*?>.*?</li>").matcher(text);
		int i = 0;
		while (matcher.find())
		{
			String str = matcher.group();
			if (str.indexOf("2015年年度报告") < 0)
				continue;
				
			i++;
			
			String code = "";
			Matcher m1 = Pattern.compile("<font[^>]*?>(\\d+)</font>").matcher(str);
			if (m1.find())
			{
				code = m1.group(1);
			}
			
			String id = "";
			String date = "";
			Matcher m2 = Pattern.compile("/cninfo-new/disclosure/sse/bulletin_detail/true/(\\d+)\\?announceTime=(\\d{4}-\\d{2}-\\d{2})").matcher(str);
			if (m2.find())
			{
				id = m2.group(1);
				date = m2.group(2);
			}
			
			String url = "http://www.cninfo.com.cn/finalpage/" + date + "/" + id + ".PDF";
			String out = "C:\\Users\\Administrator\\Desktop\\download\\" + i + "-" + code + ".PDF";
			download(url, out);
			System.out.println(String.format("index[%s],code[%s],id[%s],date[%s]", i, code, id, date));
		}
		System.out.println(i);
		
//		String text = "<li><div class=\"t1\" code=\"002522\" orgid=\"9900016329\">002522</div><div class=\"t2\" code=\"002522\" orgid=\"9900016329\">浙江众成</div><div class=\"t3\"><dd><a href=\"/cninfo-new/disclosure/sse/bulletin_detail/true/1201958857?announceTime=2016-02-02\" target=\"_blank\"><span class=\"d1\">2015年年度报告<img width=\"20px\" src=\"/cninfo-new/images/720pdf.png\">(1696 k)</span><span class=\"d3\">2016-02-02</span></a></dd></div></li>";
		
//		Matcher matcher = Pattern.compile("<div[^>]*?>(\\d+)</div>").matcher(text);
//		while (matcher.find())
//		{
//			System.out.println(matcher.group());
//		}
		
//		Matcher matcher = Pattern.compile("/cninfo-new/disclosure/sse/bulletin_detail/true/(\\d+)\\?announceTime=(\\d{4}-\\d{2}-\\d{2})").matcher(text);
//		while (matcher.find())
//		{
//			System.out.println(matcher.group(0));
//		}
	}

}
