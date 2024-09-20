package com.comcast.crm.contactTest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Comcast.baseTest.BaseClass;

public class CreateContactTest extends BaseClass {

	@Test(groups="smokeTest")
	
	public void createContactTest() throws Throwable {
		//Fetch data from property file
		//No Need Since Browser Launch and login is Taking place from Base Class
		
		//Fetch data from excel sheet
		
		String LASTNAME= eutil.getDataFromExcel("contact", 1, 3)+jutil.getRandomNumber();
		
		//Step1: login
		//Taking Place from Base Class
		
		//Step2:Navigate to contact module
		
		driver.findElement(By.linkText("Contacts")).click();
		
		//Step3:Click on create contact button
		driver.findElement(By.xpath("//img[@title=\"Create Contact...\"]")).click();
		
		//Step 4:Enter all the details and create new contact
		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
		
		//verify header msg expected result
		String headerInfo = driver.findElement(By.xpath("//span[@class=\"dvHeaderText\"]")).getText();
		boolean status = headerInfo.contains(LASTNAME);
		Assert.assertEquals(status, true);
		
		//Verify Header orgName info Expected result
		String actLastName = driver.findElement(By.id("mouseArea_Last Name")).getText().trim();
		
		SoftAssert sa=new SoftAssert();
		sa.assertEquals(actLastName, LASTNAME);
		//Step 5: Logout
			//Taking Plce from Base Class
		
		 //close driver.quit();
			//Taking Plce from Base Class
		
	}
	
	@Test(groups="regressionTest")
	public void createContactWithSupportDateTest() throws IOException, Exception {
		
		
		//Fetch data from property file
				
		
		//Fetch data from excel sheet

		String LASTNAME= eutil.getDataFromExcel("contact", 1, 3)+jutil.getRandomNumber();
		
		
		//Step1: login
		
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
	


	}
	
	@Test(groups="regressionTest")
	public void createContactWithOrgTest() throws IOException, Exception
	{
		
		//Fetch data from excel sheet
		
		String LASTNAME= eutil.getDataFromExcel("contact", 1, 3)+jutil.getRandomNumber();
		String ORGNAME= eutil.getDataFromExcel("contact", 1, 2)+jutil.getRandomNumber();
		
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
	
				//close
	}

}
