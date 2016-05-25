package com.uu.test;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.CssSelectorNodeFilter;
import org.htmlparser.util.NodeList;

import com.uu.util.Http;

public class Test8
{
	public static void main(String[] args) throws Exception
	{
		getAll();
	}
	
	public static void getAll() throws Exception
	{
		File file = new File("D:\\stock.txt");
		String charset = "UTF-8";
		StringBuffer sb = new StringBuffer();
		
		for (int j = 1; j <= 44; j++)
		{
			System.out.println("current:" + j);
			String url = "http://d.qianzhan.com/xdata/listed?page=" + j + "&stk=A&addr=%E4%B8%8A%E6%B5%B7";
			byte[] b = Http.request(url, null, null);
			String html = new String(b, charset);
			NodeFilter filter = new CssSelectorNodeFilter("table a.blue_t");
			Parser parser = Parser.createParser(html, charset);
			NodeList list = parser.extractAllNodesThatMatch(filter);
			for (int i = 0; i < list.size(); i+=3)
			{
				String code = list.elementAt(i).getChildren().toHtml();
				String name = list.elementAt(i + 1).getChildren().toHtml();
				sb.append(code).append("|").append(name).append("\r\n");
				
				System.out.println("\t" + code + " " + name);
			}
		}
		
		FileUtils.writeStringToFile(file, sb.toString());
	}

	
}
