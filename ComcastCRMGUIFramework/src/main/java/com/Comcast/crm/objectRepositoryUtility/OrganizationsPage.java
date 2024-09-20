package com.Comcast.crm.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage {
	
	WebDriver driver;
	public OrganizationsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	
	@FindBy(xpath="//img[@title='Create Organization...']")
	private WebElement createOrgEdit;
	
	@FindBy(xpath="//input[@name='search_text']")
	private WebElement SearchBoxEdit;
	
	@FindBy(xpath="//select[@id='bas_searchfield']")
	private WebElement SearchDropDownEdit;
	
	@FindBy(xpath="//input[@class='crmbutton small create']")
	private WebElement searchNowButtonEdit;
	
	public WebElement getSearchBoxEdit() {
		return SearchBoxEdit;
	}

	public WebElement getSearchDropDownEdit() {
		return SearchDropDownEdit;
	}

	public WebElement getSearchNowButtonEdit() {
		return searchNowButtonEdit;
	}

	public WebElement getCreateOrgEdit() {
		return createOrgEdit;
	} 
	
	public void clickOnCreateOrgButton() {
		createOrgEdit.click();
	}
}
