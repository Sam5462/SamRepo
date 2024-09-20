package com.Comcast.crm.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewOrganizationsPage {

	WebDriver driver;
	
	public CreatingNewOrganizationsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
			this.driver=driver;
	}
	
	@FindBy(name="accountname")
	private WebElement orgNameEdit;
	
	@FindBy(xpath="//input[@class=\"crmbutton small save\"]")
	private WebElement saveButtonEdit;
	
	@FindBy(name="industry")
	private WebElement industryDDEdit;

	public WebElement getOrgNameEdit() {
		return orgNameEdit;
	}

	public WebElement getIndustryDDEdit() {
		return industryDDEdit;
	}

	public WebElement getSaveButtonEdit() {
		return saveButtonEdit;
	}
	
	public void createOrg(String name) {
		orgNameEdit.sendKeys(name);
		saveButtonEdit.click();
	}
	
	public void createOrg(String name, String industry) {
		orgNameEdit.sendKeys(name);
		Select se= new Select(industryDDEdit);
		se.selectByVisibleText(industry);
		saveButtonEdit.click();
	}
	
	
}
