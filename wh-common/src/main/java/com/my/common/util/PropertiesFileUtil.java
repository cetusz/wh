package com.my.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesFileUtil {
	private static Map<String, Long> fileLastModifyMap = new HashMap<String, Long>();
	private static Map<String,Properties> storeMap = new HashMap<String, Properties>();
	
	/**
	 * 根据文件名加载properties文件
	 * 1.默认根据capVideoConfigPath加载文件
	 * 2.如果上述目录不存在或为空则以classpath为基准目录
	 * @param fileName
	 * @return
	 */
	public static Properties loadProperties(String fileName)
	{
		String appConfigHome = System.getProperty("appConfigHome");
		fileName = fileName.replace("appConfigHome:", appConfigHome+File.separator);
		fileName = fileName.replace("userpath:", System.getProperty("user.dir")+File.separator);
		fileName = fileName.replace("classpath:", PropertiesFileUtil.class.getClassLoader().getResource("").getPath());
		if(fileName.indexOf("/")==-1){
			fileName = appConfigHome+"/"+fileName;
		}
		//System.out.println(" capVideoConfigDir is : " + configPath);
		InputStream is = null;
		Properties prop = new Properties();
		
		try 
		{
			File file = new File(fileName);
			if(fileLastModifyMap.containsKey(fileName))
			{
				//文件是否修改
				if(fileLastModifyMap.get(fileName).equals(file.lastModified()))
				{//未修改
					return storeMap.get(fileName);
				}
				else
				{
					fileLastModifyMap.put(fileName, file.lastModified());
					is = new FileInputStream(fileName);
					prop.load(is);
					storeMap.put(fileName, prop);
				}
			}
			else
			{
				fileLastModifyMap.put(fileName, file.lastModified());
				is = new FileInputStream(fileName);
				prop.load(is);
				storeMap.put(fileName, prop);
			}

			return prop;
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 根据文件名和key加载参数
	 * @param fileName
	 * @param paramKey
	 * @return
	 */
	public static String getStringValue(String fileName , String paramKey)
	{
		Properties prop = loadProperties(fileName);
		if(null==prop)
		{
			return null;
		}
		return prop.getProperty(paramKey);
	}
	
	/**
	 * 根据文件名和key加载参数
	 * @param fileName
	 * @param paramKey
	 * @return
	 */
	public static Double getDoubleValue(String fileName , String paramKey)
	{
		Properties prop = loadProperties(fileName);
		if(null==prop)
		{
			return null;
		}
		String value = prop.getProperty(paramKey);
		return Double.valueOf(value);
	}
	
	/**
	 * 根据文件名和key加载参数
	 * @param fileName
	 * @param paramKey
	 * @return
	 */
	public static Integer getIntegerValue(String fileName , String paramKey)
	{
		Properties prop = loadProperties(fileName);
		if(null==prop)
		{
			return -1;
		}
		String value = prop.getProperty(paramKey);
		if(null==value)
		{
			return null;
		}
		return Integer.valueOf(value.trim());
	}
	
}
