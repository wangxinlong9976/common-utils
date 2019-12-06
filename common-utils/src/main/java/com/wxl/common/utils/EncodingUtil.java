package com.wxl.common.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * 	
 * @ClassName: EncodingUtil
 * @Description: TODO	����ת��������
 * @author lenovo
 * @date 2019��12��6��
 *
 */
public class EncodingUtil {
	/**
	 * 	
	 * @Title: encoding
	 * @Description: TODO   URL����
	 * @param str
	 * @param encode
	 * @return
	 * @throws UnsupportedEncodingException    
	 * String    
	 *
	 */
	public static String URLEncoding(String str,String encode) throws UnsupportedEncodingException {
			String encoded = URLEncoder.encode(str, encode);
			return encoded;
	}
	/**
	 * 	
	 * @Title: decoding
	 * @Description: TODO 	URL����
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException    
	 * String    
	 *
	 */
	public static String URLDecoding(String str,String encode) throws UnsupportedEncodingException {
		String encoded = URLDecoder.decode(str,encode);
		return encoded;
	}
	/**
	 * 	
	 * @Title: MD5Encoding
	 * @Description: TODO     MD5����
	 * @param password
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException    
	 * String    
	 *
	 */
	public static String MD5Encoding(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest instance = MessageDigest.getInstance("MD5");
		instance.update(password.getBytes("utf-8"));
		byte[] by = instance.digest();
		return new BigInteger(1, by).toString(16);
	}
}
