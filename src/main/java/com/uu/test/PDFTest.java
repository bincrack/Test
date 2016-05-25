package com.uu.test;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class PDFTest
{
	public static String find2(String text, String... names)
	{
		String name = "";
		String str = "";
		boolean find = false;
		String numberRegex = "-?\\d+((\\,\\d{3}){1,})?\\.\\d+";
		for (int i = 0; i < names.length; i++)
		{
			name = names[i];
			Matcher matcher = Pattern.compile(name + ".*" + numberRegex).matcher(text);
			if (matcher.find())
			{
				find = true;
				str = matcher.group();
				break;
			}
		}
		
		if (find)
		{
			Matcher matcher = Pattern.compile(numberRegex).matcher(str);
			if (matcher.find())
			{
				str = matcher.group();
			}
			else
			{
				str = "";
			}
			
			System.out.println("find name '" + name + "' " + str);
		}
		else
		{
			StringBuffer sb = new StringBuffer("not find name ");
			for (String s : names)
			{
				sb.append(s).append(" ");
			}
			
			System.err.println(sb);
			
			str = "δ�ҵ�";
		}
		
		return str;
	}
	
	public static String find1(String text, String... names)
	{
		String name = "";
		String str = "";
		boolean find = false;
		String numberRegex = "-?\\d+((\\,\\d{3}){1,})?\\.\\d+";
		for (int i = 0; i < names.length; i++)
		{
			name = names[i];
			Matcher matcher = Pattern.compile(name + "(\\S+)?[^\\ ]*\\ (\r\n)?(" + numberRegex + ")").matcher(text);
			if (matcher.find())
			{
				find = true;
				str = matcher.group();
				break;
			}
		}
		
		if (find)
		{
			Matcher matcher = Pattern.compile(numberRegex).matcher(str);
			if (matcher.find())
			{
				str = matcher.group();
			}
			else
			{
				str = "";
			}
			
			System.out.println("find name '" + name + "' " + str);
		}
		else
		{
			StringBuffer sb = new StringBuffer("not find name ");
			for (String s : names)
			{
				sb.append(s).append(" ");
			}
			
			System.err.println(sb);
			
			str = "δ�ҵ�";
		}
		
		return str;
	}
	
	public static String find(String text, String... names)
	{
		return find2(text, names);
	}
	public static void main(String[] args) throws Exception
	{
		String path = "C:\\Users\\Administrator\\Desktop\\pdf\\600074.PDF";
		PDDocument document = PDDocument.load(path);
		if (document.isEncrypted())
		{
			System.out.println(document.isUserPassword("123456"));
		}
			
		String str = new PDFTextStripper().getText(document).toString();
		System.out.println("find content length " + str.length());
		int begin = str.indexOf("���� ���񱨱�");
		int end = str.indexOf("���� ��˾�������");
		
		System.out.println("find begin " + begin + " end " + end);
		
		if (begin > -2)
			return;
		
		String text = str.substring(begin, end).replaceAll("(.*)��ȱ���ȫ��\\s\r\n\\d+\\s\r\n", "");
//		FileUtils.writeStringToFile(new File("C:\\Users\\Administrator\\Desktop\\1.txt"), content);
		System.out.println("get content");
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
		
//		String text = FileUtils.readFileToString(new File("C:\\Users\\Administrator\\Desktop\\1.txt")).replaceAll("(.*)����ȱ���ȫ��\\s\r\n\\d+\\s\r\n", "");
		String mergeDebtText = text.substring(text.indexOf(mergeDebt), text.indexOf(debt));
		String debtText =  text.substring(text.indexOf(debt), text.indexOf(mergeProfit));
		String mergeProfitText =  text.substring(text.indexOf(mergeProfit), text.indexOf(profit));
		String profitText =  text.substring(text.indexOf(profit), text.indexOf(mergeCash));
		String mergeCashText =  text.substring(text.indexOf(mergeCash), text.indexOf(cash));
		String cashText =  text.substring(text.indexOf(cash), text.indexOf(mergeBoot));
		String mergeBootText =  text.substring(text.indexOf(mergeBoot), text.indexOf(boot));
		String bootText =  text.substring(text.indexOf(boot));
		
		FileUtils.writeStringToFile(new File("C:\\Users\\Administrator\\Desktop\\2.txt"), text);
//		Matcher matcher = Pattern.compile("(.*)����ȱ���ȫ��\\s\r\n\\d+").matcher(text);
//		if (matcher.find())
//		{
//			System.out.println(matcher.group());
//		}
		System.out.println("find");
		WritableWorkbook workbook = Workbook.createWorkbook(new File("C:\\Users\\Administrator\\Desktop\\2.xls"));
		WritableSheet sheet = workbook.createSheet("111", 0);
		Map<String, String> map = new LinkedHashMap<String, String>();
//		map.put(" ", "");
//		map.put("�����ʲ�", "");
//		map.put("�����ʽ�", find(mergeDebtText, "�����ʽ�"));
//		map.put("���㱸����", find(mergeDebtText, "���㱸����"));
//		map.put("����ʽ�", find(mergeDebtText, "����ʽ�"));
//		map.put("�Թ��ʼ�ֵ��������䶯���뵱������Ľ����ʲ�", find(mergeDebtText, "�Թ��ʼ�ֵ��������䶯���뵱������Ľ����ʲ�", "�����Խ����ʲ�"));
//		map.put("���������ʲ�", find(mergeDebtText, "���������ʲ�"));
//		map.put("Ӧ��Ʊ��", find(mergeDebtText, "Ӧ��Ʊ��"));
//		map.put("Ӧ���˿�", find(mergeDebtText, "Ӧ���˿�"));
//		map.put("Ԥ������", find(mergeDebtText, "Ԥ������"));
//		map.put("Ӧ�ձ���", find(mergeDebtText, "Ӧ�ձ���"));
//		map.put("Ӧ�շֱ��˿�", find(mergeDebtText, "Ӧ�շֱ��˿�"));
//		map.put("Ӧ�շֱ���ͬ׼����", find(mergeDebtText, "Ӧ�շֱ���ͬ׼����"));
//		map.put("Ӧ����Ϣ", find(mergeDebtText, "Ӧ����Ϣ"));
//		map.put("Ӧ�չ���", find(mergeDebtText, "Ӧ�չ���"));
//		map.put("����Ӧ�տ�", find(mergeDebtText, "����Ӧ�տ�"));
//		map.put("���뷵�۽����ʲ�", find(mergeDebtText, "���뷵�۽����ʲ�"));
//		map.put("���", find(mergeDebtText, "���"));
//		map.put("����Ϊ���д��۵��ʲ�", find(mergeDebtText, "����Ϊ���д��۵��ʲ�"));
//		map.put("һ���ڵ��ڵķ������ʲ�", find(mergeDebtText, "һ���ڵ��ڵķ������ʲ�"));
//		map.put("���������ʲ�", find(mergeDebtText, "���������ʲ�"));
//		map.put("�����ʲ��ϼ�", find(mergeDebtText, "�����ʲ��ϼ�"));
//		map.put("�������ʲ�", "");
//		map.put("���Ŵ�����", find(mergeDebtText, "���Ŵ�����"));
//		map.put("�ɹ����۽����ʲ�", find(mergeDebtText, "�ɹ����۽����ʲ�"));
//		map.put("����������Ͷ��", find(mergeDebtText, "����������Ͷ��"));
//		map.put("����Ӧ�տ�", find(mergeDebtText, "����Ӧ�տ�"));
//		map.put("���ڹ�ȨͶ��", find(mergeDebtText, "���ڹ�ȨͶ��"));
//		map.put("Ͷ���Է��ز�", find(mergeDebtText, "Ͷ���Է��ز�"));
//		map.put("�̶��ʲ�", find(mergeDebtText, "�̶��ʲ�"));
//		map.put("�ڽ�����", find(mergeDebtText, "�ڽ�����"));
//		map.put("��������", find(mergeDebtText, "��������"));
//		map.put("�̶��ʲ�����", find(mergeDebtText, "�̶��ʲ�����"));
//		map.put("�����������ʲ�", find(mergeDebtText, "�����������ʲ�"));
//		map.put("�����ʲ�", find(mergeDebtText, "�����ʲ�"));
//		map.put("�����ʲ�", find(mergeDebtText, "�����ʲ�"));
//		map.put("����֧��", find(mergeDebtText, "����֧��"));
//		map.put("����", find(mergeDebtText, "����"));
//		map.put("���ڴ�̯����", find(mergeDebtText, "���ڴ�̯����"));
//		map.put("��������˰�ʲ�", find(mergeDebtText, "��������˰�ʲ�"));
//		map.put("�����������ʲ�", find(mergeDebtText, "�����������ʲ�"));
//		map.put("�������ʲ��ϼ�", find(mergeDebtText, "�������ʲ��ϼ�"));
//		map.put("�ʲ��ܼ�", find(mergeDebtText, "�ʲ��ܼ�"));
//		map.put("������ծ", "");
//		map.put("���ڽ��", find(mergeDebtText, "���ڽ��"));
//		map.put("���������н��", find(mergeDebtText, "���������н��"));
//		map.put("���մ�ͬҵ���", find(mergeDebtText, "���մ�ͬҵ���"));
//		map.put("�����ʽ�", find(mergeDebtText, "�����ʽ�"));
//		map.put("�Թ��ʼ�ֵ��������䶯���뵱������Ľ��ڸ�ծ", find(mergeDebtText, "�Թ��ʼ�ֵ��������䶯���뵱������Ľ��ڸ�ծ"));
//		map.put("�������ڸ�ծ", find(mergeDebtText, "�������ڸ�ծ"));
//		map.put("Ӧ��Ʊ��", find(mergeDebtText, "Ӧ��Ʊ��"));
//		map.put("Ӧ���˿�", find(mergeDebtText, "Ӧ���˿�"));
//		map.put("Ԥ�տ���", find(mergeDebtText, "Ԥ�տ���"));
//		map.put("�����ع������ʲ���", find(mergeDebtText, "�����ع������ʲ���"));
//		map.put("Ӧ�������Ѽ�Ӷ��", find(mergeDebtText, "Ӧ�������Ѽ�Ӷ��"));
//		map.put("Ӧ��ְ��н��", find(mergeDebtText, "Ӧ��ְ��н��"));
//		map.put("Ӧ��˰��", find(mergeDebtText, "Ӧ��˰��"));
//		map.put("Ӧ����Ϣ", find(mergeDebtText, "Ӧ����Ϣ"));
//		map.put("Ӧ������", find(mergeDebtText, "Ӧ������"));
//		map.put("����Ӧ����", find(mergeDebtText, "����Ӧ����"));
//		map.put("Ӧ���ֱ��˿�", find(mergeDebtText, "Ӧ���ֱ��˿�"));
//		map.put("���պ�ͬ׼����", find(mergeDebtText, "���պ�ͬ׼����"));
//		map.put("��������֤ȯ��", find(mergeDebtText, "��������֤ȯ��"));
//		map.put("�������֤ȯ��", find(mergeDebtText, "�������֤ȯ��"));
//		map.put("����Ϊ���д��۵ĸ�ծ", find(mergeDebtText, "����Ϊ���д��۵ĸ�ծ"));
//		map.put("�������֤ȯ��", find(mergeDebtText, "�������֤ȯ��"));
//		map.put("һ���ڵ��ڵķ�������ծ", find(mergeDebtText, "һ���ڵ��ڵķ�������ծ"));
//		map.put("����������ծ", find(mergeDebtText, "����������ծ"));
//		map.put("������ծ�ϼ�", find(mergeDebtText, "������ծ�ϼ�"));
//		map.put("��������ծ", "");
//		map.put("���ڽ��", find(mergeDebtText, "���ڽ��"));
//		map.put("Ӧ��ծȯ", find(mergeDebtText, "Ӧ��ծȯ"));
//		map.put("����Ӧ����", find(mergeDebtText, "����Ӧ����"));
//		map.put("����Ӧ��ְ��н��", find(mergeDebtText, "����Ӧ��ְ��н��"));
//		map.put("ר��Ӧ����", find(mergeDebtText, "ר��Ӧ����"));
//		map.put("Ԥ�Ƹ�ծ", find(mergeDebtText, "Ԥ�Ƹ�ծ"));
//		map.put("��������", find(mergeDebtText, "��������"));
//		map.put("��������˰��ծ", find(mergeDebtText, "��������˰��ծ"));
//		map.put("������������ծ", find(mergeDebtText, "������������ծ"));
//		map.put("��������ծ�ϼ�", find(mergeDebtText, "��������ծ�ϼ�"));
//		map.put("��ծ�ϼ�", find(mergeDebtText, "\r\n��ծ�ϼ�"));
//		map.put("������Ȩ��", "");
//		map.put("�ɱ�", find(mergeDebtText, "�ɱ�", "ʵ���ʱ�����ɱ���"));
//		map.put("����Ȩ�湤��", find(mergeDebtText, "����Ȩ�湤��"));
//		map.put("�ʱ�����", find(mergeDebtText, "�ʱ�����"));
//		map.put("��������", find(mergeDebtText, "��������"));
//		map.put("�����ۺ�����", find(mergeDebtText, "�����ۺ�����"));
//		map.put("ר���", find(mergeDebtText, "ר���"));
//		map.put("ӯ�๫��", find(mergeDebtText, "ӯ�๫��"));
//		map.put("һ�����׼��", find(mergeDebtText, "һ�����׼��"));
//		map.put("δ��������", find(mergeDebtText, "δ��������"));
//		map.put("������ĸ��˾������Ȩ��ϼ�", find(mergeDebtText, "������ĸ��˾������Ȩ��ϼ�"));
//		map.put("�����ɶ�Ȩ��", find(mergeDebtText, "�����ɶ�Ȩ��"));
//		map.put("������Ȩ��ϼ�", find(mergeDebtText, "������Ȩ��ϼ�"));
//		map.put("��ծ��������Ȩ���ܼ�", find(mergeDebtText, "��ծ��������Ȩ���ܼ�", "��ծ��������Ȩ�棨��ɶ�Ȩ�棩�ܼ�"));
//
//		map.put("����", "");
//		map.put("Ӫҵ������", find(mergeProfitText, "Ӫҵ������"));
//		map.put("���У�Ӫҵ����", find(mergeProfitText, "���У�Ӫҵ����"));
//		map.put("��Ϣ����", find(mergeProfitText, "��Ϣ����"));
//		map.put("��׬����", find(mergeProfitText, "��׬����"));
//		map.put("�����Ѽ�Ӷ������", find(mergeProfitText, "�����Ѽ�Ӷ������"));
//		map.put("Ӫҵ�ܳɱ�", find(mergeProfitText, "Ӫҵ�ܳɱ�"));
//		map.put("���У�Ӫҵ�ɱ�", find(mergeProfitText, "���У�Ӫҵ�ɱ�"));
//		map.put("��Ϣ֧��", find(mergeProfitText, "��Ϣ֧��"));
//		map.put("�����Ѽ�Ӷ��֧��", find(mergeProfitText, "�����Ѽ�Ӷ��֧��"));
//		map.put("�˱���", find(mergeProfitText, "�˱���"));
//		map.put("�⸶֧������", find(mergeProfitText, "�⸶֧������"));
//		map.put("��ȡ���պ�ͬ׼���𾻶�", find(mergeProfitText, "��ȡ���պ�ͬ׼���𾻶�"));
//		map.put("��������֧��", find(mergeProfitText, "��������֧��"));
//		map.put("�ֱ�����", find(mergeProfitText, "�ֱ�����"));
//		map.put("Ӫҵ˰�𼰸���", find(mergeProfitText, "Ӫҵ˰�𼰸���"));
//		map.put("���۷���", find(mergeProfitText, "���۷���"));
//		map.put("�������", find(mergeProfitText, "�������"));
//		map.put("�������", find(mergeProfitText, "�������"));
//		map.put("�ʲ���ֵ��ʧ", find(mergeProfitText, "�ʲ���ֵ��ʧ"));
//		map.put("�ӣ����ʼ�ֵ�䶯���棨��ʧ�ԡ����������У�", find(mergeProfitText, "�ӣ����ʼ�ֵ�䶯���棨��ʧ�ԡ����������У�"));
//		map.put("Ͷ�����棨��ʧ�ԡ����������У�", find(mergeProfitText, "Ͷ�����棨��ʧ�ԡ����������У�"));
//		map.put("���У�����Ӫ��ҵ�ͺ�Ӫ��ҵ��Ͷ������", find(mergeProfitText, "���У�����Ӫ��ҵ�ͺ�Ӫ��ҵ��Ͷ������"));
//		map.put("������棨��ʧ�ԡ�-�������У�", find(mergeProfitText, "������棨��ʧ�ԡ�-�������У�"));
//		map.put("Ӫҵ����", find(mergeProfitText, "Ӫҵ����"));
//		map.put("�ӣ�Ӫҵ������", find(mergeProfitText, "�ӣ�Ӫҵ������"));
//		map.put("���У��������ʲ���������", find(mergeProfitText, "���У��������ʲ���������"));
//		map.put("����Ӫҵ��֧��", find(mergeProfitText, "����Ӫҵ��֧��"));
//		map.put("���У��������ʲ�������ʧ", find(mergeProfitText, "���У��������ʲ�������ʧ"));
//		map.put("�����ܶ�", find(mergeProfitText, "�����ܶ�"));
//		map.put("��������˰����", find(mergeProfitText, "��������˰����"));
//		map.put("������", find(mergeProfitText, "������"));
//		map.put("������ĸ��˾�����ߵľ�����", find(mergeProfitText, "������ĸ��˾�����ߵľ�����"));
//		map.put("�����ɶ�����", find(mergeProfitText, "�����ɶ�����"));
//		map.put("���������ۺ������˰�󾻶�", find(mergeProfitText, "�������ۺ������˰�󾻶�"));
//		map.put("����ĸ��˾�����ߵ������ۺ������˰�󾻶�", find(mergeProfitText, "����ĸ��˾�����ߵ������ۺ������˰�󾻶�"));
//		map.put("�����������ɶ��������ۺ������˰�󾻶�", find(mergeProfitText, "�����������ɶ��������ۺ������˰�󾻶�"));
//		map.put("�ۺ������ܶ�", find(mergeProfitText, "�ۺ������ܶ�"));
//		map.put("������ĸ��˾�����ߵ��ۺ������ܶ�", find(mergeProfitText, "�ۺ������ܶ�"));
//		map.put("������ĸ��˾�����ߵ��ۺ������ܶ�", find(mergeProfitText, "������ĸ��˾�����ߵ��ۺ������ܶ�"));
//		map.put("�����������ɶ����ۺ������ܶ�", find(mergeProfitText, "�����������ɶ����ۺ������ܶ�"));
//		map.put("�ˡ�ÿ������", "");
//		map.put("����ÿ������", find(mergeProfitText, "����ÿ������"));
//		map.put("ϡ��ÿ������", find(mergeProfitText, "ϡ��ÿ������"));
//		
//		map.put("�ֽ�����", "");
//		map.put("һ����Ӫ��������ֽ�������", "");
//		map.put("������Ʒ���ṩ�����յ����ֽ�", find(mergeCashText, "������Ʒ���ṩ�����յ����ֽ�"));
//		map.put("�ͻ�����ͬҵ��ſ�����Ӷ�", find(mergeCashText, "�ͻ�����ͬҵ��ſ�����Ӷ�"));
//		map.put("���������н����Ӷ�", find(mergeCashText, "���������н����Ӷ�"));
//		map.put("���������ڻ��������ʽ����Ӷ�", find(mergeCashText, "���������ڻ��������ʽ����Ӷ�"));
//		map.put("�յ�ԭ���պ�ͬ����ȡ�õ��ֽ�", find(mergeCashText, "�յ�ԭ���պ�ͬ����ȡ�õ��ֽ�"));
//		map.put("�յ��ٱ���ҵ���ֽ𾻶�", find(mergeCashText, "�յ��ٱ���ҵ���ֽ𾻶�"));
//		map.put("��������Ͷ�ʿ���Ӷ�", find(mergeCashText, "��������Ͷ�ʿ���Ӷ�"));
//		map.put("�����Թ��ʼ�ֵ��������䶯���뵱������Ľ����ʲ������Ӷ�", find(mergeCashText, "�����Թ��ʼ�ֵ��������䶯���뵱������Ľ����ʲ������Ӷ�"));
//		map.put("��ȡ��Ϣ�������Ѽ�Ӷ����ֽ�", find(mergeCashText, "��ȡ��Ϣ�������Ѽ�Ӷ����ֽ�"));
//		map.put("�����ʽ����Ӷ�", find(mergeCashText, "�����ʽ����Ӷ�"));
//		map.put("�ع�ҵ���ʽ����Ӷ�", find(mergeCashText, "�ع�ҵ���ʽ����Ӷ�"));
//		map.put("�յ���˰�ѷ���", find(mergeCashText, "�յ���˰�ѷ���"));
//		map.put("�յ������뾭Ӫ��йص��ֽ�", find(mergeCashText, "�յ������뾭Ӫ��йص��ֽ�"));
//		map.put("��Ӫ��ֽ�����С��", find(mergeCashText, "��Ӫ��ֽ�����С��"));
//		map.put("������Ʒ����������֧�����ֽ�", find(mergeCashText, "������Ʒ����������֧�����ֽ�"));
//		map.put("�ͻ���������Ӷ�", find(mergeCashText, "�ͻ���������Ӷ�"));
//		map.put("����������к�ͬҵ������Ӷ�", find(mergeCashText, "����������к�ͬҵ������Ӷ�"));
//		map.put("֧��ԭ���պ�ͬ�⸶������ֽ�", find(mergeCashText, "֧��ԭ���պ�ͬ�⸶������ֽ�"));
//		map.put("֧����Ϣ�������Ѽ�Ӷ����ֽ�", find(mergeCashText, "֧����Ϣ�������Ѽ�Ӷ����ֽ�"));
//		map.put("֧�������������ֽ�", find(mergeCashText, "֧�������������ֽ�"));
//		map.put("֧����ְ���Լ�Ϊְ��֧�����ֽ�", find(mergeCashText, "֧����ְ���Լ�Ϊְ��֧�����ֽ�"));
//		map.put("֧���ĸ���˰��", find(mergeCashText, "֧���ĸ���˰��"));
//		map.put("֧�������뾭Ӫ��йص��ֽ�", find(mergeCashText, "֧�������뾭Ӫ��йص��ֽ�"));
//		map.put("��Ӫ��ֽ�����С��", find(mergeCashText, "��Ӫ��ֽ�����С��"));
//		map.put("��Ӫ��������ֽ���������", find(mergeCashText, "��Ӫ��������ֽ���������"));
//		map.put("����Ͷ�ʻ�������ֽ�������", "");
//		map.put("�ջ�Ͷ���յ����ֽ�", find(mergeCashText, "�ջ�Ͷ���յ����ֽ�"));
//		map.put("ȡ��Ͷ�������յ����ֽ�", find(mergeCashText, "ȡ��Ͷ�������յ����ֽ�", "ȡ��Ͷ���������յ����ֽ�"));
//		map.put("���ù̶��ʲ��������ʲ������������ʲ��ջص��ֽ𾻶�", find(mergeCashText, "���ù̶��ʲ��������ʲ������������ʲ��ջص��ֽ𾻶�"));
//		map.put("�����ӹ�˾������Ӫҵ��λ�յ����ֽ𾻶�", find(mergeCashText, "�����ӹ�˾������Ӫҵ��λ�յ����ֽ𾻶�"));
//		map.put("�յ�������Ͷ�ʻ�йص��ֽ�", find(mergeCashText, "�յ�������Ͷ�ʻ�йص��ֽ�"));
//		map.put("Ͷ�ʻ�ֽ�����С��", find(mergeCashText, "Ͷ�ʻ�ֽ�����С��"));
//		map.put("�����̶��ʲ��������ʲ������������ʲ�֧�����ֽ�", find(mergeCashText, "�����̶��ʲ��������ʲ������������ʲ�֧�����ֽ�"));
//		map.put("Ͷ��֧�����ֽ�", find(mergeCashText, "Ͷ��֧�����ֽ�"));
//		map.put("��Ѻ������Ӷ�", find(mergeCashText, "��Ѻ������Ӷ�"));
//		map.put("ȡ���ӹ�˾������Ӫҵ��λ֧�����ֽ𾻶�", find(mergeCashText, "ȡ���ӹ�˾������Ӫҵ��λ֧�����ֽ𾻶�"));
//		map.put("֧��������Ͷ�ʻ�йص��ֽ�", find(mergeCashText, "֧��������Ͷ�ʻ�йص��ֽ�"));
//		map.put("Ͷ�ʻ�ֽ�����С��", find(mergeCashText, "Ͷ�ʻ�ֽ�����С��"));
//		map.put("Ͷ�ʻ�������ֽ���������", find(mergeCashText, "Ͷ�ʻ�������ֽ���������"));
//		map.put("�������ʻ�������ֽ�������", "");
//		map.put("����Ͷ���յ����ֽ�", find(mergeCashText, "����Ͷ���յ����ֽ�"));
//		map.put("���У��ӹ�˾���������ɶ�Ͷ���յ����ֽ�", find(mergeCashText, "���У��ӹ�˾���������ɶ�Ͷ���յ����ֽ�"));
//		map.put("ȡ�ý���յ����ֽ�", find(mergeCashText, "ȡ�ý���յ����ֽ�"));
//		map.put("����ծȯ�յ����ֽ�", find(mergeCashText, "����ծȯ�յ����ֽ�"));
//		map.put("�յ���������ʻ�йص��ֽ�", find(mergeCashText, "�յ���������ʻ�йص��ֽ�"));
//		map.put("���ʻ�ֽ�����С��", find(mergeCashText, "���ʻ�ֽ�����С��"));
//		map.put("����ծ��֧�����ֽ�", find(mergeCashText, "����ծ��֧�����ֽ�"));
//		map.put("�������������򳥸���Ϣ֧�����ֽ�", find(mergeCashText, "�������������򳥸���Ϣ֧�����ֽ�"));
//		map.put("���У��ӹ�˾֧���������ɶ��Ĺ���������", find(mergeCashText, "���У��ӹ�˾֧���������ɶ��Ĺ���������"));
//		map.put("֧����������ʻ�йص��ֽ� ", find(mergeCashText, "֧����������ʻ�йص��ֽ�"));
//		map.put("���ʻ�ֽ�����С��", find(mergeCashText, "���ʻ�ֽ�����С��"));
//		map.put("���ʻ�������ֽ���������", find(mergeCashText, "���ʻ�������ֽ���������"));
//		map.put("�ġ����ʱ䶯���ֽ��ֽ�ȼ����Ӱ��", find(mergeCashText, "�ġ����ʱ䶯���ֽ��ֽ�ȼ����Ӱ��"));
//		map.put("�塢�ֽ��ֽ�ȼ��ﾻ���Ӷ�", find(mergeCashText, "�塢�ֽ��ֽ�ȼ��ﾻ���Ӷ�"));
//		map.put("�ӣ��ڳ��ֽ��ֽ�ȼ������", find(mergeCashText, "�ӣ��ڳ��ֽ��ֽ�ȼ������"));
//		map.put("������ĩ�ֽ��ֽ�ȼ������", find(mergeCashText, "������ĩ�ֽ��ֽ�ȼ������"));
		
		int i = 0;
		for (String key : map.keySet())
		{
			sheet.addCell(new Label(0, i, key));
			sheet.addCell(new Label(1, i, map.get(key)));
			i++;
		}

		workbook.write();
		workbook.close();
		
		map.put("�����ʲ��ϼ�", find(mergeDebtText, "�����ʲ��ϼ�"));
		map.put("�������ʲ��ϼ�", find(mergeDebtText, "�������ʲ��ϼ�"));
		map.put("������ծ�ϼ�", find(mergeDebtText, "������ծ�ϼ�"));
		map.put("��������ծ�ϼ�", find(mergeDebtText, "��������ծ�ϼ�"));
		map.put("������Ȩ��ϼ�", find(mergeDebtText, "������Ȩ��ϼ�"));
		map.put("Ӫҵ������", find(mergeProfitText, "Ӫҵ������"));
		map.put("Ӫҵ�ܳɱ�", find(mergeProfitText, "Ӫҵ�ܳɱ�"));
		map.put("Ӫҵ����", find(mergeProfitText, "Ӫҵ����"));
		map.put("�����ܶ�", find(mergeProfitText, "�����ܶ�"));
		map.put("������", find(mergeProfitText, "������"));
		map.put("�ۺ������ܶ�", find(mergeProfitText, "�ۺ������ܶ�"));
		
		System.out.println("done");
	}
	
	public static void createExcel(List<Map<String, String>> list, String[] header, String[] keys, String out) throws Exception
	{
		System.out.println("create");
		
		WritableWorkbook workbook = Workbook.createWorkbook(new File(out));
		WritableSheet sheet = workbook.createSheet("111", 0);
		
		if (header != null)
		{
			for (int i = 0; i < header.length; i++)
			{
				sheet.addCell(new Label(i, 0, header[i]));
			}
		}
		
		if (list != null && keys != null)
		{
			for (int i = 0; i < list.size(); i++)
			{
				Map<String, String> map = list.get(i);
				for  (int j = 0; j < keys.length; j++)
				{
					sheet.addCell(new Label(j, i + 1, map.get(keys[j])));
				}
			}
		}
		
		workbook.write();
		workbook.close();
	}
}
