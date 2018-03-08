package com.qtpselenium.facebook.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qtpselenium.facebook.pages.base.BasePage;
import com.qtpselenium.facebook.pages.util.PFConstants;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LoginPage extends BasePage{
	
	@FindBy(xpath=PFConstants.XPATH_EMAIL)
	WebElement email;
	
	@FindBy(xpath=PFConstants.XPATH_PASSWD)
	WebElement passwd;
	
	public LoginPage(WebDriver driver, ExtentTest test){
		super(driver,test);
	}
	
	public Object loginUsrPass(String Uname,String Passwd){
		test.log(LogStatus.INFO, "Entering Username and Password");
		email.sendKeys(Uname);
		test.log(LogStatus.INFO, "Email id/Username typed");
		passwd.sendKeys(Passwd);
		test.log(LogStatus.INFO, "Password Typed");
		passwd.sendKeys(Keys.ENTER);
		//Validation whether login was succes or not .e.g. verifyTitle correct then loginSuccess=true else false
		boolean loginSuccess = true;
		if(loginSuccess==true){
			LandingPage landingpage = new LandingPage(driver, test);
			PageFactory.initElements(driver, landingpage);
			test.log(LogStatus.INFO, "Login Successfull");
			return landingpage;
		}else{
			LoginPage loginpage = new LoginPage(driver, test);
			PageFactory.initElements(driver, loginpage);
			test.log(LogStatus.INFO, "Login Unsuccessfull");
			return loginpage;
		}
	}
}
