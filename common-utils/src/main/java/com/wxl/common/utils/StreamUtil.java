package com.wxl.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: StreamUtil
 * @Description: TODO
 * @author lenovo
 * @date 2019年12月5日
 *
 */
public class StreamUtil {
	
	/**
	 * 	
	 * @Title: closeAll
	 * @Description: TODO		关流工具
	 * @param autoCloseables    
	 * void    
	 *
	 */
	public static void closeAll(AutoCloseable...autoCloseables) {
		if(autoCloseables!=null) {
			for (AutoCloseable autoCloseable : autoCloseables) {
				if(autoCloseable!=null) {
					try {
						autoCloseable.close();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	/**
	 * 	
	 * @Title: writeToFile
	 * @Description: TODO		向目标文件写入内容
	 * @param content
	 * @param file
	 * @param isAppend
	 * @return
	 * @throws IOException    
	 * boolean    
	 *
	 */
	public static boolean writeToFile(String content,File file,boolean isAppend) throws IOException {
		if(file==null) {
			throw new NullPointerException("文件不能为空");
		}
		if(!file.isFile()) {
			throw new IOException("目标不是一个文件");
		}
		BufferedWriter buffer = null;
		try{
			buffer = new BufferedWriter(new FileWriter(file,isAppend));
			buffer.write(content);
			buffer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(buffer);
		}

		return true;
	}
	/**
	 * 
	 * @Title: readToFile
	 * @Description: TODO     	读取文件
	 * @param file
	 * @return
	 * @throws IOException    
	 * String    
	 *
	 */
	public static String readToFile(File file) throws IOException {
		if(file==null) {
			throw new NullPointerException("文件不存在");
		}
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		int len = 0;
		byte[] b = new byte[bis.available()];
		StringBuilder sb = new StringBuilder();
		while((len=bis.read(b))!=-1) {
			sb.append((char)bis.read(b));
		}
		closeAll(bis);
		
		return sb.toString();
	}
	
	/**
	 * 	
	 * @Title: copyFile
	 * @Description: TODO		文件复制
	 * @param source			源文件路径
	 * @param target			目标文件夹
	 * @return
	 * @throws IOException    
	 * boolean    
	 *
	 */
	@SuppressWarnings("resource")
	public static boolean copyFile(File source,File target) throws IOException {
		if(!source.exists() || !source.exists()) {
			throw new FileNotFoundException("源文件或目标文件不存在");
		}
		String sourceFileName = source.getName();
		target = new File(target,sourceFileName);
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(source));
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(target,true));
		
		int len = 0;
		byte[] b = new byte[bis.available()];
		while((len=bis.read(b))!=-1) {
			bos.write(b, 0, len);
		}
		bos.flush();
		closeAll(bis,bos);
		return false;
	}
	
	/**
	 * 	
	 * @Title: copyDirectory
	 * @Description: TODO    复制整个文件夹
	 * @param source
	 * @param target
	 * @return
	 * @throws IOException    
	 * boolean    
	 *
	 */
	public static boolean copyDirectory(File source,File target) throws IOException {
		if(source.isFile()) {
			StreamUtil.copyFile(source, target);
			return true;
		}
		String dirName = source.getName();
		File originDir = new File(target,dirName);
		originDir.mkdirs();
		File[] sourceList = source.listFiles();
		for (File file : sourceList) {
			if(file.isFile()) {
				new File(originDir,file.getName());
			}
		}
		
		return false;
	}
	
	
	/**
	 * 	
	 * @Title: stringToFile
	 * @Description: TODO		将字符串路径转化成File对象
	 * @param fileName
	 * @return    
	 * File    
	 *
	 */
	public static File stringToFile(String fileName) {
		return new File(fileName);
	}
	 
	
	@SuppressWarnings("resource")
	public static List<String> readTxtOfLine(String path) {
		FileInputStream fis = null;
		Reader isr = null;
		BufferedReader buffer = null;
		List<String> list = null;
		try {
			fis = new FileInputStream(new File(path));
			isr = new InputStreamReader(fis,"GBK");
			buffer = new BufferedReader(isr);
			String line = "";
			list = new ArrayList<String>();
			while((line=buffer.readLine())!=null) {
				list.add(line);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll(buffer,isr,fis);
		}
		
		return list;
	}
	public static void main(String[] args) {
		List<String> readTxtOfLine = readTxtOfLine("aaa.txt");
		for (String string : readTxtOfLine) {
			System.out.println(string);
		}
	}
}
