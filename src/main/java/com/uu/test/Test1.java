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
//		String text = "�ˡ�ÿ�����棺 \r\n��һ������ÿ������(Ԫ/��) ���ߣ�45 0.34 0.54 \r\n������ϡ��ÿ������(Ԫ/��) ���ߣ�46 0.34 0.54 ";
//		System.out.println(text);
//		String name ="�ɱ�";
//		String numberRegex = "-?\\d+((\\,\\d{3}){1,})?\\.\\d+";
//		Matcher matcher = Pattern.compile(name + ".*" + numberRegex).matcher(text);
//		int index = 0;
//		while (matcher.find())
//		{
//			index++;
//			String str = matcher.group();
//			System.out.println("�ҵ���" + index + "��\t" + str);
//			
//			Matcher m = Pattern.compile(numberRegex).matcher(str);
//			if (m.find())
//			{
//				System.out.println("\t" + m.group());
//			}
//		}
	}

}
