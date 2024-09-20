package com.Comcast.crm.Listners;

import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.Comcast.baseTest.BaseClass;
import com.Comcast.crm.generic.WebDriverUtility.UtilityClassObject;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListImpClass implements ITestListener,ISuiteListener {
	ExtentReports report;
	ExtentTest test;
	
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		System.out.println("Report configuration");
		
		//Spark report Config
		String time = new Date().toString().replace(":", "_").replace(" ","_");
		ExtentSparkReporter spark=new ExtentSparkReporter("./AdvanceReports/reports_"+time+".html");
		spark.config().setDocumentTitle("CRM Test suit Result");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.STANDARD);
		
		//Add Env information and create Test
		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows 11");
		report.setSystemInfo("BROWSER", "Chrome11.2.5");
		
	}

	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		System.out.println("Report backup");
		report.flush();
	}

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("=========>"+result.getMethod().getMethodName()+"<========START=========");
		 test = report.createTest("createContactTest");
		 UtilityClassObject.setTest(test);
		 test.log(Status.INFO, result.getMethod().getMethodName()+"====>START<===");
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("=========>"+result.getMethod().getMethodName()+"<========END<=========");
		test.log(Status.PASS, result.getMethod().getMethodName()+"====>COMPLETED<====");
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
		String testName=result.getMethod().getMethodName();
		TakesScreenshot eDriver=(TakesScreenshot)BaseClass.sdriver;
		 String filepath = eDriver.getScreenshotAs(OutputType.BASE64);
		String time = new Date().toString().replace(":", "_").replace(" ","_");
					test.addScreenCaptureFromBase64String(filepath, result.getMethod().getMethodName()+" "+time);
					test.log(Status.FAIL, result.getMethod().getMethodName()+"====>FAIL<=====");
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
