package com.wxl.common.utils;

import java.io.File;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class SqlResolver {
	
	
	
	public void resolver() {
		List<String> list = new ArrayList<String>();
		
//		new File(this.getClass().getResource("\\").getPath());
		System.out.println(this.getClass().getResource("/").getPath());
		File file = new File(this.getClass().getResource("/").getPath()+"/com/wxl/common/utils");
		System.out.println(file.getAbsolutePath());
		File[] listFiles = file.listFiles();
		for (File file2 : listFiles) {
			System.out.println(file2.getName());
		}
	}
	
}
