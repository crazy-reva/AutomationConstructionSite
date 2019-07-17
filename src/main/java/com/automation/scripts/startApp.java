package com.automation.scripts;

import org.openqa.selenium.WebDriver;

import com.automation.baseclasses.BasePage;
import com.automation.util.ConfigFileReader;

public class startApp {

	public static void main(String[] args) throws InterruptedException {
		
		ConfigFileReader configFileReader=new ConfigFileReader();
		
		WebDriver driver;
		driver = BasePage.loadBrowser();
		driver.get(configFileReader.getApplicationUrl());
		driver.manage().window().maximize();
		
		PageObjects pageObjects=new PageObjects(driver);
		
		//click on View Map button
		pageObjects.clickOnViewOnMapButton();
		
		//Load Map
		pageObjects.loadMap();

		//read pin information
	    pageObjects.getAllThePinsOnMap();
		
		driver.quit();

	}

}
