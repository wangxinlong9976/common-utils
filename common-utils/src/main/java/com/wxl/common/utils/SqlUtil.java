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
 * @Description: TODO	通过表DLL自动生成实体类
 * @author 1709D-王鑫龙
 * @date 2019年12月10日
 *
 */
public class SqlUtil {
	
/*******************************************V2.0********************************************/	
	
	/**
	 * 
	 * @Title: createEntity	
	 * @Description: TODO  		将DDL中提取出来字段名和类型  
	 * 							将字段名和类型进行映射
	 * Map<String,String>   
	 *
	 */
	public static Map<String,String> varAndTypeMap() {
		List<String> str = StreamUtil.readTxtOfLine("aaa.txt");
		/**
		 * 	存储变量名和类型的映射关系
		 */
		Map<String,String> map = new LinkedHashMap<>();
		
		/**
		 * 	获取java和mysql类型的映射关系
		 */
		Map<String, String> typeMap = getTypeMap();
		
		String key = "";
		String value = "";
		for (int i = 0; i < str.size()-1; i++) {
			String curr = str.get(i);
			System.out.println("当前处理第"+(i+1)+"行:>>>"+curr);
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
	 * @Description: TODO		获取java与mysql类型的对应map   可以手动添加
	 * @return    
	 * Map<String,String>    
	 *
	 */
	public static Map<String,String> getTypeMap() {
		Map<String, String> typeMap = new LinkedHashMap<>();
		//	key是mysql数据类型                 value是java数据类型
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
	 * @Description: TODO		创建实体类的入口方法
	 * @param packName
	 * @param file
	 * @throws URISyntaxException    
	 * void    
	 *
	 */
	public static void createEntity(String packName,File file) throws URISyntaxException {
		/**
		 * 	将包名转换成路径
		 */
		String relaPath = packName.replace(".", "\\");
		URL url = SqlUtil.class.getClassLoader().getResource(relaPath);
		URI uri = url.toURI();
		
		/**
		 * 	获取变量名和类型的对应map
		 */
		Map<String,String> map = varAndTypeMap();
		List<String> proccessList = proccessTxt(packName, map);
		BufferedOutputStream bos = null;
		
		String fileName = map.get("$name$").substring(0,1).toUpperCase()+map.get("$name$").substring(1);
		System.out.println("文件名:"+fileName+".java");
		File f1 = new File(fileName+".java");
		File f2 = new File(uri);
		String f2Abs = f2.getAbsolutePath();
		String rPath = f2Abs.substring(0,f2Abs.indexOf("target\\classes"))+"src\\main\\java\\"+relaPath;
		System.out.println("类所在的绝对路径:   "+rPath);
		
		File f3 = new File(rPath,f1.getName());
		if(f2.isDirectory()) {
			if(f3.exists()) {
				System.err.println("文件已存在!");
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
	 * @Description: TODO	生成实体类模板
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
		 * 	拼接package 和 import部分
		 */
		txt.add("/*********************************自动生成的bean模板*********************************/");
		txt.add("package "+pageName+";");
		txt.add("	");
		txt.add("import java.sql.Date;");
		txt.add("	");
		
		/**
		 * 	拼接类名部分
		 */
		txt.add("public class "+className+"{");
		/**
		 * 	拼接成员变量
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
		 * 	拼接setter   getter方法
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
		 * 	拼接无参构造方法
		 */
		txt.add("	public "+className+"(){}");
		txt.add("	");
		/**
		 * 	拼接满参构造方法
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
		txt.add("/*********************************自动生成的bean模板*********************************/");
		for (String string : txt) {
			System.out.println(string);
		}
		return txt;
	}
	public static void main(String[] args) throws URISyntaxException {
//		createEntity("");
		createEntity("com.wxl.common.pojo",null);
//		System.out.println("\"");
//		\"  ==>  "   转义
	}
	
	
	
	
}
