package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {

	private WebDriver driver;
	private JavaScriptUtil jsUtil;

	//ctrl + O
	
	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil = new JavaScriptUtil(driver);
	}

	public By getBy(String locatorType, String locatorValue) {
		By locator = null;

		switch (locatorType) {
		case "id":
			locator = By.id(locatorValue);
			break;
		case "name":
			locator = By.name(locatorValue);
			break;
		case "className":
			locator = By.className(locatorValue);
			break;
		case "xpath":
			locator = By.xpath(locatorValue);
			break;
		case "cssSelector":
			locator = By.cssSelector(locatorValue);
			break;
		case "linkText":
			locator = By.linkText(locatorValue);
			break;
		case "partialLinkText":
			locator = By.partialLinkText(locatorValue);
			break;
		default:
			System.out.println("Please pass the right locator...");
			break;
		}
		return locator;
	}

	public WebElement getElement(By locator) {
		
		WebElement element =  driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highLight))
		{
			jsUtil.flash(element);
		}
		return element;
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public WebElement getElement(String locatorType, String locatorValue) {
		return driver.findElement(getBy(locatorType, locatorValue));
	}

	public void doSendKeys(By locator, String value) {
		getElement(locator).clear();
		getElement(locator).sendKeys(value);
	}

	public void doSendKeys(String locatorType, String locatorValue, String value) {
		getElement(locatorType, locatorValue).sendKeys(value);
	}
	public void doSendKeys(By locator,String value,int timeOut)
	{
		doPresenceOfElementLocated(locator, timeOut).sendKeys(value);
	}
	public void doSendkeys(By locator,String value,int timeOut,long intervalTime)
	{
		doPresenceOfElementLocated(locator, timeOut, intervalTime).sendKeys(value);
	}
	
	public void doClick(By locator) {
		getElement(locator).click();
	}

	public void doClick(String loactorType, String locatorValue) {
		getElement(loactorType, locatorValue).click();
	}
	
	public void doClick(By locator,int timeOut,long intervalTime)
	{
		doPresenceOfElementLocated(locator, timeOut,intervalTime).click();
	}
	
	public void doClick(By locator,int timeOut)
	{
		doPresenceOfElementLocated(locator, timeOut).click();
	}
	
	public void doClear(By locator)
	{
		getElement(locator).clear();
	}
	
	public boolean doIsEnabled(By locator) {
		return getElement(locator).isEnabled();
	}

	public String doGetText(By locator) {
		return getElement(locator).getText();
	}
	
	public boolean doIsDisplayed(By locator)
	{
		return getElement(locator).isDisplayed();
	}

	
	
	public boolean isElementExist(By locator)
	{
		int elementCount = getElements(locator).size();
		if(elementCount>=1)
		{
			System.out.println("Element is present on the page");
			return true;
		}
		else
		{
			System.out.println("Element is not present on the page");
			return false;
		}
	}
	
	public String getAttributeValue(By locator,String attrName)
	{
		String attrValue = getElement(locator).getAttribute(attrName);
		System.out.println("Attribute Value ; " + attrValue);
		return attrValue;
	}
	
	public List<String> getElementTextList(By locator) {
		List<WebElement> eleList = getElements(locator);
		List<String> eleTextList = new ArrayList<String>();
		for (WebElement e : eleList) {
			String text = e.getText();
			if (!text.isEmpty()) {
				eleTextList.add(text);
			}
		}
		return eleTextList;
	}
	public List<String> getAttributeTextList(By locator, String attName) {
		List<WebElement> eleList = getElements(locator);
		List<String> eleTextList = new ArrayList<String>();
		for (WebElement e : eleList) {
			String text = e.getAttribute(attName);
			eleTextList.add(text);
		}
		return eleTextList;
	}
	
	//*****************************do drop down select************************************//
	
	public void doDropDownSelectByIndex(By locator,int index)
	{
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}
	public void doDropDownSelectByValue(By locator,String value)
	{
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}
	public void doDropDownSelectByVisibleText(By locator,String text)
	{
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);
	}
	public void doSelectDropDownValueWithoutSelectMethod(By locator,String value)
	{
		Select select = new Select(getElement(locator));
		List<WebElement> countryList = select.getOptions();
		
		for(WebElement e : countryList)
		{
			String text = e.getText();
			if(text.equals(value))
			{
				e.click();
				break;
			}
		}
	}
	public void selectDropDownWithoutUsingSelectClass(By locator,String value) {
		List<WebElement> countryList = driver.findElements(locator);

		for (WebElement e : countryList) {
			String text = e.getText();
			if (text.equals(value)) {
				e.click();
				break;
			}
		}
	}
	
	/******************************Links Utils*******************************************/
	
	public List<String> getLinksTextList(By locator)
	{
		List<WebElement> languageList = getElements(locator);
		List<String> languageTextList = new ArrayList<String>();
		for(WebElement e:languageList)
		{
			String text = e.getText().trim();
			languageTextList.add(text);
		}
		return languageTextList;
	}
	
	
	public void clickOnElement(By locator,String value) {
		List<WebElement> languageList = getElements(locator);

		System.out.println(languageList.size());

		for (WebElement e : languageList) {
			String text = e.getText().trim();
			System.out.println(text);
			if(text.equals(value))
			{
				e.click();
				break;
			}
		}
	}
	
	/*****************************Web Table Utils***************************/
	public void printTable(int rowCount,int columnCount,String beforeXpath,String afterXpath)
	{
		for(int i = 2 ; i<=rowCount ; i++)
		{
			for(int j = 1;j<=columnCount ; j++)
			{
				String xpath = beforeXpath + i + afterXpath + j + "]";
				String text = doGetText(By.xpath(xpath));
				System.out.print(text + " - ");
			}
			System.out.println();
		}
	}
	
	/**********************Actions Util**************************************/
	
	public void doClickOnChildMenu(By parentMenu, By childMenu) throws InterruptedException
	{
		doMoveToElement(parentMenu);
		Thread.sleep(3000);
		driver.findElement(childMenu).click();
	}
	public void doMoveToElement(By locator) throws InterruptedException {
		Actions act = new Actions(driver);
		act.moveToElement(getElement(locator)).perform();
	}
	public void doActionsSendKeys(By locator,String value)
	{
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).build().perform();		
	}
	public void doActionsClickSendKeys(By locator,String value)
	{
		Actions act = new Actions(driver);
		act.click(getElement(locator)).sendKeys(value).build().perform();
	}
	public void doActionsClick(By locator) 
	{
		Actions act = new Actions(driver);
		act.click(getElement(locator)).build().perform();
	}
	public void doActionsMoveToElementClick(By locator)
	{
		Actions act = new Actions(driver);
		act.moveToElement(getElement(locator)).click().build().perform();
	}
	
	/*****************************Wait Utils****************************************/
	public WebElement doPresenceOfElementLocated(By locator,int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	public WebElement doPresenceOfElementLocated(By locator,int timeOut,long intervalTime)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut),Duration.ofMillis(intervalTime));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	public WebElement waitForElementToBeVisible(By locator,int timeOut,long intervalTime)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut),Duration.ofMillis(intervalTime));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	public List<WebElement> waitForElementsToBeVisisble(By locator,int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	public List<WebElement> waitForElementsToBeVisisble(By locator,int timeOut,long intervalTime)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut),Duration.ofSeconds(intervalTime));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	public List<String> getElementsTextListWithWait(By locator,int timeOut)
	{
		List<WebElement> linksList = waitForElementsToBeVisisble(locator, timeOut);
		List<String> linksTextList = new ArrayList<String>();
		for(WebElement e : linksList)
		{
			String text = e.getText();
			linksTextList.add(text);
		}
		return linksTextList;
	}
	public List<String> getElementsTextListWithWait(By locator,int timeOut,long intervalTime)
	{
		List<WebElement> linksList = waitForElementsToBeVisisble(locator, timeOut,intervalTime);
		List<String> linksTextList = new ArrayList<String>();
		for(WebElement e : linksList)
		{
			String text = e.getText();
			linksTextList.add(text);
		}
		return linksTextList;
	}
	
	/***********************Wait Util for Non WebElement*****************************************/
	
	public boolean waitForUrlToContain(String urlFraction,int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.urlContains(urlFraction));
	}
	public boolean waitForUrlToBe(String url,int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.urlToBe(url));
	}
	
	public boolean waitForTitleContain(String titleFraction,int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.titleContains(titleFraction));
	}
	
	public boolean waitForTitleToBe(String title,int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.titleIs(title));
	}
	
	public Alert waitForAlert(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public void doAlertAccept(int timeOut)
	{
		waitForAlert(timeOut).accept();
	}
	public void doAlertDismiss(int timeOut)
	{
		waitForAlert(timeOut).dismiss();
	}
	public String doAlertGetText(int timeOut)
	{
		return waitForAlert(timeOut).getText();
	}
	public void alertSendKey(String value,int timeOut)
	{
		waitForAlert(timeOut).sendKeys(value);;
	}
	
	public void waitForFrameByNameOrId(String nameOrId,int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameOrId));
	}
	public void waitForFrameByIndex(int index,int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
	}
	public void waitForFrameByLocator(By locator,int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}
	public void waitForFrame(WebElement frameElement,int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}
	
	public void clickElementWhenReady(By locator,int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}
	public void clickElementWhenReady(By locator,int timeOut,long intervalTime)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10),Duration.ofMillis(intervalTime));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}
	
	public WebElement waitForElementPresentUsingFluentWait(By locator, int timeOut, int intervalTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(2))
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class)
				.withMessage(Errors.ELEMENT_NOT_FOUND_ERROR_MESSAGE);

		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement waitForElement(By locator,int timeOut,int intervalTime)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait
		.pollingEvery(Duration.ofSeconds(intervalTime))
		.ignoring(NoSuchElementException.class)
		.ignoring(StaleElementReferenceException.class)
		.withMessage(Errors.ELEMENT_NOT_FOUND_ERROR_MESSAGE);

		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		
	}

	/***********************************CustomWait Utils*********************************/
	public WebElement retryingElement(By locator,int timeOut)
	{
		WebElement element = null;
		int attempt = 0;
		
		while(attempt<timeOut)
		{
			try {
				element = getElement(locator);
				break;
			}
			catch(Exception e)
			{
				System.out.println("NoSuchElementException..." + attempt);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			attempt++;
			
		}
		if(element==null)
		{
			try {
				throw new Exception("ELEMENTISNOTFOUNDEXCEPTION");
			}
			catch(Exception e)
			{
				System.out.println("Element is not found exception...tried for " + timeOut );
			}
		}
		return element;
	}
	public WebElement retryingElement(By locator,int timeOut,long pollingTime)
	{
		WebElement element = null;
		int attempt = 0;
		
		while(attempt<timeOut)
		{
			try {
				element = getElement(locator);
				break;
			}
			catch(Exception e)
			{
				System.out.println("NoSuchElementException..." + attempt);
				try {
					Thread.sleep(pollingTime);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			attempt++;
			
		}
		if(element==null)
		{
			try {
				throw new Exception("ELEMENTISNOTFOUNDEXCEPTION");
			}
			catch(Exception e)
			{
				System.out.println("Element is not found exception...tried for " + timeOut +
						" with the interval of " + pollingTime);
			}
		}
		return element;
	}
}
