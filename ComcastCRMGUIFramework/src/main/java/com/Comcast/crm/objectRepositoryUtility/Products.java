package com.Comcast.crm.objectRepositoryUtility;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Products {

	/*
	 * public WebElement getCreateProductImgBtn() { return createProductImgBtn; }
	 */

	@FindBy(xpath="//img[@alt='Create Product...']")
	private WebElement createProductImgBtn;
}

