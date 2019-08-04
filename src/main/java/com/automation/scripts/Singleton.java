package com.automation.scripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.automation.util.ConfigFileReader;

public class Singleton {
	
	// create a private constructor
	// write a static method (getInstance) type of object of this singleton class
	//lazy intialization
	
	public static Singleton instanceDriver = null;

	static WebDriver driver;
	static ConfigFileReader configFileReader= new ConfigFileReader();
	private static String browser=configFileReader.getBrowser();
	
	private Singleton()
	{
		
	}
	
	public WebDriver loadBrowser() {
		
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
	
	public static Singleton getInstance()
	{
		
		if(instanceDriver == null)
			instanceDriver = new Singleton();
		return instanceDriver;
	}
	

}
