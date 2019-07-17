package com.automation.scripts;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.util.ConfigFileReader;
import com.automation.util.readLocationInfoExcel;
import com.automation.util.writeLocationInfoToExcel;

public class PageObjects {
	
	WebDriver driver;
	ConfigFileReader configFileReader=new ConfigFileReader();
	long waitTime=configFileReader.getWaitForPageToLoad();
	
	public PageObjects(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void clickOnViewOnMapButton()
	{	
		List<WebElement> viewOnMapButton=driver.findElements(By.tagName("a"));
		for(int i=0;i<viewOnMapButton.size();i++)
		{
			String text_on_map_Button=viewOnMapButton.get(i).getText();
			if(text_on_map_Button.equals("View the Map"))
			{
				viewOnMapButton.get(i).click();
				break;
			}
		}
	}
	
	public void waitForPageToLoad(WebElement elementToLoad)
	{
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.elementToBeClickable(elementToLoad));
	}
	
	public void loadMap() {
		
		//scroll into view and switch to frame
		
		WebElement iframe = driver.findElement(By.className("cw-map"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", iframe);
		driver.switchTo().frame(iframe);
		
		
	    WebElement mapToBeLoaded = driver.findElement(By.id("main-loading"));
		waitForPageToLoad(mapToBeLoaded);
		
	}
	
	public void handleForm()
	{
		driver.switchTo().activeElement();
		
		WebElement form= driver.findElement(By.id("bx-form-966264-step-1"));
		waitForPageToLoad(form);
		form.click();
		
		WebElement closeButton=driver.findElement(By.id("bx-close-inside-966264"));
		closeButton.click();
		
		WebElement iframe = driver.findElement(By.className("cw-map"));
		driver.switchTo().frame(iframe);
	}

	public void getAllThePinsOnMap() throws InterruptedException {
		
		Thread.sleep(20000);
		//WebDriverWait waitForAllPins = new WebDriverWait(driver, 60);
		List<WebElement> getAllPins = driver.findElements(By.xpath("//*[@id='map_layers']//following-sibling::*[@id='seattle_461_layer']//following-sibling::*"));
		//waitForAllPins.until(ExpectedConditions.visibilityOfAllElements(getAllPins));
		
		//System.out.println(getAllPins.isEmpty());
		//System.out.println("size of the pins:"+ getAllPins.size()); 
		int count=0;
		writeLocationInfoToExcel locationInfoToExcel = new writeLocationInfoToExcel();
		readLocationInfoExcel readLocationInfoExcel = new readLocationInfoExcel();
		if(count==0)
		{
			locationInfoToExcel.writeInfoToExcelMethod("Location",count);
		}
		
		count=1;
		int rowNumberFromWriteExcel=0;
	    for(int i=0;i<getAllPins.size();i++) 
	    { 
	    	
	    	String imageLinks=getAllPins.get(i).getAttribute("xlink:href");
	    	
	    	try {
	    	
		    	if(imageLinks.contains("https") && imageLinks!=null)
		    	{
		    		
			    	WebElement pin = getAllPins.get(i);
			    	Actions actions=new Actions(driver);
			    	actions.moveToElement(pin).click().perform();
			    	
			    	Thread.sleep(1000);
			    	WebElement popup = driver.findElement(By.xpath("//*[@class='esriViewPopup']//tbody/tr[1]/td[2]"));
					String LocationInfo=popup.getText();
					
					if(count==1)
					{
						rowNumberFromWriteExcel=locationInfoToExcel.writeInfoToExcelMethod(LocationInfo,count);
						count++;
					}
					else if(count>1)
					{
						if(readLocationInfoExcel.readInfoFromExcelMethod(LocationInfo, rowNumberFromWriteExcel))
						{
							rowNumberFromWriteExcel=locationInfoToExcel.writeInfoToExcelMethod(LocationInfo,count);
							count++;
						}
					}
					
			    	WebElement homeButton=driver.findElement(By.xpath("//div[@class='HomeButton']"));
			    	actions.moveToElement(homeButton).click().perform();
			    	System.out.println(imageLinks);
			    	System.out.println(LocationInfo);
		    	}
	    	}
	    	catch(Exception e)
	    	{
	    		System.out.println("Null or element with no link is found");
	    		continue;
	    	}
	    	
	    }
		
	}
	
}
