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
	
	
	/**
	 * 	
	 * @Title: isEnglishChar
	 * @Description: TODO	��֤��ȫΪӢ���ַ���
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
	 * @Description: TODO	��֤�Ƿ���һ���Ϸ����ֻ���
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
	 * @Description: TODO   �ж��Ƿ�������
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
