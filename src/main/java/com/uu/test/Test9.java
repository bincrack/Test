package com.uu.test;

import java.util.HashMap;
import java.util.Map;

import org.htmlparser.Parser;
import org.htmlparser.filters.CssSelectorNodeFilter;
import org.htmlparser.util.NodeList;

import com.uu.util.Http;

public class Test9
{
	public static void main(String[] args) throws Exception
	{
		String charset = "UTF-8";
		String url = "http://d.qianzhan.com/xdata/listedsource/603609.html?y=1";
		byte[] b = Http.request(url, null, null);
		String html = new String(b, charset);
		Map<String, Map<String, String>> map = new HashMap<String, Map<String,String>>();
		
		NodeList list = Parser.createParser(html, charset).extractAllNodesThatMatch(new CssSelectorNodeFilter("#tblData1 tr"));
		NodeList headList = Parser.createParser(list.elementAt(0).toHtml(), charset).extractAllNodesThatMatch(new CssSelectorNodeFilter("th"));
		for (int i = 1; i < list.size(); i++)
		{
			NodeList valueList = Parser.createParser(list.elementAt(i).toHtml(), charset).extractAllNodesThatMatch(new CssSelectorNodeFilter("td"));
			String item = valueList.elementAt(0).getChildren().asString();
			
			System.out.println("\t" + item);
			
			for (int j = 1; j < valueList.size(); j++)
			{
				String head = headList.elementAt(j).getChildren().asString();
				String value = valueList.elementAt(j).getChildren().asString();
				Map<String, String> m = null;
				
				if (map.containsKey(head))
				{
					m = map.get(head);
				}
				else
				{
					m = new HashMap<String, String>();
				}
				
				m.put(item, value);
				map.put(head, m);
				
				System.out.println(head + "\t" + value);
			}
		}
	}

}
