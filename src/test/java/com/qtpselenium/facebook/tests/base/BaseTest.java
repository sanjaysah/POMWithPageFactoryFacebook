package com.qtpselenium.facebook.tests.base;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.testng.Assert;

import com.qtpselenium.facebook.pages.util.ExtentManager;
import com.qtpselenium.facebook.pages.util.PFConstants;
import com.qtpselenium.facebook.pages.util.Xls_Reader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseTest {
	
	public WebDriver driver;
	public ExtentReports extent = ExtentManager.getInstance();
	public ExtentTest test;
	public Xls_Reader xls = new Xls_Reader(PFConstants.DATA_XLS_FILEPATH);
	
	public void init(String bType){
		//test = extent.startTest("test");
		test.log(LogStatus.INFO, "Browser Opening");
		if(bType.equals("chrome")){
			//Logs coming in Console mixing with code outputs
			System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY,"true");
			ChromeOptions opt = new ChromeOptions();
			opt.addArguments("--start-maximized");
			opt.addArguments("--disable-infobars");
			opt.addArguments("--disable-notifications");
			driver = new ChromeDriver(opt);
		}else if(bType.equals("firefox")){
			//Logs coming in Console mixing with code outputs
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"none");
			FirefoxOptions opt = new FirefoxOptions();
			//opt.addArguments("dom.webnotifications.enabled", false);//not correct
			ProfilesIni allProf = new ProfilesIni();
			FirefoxProfile myProf = allProf.getProfile("dev-edition-sanjay");
			myProf.setPreference("dom.webnotifications.enabled", false);//Notification Disable
			opt.setProfile(myProf);
			driver = new FirefoxDriver(opt);
		}else if(bType.equals("IE")){
			//Logs coming in Console mixing with code outputs
			System.setProperty(InternetExplorerDriverService.IE_DRIVER_SILENT_PROPERTY,"true");
			driver = new InternetExplorerDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		test.log(LogStatus.INFO, "Browser Opened");
	}
	
	public void reportFailure(String message){
		takeScreenshot();
		test.log(LogStatus.FAIL, "Profile Test Login Unsuccessfull");
		Assert.fail(message);
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
