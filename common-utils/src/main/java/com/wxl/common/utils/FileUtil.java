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
	 * @Description: TODO	获取文件后缀名
	 * @param fileName		文件路径
	 * @return
	 * @throws Exception    
	 * String    
	 *
	 */
	public static String getFileSuffix(String fileName) throws Exception{
		if(fileName==null || fileName.equals("")) {
			throw new FileNotFoundException("文件名不能为空");
		}
		if(!fileName.contains(".")) {
			throw new FileNotFoundException("文件名格式不正确");
		}
		return fileName.substring(fileName.lastIndexOf("."));
	}
	
	
	/**
	 * 	
	 * @Title: getFileSuffix
	 * @Description: TODO	截取文件后缀名
	 * @param file			文件对象
	 * @return
	 * @throws Exception    
	 * String    
	 *
	 */
	public static String getFileSuffix(File file) throws Exception{
		if(file==null || file.getName().equals("")) {
			throw new FileNotFoundException("文件存在");
		}
		if(file.getName().indexOf(".")<0) {
			throw new FileNotFoundException("不是一个文件");
		}
		
		return file.getName().substring(file.getName().lastIndexOf("."));
	}
	
	/**
	 * 	
	 * @Title: downlodaFile
	 * @Description: TODO		从网络上下载文件
	 * @param url1	要下载文件的路径
	 * @param des	要保存到本机的路径
	 * @return
	 * @throws Exception    
	 * boolean    	下载成功返回true   
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
