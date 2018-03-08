package com.qtpselenium.facebook.tests;

import java.util.Hashtable;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qtpselenium.facebook.pages.LandingPage;
import com.qtpselenium.facebook.pages.LaunchPage;
import com.qtpselenium.facebook.pages.LoginPage;
import com.qtpselenium.facebook.pages.util.PFConstants;
import com.qtpselenium.facebook.tests.base.BaseTest;
import com.relevantcodes.extentreports.LogStatus;

public class LoginTest extends BaseTest{
	
	String testCaseName=PFConstants.TESTCASE_NAME;
	
	@Test(dataProvider="getDataLoginTest")
	public void loginTest(Hashtable<String,String> data){
		test = extent.startTest("loginTest");
		if(data.get(PFConstants.RUNMODE_COL).equals("N")){
			test.log(LogStatus.INFO, "TC Skipped as Runmode is No");
			throw new SkipException("TC Skipped as Runmode is No");
		}
		test.log(LogStatus.INFO, "Browser Initialized");
		
		init(data.get("Browser"));

		LaunchPage launchpage = new LaunchPage(driver, test);
		PageFactory.initElements(driver, launchpage);
		
		LoginPage loginpage = launchpage.goToLoginPage();
		test.log(LogStatus.INFO, "Facebook Login page opened");
		Object page = loginpage.loginUsrPass(data.get("Username"), data.get("Password"));
		if(page instanceof LandingPage){
			test.log(LogStatus.INFO, "Profile Test Login Successfull");
		}else{
			reportFailure("Login Unsuccessfull");
		}
		loginpage.takeScreenshot();
		test.log(LogStatus.PASS, "Login Test Passed");
		driver.quit();
	}
	
	@DataProvider
	public Object[][] getDataLoginTest(){
		
		String sheetName = PFConstants.DATAXLS_SHEETNAME;
		//Find Row No from where Test Case details starts
		int testStartRowNo = 1;
		while(!xls.getCellData(sheetName, 0, testStartRowNo).equals(testCaseName)){
			testStartRowNo++;
		}
		int colStartRowNo = testStartRowNo+1;
		int dataStartRowNo = testStartRowNo+2;
		//System.out.println(colStartRowNo+" - "+dataStartRowNo);
		//Count No of rows of Data in mentioned TC
		int rows=0;
		while(!xls.getCellData(sheetName, 0, dataStartRowNo+rows).equals("")){
			rows++;
		}
		//System.out.println("No of rows of Data are - "+rows);
		//Count No of rows of Data in mentioned TC
		int cols=0;
		while(!xls.getCellData(sheetName, cols, colStartRowNo).equals("")){
			cols++;
		}
		//System.out.println("No of cols of Data are - "+cols);
		Object[][] data = new Object[rows][1];
		Hashtable<String, String> table;
		int dataRow=0;
		for(int rNum=dataStartRowNo;rNum<dataStartRowNo+rows;rNum++){
			table=new Hashtable<String, String>();
			for(int cNum=0;cNum<cols;cNum++){
				String key = xls.getCellData(sheetName, cNum, colStartRowNo);
				String value = xls.getCellData(sheetName, cNum, rNum);
				//System.out.println("Row No - "+rNum+" Colm No - "+cols+" - Data is - "+data);
				// OR you can store data into 2 dimensional array from HashTable
				table.put(key, value);
			}
			data[dataRow][0]=table;
			dataRow++;
		}
		return data;
	}
	
	@AfterMethod
	public void quit(){
		if(extent!=null){
			extent.endTest(test);
			extent.flush();
		}
	}

}
