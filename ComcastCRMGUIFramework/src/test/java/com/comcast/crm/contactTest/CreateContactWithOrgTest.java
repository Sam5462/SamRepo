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

public class CreateContactWithOrgTest {

	public static void main(String[] args) throws Throwable {
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
		String ORGNAME= eutil.getDataFromExcel("contact", 1, 2)+jutil.getRandomNumber();
		
		
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
		
		//Step2:Navigate to organization module
		
		driver.findElement(By.linkText("Organizations")).click();
		
		//Step3:Click on create organization button
		driver.findElement(By.xpath("//img[@title=\"Create Organization...\"]")).click();
		
		//Step 4:Enter all the details and create new organization
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME);
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
		
		//verify header msg expected result
		String headerInfo = driver.findElement(By.xpath("//span[@class=\"dvHeaderText\"]")).getText();
		if(headerInfo.contains(ORGNAME)) {
			System.out.println(ORGNAME+" is created==PASS");
		}
		else {
			System.out.println(ORGNAME+" is not created==FAIL");
		}
		
		
		
		
		
		//navigate To Contact module		
		
		
				driver.findElement(By.linkText("Contacts")).click();
				
				//Step3:Click on create contact button
				driver.findElement(By.xpath("//img[@title=\"Create Contact...\"]")).click();
				
				//Step 4:Enter all the details and create new contact
				driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
				driver.findElement(By.xpath("//input[@name=\"account_name\"]/..//img[@title=\"Select\"]")).click();
				
				//switch to child window
			wutil.switchToTabonURL(driver, "module=Accounts");
			  
			  driver.findElement(By.name("search_text")).sendKeys(ORGNAME);
			  driver.findElement(By.name("search")).click();
			  driver.findElement(By.linkText(ORGNAME)).click();
			  
			//switch to parent window
			  
			wutil.switchToTabonURL(driver, "Accounts&action");
			
		  //Save the organization page
				  
				driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
				
				
				//verify header msg expected result
				String headerInfo1 = driver.findElement(By.xpath("//span[@class=\"dvHeaderText\"]")).getText();
				if(headerInfo1.contains(LASTNAME)) {
					System.out.println(LASTNAME+" contact is created==PASS");
				}
				else {
					System.out.println(LASTNAME+" contact is not created==FAIL");
				}
				
				//Verify Header orgName info Expected result
				String actOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
				
				if(actOrgName.trim().equals(ORGNAME)) {
					System.out.println(ORGNAME+" is verified==PASS");
				}
				else {
					System.out.println(ORGNAME+" is not verified==FAIL");
				}
				//Step 5: Logout
				
				Thread.sleep(3000);
				WebElement AdministratorIcon = driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
				
				Actions act=new Actions(driver);
				act.moveToElement(AdministratorIcon).perform();
				
				driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
				
				//close
				driver.quit();

			}
	}

