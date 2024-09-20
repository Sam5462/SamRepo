package com.Comcast.crm.generic.FileUtility;

import java.io.FileInputStream;
import java.util.Properties;

public class FileUtility {
	
	public String getdataFromPropertyFile(String key) throws Throwable {
		FileInputStream fis=new FileInputStream("./configAppData/commonData.properties");
		Properties pObj=new Properties();
		pObj.load(fis);
		String data = pObj.getProperty(key);
		return data;
	}

}
