package practice.test;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.Comcast.baseTest.BaseClass;


public class InvoiceTest extends BaseClass {

	@Test(retryAnalyzer=com.Comcast.crm.Listners.RetryListenerImp.class)
	public void createInvoiceTest() {
		System.out.println("execute createInvoiceTest");
		String actTitle=driver.getTitle();
		
		System.out.println("step-1");
		System.out.println("step-2");
		Assert.assertEquals(actTitle, "Login");
		System.out.println("step-3");
		System.out.println("step-4");
	}
	
//	@Test
//	public void createInvoiceWithContactTest() {
//		System.out.println("execute createInvoiceWithContactTest");
//		System.out.println("step-1");
//		System.out.println("step-2");
//		System.out.println("step-3");
//		System.out.println("step-4");
//	}
}
