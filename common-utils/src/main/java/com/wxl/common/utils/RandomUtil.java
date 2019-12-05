package com.wxl.common.utils;

import java.util.Random;

/**
 * 
 * @ClassName: RandomUtil
 * @Description: TODO
 * @author lenovo
 * @date 2019年12月5日
 *
 */
public class RandomUtil {
	
	/**
	 * 
	 * @Title: randomChinese
	 * @Description: TODO 随机生成range个中文字符
	 * @param range
	 * @return    
	 * String    
	 *
	 */
	public static String randomChinese(Integer range) {
		if(range<=0) {
			System.err.println("随机生成的字符应该>0个");
			return null;
		}
		Integer start = Integer.parseInt("4e00",16);   //19968
		Integer end = Integer.parseInt("9fa5", 16);	   //40869
		
		char[] character = new char[range];
		int code = 0;
		int i = 0;
		while((range--)>0) {
			code = new Random().nextInt(end-start+1)+start;
			character[i++]=(char)code;
		}
		return new String(character);
		
	}
}
