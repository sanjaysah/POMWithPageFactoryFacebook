package com.qtpselenium.facebook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.qtpselenium.facebook.pages.LandingPage;
import com.qtpselenium.facebook.pages.LaunchPage;
import com.qtpselenium.facebook.pages.LoginPage;
import com.qtpselenium.facebook.pages.ProfilePage;
import com.qtpselenium.facebook.pages.util.PFConstants;
import com.qtpselenium.facebook.tests.base.BaseTest;
import com.relevantcodes.extentreports.LogStatus;

public class ProfileTest extends BaseTest {
	
	@Test
	public void profiletest(){
		test = extent.startTest("profileTest");
		test.log(LogStatus.INFO, "Browser Initialized");
		
		init("firefox");

		LaunchPage launchpage = new LaunchPage(driver, test);
		PageFactory.initElements(driver, launchpage);
		launchpage.getMenu().goToProfilePage();
		
		LoginPage loginpage = launchpage.goToLoginPage();
		loginpage.getMenu().logOut();
		
		Object obj = loginpage.loginUsrPass(PFConstants.LOGIN_USERNAME, PFConstants.LOGIN_PASSWORD);
		if(obj instanceof LandingPage){
			test.log(LogStatus.INFO, "Profile Test Login Successfull");
		}else{
			test.log(LogStatus.FAIL, "Profile Test Login Unsuccessfull");
			Assert.fail("Login Unsuccessfull");
		}
		
		LandingPage landingpage = (LandingPage)obj;
		landingpage.getMenu().goToSettings();
		
		ProfilePage profpage = landingpage.goToProfile();
		test.log(LogStatus.INFO, "Profile Page Opened");
		profpage.verifyTitle("Sanjay Kumar Facebook Profile Page");
		profpage.takeScreenshot();
		profpage.getMenu().goToHomePage();//HAS A relationship
		
		driver.quit();
		
	}
	
	@AfterMethod
	public void quit(){
		if(extent!=null){
			extent.endTest(test);
			extent.flush();
		}
	}
	
	
}
