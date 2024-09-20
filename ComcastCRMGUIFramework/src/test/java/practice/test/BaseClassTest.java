package practice.test;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.Comcast.baseTest.BaseClass;

public class BaseClassTest extends BaseClass {

	@Test
	public void useDriverFromBC() {
		BaseClass b=new BaseClass();
		b.driver.findElement(By.xpath("//a[text()='Products']")).click();
	}
}
