package com.uu.test;

import java.io.File;


public class Test4
{

	public static void main(String[] args)
	{
		String path = "C:\\Users\\Administrator\\Desktop\\download\\";
		File[] files = new File(path).listFiles();
		for (File file : files)
		{
			String name = file.getName();
			String newName = name.substring(name.indexOf("-") + 1);
			File f = new File(path + "\\new\\" + newName);
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
