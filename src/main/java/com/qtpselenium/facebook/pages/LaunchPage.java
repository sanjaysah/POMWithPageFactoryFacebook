package com.qtpselenium.facebook.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.qtpselenium.facebook.pages.base.BasePage;
import com.qtpselenium.facebook.pages.util.PFConstants;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LaunchPage extends BasePage{
	
	public LaunchPage(WebDriver driver, ExtentTest test){
		super(driver,test);
	}
	
	public LoginPage goToLoginPage(){
		test.log(LogStatus.INFO, "Opening Facebook URL");
		driver.get(PFConstants.FACEBOOK_URL);
		LoginPage loginpage = new LoginPage(driver, test);
		PageFactory.initElements(driver, loginpage);
		test.log(LogStatus.INFO, "Facebook URL Openend");
		return loginpage;
	}

}
