package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class NegativeLoginPageTest extends BaseTest{

	@DataProvider
	public Object[][] provideData()
	{
		return new Object[][]{
			{"dskjdhsfh@jgmail.com","Vishu@123"},
			{"vishu@gmail.com","Vishakha212"},
			{"vishu6@6787gmail.com","Vishakha212"},
			{"vishugmail.com","Vishakha212"}
		};
	}
	
	@Test(dataProvider="provideData")
	public void doNegativeLoginTest(String un,String pswd)
	{
		Assert.assertFalse(loginPage.doLoginWithWrongCredentials(un, pswd));
	}
	
}
