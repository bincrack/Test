package com.uu.test;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class PDFListTest
{
	public static Map<String, String> make(File file) throws Exception
	{
		System.out.println(file);
		
		PDDocument document = PDDocument.load(file);
		if (document.isEncrypted())
			throw new Exception("Document is encrypted");
		
		String str = new PDFTextStripper().getText(document).toString();
		document.close();
		int begin = str.indexOf("二、财务报表");
		int end = str.indexOf("三、公司基本情况");
		if (begin < 0)
			begin = str.indexOf("二、 财务报表");
		if (end < 0)
			end = str.indexOf("三、 公司基本情况");
		
		System.out.println("find begin " + begin + " end " + end);
		if (begin < 0 || end < 0)
			throw new Exception("not find position");
		
		String text = str.substring(begin, end).replaceAll("(.*)年度报告(.*)\r\n(.*)", "").replaceAll("[\u4e00-\u9fa5]+\\-\\d+\\ ", "").replaceAll("（[^）]*）\\d+[^\\ ]\\ ", "").replaceAll("\\ +", " ");
		
		FileUtils.writeStringToFile(new File("C:\\Users\\Administrator\\Desktop\\1.txt"), text.replaceAll("\\s..\\-\\d+\\s", ""));
		
		System.out.println("find content");
		
		// 1.合并资产负债表
		String mergeDebt = "合并资产负债表";
		// 2.母公司资产负债表
		String debt = "母公司资产负债表";
		// 3.合并利润表
		String mergeProfit = "合并利润表";
		// 4.母公司利润表
		String profit = "母公司利润表";
		// 5.合并现金流量表
		String mergeCash = "合并现金流量表";
		// 6.母公司现金流量表
		String cash = "母公司现金流量表";
		// 7.合并所有者权益变动表
		String mergeBoot = "合并所有者权益变动表";
		// 8.母公司所有者权益变动表
		String boot = "母公司所有者权益变动表";
		
		String debtEx = "资产负债表";
		String profitEx = "利润表";
		String cashEx = "现金流量表";
		String bootEx = "所有者权益变动表";
		
		int mergeDebtBegin = text.indexOf(mergeDebt);
		if (mergeDebtBegin < 0)
			mergeDebtBegin = text.indexOf(debtEx);
		
		int mergeDebtEnd = text.indexOf(debt);
		if (mergeDebtEnd < 0)
			mergeDebtEnd = text.indexOf(profitEx);
		
		int mergeProfitBegin = text.indexOf(mergeProfit);
		if (mergeProfitBegin < 0)
			mergeProfitBegin = text.indexOf(profitEx);
		
		int mergeProfitEnd = text.indexOf(profit);
		if (mergeProfitEnd < 0)
			mergeProfitEnd = text.indexOf(cashEx);
		
		int mergeCashBegin = text.indexOf(mergeCash);
		if (mergeCashBegin < 0)
			mergeCashBegin = text.indexOf(cashEx);
		
		int mergeCashEnd = text.indexOf(cash);
		if (mergeCashEnd < 0)
			mergeCashEnd = text.indexOf(bootEx);
		
		String mergeDebtText = text.substring(mergeDebtBegin, mergeDebtEnd);
		String mergeProfitText =  text.substring(mergeProfitBegin, mergeProfitEnd);
		String mergeCashText =  text.substring(mergeCashBegin, mergeCashEnd);
//		String mergeBootText =  text.substring(text.indexOf(mergeBoot), text.indexOf(boot));
		
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("代码", file.getName());
//		map.put("流动资产合计", PDFTest.find(mergeDebtText, "流动资产合计"));
//		map.put("非流动资产合计", PDFTest.find(mergeDebtText, "非流动资产合计"));
//		map.put("流动负债合计", PDFTest.find(mergeDebtText, "流动负债合计"));
//		map.put("非流动负债合计", PDFTest.find(mergeDebtText, "非流动负债合计"));
//		map.put("所有者权益合计", PDFTest.find(mergeDebtText, "所有者权益合计"));
//		map.put("营业总收入", PDFTest.find(mergeProfitText, "营业总收入"));
//		map.put("营业总成本", PDFTest.find(mergeProfitText, "营业总成本"));
//		map.put("营业利润", PDFTest.find(mergeProfitText, "营业利润"));
//		map.put("利润总额", PDFTest.find(mergeProfitText, "利润总额"));
//		map.put("净利润", PDFTest.find(mergeProfitText, "净利润"));
//		map.put("综合收益总额", PDFTest.find(mergeProfitText, "综合收益总额"));
		map.put("资产总计", PDFTest.find(mergeDebtText, "资产总计"));
		map.put("负债合计", PDFTest.find(mergeDebtText, "负债合计"));
		map.put("股本", PDFTest.find(mergeDebtText, "股本", "实收资本（或股本）"));
		map.put("所有者权益合计", PDFTest.find(mergeDebtText, "\n所有者权益合计", "所有者权益（或股东权益）合计"));
		
		map.put("营业收入", PDFTest.find(mergeProfitText, "营业总收入", "营业收入"));
		map.put("营业成本", PDFTest.find(mergeProfitText, "营业总成本", "营业支出"));
		map.put("每股收益", PDFTest.find(mergeProfitText, "基本每股收益"));
		
		return map;
	}
	public static void main(String[] args) throws Exception
	{
		String out = "C:\\Users\\Administrator\\Desktop\\2.xls";
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		String[] keys = new String[] {
				"代码",
//				"非流动资产合计", 
//				"流动负债合计", 
//				"非流动负债合计", 
//				"所有者权益合计", 
//				"营业总收入", 
//				"营业总成本", 
//				"营业利润", 
//				"利润总额", 
//				"净利润", 
//				"综合收益总额" 
				"资产总计",
				"负债合计",
				"股本",
				"所有者权益合计",
				"营业收入",
				"营业成本",
				"每股收益"
		};
		File[] files = new File("C:\\Users\\Administrator\\Desktop\\download\\").listFiles();
		for (File file : files)
		{
			try
			{
				Map<String, String> map = make(file);
				if (map == null)
					continue;
				
				list.add(map);
			}
			catch (Exception e)
			{
				file.renameTo(new File(file.getPath() + ".PDFX"));
				e.printStackTrace();
			}
		}
		
		PDFTest.createExcel(list, keys, keys, out);
	}
}
