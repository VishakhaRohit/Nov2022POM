package com.qa.opencart.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage{
	
//	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public ProductInfoPage(WebDriver driver)
	{
//		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private By imgCount = By.cssSelector("ul.thumbnails img");
	private By productHeader = By.cssSelector("div#content h1");
	private By productMetaData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By productPriceData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private Map<String,String> productDataMap;
	
	public int getImageCount()
	{
		return eleUtil.waitForElementsToBeVisisble(imgCount,10).size();
	}
	
	public String getProductHeader()
	{
		String header =  eleUtil.getElement(productHeader).getText();
		System.out.println("Product Header : " + header);
		return header;
	}
	
	public Map<String,String> getProductInfo()
	{
		productDataMap = new LinkedHashMap<String, String>();
		productDataMap.put("name", getProductHeader());

		List<WebElement> metaDataList = eleUtil.getElements(productMetaData);
		System.out.println("total product meta data list: " + metaDataList.size());

		// meta data:
//		Brand: Apple
//		Product Code: Product 18
//		Reward Points: 800
//		Availability: Out Of Stock
		for (WebElement e : metaDataList) {
			String meta[] = e.getText().split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productDataMap.put(metaKey, metaValue);
		}

		// price data:
		List<WebElement> priceList = eleUtil.getElements(productPriceData);
		System.out.println("total product price list: " + priceList.size());

		String price = priceList.get(0).getText().trim();
		String exTaxPrice = priceList.get(1).getText().trim();

		productDataMap.put("price", price);
		productDataMap.put("ExTaxPrice", exTaxPrice);

		return productDataMap;
	}

}



