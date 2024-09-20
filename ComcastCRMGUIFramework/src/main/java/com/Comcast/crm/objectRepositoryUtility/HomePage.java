package com.Comcast.crm.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	public HomePage(WebDriver driver){
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}

	@FindBy(linkText="Organizations")
	private WebElement organizationEdit;
	
	@FindBy(linkText="More")
	private WebElement MoreLinkEdit;
	
	@FindBy(name="Campaigns")
	private WebElement CampaignEdit;

	@FindBy(xpath="//img[@src=\"themes/softed/images/user.PNG\"]" )
	private WebElement AdministratorEdit;
	
	
	@FindBy(linkText="Sign Out")
	private WebElement SignOutEdit;
	
	public WebElement getMoreLinkEdit() {
		return MoreLinkEdit;
	}

	

	public WebElement getCampaignEdit() {
		return CampaignEdit;
	}


	public WebElement getAdministratorEdit() {
		return AdministratorEdit;
	}

	public WebElement getSignOutEdit() {
		return SignOutEdit;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getOrganizationEdit() {
		return organizationEdit;
	}
	
	public void navigateToCampaignPage() {
		Actions act= new Actions(driver);
		act.moveToElement(MoreLinkEdit).perform();
		CampaignEdit.click();	
	}
	
	public void signOut() {
		Actions act=new Actions(driver);
		act.moveToElement(AdministratorEdit).perform();
		SignOutEdit.click();
		
	}
}
