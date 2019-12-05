package com.wxl.common.utils;
/**
 * 
 * @ClassName: StringUtil
 * @Description: TODO
 * @author lenovo
 * @date 2019��12��5��
 *
 */
public class StringUtil {
	/**
	 * 	
	 * @Title: isBlank
	 * @Description: TODO ��֤�Ƿ�Ϊ���ַ���
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
	 * @Description: TODO	��֤�ֻ��Ÿ�ʽ�Ƿ���ȷ
	 * @param phone			�ֻ���
	 * @return	true || false
	 * @throws Exception    
	 * boolean    
	 *
	 */
	public static boolean isPhone(String phone) throws Exception{
		if(phone==null || phone.equals("")) {
			throw new NumberFormatException("�ֻ��Ų���Ϊ��");
		}
		
		return phone.matches("1[3579]\\d{9}");
	}
	
	
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
	
}
