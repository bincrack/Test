package com.uu.test;

import java.io.File;


public class Test5
{

	public static void main(String[] args)
	{
		String path = "C:\\Users\\Administrator\\Desktop\\fix\\";
		File[] files = new File(path).listFiles();
		for (File file : files)
		{
			String name = file.getName().replace(".PDFX", "");
			File f = new File(path + name);
			if (f.exists())
			{
				System.out.println(file + " exists");
			}
			else
			{
				file.renameTo(f);
			}
		}
	}

}
