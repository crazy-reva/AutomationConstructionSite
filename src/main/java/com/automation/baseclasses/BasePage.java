package com.automation.baseclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.automation.util.ConfigFileReader;

public class BasePage {

	static WebDriver driver;
	static ConfigFileReader configFileReader= new ConfigFileReader();
	private static String browser=configFileReader.getBrowser();
	
	public static WebDriver loadBrowser() {
		
		if(browser.equalsIgnoreCase("chrome"))
		{
			System.out.println(configFileReader.getDriverPath());
			System.setProperty("webdriver.chrome.driver",configFileReader.getDriverPath());
			driver=new ChromeDriver();
			
		}
		else if(browser.equalsIgnoreCase("IE"))
		{
			System.setProperty("webdriver.ie.driver",configFileReader.getDriverPath());
			driver=new InternetExplorerDriver();
			
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",configFileReader.getDriverPath());
			driver=new FirefoxDriver();
			
		}
		return driver;
	}

}
