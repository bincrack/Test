package com.uu.test;

import org.apache.commons.codec.digest.DigestUtils;

public class Test7
{
	static
	{
		System.loadLibrary("mscorlib");
	}
	
	public static void main(String[] args)
	{
		String password = "123456";
		String encryption = "Encryption;";
		String s = password + encryption;
//		byte[] b = DigestUtils.md5(s.getBytes());
//		for (int i = 0; i < b.length; i++)
//		{
//			System.out.println(b[i]);
//		}
		
//		System.out.println(System.getProperty("java.library.path"));
//		System.setProperty("-Djava.library.path", "C:/Windows/Microsoft.NET/Framework/v4.0.30319");
//		System.out.println(System.getProperty("java.library.path"));
		
		System.out.println(new Test7().HashCore(s.getBytes(), 0, s.length()));
	}
	
	private void ComputeHash(byte[] b)
	{
		
	}
	
	public native byte[] HashCore(byte[] array, int ibStart, int cbSize);

}
