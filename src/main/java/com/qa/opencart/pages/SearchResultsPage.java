package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	private By productResults = By.cssSelector("div.caption  a");
	private By searchPageHeader = By.cssSelector("div#content h1");

	public String getSearchPageHeader() {
		return eleUtil.doGetText(searchPageHeader);
	}
	
	public int getProductListCount() {
		int resultCount = eleUtil.waitForElementsToBeVisisble(productResults, Constants.DEFAULT_TIME_OUT, 2000).size();
		System.out.println("The search product count : " + resultCount);
		return resultCount;
	}

	public ProductInfoPage selectProduct(String mainProductName) {
		
		System.out.println("Main Product name : " + mainProductName);
		List<WebElement> productList = eleUtil.waitForElementsToBeVisisble(productResults, Constants.DEFAULT_TIME_OUT,2000);
		for (WebElement e : productList) {
			String text = e.getText().trim();
			if (text.equals(mainProductName)) {
				e.click();
				break;
			}
		}
		
		return new ProductInfoPage(driver);
	}

}
