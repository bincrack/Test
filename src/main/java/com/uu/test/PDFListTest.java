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
		int begin = str.indexOf("�������񱨱�");
		int end = str.indexOf("������˾�������");
		if (begin < 0)
			begin = str.indexOf("���� ���񱨱�");
		if (end < 0)
			end = str.indexOf("���� ��˾�������");
		
		System.out.println("find begin " + begin + " end " + end);
		if (begin < 0 || end < 0)
			throw new Exception("not find position");
		
		String text = str.substring(begin, end).replaceAll("(.*)��ȱ���(.*)\r\n(.*)", "").replaceAll("[\u4e00-\u9fa5]+\\-\\d+\\ ", "").replaceAll("��[^��]*��\\d+[^\\ ]\\ ", "").replaceAll("\\ +", " ");
		
		FileUtils.writeStringToFile(new File("C:\\Users\\Administrator\\Desktop\\1.txt"), text.replaceAll("\\s..\\-\\d+\\s", ""));
		
		System.out.println("find content");
		
		// 1.�ϲ��ʲ���ծ��
		String mergeDebt = "�ϲ��ʲ���ծ��";
		// 2.ĸ��˾�ʲ���ծ��
		String debt = "ĸ��˾�ʲ���ծ��";
		// 3.�ϲ������
		String mergeProfit = "�ϲ������";
		// 4.ĸ��˾�����
		String profit = "ĸ��˾�����";
		// 5.�ϲ��ֽ�������
		String mergeCash = "�ϲ��ֽ�������";
		// 6.ĸ��˾�ֽ�������
		String cash = "ĸ��˾�ֽ�������";
		// 7.�ϲ�������Ȩ��䶯��
		String mergeBoot = "�ϲ�������Ȩ��䶯��";
		// 8.ĸ��˾������Ȩ��䶯��
		String boot = "ĸ��˾������Ȩ��䶯��";
		
		String debtEx = "�ʲ���ծ��";
		String profitEx = "�����";
		String cashEx = "�ֽ�������";
		String bootEx = "������Ȩ��䶯��";
		
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
		map.put("����", file.getName());
//		map.put("�����ʲ��ϼ�", PDFTest.find(mergeDebtText, "�����ʲ��ϼ�"));
//		map.put("�������ʲ��ϼ�", PDFTest.find(mergeDebtText, "�������ʲ��ϼ�"));
//		map.put("������ծ�ϼ�", PDFTest.find(mergeDebtText, "������ծ�ϼ�"));
//		map.put("��������ծ�ϼ�", PDFTest.find(mergeDebtText, "��������ծ�ϼ�"));
//		map.put("������Ȩ��ϼ�", PDFTest.find(mergeDebtText, "������Ȩ��ϼ�"));
//		map.put("Ӫҵ������", PDFTest.find(mergeProfitText, "Ӫҵ������"));
//		map.put("Ӫҵ�ܳɱ�", PDFTest.find(mergeProfitText, "Ӫҵ�ܳɱ�"));
//		map.put("Ӫҵ����", PDFTest.find(mergeProfitText, "Ӫҵ����"));
//		map.put("�����ܶ�", PDFTest.find(mergeProfitText, "�����ܶ�"));
//		map.put("������", PDFTest.find(mergeProfitText, "������"));
//		map.put("�ۺ������ܶ�", PDFTest.find(mergeProfitText, "�ۺ������ܶ�"));
		map.put("�ʲ��ܼ�", PDFTest.find(mergeDebtText, "�ʲ��ܼ�"));
		map.put("��ծ�ϼ�", PDFTest.find(mergeDebtText, "��ծ�ϼ�"));
		map.put("�ɱ�", PDFTest.find(mergeDebtText, "�ɱ�", "ʵ���ʱ�����ɱ���"));
		map.put("������Ȩ��ϼ�", PDFTest.find(mergeDebtText, "\n������Ȩ��ϼ�", "������Ȩ�棨��ɶ�Ȩ�棩�ϼ�"));
		
		map.put("Ӫҵ����", PDFTest.find(mergeProfitText, "Ӫҵ������", "Ӫҵ����"));
		map.put("Ӫҵ�ɱ�", PDFTest.find(mergeProfitText, "Ӫҵ�ܳɱ�", "Ӫҵ֧��"));
		map.put("ÿ������", PDFTest.find(mergeProfitText, "����ÿ������"));
		
		return map;
	}
	public static void main(String[] args) throws Exception
	{
		String out = "C:\\Users\\Administrator\\Desktop\\2.xls";
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		String[] keys = new String[] {
				"����",
//				"�������ʲ��ϼ�", 
//				"������ծ�ϼ�", 
//				"��������ծ�ϼ�", 
//				"������Ȩ��ϼ�", 
//				"Ӫҵ������", 
//				"Ӫҵ�ܳɱ�", 
//				"Ӫҵ����", 
//				"�����ܶ�", 
//				"������", 
//				"�ۺ������ܶ�" 
				"�ʲ��ܼ�",
				"��ծ�ϼ�",
				"�ɱ�",
				"������Ȩ��ϼ�",
				"Ӫҵ����",
				"Ӫҵ�ɱ�",
				"ÿ������"
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
