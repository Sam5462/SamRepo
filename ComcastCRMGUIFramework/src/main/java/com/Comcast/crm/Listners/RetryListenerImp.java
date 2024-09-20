package com.Comcast.crm.Listners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListenerImp implements IRetryAnalyzer{

	int count=0;
	int limit=3;
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if(count<limit) {
			count++;
			return true;
		}
		return false;
	}
	
	
}
