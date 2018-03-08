package com.qtpselenium.facebook.pages.util;

import java.io.File;
import java.util.Date;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	
	private static ExtentReports extent;
	
	public static ExtentReports getInstance(){
		if(extent==null){
			Date d = new Date();
			String filePath = d.toString().replace(":", "_").replace(" ", "_")+".html";
			extent = new ExtentReports(System.getProperty("user.dir")+"\\reports\\"+filePath, true, DisplayOrder.NEWEST_FIRST);
			extent.loadConfig(new File(System.getProperty("user.dir")+"\\ReportsConfig.xml"));
			//Optional
			extent.addSystemInfo("Selenium Version", "3.9.1").addSystemInfo("Build Management Tool", "MAVEN").addSystemInfo("Framework Model", "POM with Page Factory");
		}
		
		return extent;
	}

}
