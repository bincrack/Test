package com.uu.test;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

public class Test2
{

	public static void main(String[] args) throws Exception
	{
//		String key = "货币资金"; // 货币资金 290,156,357.14 668,313,239.93 
//		String key = "实收资本"; // 实收资本（或股本） 242,900,000.00 240,000,000.00
//		String key = "所有者权益"; // 所有者权益（或股东权益）合计 1,548,250,908.64 1,407,027,783.75 
//		String key = "公允价值变动收益"; //   加：公允价值变动收益（损\r\n失以“－”号填列）\r\n0.00 0.00
//		String key = "负债和所有者权益"; // 负债和所有者权益（或股东权\r\n益）总计 \r\n2,008,849,824.71 1,670,802,166.00 
//		String key = "净利润"; // 净利润（净亏损以“－”号 177,476,066.65 145,429,012.75 \r\n填列）
		String key = "每股收益"; // 少数股东损益
		String numberRegex = "(-?\\d+((\\,\\d{3}){1,})?\\.\\d+)";
//		String text = FileUtils.readFileToString(new File("C:\\Users\\Administrator\\Desktop\\1.txt"));
		String text = "八、每股收益： \r\n（一）基本每股收益(元/股) （七）45 0.34 0.54 \r\n（二）稀释每股收益(元/股) （七）46 0.34 0.54 ";
		text = text.replaceAll("（[^）]*）\\d+[^\\ ]\\ ", "");
		System.out.println(text);
//		text = text.replaceAll("(.*)年度报告全文\\s\r\n\\d+\\s\r\n", "");
		Matcher matcher = Pattern.compile("(.*)" + key + "[^\\ ]*\\ " + numberRegex).matcher(text);
//		Matcher matcher = Pattern.compile("(.*)" + key + "(\\S+)?[^\\ ]*\\ (\r\n)?" + numberRegex).matcher(text);
		int index = 0;
		while (matcher.find())
		{
			index++;
			System.out.println("找到第" + index + "个\t" + matcher.group());
		}
	}

}
