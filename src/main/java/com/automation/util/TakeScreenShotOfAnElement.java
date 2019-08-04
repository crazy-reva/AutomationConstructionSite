package com.automation.util;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;

import com.automation.baseclasses.BasePage;

public class TakeScreenShotOfAnElement {
	
	public TakeScreenShotOfAnElement(WebDriver driver) throws IOException
	{
		
		//search webElement on page
		WebElement logo = driver.findElement(By.partialLinkText("Sign in"));
		
		//Get entire page screenshot
		File screenshot= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		/*
		 * BufferedImage fullImg = ImageIO.read(screenshot);
		 * 
		 * //Get element location on the page org.openqa.selenium.Point point =
		 * logo.getLocation();
		 * 
		 * //Get width and height of an element int logoWidth =
		 * logo.getSize().getWidth(); int logoHeight = logo.getSize().getHeight();
		 * 
		 * //Crop the entire screenshot to get only element screenshot BufferedImage
		 * eleScreenshot = fullImg.getSubimage(point.getX(), point.getY(), logoWidth,
		 * logoHeight); ImageIO.write(eleScreenshot, "png", screenshot);
		 */
		
		//copy the element screenshot to disk
		File screenshotLocation = new File(".//TakeScreenshot.png");
		FileHandler.copy(screenshot, screenshotLocation);
		
		
	}
	
	public static void main(String args[]) throws IOException
	{
		WebDriver driver;
		driver = BasePage.loadBrowser();
		driver.get("http://www.google.com");
		driver.manage().window().maximize();
		
		TakeScreenShotOfAnElement ts=new TakeScreenShotOfAnElement(driver);
		driver.quit();
	}

}
