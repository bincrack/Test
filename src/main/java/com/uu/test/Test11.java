package com.uu.test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.uu.util.MD5;

public class Test11 implements Runnable
{
	String uri = "";
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	CountDownLatch cdAnswer = null;
	
	public Test11(String uri, CountDownLatch cdAnswer)
	{
		this.uri = uri;
		this.cdAnswer = cdAnswer;
	}
	
	public static void main(String[] args) throws Exception
	{
		ExecutorService threadPool = Executors.newCachedThreadPool();
		int index = 0; // 用户号
		int size = 10; // 每组数量
		int max = 4; // 最大用户号
		
		while (true)
		{
			System.out.println("continue...");
			CountDownLatch cdAnswer = new CountDownLatch(size);
			for (int i = 0; i < size; i++)
			{
				BigDecimal amount = new BigDecimal(String.valueOf(Math.random())).multiply(new BigDecimal("5000")).setScale(2, BigDecimal.ROUND_DOWN);
				String userID = MD5.GetMD5Code(String.valueOf(index));
				String uri = "http://localhost:8686/NonStandardAPI/trade/buy.do?productID=10000&userID=" + userID + "&amount=" + amount;
				threadPool.execute(new Test11(uri, cdAnswer));
				index = (index >= max) ? 0 : (index + 1);
			}
			
			System.out.println("await...");
			cdAnswer.await();
		}
	
//		threadPool.shutdown();
	}

	@Override
	public void run()
	{
		long _StartTime = new Date().getTime();
		String content = null;
		
		try {
			HttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet(uri);
			HttpResponse response = client.execute(get);
			content = EntityUtils.toString(response.getEntity(), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		long _EndTime = new Date().getTime();
		
		System.out.println("耗时：【" + (_EndTime - _StartTime) + "】毫秒" + "\t" + content + "\t" + uri);
		
		cdAnswer.countDown();
	}

}
