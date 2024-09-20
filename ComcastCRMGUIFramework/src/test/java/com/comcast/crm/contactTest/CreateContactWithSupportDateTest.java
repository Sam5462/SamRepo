package com.comcast.crm.contactTest;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.Comcast.crm.generic.FileUtility.ExcelUtility;
import com.Comcast.crm.generic.FileUtility.FileUtility;
import com.Comcast.crm.generic.WebDriverUtility.JavaUtility;
import com.Comcast.crm.generic.WebDriverUtility.WebDriverUtility;

public class CreateContactWithSupportDateTest {

	public static void main(String[] args)throws Throwable {
		FileUtility futil=new FileUtility();
		ExcelUtility eutil=new ExcelUtility();
		JavaUtility jutil=new JavaUtility();
		WebDriverUtility wutil=new WebDriverUtility();
		
		
		//Fetch data from property file
				
		String BROWSER=futil.getdataFromPropertyFile("browser");
		String URL=futil.getdataFromPropertyFile("url");
		String USERNAME=futil.getdataFromPropertyFile("username");
		String PASSWORD=futil.getdataFromPropertyFile("password");
		
		//Fetch data from excel sheet

		String LASTNAME= eutil.getDataFromExcel("contact", 1, 3)+jutil.getRandomNumber();
		
		
		//Step1: login
		WebDriver driver=null;
		if(BROWSER.equals("chrome")) {
			driver=new ChromeDriver();
		}
		else if(BROWSER.equals("edge")) {
			driver=new EdgeDriver();
		}
		else if(BROWSER.equals("firefox")) {
			driver=new FirefoxDriver();
		}
		
		driver.get(URL);
		wutil.waitForPageaToLoad(driver);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//Step2:Navigate to contact module
		
		driver.findElement(By.linkText("Contacts")).click();
		
		//Step3:Click on create contact button
		driver.findElement(By.xpath("//img[@title=\"Create Contact...\"]")).click();
		
		//Step 4:Enter all the details and create new contact
		
		String startDate = jutil.getSystemDateYYYYDDMM();
		
		String endDate = jutil.getRequiredDateYYYYDDMM(30);
	
		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(startDate);
		
		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(endDate);
		
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
		
		//verify header msg expected result
		String Actualstartdate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
		
		if(Actualstartdate.equals(startDate)) {
			System.out.println(startDate+" information is verified==PASS");
		}
		else {
			System.out.println( startDate+" information is not verified==FAIL");
		}
		
		String Actualenddate = driver.findElement(By.id("dtlview_Support End Date")).getText();
		
		if(Actualenddate.equals(endDate)) {
			System.out.println(endDate+" information is verified==PASS");
		}
		else {
			System.out.println( endDate+" information is not verified==FAIL");
		}
		//Step 5: Logout
		
		Thread.sleep(3000);
		WebElement AdministratorIcon = driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
		
		Actions act=new Actions(driver);
		act.moveToElement(AdministratorIcon).perform();
		
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

	}

}
