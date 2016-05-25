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
			
			str = "未找到";
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
			
			str = "未找到";
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
		int begin = str.indexOf("二、 财务报表");
		int end = str.indexOf("三、 公司基本情况");
		
		System.out.println("find begin " + begin + " end " + end);
		
		if (begin > -2)
			return;
		
		String text = str.substring(begin, end).replaceAll("(.*)年度报告全文\\s\r\n\\d+\\s\r\n", "");
//		FileUtils.writeStringToFile(new File("C:\\Users\\Administrator\\Desktop\\1.txt"), content);
		System.out.println("get content");
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
		
//		String text = FileUtils.readFileToString(new File("C:\\Users\\Administrator\\Desktop\\1.txt")).replaceAll("(.*)年年度报告全文\\s\r\n\\d+\\s\r\n", "");
		String mergeDebtText = text.substring(text.indexOf(mergeDebt), text.indexOf(debt));
		String debtText =  text.substring(text.indexOf(debt), text.indexOf(mergeProfit));
		String mergeProfitText =  text.substring(text.indexOf(mergeProfit), text.indexOf(profit));
		String profitText =  text.substring(text.indexOf(profit), text.indexOf(mergeCash));
		String mergeCashText =  text.substring(text.indexOf(mergeCash), text.indexOf(cash));
		String cashText =  text.substring(text.indexOf(cash), text.indexOf(mergeBoot));
		String mergeBootText =  text.substring(text.indexOf(mergeBoot), text.indexOf(boot));
		String bootText =  text.substring(text.indexOf(boot));
		
		FileUtils.writeStringToFile(new File("C:\\Users\\Administrator\\Desktop\\2.txt"), text);
//		Matcher matcher = Pattern.compile("(.*)年年度报告全文\\s\r\n\\d+").matcher(text);
//		if (matcher.find())
//		{
//			System.out.println(matcher.group());
//		}
		System.out.println("find");
		WritableWorkbook workbook = Workbook.createWorkbook(new File("C:\\Users\\Administrator\\Desktop\\2.xls"));
		WritableSheet sheet = workbook.createSheet("111", 0);
		Map<String, String> map = new LinkedHashMap<String, String>();
//		map.put(" ", "");
//		map.put("流动资产", "");
//		map.put("货币资金", find(mergeDebtText, "货币资金"));
//		map.put("结算备付金", find(mergeDebtText, "结算备付金"));
//		map.put("拆出资金", find(mergeDebtText, "拆出资金"));
//		map.put("以公允价值计量且其变动计入当期损益的金融资产", find(mergeDebtText, "以公允价值计量且其变动计入当期损益的金融资产", "交易性金融资产"));
//		map.put("衍生金融资产", find(mergeDebtText, "衍生金融资产"));
//		map.put("应收票据", find(mergeDebtText, "应收票据"));
//		map.put("应收账款", find(mergeDebtText, "应收账款"));
//		map.put("预付款项", find(mergeDebtText, "预付款项"));
//		map.put("应收保费", find(mergeDebtText, "应收保费"));
//		map.put("应收分保账款", find(mergeDebtText, "应收分保账款"));
//		map.put("应收分保合同准备金", find(mergeDebtText, "应收分保合同准备金"));
//		map.put("应收利息", find(mergeDebtText, "应收利息"));
//		map.put("应收股利", find(mergeDebtText, "应收股利"));
//		map.put("其他应收款", find(mergeDebtText, "其他应收款"));
//		map.put("买入返售金融资产", find(mergeDebtText, "买入返售金融资产"));
//		map.put("存货", find(mergeDebtText, "存货"));
//		map.put("划分为持有待售的资产", find(mergeDebtText, "划分为持有待售的资产"));
//		map.put("一年内到期的非流动资产", find(mergeDebtText, "一年内到期的非流动资产"));
//		map.put("其他流动资产", find(mergeDebtText, "其他流动资产"));
//		map.put("流动资产合计", find(mergeDebtText, "流动资产合计"));
//		map.put("非流动资产", "");
//		map.put("发放贷款及垫款", find(mergeDebtText, "发放贷款及垫款"));
//		map.put("可供出售金融资产", find(mergeDebtText, "可供出售金融资产"));
//		map.put("持有至到期投资", find(mergeDebtText, "持有至到期投资"));
//		map.put("长期应收款", find(mergeDebtText, "长期应收款"));
//		map.put("长期股权投资", find(mergeDebtText, "长期股权投资"));
//		map.put("投资性房地产", find(mergeDebtText, "投资性房地产"));
//		map.put("固定资产", find(mergeDebtText, "固定资产"));
//		map.put("在建工程", find(mergeDebtText, "在建工程"));
//		map.put("工程物资", find(mergeDebtText, "工程物资"));
//		map.put("固定资产清理", find(mergeDebtText, "固定资产清理"));
//		map.put("生产性生物资产", find(mergeDebtText, "生产性生物资产"));
//		map.put("油气资产", find(mergeDebtText, "油气资产"));
//		map.put("无形资产", find(mergeDebtText, "无形资产"));
//		map.put("开发支出", find(mergeDebtText, "开发支出"));
//		map.put("商誉", find(mergeDebtText, "商誉"));
//		map.put("长期待摊费用", find(mergeDebtText, "长期待摊费用"));
//		map.put("递延所得税资产", find(mergeDebtText, "递延所得税资产"));
//		map.put("其他非流动资产", find(mergeDebtText, "其他非流动资产"));
//		map.put("非流动资产合计", find(mergeDebtText, "非流动资产合计"));
//		map.put("资产总计", find(mergeDebtText, "资产总计"));
//		map.put("流动负债", "");
//		map.put("短期借款", find(mergeDebtText, "短期借款"));
//		map.put("向中央银行借款", find(mergeDebtText, "向中央银行借款"));
//		map.put("吸收存款及同业存放", find(mergeDebtText, "吸收存款及同业存放"));
//		map.put("拆入资金", find(mergeDebtText, "拆入资金"));
//		map.put("以公允价值计量且其变动计入当期损益的金融负债", find(mergeDebtText, "以公允价值计量且其变动计入当期损益的金融负债"));
//		map.put("衍生金融负债", find(mergeDebtText, "衍生金融负债"));
//		map.put("应付票据", find(mergeDebtText, "应付票据"));
//		map.put("应付账款", find(mergeDebtText, "应付账款"));
//		map.put("预收款项", find(mergeDebtText, "预收款项"));
//		map.put("卖出回购金融资产款", find(mergeDebtText, "卖出回购金融资产款"));
//		map.put("应付手续费及佣金", find(mergeDebtText, "应付手续费及佣金"));
//		map.put("应付职工薪酬", find(mergeDebtText, "应付职工薪酬"));
//		map.put("应交税费", find(mergeDebtText, "应交税费"));
//		map.put("应付利息", find(mergeDebtText, "应付利息"));
//		map.put("应付股利", find(mergeDebtText, "应付股利"));
//		map.put("其他应付款", find(mergeDebtText, "其他应付款"));
//		map.put("应付分保账款", find(mergeDebtText, "应付分保账款"));
//		map.put("保险合同准备金", find(mergeDebtText, "保险合同准备金"));
//		map.put("代理买卖证券款", find(mergeDebtText, "代理买卖证券款"));
//		map.put("代理承销证券款", find(mergeDebtText, "代理承销证券款"));
//		map.put("划分为持有待售的负债", find(mergeDebtText, "划分为持有待售的负债"));
//		map.put("代理承销证券款", find(mergeDebtText, "代理承销证券款"));
//		map.put("一年内到期的非流动负债", find(mergeDebtText, "一年内到期的非流动负债"));
//		map.put("其他流动负债", find(mergeDebtText, "其他流动负债"));
//		map.put("流动负债合计", find(mergeDebtText, "流动负债合计"));
//		map.put("非流动负债", "");
//		map.put("长期借款", find(mergeDebtText, "长期借款"));
//		map.put("应付债券", find(mergeDebtText, "应付债券"));
//		map.put("长期应付款", find(mergeDebtText, "长期应付款"));
//		map.put("长期应付职工薪酬", find(mergeDebtText, "长期应付职工薪酬"));
//		map.put("专项应付款", find(mergeDebtText, "专项应付款"));
//		map.put("预计负债", find(mergeDebtText, "预计负债"));
//		map.put("递延收益", find(mergeDebtText, "递延收益"));
//		map.put("递延所得税负债", find(mergeDebtText, "递延所得税负债"));
//		map.put("其他非流动负债", find(mergeDebtText, "其他非流动负债"));
//		map.put("非流动负债合计", find(mergeDebtText, "非流动负债合计"));
//		map.put("负债合计", find(mergeDebtText, "\r\n负债合计"));
//		map.put("所有者权益", "");
//		map.put("股本", find(mergeDebtText, "股本", "实收资本（或股本）"));
//		map.put("其他权益工具", find(mergeDebtText, "其他权益工具"));
//		map.put("资本公积", find(mergeDebtText, "资本公积"));
//		map.put("减：库存股", find(mergeDebtText, "减：库存股"));
//		map.put("其他综合收益", find(mergeDebtText, "其他综合收益"));
//		map.put("专项储备", find(mergeDebtText, "专项储备"));
//		map.put("盈余公积", find(mergeDebtText, "盈余公积"));
//		map.put("一般风险准备", find(mergeDebtText, "一般风险准备"));
//		map.put("未分配利润", find(mergeDebtText, "未分配利润"));
//		map.put("归属于母公司所有者权益合计", find(mergeDebtText, "归属于母公司所有者权益合计"));
//		map.put("少数股东权益", find(mergeDebtText, "少数股东权益"));
//		map.put("所有者权益合计", find(mergeDebtText, "所有者权益合计"));
//		map.put("负债和所有者权益总计", find(mergeDebtText, "负债和所有者权益总计", "负债和所有者权益（或股东权益）总计"));
//
//		map.put("利润", "");
//		map.put("营业总收入", find(mergeProfitText, "营业总收入"));
//		map.put("其中：营业收入", find(mergeProfitText, "其中：营业收入"));
//		map.put("利息收入", find(mergeProfitText, "利息收入"));
//		map.put("已赚保费", find(mergeProfitText, "已赚保费"));
//		map.put("手续费及佣金收入", find(mergeProfitText, "手续费及佣金收入"));
//		map.put("营业总成本", find(mergeProfitText, "营业总成本"));
//		map.put("其中：营业成本", find(mergeProfitText, "其中：营业成本"));
//		map.put("利息支出", find(mergeProfitText, "利息支出"));
//		map.put("手续费及佣金支出", find(mergeProfitText, "手续费及佣金支出"));
//		map.put("退保金", find(mergeProfitText, "退保金"));
//		map.put("赔付支出净额", find(mergeProfitText, "赔付支出净额"));
//		map.put("提取保险合同准备金净额", find(mergeProfitText, "提取保险合同准备金净额"));
//		map.put("保单红利支出", find(mergeProfitText, "保单红利支出"));
//		map.put("分保费用", find(mergeProfitText, "分保费用"));
//		map.put("营业税金及附加", find(mergeProfitText, "营业税金及附加"));
//		map.put("销售费用", find(mergeProfitText, "销售费用"));
//		map.put("管理费用", find(mergeProfitText, "管理费用"));
//		map.put("财务费用", find(mergeProfitText, "财务费用"));
//		map.put("资产减值损失", find(mergeProfitText, "资产减值损失"));
//		map.put("加：公允价值变动收益（损失以“－”号填列）", find(mergeProfitText, "加：公允价值变动收益（损失以“－”号填列）"));
//		map.put("投资收益（损失以“－”号填列）", find(mergeProfitText, "投资收益（损失以“－”号填列）"));
//		map.put("其中：对联营企业和合营企业的投资收益", find(mergeProfitText, "其中：对联营企业和合营企业的投资收益"));
//		map.put("汇兑收益（损失以“-”号填列）", find(mergeProfitText, "汇兑收益（损失以“-”号填列）"));
//		map.put("营业利润", find(mergeProfitText, "营业利润"));
//		map.put("加：营业外收入", find(mergeProfitText, "加：营业外收入"));
//		map.put("其中：非流动资产处置利得", find(mergeProfitText, "其中：非流动资产处置利得"));
//		map.put("减：营业外支出", find(mergeProfitText, "减：营业外支出"));
//		map.put("其中：非流动资产处置损失", find(mergeProfitText, "其中：非流动资产处置损失"));
//		map.put("利润总额", find(mergeProfitText, "利润总额"));
//		map.put("减：所得税费用", find(mergeProfitText, "减：所得税费用"));
//		map.put("净利润", find(mergeProfitText, "净利润"));
//		map.put("归属于母公司所有者的净利润", find(mergeProfitText, "归属于母公司所有者的净利润"));
//		map.put("少数股东损益", find(mergeProfitText, "少数股东损益"));
//		map.put("六、其他综合收益的税后净额", find(mergeProfitText, "、其他综合收益的税后净额"));
//		map.put("归属母公司所有者的其他综合收益的税后净额", find(mergeProfitText, "归属母公司所有者的其他综合收益的税后净额"));
//		map.put("归属于少数股东的其他综合收益的税后净额", find(mergeProfitText, "归属于少数股东的其他综合收益的税后净额"));
//		map.put("综合收益总额", find(mergeProfitText, "综合收益总额"));
//		map.put("归属于母公司所有者的综合收益总额", find(mergeProfitText, "综合收益总额"));
//		map.put("归属于母公司所有者的综合收益总额", find(mergeProfitText, "归属于母公司所有者的综合收益总额"));
//		map.put("归属于少数股东的综合收益总额", find(mergeProfitText, "归属于少数股东的综合收益总额"));
//		map.put("八、每股收益", "");
//		map.put("基本每股收益", find(mergeProfitText, "基本每股收益"));
//		map.put("稀释每股收益", find(mergeProfitText, "稀释每股收益"));
//		
//		map.put("现金流量", "");
//		map.put("一、经营活动产生的现金流量：", "");
//		map.put("销售商品、提供劳务收到的现金", find(mergeCashText, "销售商品、提供劳务收到的现金"));
//		map.put("客户存款和同业存放款项净增加额", find(mergeCashText, "客户存款和同业存放款项净增加额"));
//		map.put("向中央银行借款净增加额", find(mergeCashText, "向中央银行借款净增加额"));
//		map.put("向其他金融机构拆入资金净增加额", find(mergeCashText, "向其他金融机构拆入资金净增加额"));
//		map.put("收到原保险合同保费取得的现金", find(mergeCashText, "收到原保险合同保费取得的现金"));
//		map.put("收到再保险业务现金净额", find(mergeCashText, "收到再保险业务现金净额"));
//		map.put("保户储金及投资款净增加额", find(mergeCashText, "保户储金及投资款净增加额"));
//		map.put("处置以公允价值计量且其变动计入当期损益的金融资产净增加额", find(mergeCashText, "处置以公允价值计量且其变动计入当期损益的金融资产净增加额"));
//		map.put("收取利息、手续费及佣金的现金", find(mergeCashText, "收取利息、手续费及佣金的现金"));
//		map.put("拆入资金净增加额", find(mergeCashText, "拆入资金净增加额"));
//		map.put("回购业务资金净增加额", find(mergeCashText, "回购业务资金净增加额"));
//		map.put("收到的税费返还", find(mergeCashText, "收到的税费返还"));
//		map.put("收到其他与经营活动有关的现金", find(mergeCashText, "收到其他与经营活动有关的现金"));
//		map.put("经营活动现金流入小计", find(mergeCashText, "经营活动现金流入小计"));
//		map.put("购买商品、接受劳务支付的现金", find(mergeCashText, "购买商品、接受劳务支付的现金"));
//		map.put("客户贷款及垫款净增加额", find(mergeCashText, "客户贷款及垫款净增加额"));
//		map.put("存放中央银行和同业款项净增加额", find(mergeCashText, "存放中央银行和同业款项净增加额"));
//		map.put("支付原保险合同赔付款项的现金", find(mergeCashText, "支付原保险合同赔付款项的现金"));
//		map.put("支付利息、手续费及佣金的现金", find(mergeCashText, "支付利息、手续费及佣金的现金"));
//		map.put("支付保单红利的现金", find(mergeCashText, "支付保单红利的现金"));
//		map.put("支付给职工以及为职工支付的现金", find(mergeCashText, "支付给职工以及为职工支付的现金"));
//		map.put("支付的各项税费", find(mergeCashText, "支付的各项税费"));
//		map.put("支付其他与经营活动有关的现金", find(mergeCashText, "支付其他与经营活动有关的现金"));
//		map.put("经营活动现金流出小计", find(mergeCashText, "经营活动现金流出小计"));
//		map.put("经营活动产生的现金流量净额", find(mergeCashText, "经营活动产生的现金流量净额"));
//		map.put("二、投资活动产生的现金流量：", "");
//		map.put("收回投资收到的现金", find(mergeCashText, "收回投资收到的现金"));
//		map.put("取得投资收益收到的现金", find(mergeCashText, "取得投资收益收到的现金", "取得投资收益所收到的现金"));
//		map.put("处置固定资产、无形资产和其他长期资产收回的现金净额", find(mergeCashText, "处置固定资产、无形资产和其他长期资产收回的现金净额"));
//		map.put("处置子公司及其他营业单位收到的现金净额", find(mergeCashText, "处置子公司及其他营业单位收到的现金净额"));
//		map.put("收到其他与投资活动有关的现金", find(mergeCashText, "收到其他与投资活动有关的现金"));
//		map.put("投资活动现金流入小计", find(mergeCashText, "投资活动现金流入小计"));
//		map.put("购建固定资产、无形资产和其他长期资产支付的现金", find(mergeCashText, "购建固定资产、无形资产和其他长期资产支付的现金"));
//		map.put("投资支付的现金", find(mergeCashText, "投资支付的现金"));
//		map.put("质押贷款净增加额", find(mergeCashText, "质押贷款净增加额"));
//		map.put("取得子公司及其他营业单位支付的现金净额", find(mergeCashText, "取得子公司及其他营业单位支付的现金净额"));
//		map.put("支付其他与投资活动有关的现金", find(mergeCashText, "支付其他与投资活动有关的现金"));
//		map.put("投资活动现金流出小计", find(mergeCashText, "投资活动现金流出小计"));
//		map.put("投资活动产生的现金流量净额", find(mergeCashText, "投资活动产生的现金流量净额"));
//		map.put("三、筹资活动产生的现金流量：", "");
//		map.put("吸收投资收到的现金", find(mergeCashText, "吸收投资收到的现金"));
//		map.put("其中：子公司吸收少数股东投资收到的现金", find(mergeCashText, "其中：子公司吸收少数股东投资收到的现金"));
//		map.put("取得借款收到的现金", find(mergeCashText, "取得借款收到的现金"));
//		map.put("发行债券收到的现金", find(mergeCashText, "发行债券收到的现金"));
//		map.put("收到其他与筹资活动有关的现金", find(mergeCashText, "收到其他与筹资活动有关的现金"));
//		map.put("筹资活动现金流入小计", find(mergeCashText, "筹资活动现金流入小计"));
//		map.put("偿还债务支付的现金", find(mergeCashText, "偿还债务支付的现金"));
//		map.put("分配股利、利润或偿付利息支付的现金", find(mergeCashText, "分配股利、利润或偿付利息支付的现金"));
//		map.put("其中：子公司支付给少数股东的股利、利润", find(mergeCashText, "其中：子公司支付给少数股东的股利、利润"));
//		map.put("支付其他与筹资活动有关的现金 ", find(mergeCashText, "支付其他与筹资活动有关的现金"));
//		map.put("筹资活动现金流出小计", find(mergeCashText, "筹资活动现金流出小计"));
//		map.put("筹资活动产生的现金流量净额", find(mergeCashText, "筹资活动产生的现金流量净额"));
//		map.put("四、汇率变动对现金及现金等价物的影响", find(mergeCashText, "四、汇率变动对现金及现金等价物的影响"));
//		map.put("五、现金及现金等价物净增加额", find(mergeCashText, "五、现金及现金等价物净增加额"));
//		map.put("加：期初现金及现金等价物余额", find(mergeCashText, "加：期初现金及现金等价物余额"));
//		map.put("六、期末现金及现金等价物余额", find(mergeCashText, "六、期末现金及现金等价物余额"));
		
		int i = 0;
		for (String key : map.keySet())
		{
			sheet.addCell(new Label(0, i, key));
			sheet.addCell(new Label(1, i, map.get(key)));
			i++;
		}

		workbook.write();
		workbook.close();
		
		map.put("流动资产合计", find(mergeDebtText, "流动资产合计"));
		map.put("非流动资产合计", find(mergeDebtText, "非流动资产合计"));
		map.put("流动负债合计", find(mergeDebtText, "流动负债合计"));
		map.put("非流动负债合计", find(mergeDebtText, "非流动负债合计"));
		map.put("所有者权益合计", find(mergeDebtText, "所有者权益合计"));
		map.put("营业总收入", find(mergeProfitText, "营业总收入"));
		map.put("营业总成本", find(mergeProfitText, "营业总成本"));
		map.put("营业利润", find(mergeProfitText, "营业利润"));
		map.put("利润总额", find(mergeProfitText, "利润总额"));
		map.put("净利润", find(mergeProfitText, "净利润"));
		map.put("综合收益总额", find(mergeProfitText, "综合收益总额"));
		
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
