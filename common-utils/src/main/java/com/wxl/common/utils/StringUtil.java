package com.wxl.common.utils;
/**
 * 
 * @ClassName: StringUtil
 * @Description: TODO
 * @author lenovo
 * @date 2019年12月5日
 *
 */
public class StringUtil {
	/**
	 * 	
	 * @Title: isBlank
	 * @Description: TODO 验证是否为空字符串
	 * @param str
	 * @return    true || false
	 * boolean    
	 *
	 */
	public static boolean isBlank(String str) {
		if(str!=null) {
			return false;
		}
		str = str.trim();
		if(str.equals("") || str.length()==0) {
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @Title: isPhone
	 * @Description: TODO	验证手机号格式是否正确
	 * @param phone			手机号
	 * @return	true || false
	 * @throws Exception    
	 * boolean    
	 *
	 */
	public static boolean isPhone(String phone) throws Exception{
		if(phone==null || phone.equals("")) {
			throw new NumberFormatException("手机号不能为空");
		}
		
		return phone.matches("1[3579]\\d{9}");
	}
	
	
	/**
	 * 	
	 * @Title: isEnglishChar
	 * @Description: TODO	验证是全为英文字符串
	 * @param str
	 * @return    
	 * boolean    
	 *
	 */
	public static boolean isEnglishChar(String str) {
		int i = 0;
		while(i!=(str.length()-1)) {
			if(!((int)str.charAt(i)>=97 && (int)str.charAt(i)<=122)) {
				return false;
			}
			i++;
		}
		return true;
	}
	
	
	/**
	 * 	
	 * @Title: judgeTelephoneIsOk
	 * @Description: TODO	验证是否是一个合法的手机号
	 * @param phone
	 * @return    
	 * boolean    
	 *
	 */
	public static boolean judgeTelephoneIsOk(String phone) {
		if(phone==null) {
			return false;
		}
		return phone.matches("1[3579]\\d{9}");
	}
	
	
	/**
	 * 	
	 * @Title: isNumber
	 * @Description: TODO   判断是否是数字
	 * @param num
	 * @return    
	 * boolean    
	 *
	 */
	public static boolean isNumber(String num) {
		if(num==null) {
			return false;
		}
		int length = num.length();
		int i = 0;
		while((length--)>0) {
			if(num.charAt(i)<48 || num.charAt(i)>57) {
				return false;
			}
			i++;
		}
		return true;
		
	}
	

	
	
	
}
