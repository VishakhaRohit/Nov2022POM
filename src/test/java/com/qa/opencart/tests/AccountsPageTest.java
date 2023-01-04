package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.Errors;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Design Open Cart App : Account Page")
@Story("Open Cart Account Page Design with multiple features...")

public class AccountsPageTest extends BaseTest{

	@BeforeClass
	public void accSetUp()
	{
		accountsPage = loginPage.doLogin(prop.getProperty("userName"), prop.getProperty("password"));
	}
	
	@Description("Account Page Title Test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void getAccountPageTitleTest()
	{
		String actTitle = accountsPage.getAccountPageTitle();
		System.out.println("Act title : " + actTitle);
		Assert.assertEquals(actTitle,Constants.ACCOUNT_PAGE_TITLE,Errors.ACCOUNT_PAGE_TITLE_NOT_FOUND_ERROR_MSG);
	}
	
	@Description("Account Page search field Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 2)
	public void getSearchFieldExistTest()
	{
		Assert.assertTrue(accountsPage.isSearchFieldExist());
	}
	
	@Description("LogOut Link Test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 3)
	public void isLogOutLinkExistTest()
	{
		Assert.assertTrue(accountsPage.isLogOutLinkExist());
	}
	
	@Description("Account Section List Test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 4)
	public void getAccountsSectionListTest()
	{
		List<String> accountSecTextList = accountsPage.getAccountsPageSection();
		Assert.assertEquals(accountSecTextList, Constants.accountSecList());
	}
	
	@DataProvider
	public Object[][] productData()
	{
		return new Object[][] {
			{"Macbook"},
			{"iMac"},
			{"Samsung"}
		};
	}
	
	@Description("Do Search Test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 5,dataProvider = "productData")
	public void doSearchTest(String productName)
	{
		searchResultsPage = accountsPage.doSearch(productName);
		Assert.assertTrue(searchResultsPage.getProductListCount()>0);

	}
	
	@Description("Select Product Test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=6)
	public void selectProductTest()
	{
		searchResultsPage = accountsPage.doSearch("macBook");
		productInfoPage = searchResultsPage.selectProduct("MacBook");
	}
	
}
