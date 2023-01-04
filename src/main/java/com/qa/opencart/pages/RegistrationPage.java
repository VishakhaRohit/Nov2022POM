package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegistrationPage {

//	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public RegistrationPage(WebDriver driver)
	{
//		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By emailId = By.id("input-email");
	private By telePhone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPswd = By.id("input-confirm");
	private By subScribeYes = By.xpath("(//input[@type='radio' and @name='newsletter'])[1]");
	private By subScribeNo = By.xpath("(//input[@type='radio' and @name='newsletter'])[2]");
	private By agreeCheckBox = By.xpath("//input[@type='checkbox' and @name='agree']");
	private By continueBtn = By.xpath("//input[@type='submit']");
	private By successMsg = By.xpath("//div[@id='content']//h1");
	private By logOutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
	public boolean doRegistration(String firstName,String lastName,String emailId,String telePhone,
								String password,String SubScribe)
	{
		eleUtil.doSendKeys(this.firstName, firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.emailId, emailId);
		eleUtil.doSendKeys(this.telePhone, telePhone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmPswd, password);
		
		if(SubScribe.equals("yes"))
		{
			eleUtil.doClick(subScribeYes);
		}
		else if(SubScribe.equals("no"))
		{
			eleUtil.doClick(subScribeNo);
		}
		eleUtil.doClick(agreeCheckBox);
		eleUtil.doClick(continueBtn);
		
		String text = eleUtil.waitForElementToBeVisible(successMsg, 10, 5000).getText();
		if(text.contains(Constants.REGISTRATION_SUCCESS_MSG))
		{
			eleUtil.doClick(logOutLink);
			eleUtil.doClick(registerLink);
			return true;
		}
		return false;
	}
	
}
