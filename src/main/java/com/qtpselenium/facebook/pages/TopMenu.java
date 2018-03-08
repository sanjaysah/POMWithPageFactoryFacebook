package com.qtpselenium.facebook.pages;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TopMenu {
	
	WebDriver driver;
	ExtentTest test;
	
	public TopMenu(WebDriver driver, ExtentTest test){
		this.driver=driver;
		this.test=test;
	}
	
	public void goToSettings(){
		test.log(LogStatus.INFO, "From Top Menu, Settings page is Openend");
	}
	
	public void logOut(){
		test.log(LogStatus.INFO, "From Top Menu, Page Logged Out");
	}
	
	
	public void goToHomePage(){
		test.log(LogStatus.INFO, "From Top Menu, Home page is Openend");
	}
	
	public void goToProfilePage(){
		test.log(LogStatus.INFO, "From Top Menu, Profile page is Openend");
	}
	
	

}
