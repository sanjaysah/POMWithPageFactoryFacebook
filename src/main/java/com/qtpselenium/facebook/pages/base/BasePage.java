package com.qtpselenium.facebook.pages.base;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.qtpselenium.facebook.pages.TopMenu;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BasePage {
	
	public WebDriver driver;
	public TopMenu menu;
	public ExtentTest test;
	
	public BasePage(){}
	
	public BasePage(WebDriver driver, ExtentTest test){
		this.driver=driver;
		this.test=test;
		menu=new TopMenu(driver,test);
		PageFactory.initElements(driver, menu);
	}
	
	public TopMenu getMenu() {
		return menu;
	}

	//generic function commonly used across pages
	
	public void verifyTitle(String expTitle) {
		//Webdriver code to verofy Title of Profile Page
		System.out.println(this.getClass().getName()+" openend and Title is as excpected");
		test.log(LogStatus.INFO, this.getClass().getName()+" openend and Title is as excpected");
	}
	
	public void takeScreenshot(){
		
		Date d = new Date();
		String imgFileName = d.toString().replace(":", "_").replace(" ", "_")+".jpg";
		String imgFilePath = System.getProperty("user.dir")+"\\Screenshot\\"+imgFileName;
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try{
			FileUtils.copyFile(src, new File(imgFilePath));
		}catch(IOException e){
			e.printStackTrace();
		}
		test.log(LogStatus.INFO, test.addScreenCapture(imgFilePath));
	}
	
}
