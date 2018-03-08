package com.qtpselenium.facebook.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qtpselenium.facebook.pages.base.BasePage;
import com.qtpselenium.facebook.pages.util.PFConstants;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LandingPage extends BasePage{
	
	public LandingPage(WebDriver driver, ExtentTest test){
		super(driver,test);
	}
	
	@FindBy(xpath=PFConstants.XPATH_LANDINGPAGE_PROFILELINK)
	WebElement profileLink;
	
	public ProfilePage goToProfile(){
		test.log(LogStatus.INFO, "Clicking Profile Link");
		profileLink.click();
		test.log(LogStatus.INFO, "Profile Link Clicked");
		ProfilePage profilepage = new ProfilePage(driver, test);
		PageFactory.initElements(driver, profilepage);
		return profilepage;
	}
}
