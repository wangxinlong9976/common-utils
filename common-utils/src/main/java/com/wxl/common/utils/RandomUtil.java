package com.wxl.common.utils;

import java.util.Random;

/**
 * 
 * @ClassName: RandomUtil
 * @Description: TODO
 * @author lenovo
 * @date 2019��12��5��
 *
 */
public class RandomUtil {
	/**
	 * 
	 * @Title: getRandomIntPrositive
	 * @Description: TODO �������һ��������
	 * @param range
	 * @return	����һ�� 0 - range֮���������
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
	 * @Description: TODO �������һ��������
	 * @param range
	 * @return ����һ�� 0 - range֮��ĸ�����
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
