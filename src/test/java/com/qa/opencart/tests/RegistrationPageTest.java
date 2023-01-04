 package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest{

	@BeforeClass
	public void doRegistration()
	{
		registrationPage = loginPage.goToRegistrationPage();
	}
	
	public String getRandomEmail()
	{
		Random random = new Random();
		String emailId = "nidhiksh" + random.nextInt(1000) + "@gmail.com";
		return emailId;
	}
	
	@DataProvider
	public Object[][] getDataFromExcelSheet()
	{
		return ExcelUtil.getData(Constants.REGISTRATION_SHEET);
	}
	
	@Test(dataProvider = "getDataFromExcelSheet")
	public void getRegistrationTest(String firstname,String lastname,String telephone,
									String password,String subscribe)
	{
		Assert.assertTrue(registrationPage.doRegistration(firstname,lastname,getRandomEmail(),telephone,password,subscribe));
	}
	
}
