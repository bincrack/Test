package com.uu.test;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

public class Test2
{

	public static void main(String[] args) throws Exception
	{
//		String key = "�����ʽ�"; // �����ʽ� 290,156,357.14 668,313,239.93 
//		String key = "ʵ���ʱ�"; // ʵ���ʱ�����ɱ��� 242,900,000.00 240,000,000.00
//		String key = "������Ȩ��"; // ������Ȩ�棨��ɶ�Ȩ�棩�ϼ� 1,548,250,908.64 1,407,027,783.75 
//		String key = "���ʼ�ֵ�䶯����"; //   �ӣ����ʼ�ֵ�䶯���棨��\r\nʧ�ԡ����������У�\r\n0.00 0.00
//		String key = "��ծ��������Ȩ��"; // ��ծ��������Ȩ�棨��ɶ�Ȩ\r\n�棩�ܼ� \r\n2,008,849,824.71 1,670,802,166.00 
//		String key = "������"; // �����󣨾������ԡ������� 177,476,066.65 145,429,012.75 \r\n���У�
		String key = "ÿ������"; // �����ɶ�����
		String numberRegex = "(-?\\d+((\\,\\d{3}){1,})?\\.\\d+)";
//		String text = FileUtils.readFileToString(new File("C:\\Users\\Administrator\\Desktop\\1.txt"));
		String text = "�ˡ�ÿ�����棺 \r\n��һ������ÿ������(Ԫ/��) ���ߣ�45 0.34 0.54 \r\n������ϡ��ÿ������(Ԫ/��) ���ߣ�46 0.34 0.54 ";
		text = text.replaceAll("��[^��]*��\\d+[^\\ ]\\ ", "");
		System.out.println(text);
//		text = text.replaceAll("(.*)��ȱ���ȫ��\\s\r\n\\d+\\s\r\n", "");
		Matcher matcher = Pattern.compile("(.*)" + key + "[^\\ ]*\\ " + numberRegex).matcher(text);
//		Matcher matcher = Pattern.compile("(.*)" + key + "(\\S+)?[^\\ ]*\\ (\r\n)?" + numberRegex).matcher(text);
		int index = 0;
		while (matcher.find())
		{
			index++;
			System.out.println("�ҵ���" + index + "��\t" + matcher.group());
		}
	}

}
