package com.qa.opencart.tests;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.PropertyReader;

public class AccountsPageTest extends BaseTest{

	
	@BeforeClass
	public void accPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void accPageTitleTest() {
		String actTitle = accPage.getAccPageTitle();
		System.out.println("Account Header ===>" + actTitle);
		Assert.assertEquals(actTitle, AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);
		PropertyReader.write("Account Header=",actTitle);
		
	}
	
	@Test
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
		
	}
	
	@Test
	public void isMyAccLinkExistTest() {
		Assert.assertTrue(accPage.isMyAccountLinkExist());
	}
	
	@Test
	public void accPageHeaderCountTest() {
		List<String> accAccHeadersList = accPage.getAccountPageHeadersList();
	    Assert.assertEquals(accAccHeadersList.size(), 4);
		
	}
	
	@Test
	public void accPageHeaderTest() {
		List<String> accAccHeadersList = accPage.getAccountPageHeadersList();
		Assert.assertEquals(accAccHeadersList, AppConstants.EXP_ACCOUNTS_HEADERS_LIST);//Sort and compare assignment
		
	}
	

}
