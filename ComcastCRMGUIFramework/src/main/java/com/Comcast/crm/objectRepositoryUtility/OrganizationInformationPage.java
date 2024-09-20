package com.Comcast.crm.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInformationPage {

	WebDriver driver;
	public OrganizationInformationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	
	@FindBy(xpath="//span[@class=\"dvHeaderText\"]")
	private WebElement headerEdit;
	
	@FindBy(id="dtlview_Organization Name")
	private WebElement actOrgNameEdit;
	public WebElement getHeaderEdit() {
		return headerEdit;
	}

	public WebElement getActOrgNameEdit() {
		return actOrgNameEdit;
	}
	
	public void verifyHeader(String orgName) {
		String headerMsg = headerEdit.getText();
		if(headerMsg.contains(orgName)){
			System.out.println("Header msg is Verified==PASS");
		}
		else {
			System.out.println("header msg is not verified==FAIL");
		}
	}
	
	public void verifyOrgName(String orgName) {
		String actorgName = actOrgNameEdit.getText();
		if(actorgName.contains(orgName)){
			System.out.println("OrgName  is Verified==PASS");
		}
		else {
			System.out.println("OrgName msg is not verified==FAIL");
		}
	}
}
