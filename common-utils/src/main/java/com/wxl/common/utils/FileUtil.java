package com.wxl.common.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileUtil {
	
	/**
	 * 	
	 * @Title: getFileSuffix
	 * @Description: TODO	��ȡ�ļ���׺��
	 * @param fileName		�ļ�·��
	 * @return
	 * @throws Exception    
	 * String    
	 *
	 */
	public static String getFileSuffix(String fileName) throws Exception{
		if(fileName==null || fileName.equals("")) {
			throw new FileNotFoundException("�ļ�������Ϊ��");
		}
		if(!fileName.contains(".")) {
			throw new FileNotFoundException("�ļ�����ʽ����ȷ");
		}
		return fileName.substring(fileName.lastIndexOf("."));
	}
	
	
	/**
	 * 	
	 * @Title: getFileSuffix
	 * @Description: TODO	��ȡ�ļ���׺��
	 * @param file			�ļ�����
	 * @return
	 * @throws Exception    
	 * String    
	 *
	 */
	public static String getFileSuffix(File file) throws Exception{
		if(file==null || file.getName().equals("")) {
			throw new FileNotFoundException("�ļ�����");
		}
		if(file.getName().indexOf(".")<0) {
			throw new FileNotFoundException("����һ���ļ�");
		}
		
		return file.getName().substring(file.getName().lastIndexOf("."));
	}
	
	/**
	 * 	
	 * @Title: downlodaFile
	 * @Description: TODO		�������������ļ�
	 * @param url1	Ҫ�����ļ���·��
	 * @param des	Ҫ���浽������·��
	 * @return
	 * @throws Exception    
	 * boolean    	���سɹ�����true   
	 *
	 */
	public static boolean downlodaFile(String url1,String des) throws Exception{
		boolean bool = false;
		String uri = url1;
		String fileName = uri.substring(uri.lastIndexOf("/")+1);
		File newFile = new File(des,fileName);
		newFile.createNewFile();
		System.out.println(fileName);
		URL url = new URL(uri);
		HttpURLConnection huc = (HttpURLConnection) url.openConnection();
		InputStream is = huc.getInputStream();
		OutputStream os = new FileOutputStream(newFile,true);
		
		File file = new File(uri);
		
		byte[] b = new byte[1024*5];
		int i = 0;
		while((i=is.read(b))!=-1) {
			os.write(b, 0, i);
		}
		os.close();
		return bool;
	}

}
