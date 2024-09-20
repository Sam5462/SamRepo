package com.comcast.crm.orgTest;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.Comcast.crm.generic.FileUtility.ExcelUtility;
import com.Comcast.crm.generic.FileUtility.FileUtility;
import com.Comcast.crm.generic.WebDriverUtility.JavaUtility;
import com.Comcast.crm.generic.WebDriverUtility.WebDriverUtility;

public class CreateOrganizationWithIndustriesTest2 {

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
				
				
				
				String ORGNAME= eutil.getDataFromExcel("Org", 4, 2)+jutil.getRandomNumber();
				String INDUSTRY= eutil.getDataFromExcel("Org", 4, 3);
				String TYPE= eutil.getDataFromExcel("Org", 4, 5);
				
				
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
				Select sel1=new Select(driver.findElement(By.name("industry")));
				sel1.selectByVisibleText(INDUSTRY);
				
				Select sel2=new Select(driver.findElement(By.name("accounttype")));
				sel2.selectByVisibleText(TYPE);
				
				driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
				
				
				
				//Verify the industries and type info
				String actIndustryName = driver.findElement(By.id("mouseArea_Industry")).getText();
				if(actIndustryName.equals(INDUSTRY)) {
					System.out.println(INDUSTRY+" Information is verified==PASS");
				}
				else {
					System.out.println(INDUSTRY+" is information not verified==FAIL");
				}
				
				String actType = driver.findElement(By.id("mouseArea_Type")).getText();
				if(actType.equals(TYPE)) {
					System.out.println(TYPE+" Information is verified==PASS");
				}
				else {
					System.out.println(TYPE+" is information not verified==FAIL");
				}
				//Step 5: Logout
				
				Thread.sleep(3000);
				WebElement AdministratorIcon = driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
				
				Actions act=new Actions(driver);
				act.moveToElement(AdministratorIcon).perform();
				
				driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

	}

}
