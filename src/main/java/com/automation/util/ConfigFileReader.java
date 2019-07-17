package com.automation.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

	private Properties properties;
	private final String propertyFilePath=".//configuration.properties";

	public ConfigFileReader()
	{
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("configuration.properties not found at " + propertyFilePath);
		} 
	}

	public String getBrowser(){
		String browser = properties.getProperty("browser");
		if(browser!= null) 
			return browser;
		else throw new RuntimeException("driverPath not specified in the configuration.properties file."); 
	}

	public String getDriverPath() { 
		String driverPath = properties.getProperty("driverPath");
		if(driverPath != null) 
			return driverPath;
		else throw new RuntimeException("driverPath not specified in the configuration.properties file."); 
	}

	public String getApplicationUrl() {
		String appUrl = properties.getProperty("appUrl");
		if(appUrl != null) 
			return appUrl;
		else throw new RuntimeException("app url not specified in the Configuration.properties file.");
	}
	
	public long getWaitForPageToLoad() {
		String waitForPageToLoadTime = properties.getProperty("waitForPageToLoadTime");
		long waitTime = Long.parseLong(waitForPageToLoadTime);
		if(waitTime != 0) 
			return waitTime;
		else throw new RuntimeException("waitForPageToLoadTime not specified in the Configuration.properties file.");
	}

}
