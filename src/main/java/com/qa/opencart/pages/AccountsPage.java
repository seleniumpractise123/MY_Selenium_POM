package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	
private WebDriver driver;
private ElementUtil eleUtil;
	
	//1.Const. of the page class
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	//2.Private By locators.
	private By logoutlink = By.linkText("Logout");
	private By myAccountHeader = By.cssSelector("div#content h2");
	private By searchField = By.name("search");
	private By myAccount = By.linkText("My Account");
	private By searchIcon = By.cssSelector("div#search button");
	
	
	//3.Page actions:
	public String getAccPageTitle() {
		//String pageTitle = driver.getTitle();
		//System.out.println("Acc Page Title:====> " + pageTitle);
		//return pageTitle;
		return eleUtil.waitForTitleIsAndCapture(AppConstants.ACCOUNTS_PAGE_TITLE_VALUE,AppConstants.SHORT_DEFAULT_WAIT);
	}
	
	public boolean isLogoutLinkExist() {
		//return driver.findElement(logoutlink).isDisplayed();
		return eleUtil.checkElementIsDisplayed(logoutlink);
	}
	
	public boolean isMyAccountLinkExist() {
		//return driver.findElement(myAccount).isDisplayed();
		return eleUtil.checkElementIsDisplayed(myAccount);
	}
	
	public List<String> getAccountPageHeadersList() {
		List<WebElement> headersList = eleUtil.waitForElementsVisible(myAccountHeader,AppConstants.MEDIUM_DEFAULT_WAIT);
		List<String> headersValList = new ArrayList<String>();
		for(WebElement we: headersList) {
			String text = we.getText();
			headersValList.add(text);
		}
		
		return headersValList;
	}
	
	public ResultsPage doSearch(String searchTerm) {
		//driver.findElement(searchField).sendKeys(searchTerm);
		//driver.findElement(searchIcon).click();
		eleUtil.waitForElementVisible(searchField, AppConstants.MEDIUM_DEFAULT_WAIT);
		eleUtil.doSendKeys(searchField, searchTerm);
		eleUtil.doClick(searchIcon);
		return new ResultsPage(driver);
		
	}
	
	
	
	

}
