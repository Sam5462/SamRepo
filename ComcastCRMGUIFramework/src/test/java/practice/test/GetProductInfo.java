package practice.test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Comcast.crm.generic.FileUtility.ExcelUtility;

public class GetProductInfo {

	@Test(dataProvider = "getData")
	public void geProductInfo(String brandName, String productName) {
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.amazon.in/");
		
		//Search Product
		driver.findElement(By.name("field-keywords")).sendKeys(brandName,Keys.ENTER);
		
		//Capture Product Information
		String price=driver.findElement(By.xpath("//span[text()='"+productName+"']/ancestor::div[@class='puisg-col-inner']/descendant::span[@class='a-price-whole']")).getText();
		//span[text()='"+productName+"']/../../../../div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/a/span/span[2]/span[2]
		//
		System.out.println(price);
		
		driver.quit();
	}
	
	@DataProvider
	public Object[][] getData() throws Throwable {
		
		ExcelUtility eLib=new ExcelUtility();
		int rowCount = eLib.getRowCount("product");
		System.out.println("row count is: "+rowCount);
		Object[][] objArr=new Object[rowCount][2];
		
		for(int i=0;i<rowCount;i++) {
		objArr[i][0]=eLib.getDataFromExcel("product", i+1, 0);
		objArr[i][1]=eLib.getDataFromExcel("product", i+1, 1);
		}
	
		//Samsung Galaxy S21 FE 5G (Olive, 8GB RAM, 256GB Storage) with Snapdragon 888 Processor
		
		return objArr;
	}
	
	
}
