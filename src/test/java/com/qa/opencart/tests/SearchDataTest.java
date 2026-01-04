package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.dataproviders.ProductDataProvider;
import com.qa.opencart.pojo.Product;
import com.qa.opencart.utils.PropertyReader;

public class SearchDataTest extends BaseTest{
	
	@BeforeClass
	public void searchSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
		
	
	@Test(priority = 1, dataProvider="productData",dataProviderClass=ProductDataProvider.class)
	public void searchProductResultsCountTest(Product product) {
		System.out.println("Value of the searchKey==========>" + product);
		resultsPage = accPage.doSearch(product.getSearchKey());
		Assert.assertTrue(resultsPage.getProductResultsCount() > 0);
		
	}
	
	@Test(priority = 2, dataProvider="productData",dataProviderClass=ProductDataProvider.class)
	public void searchPageTitleTest(Product product) {
		System.out.println("driver came this method searchPageTitleTest");
		resultsPage = accPage.doSearch(product.getSearchKey());
		String actSerchTitle = resultsPage.getResultsPageTitle(product.getSearchKey());
		System.out.println("Search Title : " + actSerchTitle);
		Assert.assertEquals(actSerchTitle, "Search - "+product.getSearchKey());
	}
	
	


	@Test(priority = 3,dataProvider="productData",dataProviderClass=ProductDataProvider.class)
	public void selectProductTest(Product product) {
		System.out.println("driver came this method selectProductTest");
		resultsPage = accPage.doSearch(product.getSearchKey());
		productInfoPage = resultsPage.selectProduct(product.getProductName());
		String actproductHeaderName = productInfoPage.getProductHeaderName();
		System.out.println("Actual Product Name : " + actproductHeaderName);
		PropertyReader.write("ProductHeader",actproductHeaderName);
		Assert.assertEquals(actproductHeaderName, product.getProductName());
	}
	
	
	
	@Test(priority = 4,dataProvider="productData",dataProviderClass=ProductDataProvider.class)
	public void ProductImagesTest(Product product) {
		System.out.println("driver came this method ProductImagesTest");
		resultsPage = accPage.doSearch(product.getSearchKey());
		productInfoPage = resultsPage.selectProduct(product.getProductName());
		int actProductImagesCount = productInfoPage.getProductImagesCount();
		System.out.println("Actual Product Images Count : " + actProductImagesCount);
		Assert.assertEquals(actProductImagesCount, product.getProductImages());
	}

}
