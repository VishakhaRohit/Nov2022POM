package com.qa.opencart.utils;

import java.util.ArrayList;
import java.util.List;

public class Constants {

	public static final String LOGIN_PAGE_TITLE = "Account Login11";
	public static final String LOGIN_PAGE_URL_FRACTION = "route=account/login";
	public static final String ACCOUNT_PAGE_TITLE = "My Account";
	public static final int DEFAULT_TIME_OUT = 10;
	public static final int MACBBOK_IMAGE_COUNT = 5;
	public static final String ERROR_MESSAGE = "No match for E-Mail Address and/or Password";
	public static final String REGISTRATION_SUCCESS_MSG = "Account Has Been Created";
	public static final String REGISTRATION_SHEET = "registration1";
	
	public static List<String> accountSecList()
	{
		List<String> accSecList = new ArrayList<String>();
		accSecList.add("My Account");
		accSecList.add("My Orders");
		accSecList.add("My Affiliate Account");
		accSecList.add("Newsletter");
	
		return accSecList;
	}
}
