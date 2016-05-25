package com.uu.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test6
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		String str = "<li><div class=\"t1\" code=\"601038\" orgid=\"gshk0000038\"><font>601038</font></div><div class=\"t2\" code=\"601038\" orgid=\"gshk0000038\"><font>一拖股份</font></div><div class=\"t3\"><dd><span class=\"d1\"><a href=\"/cninfo-new/disclosure/sse/bulletin_detail/true/1202104806?announceTime=2016-03-30\" target=\"_blank\">2015年年度报告<img width=\"20px\" src=\"/cninfo-new/images/720pdf.png\">(3795 k)</a></span><span class=\"d3\">2016-03-30</span></dd></div></li>";
		Matcher m1 = Pattern.compile("<font[^>]*?>(\\d+)</font>").matcher(str);
		if (m1.find())
		{
			System.out.println(m1.group(1));
		}
	}

}
