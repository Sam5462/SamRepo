package com.comcast.crm.orgTest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.Comcast.baseTest.BaseClass;
import com.Comcast.crm.generic.WebDriverUtility.UtilityClassObject;
import com.Comcast.crm.objectRepositoryUtility.CreatingNewOrganizationsPage;
import com.Comcast.crm.objectRepositoryUtility.HomePage;
import com.Comcast.crm.objectRepositoryUtility.OrganizationInformationPage;
import com.Comcast.crm.objectRepositoryUtility.OrganizationsPage;
import com.aventstack.extentreports.Status;
@Listeners(com.Comcast.crm.Listners.ListImpClass.class)
public class CreateOrganizationTest extends BaseClass  {

	@Test(groups={"smokeTest"})
	public void createOrganizationTest() throws IOException, Exception{
	
				//Fetch data from excel sheet
				UtilityClassObject.getTest().log(Status.INFO, "Read data from excel");
				String ORGNAME= eutil.getDataFromExcel("org", 1, 2)+jutil.getRandomNumber();
				
				
			
				//Step2:Navigate to organization module
				UtilityClassObject.getTest().log(Status.INFO, "Navigate to organization page");

				HomePage hp=new HomePage(driver);
				hp.getOrganizationEdit().click();
				
				
				//Step3:Click on create organization button
				UtilityClassObject.getTest().log(Status.INFO, "click on create organization page");
				OrganizationsPage org=new OrganizationsPage(driver);
				org.getCreateOrgEdit().click();
				
				//Step 4:Enter all the details and create new organization
				UtilityClassObject.getTest().log(Status.INFO, "Create a new organization");
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
				
				
	}
	
	@Test(groups="regressionTest")
	public void createOrganizationWithIndustriesTest() throws IOException, Exception {

	
		
				//Fetch data from excel sheet		
				
				String ORGNAME= eutil.getDataFromExcel("Org", 4, 2)+jutil.getRandomNumber();
				String INDUSTRY= eutil.getDataFromExcel("Org", 4, 3);
				String TYPE= eutil.getDataFromExcel("Org", 4, 5);
				
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
				
	}
	
	@Test(groups="regressionTest")
	public void createOrganizationWithPhoneNumberTest() throws IOException, Exception {
	
		//Fetch data from property file
		
				//Fetch data from excel sheet
				
				
				String ORGNAME= eutil.getDataFromExcel("Org", 7, 2)+jutil.getRandomNumber();
				String PHONENO= eutil.getDataFromExcel("Org", 7, 4);
				
				
				//Step1: login
				
				//Step2:Navigate to organization module
				
				driver.findElement(By.linkText("Organizations")).click();
				
				//Step3:Click on create organization button
				driver.findElement(By.xpath("//img[@title=\"Create Organization...\"]")).click();
				
				//Step 4:Enter all the details and create new organization
				driver.findElement(By.name("accountname")).sendKeys(ORGNAME);
				driver.findElement(By.id("phone")).sendKeys(PHONENO);
				driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
				
				//verify header msg expected result
				String headerInfo = driver.findElement(By.xpath("//span[@class=\"dvHeaderText\"]")).getText();
				if(headerInfo.contains(ORGNAME)) {
					System.out.println(ORGNAME+" is created==PASS");
				}
				else {
					System.out.println(ORGNAME+" is not created==FAIL");
				}
				
				//Verify PhoneNO info Expected result
				String actPhoneNo = driver.findElement(By.id("dtlview_Phone")).getText();
				if(actPhoneNo.equals(PHONENO)) {
					System.out.println(PHONENO+" is verified==PASS");
				}
				else {
					System.out.println(PHONENO+" is not verified==FAIL");
				}
				//Step 5: Logout
			
				//close
				
	}
	

}
