package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class ProductInfoPageTest extends BaseTest{

	@BeforeClass
	public void productSetUp()
	{
		accountsPage = loginPage.doLogin(prop.getProperty("userName"), prop.getProperty("password"));
	}
	
	@Test(priority=1)
	public void getImageCountTest()
	{
		searchResultsPage = accountsPage.doSearch("MacBook");
		productInfoPage = searchResultsPage.selectProduct("MacBook");
		Assert.assertEquals(productInfoPage.getImageCount(), Constants.MACBBOK_IMAGE_COUNT);
	}
	
	@Test(priority=2)
	public void getProductInfoTest()
	{
		searchResultsPage = accountsPage.doSearch("MacBook");
		productInfoPage = searchResultsPage.selectProduct("MacBook");
		Map<String,String> productData = productInfoPage.getProductInfo();
		productData.forEach((k,v)->System.out.println(k + " : " + v));
		softAssert.assertEquals(productData.get("Brand"), "Apple");
		softAssert.assertEquals(productData.get("Product Code"), "Product 16");
		softAssert.assertEquals(productData.get("price"), "$602.00");
		softAssert.assertAll();
	}
	
}
