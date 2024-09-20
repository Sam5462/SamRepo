package practice.test;


import org.testng.annotations.Test;

import com.Comcast.baseTest.BaseClass;
import com.Comcast.crm.objectRepositoryUtility.LoginPage;

/**
 * test class for contact module
 * @author Hp
 *
 */
public class SearchContactTest extends BaseClass{
	/**
	 * Scanario: login()===> navigateContact===>createcontact()====>verify
	 * 
	 */
	@Test
	public void searchContactTest() {
		
		/*Login to application*/
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp("admin", "admin");
	}
}
