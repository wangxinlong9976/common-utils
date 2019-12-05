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
	 * @Title: getRandomIntPrositive
	 * @Description: TODO 随机生成一个正整数
	 * @param range
	 * @return	返回一个 0 - range之间的正整数
	 * @throws Exception    
	 * Integer    
	 *
	 */
	public static Integer getRandomIntPrositive(Integer range) throws Exception{
		Integer randNum = 0;
		randNum = (int) Math.floor(Math.random()*range+1);
		return randNum;
	}
	public static Integer getRandomIntPrositive(Integer minRange,Integer maxRange) throws Exception{
		Integer randNum = 0;
	
		return randNum;
	}
	/**
	 * 
	 * @Title: getRandomIntMinus
	 * @Description: TODO 随机生成一个正整数
	 * @param range
	 * @return 返回一个 0 - range之间的负整数
	 * @throws Exception    
	 * Integer    
	 *
	 */
	public static Integer getRandomIntMinus(Integer range) throws Exception{
		return getRandomIntMinus(range)*-1;
	}
	
	public static String randomChinese(Integer range) {
		Integer start = Integer.parseInt("4e00",16);
		Integer end = Integer.parseInt("9fa5", 16);
		System.out.println(start +"    "+end);
		
		return null;
		
	}
}
