package com.Comcast.baseTest;


import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.Comcast.crm.generic.FileUtility.ExcelUtility;
import com.Comcast.crm.generic.FileUtility.FileUtility;
import com.Comcast.crm.generic.WebDriverUtility.JavaUtility;
import com.Comcast.crm.generic.WebDriverUtility.UtilityClassObject;
import com.Comcast.crm.generic.WebDriverUtility.WebDriverUtility;
import com.Comcast.crm.generic.databaseUtility.DataBaseUtility;
import com.Comcast.crm.objectRepositoryUtility.HomePage;
import com.Comcast.crm.objectRepositoryUtility.LoginPage;

public class BaseClass {

	public WebDriver driver;
	public static WebDriver sdriver;
	
	public ExcelUtility eutil=new ExcelUtility();
	public JavaUtility jutil=new JavaUtility();
	public WebDriverUtility wutil=new WebDriverUtility();
	public DataBaseUtility dbLib=new DataBaseUtility();
	public FileUtility fuLib=new FileUtility();
	
	

	
	@BeforeSuite(groups={"smokeTest","regressionTest"})
	public void configBS() throws SQLException {
		System.out.println("====Connect to DB, Report config=====");
		dbLib.getDbconnection();
	}
	
	//@Parameters("BROWSER")
	@BeforeClass(groups={"smokeTest","regressionTest"})
	public void configBC() throws Throwable {
		System.out.println("Launch the Browser");
		
		String BROWSER =fuLib.getdataFromPropertyFile("browser");
		
		if(BROWSER.equals("chrome")) {
			driver=new ChromeDriver();
		}
		else if(BROWSER.equals("edge")) {
			driver=new EdgeDriver();
		}
		else if(BROWSER.equals("firefox")) {
			driver=new FirefoxDriver();
		}
		sdriver=driver;
		UtilityClassObject.setDriver(driver);
	}
	@BeforeMethod(groups={"smokeTest","regressionTest"})
	public void configBM() throws Throwable {
		System.out.println("==Login==");
		
		String URL=fuLib.getdataFromPropertyFile("url");
		String USERNAME=fuLib.getdataFromPropertyFile("username");
		String PASSWORD=fuLib.getdataFromPropertyFile("password");
		  	LoginPage lpLib= new LoginPage(driver);
		lpLib.loginToApp(URL, USERNAME, PASSWORD);
	}
	@AfterMethod(groups={"smokeTest","regressionTest"})
	public void configAM() throws InterruptedException {
		System.out.println("==Logout==");
		HomePage hp=new HomePage(driver);
		Thread.sleep(3000);
		hp.signOut();
	}
	
	@AfterClass(groups={"smokeTest","regressionTest"})
	public void configAC() {
		System.out.println("Close The Browser");
		driver.quit();
	}
	
	@AfterSuite(groups={"smokeTest","regressionTest"})
	public void configAS() {
		System.out.println("==close DB connection====");
	}
	
}
