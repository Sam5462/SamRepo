package com.comcast.crm.orgTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.Comcast.crm.generic.FileUtility.ExcelUtility;
import com.Comcast.crm.generic.FileUtility.FileUtility;
import com.Comcast.crm.generic.WebDriverUtility.JavaUtility;
import com.Comcast.crm.generic.WebDriverUtility.WebDriverUtility;
import com.Comcast.crm.objectRepositoryUtility.CreatingNewOrganizationsPage;
import com.Comcast.crm.objectRepositoryUtility.HomePage;
import com.Comcast.crm.objectRepositoryUtility.LoginPage;
import com.Comcast.crm.objectRepositoryUtility.OrganizationInformationPage;
import com.Comcast.crm.objectRepositoryUtility.OrganizationsPage;


public class DeleteOrgTest {

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
				
				String ORGNAME= eutil.getDataFromExcel("org", 10, 2)+jutil.getRandomNumber();
				
				
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
				LoginPage lp=new LoginPage(driver);
				lp.loginToApp(USERNAME, PASSWORD);
				
				//Step2:Navigate to organization module
				HomePage hp=new HomePage(driver);
				hp.getOrganizationEdit().click();
				
				
				//Step3:Click on create organization button
				OrganizationsPage org=new OrganizationsPage(driver);
				org.getCreateOrgEdit().click();
				
				//Step 4:Enter all the details and create new organization
				CreatingNewOrganizationsPage cno=new CreatingNewOrganizationsPage(driver);
				cno.createOrg(ORGNAME);
				
				//verify header msg expected result
				OrganizationInformationPage OIP=new OrganizationInformationPage(driver);
						if(OIP.getHeaderEdit().getText().contains(ORGNAME)) {
							System.out.println("Header msg is vetrified==PASS");
						}
						else {
							System.out.println("Header msg is not vetrified==FAIL");
						}
			
				
				//Verify Header orgName info Expected result
				if(OIP.getActOrgNameEdit().getText().equals(ORGNAME)) {
					System.out.println("Org Name is vetrified==PASS");
				}
				else {
					System.out.println("Org Name is not vetrified==FAIL");
				
				}
				
				
				
				  //Go Back to organization page 
				
				   hp.getOrganizationEdit().click();
				  
				  //search for Organization org.getSearchBoxEdit().sendKeys(ORGNAME);
				   org.getSearchBoxEdit().sendKeys(ORGNAME);
				  wutil.select(org.getSearchDropDownEdit(), "Organization Name");
				  org.getSearchNowButtonEdit().click();
				  
				  driver.findElement(By.xpath("//a[text()='"+ORGNAME+"']/../..//a[text()='del']")).click();
				 
				  wutil.switchToAlertAndAccept(driver);
				  
				
				//Step 5: Logout
				
				Thread.sleep(3000);
				hp.signOut();

				driver.quit();
	}
	

}
