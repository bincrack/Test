package com.uu.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Http
{

	public static byte[] request(String u, String o, String method) throws Exception
	{
		HttpURLConnection con = null;
		OutputStream out = null;
		InputStream in = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		if ("POST".equals(method))
		{
			method = "POST";
		}
		else
		{
			method = "GET";
		}

		try
		{
			URL url = new URL(u);
			con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			con.setConnectTimeout(30000);
			con.setReadTimeout(30000);
			con.setRequestMethod(method);
			con.setRequestProperty("Pragma:", "no-cache");
			con.setRequestProperty("Cache-Control", "no-cache");
			con.setRequestProperty("Content-Type", "text/xml");

			if (o != null)
			{
				out = con.getOutputStream();
				out.write(o.getBytes());
				out.flush();
			}

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

		return baos.toByteArray();
	}
}
