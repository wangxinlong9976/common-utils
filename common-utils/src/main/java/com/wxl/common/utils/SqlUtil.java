package com.wxl.common.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



/**
 * 
 * @ClassName: SqlUtil
 * @Description: TODO	ͨ����DLL�Զ�����ʵ����
 * @author 1709D-������
 * @date 2019��12��10��
 *
 */
public class SqlUtil {
	
/*******************************************V2.0********************************************/	
	
	/**
	 * 
	 * @Title: createEntity	
	 * @Description: TODO  		��DDL����ȡ�����ֶ���������  
	 * 							���ֶ��������ͽ���ӳ��
	 * Map<String,String>   
	 *
	 */
	public static Map<String,String> varAndTypeMap() {
		List<String> str = StreamUtil.readTxtOfLine("aaa.txt");
		/**
		 * 	�洢�����������͵�ӳ���ϵ
		 */
		Map<String,String> map = new LinkedHashMap<>();
		
		/**
		 * 	��ȡjava��mysql���͵�ӳ���ϵ
		 */
		Map<String, String> typeMap = getTypeMap();
		
		String key = "";
		String value = "";
		for (int i = 0; i < str.size()-1; i++) {
			String curr = str.get(i);
			System.out.println("��ǰ�����"+(i+1)+"��:>>>"+curr);
			if(curr.equals("")) {
				continue;
			}
			key = curr.substring(curr.indexOf("`")+1,curr.lastIndexOf("`"));
			if(i==0) {
				map.put("$name$", key);
				continue;
			}
			for (String keySet : typeMap.keySet()) {
				value="";
				if(curr.contains(keySet)) {
					value=typeMap.get(keySet);
					break;
				}	
			}
			System.out.println(key+"   "+value);
//	
			if(value==null || value.equals("")) {
				continue;
			}
			map.put(key, value);
		}
		return map;
	}
	
	/**
	 * 	
	 * @Title: getTypeMap	
	 * @Description: TODO		��ȡjava��mysql���͵Ķ�Ӧmap   �����ֶ����
	 * @return    
	 * Map<String,String>    
	 *
	 */
	public static Map<String,String> getTypeMap() {
		Map<String, String> typeMap = new LinkedHashMap<>();
		//	key��mysql��������                 value��java��������
 		typeMap.put("int", "Integer");
		typeMap.put("varchar", "String");
		typeMap.put("double", "double");
		typeMap.put("date ", "Date");
		typeMap.put("datetime", "String");
//		...........
//		typeMap.put("", "");
		return typeMap;
	}
	
	/**
	 * 
	 * @Title: createEntity
	 * @Description: TODO		����ʵ�������ڷ���
	 * @param packName
	 * @param file
	 * @throws URISyntaxException    
	 * void    
	 *
	 */
	public static void createEntity(String packName,File file) throws URISyntaxException {
		/**
		 * 	������ת����·��
		 */
		String relaPath = packName.replace(".", "\\");
		URL url = SqlUtil.class.getClassLoader().getResource(relaPath);
		URI uri = url.toURI();
		
		/**
		 * 	��ȡ�����������͵Ķ�Ӧmap
		 */
		Map<String,String> map = varAndTypeMap();
		List<String> proccessList = proccessTxt(packName, map);
		BufferedOutputStream bos = null;
		
		String fileName = map.get("$name$").substring(0,1).toUpperCase()+map.get("$name$").substring(1);
		System.out.println("�ļ���:"+fileName+".java");
		File f1 = new File(fileName+".java");
		File f2 = new File(uri);
		String f2Abs = f2.getAbsolutePath();
		String rPath = f2Abs.substring(0,f2Abs.indexOf("target\\classes"))+"src\\main\\java\\"+relaPath;
		System.out.println("�����ڵľ���·��:   "+rPath);
		
		File f3 = new File(rPath,f1.getName());
		if(f2.isDirectory()) {
			if(f3.exists()) {
				System.err.println("�ļ��Ѵ���!");
				System.out.println("Waiting .....");
				return ;
			}
			
			System.out.println("f1:  "+f1.getName());
			System.out.println("f2:  "+f2.getAbsolutePath());
			System.out.println("f3:  "+f3.getPath());
			try {
				f3.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			bos = new BufferedOutputStream(new FileOutputStream(f3,true));
			for (String descLine : proccessList) {
				bos.write((descLine+"\n").getBytes());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				bos.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				bos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 
	 * @Title: proccessTxt
	 * @Description: TODO	����ʵ����ģ��
	 * @param pageName
	 * @param map
	 * @return    
	 * List<String>    
	 *
	 */
	public static List<String> proccessTxt(String pageName,Map<String,String> map) {
		List<String> txt = new ArrayList<>();
		String className = map.get("$name$").substring(0,1).toUpperCase()+map.get("$name$").substring(1);
		String varName = "";
		String params = "";
		String toStringStr = "";
		/**
		 * 	ƴ��package �� import����
		 */
		txt.add("/*********************************�Զ����ɵ�beanģ��*********************************/");
		txt.add("package "+pageName+";");
		txt.add("	");
		txt.add("import java.sql.Date;");
		txt.add("	");
		
		/**
		 * 	ƴ����������
		 */
		txt.add("public class "+className+"{");
		/**
		 * 	ƴ�ӳ�Ա����
		 */
		for (String key : map.keySet()) {
			if(key.equals("$name$")) {
				continue;
			}
			txt.add("	private "+map.get(key)+" "+key+";");
			params+=","+map.get(key)+" "+key;
			toStringStr+="\",\"+"+key+"=\""+"+\""+key+"";
		}
		params=params.substring(1);
		toStringStr=toStringStr.substring(4);
		txt.add("	");
		/**
		 * 	ƴ��setter   getter����
		 */
		for(String key : map.keySet()) {
			if(key.equals("$name$")) {
				continue;
			}
			varName = key.substring(0,1).toUpperCase()+key.substring(1);
			txt.add("	public void set"+varName+"("+map.get(key)+" "+key+"){");
			txt.add("		this."+key+"="+key+";");
			txt.add("	}");
			txt.add("	public "+map.get(key)+" get"+varName+"(){");
			txt.add("		return "+key+";");
			txt.add("	}");
		}
		txt.add("	");
		/**
		 * 	ƴ���޲ι��췽��
		 */
		txt.add("	public "+className+"(){}");
		txt.add("	");
		/**
		 * 	ƴ�����ι��췽��
		 */
		txt.add("	public "+className+"("+params+"){");
		for (String key : map.keySet()) {
			if(key.equals("$name$")) {
				continue;
			}
			txt.add("		this."+key+"="+key+";");
		}
		
		txt.add("	}");
//		txt.add("	@override");
//		txt.add("	public String toString() {");
//		txt.add("	\""+className+" ["+toStringStr+"\"]\";");
//		txt.add("	}");
		txt.add("}");
		txt.add("/*********************************�Զ����ɵ�beanģ��*********************************/");
		for (String string : txt) {
			System.out.println(string);
		}
		return txt;
	}
	public static void main(String[] args) throws URISyntaxException {
//		createEntity("");
		createEntity("com.wxl.common.pojo",null);
//		System.out.println("\"");
//		\"  ==>  "   ת��
	}
	
	
	
	
}
