package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private By registerLink = By.linkText("Register");
	private By forgotPswdLink = By.linkText("Forgotten Password");
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath(" //input[@value='Login']");
	private By errorMsg = By.cssSelector("div.alert.alert-danger.alert-dismissible");
	
	@Step("getting login page title.....")
	public String getLoginPageTitle()
	{
		return driver.getTitle();
	}
	
	@Step("getting login page url...")
	public String getLoginPageUrl()
	{
		return driver.getCurrentUrl();
	}
	
	@Step("register link is exist or not...")
	public boolean isRegisterLinkExist()
	{
		return eleUtil.doIsDisplayed(registerLink);
	}
	
	@Step("	forgot password link is exist or not...")
	public boolean isForgotPswdLinkExist()
	{
		return eleUtil.doIsDisplayed(forgotPswdLink);
	}
	
	@Step("do login with username : {0} and password : {1}")
	public AccountsPage doLogin(String un,String pswd)
	{
		eleUtil.doSendKeys(emailId, un);
		eleUtil.doSendKeys(password, pswd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}
	
	public boolean doLoginWithWrongCredentials(String un,String pswd)
	{
		eleUtil.doSendKeys(emailId, un);
		eleUtil.doSendKeys(password, pswd);
		eleUtil.doClick(loginBtn);
		WebElement element = eleUtil.getElement(errorMsg);
		String errorMsg = element.getText();
		System.out.println("Error Message is : " + errorMsg);
		
		if(errorMsg.contains(Constants.ERROR_MESSAGE))
		{
			System.out.println("Login is not done...");
			return false;
		}
		return true;
	}
	
//	@Step("navigating to registration page...")
	public RegistrationPage goToRegistrationPage()
	{
		eleUtil.doClick(registerLink);
		return new RegistrationPage(driver);
	}
}
