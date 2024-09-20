package com.Comcast.crm.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Comcast.crm.generic.WebDriverUtility.WebDriverUtility;
/**
 * 
 * @author Hp
 *contains login page elements & business lib like login()
 */
public class LoginPage extends WebDriverUtility {
	
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name="user_name")
	private WebElement usernameEdit;
	
	@FindBy(name="user_password")
	private WebElement passwordEdit;
	
	@FindBy(id="submitButton")
	private WebElement loginButton;

	public WebElement getUsernameEdit() {
		return usernameEdit;
	}

	public WebElement getPasswordEdit() {
		return passwordEdit;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}
	
	/**
	 * login to application based on username and password argument
	 * @param username
	 * @param password
	 */ 
	public void loginToApp(String username, String password) {
		driver.manage().window().maximize();
		usernameEdit.sendKeys(username);
		passwordEdit.sendKeys(password);
		loginButton.click();
	}
	/**
	 * login to application based on url, username and password argument
	 * @param url
	 * @param username
	 * @param password
	 */
	public void loginToApp(String url ,String username, String password) {
		waitForPageaToLoad(driver);
		driver.get(url);
		usernameEdit.sendKeys(username);
		passwordEdit.sendKeys(password);
		loginButton.click();
	}
}
