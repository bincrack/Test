package com.uu.test;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

public class Test1
{
	public static void main(String[] args) throws Exception
	{
		File file = new File("C:\\Users\\Administrator\\Desktop\\all\\300243.PDF");
		PDFListTest.make(file);
//		String text = FileUtils.readFileToString(new File("C:\\Users\\Administrator\\Desktop\\1.txt"));
//		text = text.replaceAll("[\u4e00-\u9fa5]+\\-\\d+\\ ", "");
//		String text = "八、每股收益： \r\n（一）基本每股收益(元/股) （七）45 0.34 0.54 \r\n（二）稀释每股收益(元/股) （七）46 0.34 0.54 ";
//		System.out.println(text);
//		String name ="股本";
//		String numberRegex = "-?\\d+((\\,\\d{3}){1,})?\\.\\d+";
//		Matcher matcher = Pattern.compile(name + ".*" + numberRegex).matcher(text);
//		int index = 0;
//		while (matcher.find())
//		{
//			index++;
//			String str = matcher.group();
//			System.out.println("找到第" + index + "个\t" + str);
//			
//			Matcher m = Pattern.compile(numberRegex).matcher(str);
//			if (m.find())
//			{
//				System.out.println("\t" + m.group());
//			}
//		}
	}

}
