package com.webtrix24.rental.tests.userprofile;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.webtrix24.rental.core.BaseClass;
import com.webtrix24.rental.pages.Header;
import com.webtrix24.rental.pages.LoginPage;
import com.webtrix24.rental.utils.ConfigReader;

public class CompanySelection extends BaseClass {

	LoginPage loginPage;
	Header hr;

	// Login & basic navigation – ONLY ONCE
	@BeforeClass
	public void setUpCustomerCreate() throws InterruptedException {

		loginPage = new LoginPage(driver);

		// Login
		loginPage.login(ConfigReader.getProperty("app.username"), ConfigReader.getProperty("app.password"));
		Thread.sleep(2000);
		hr = new Header(driver);

	}

	@Test
	public void VerifySelectNonGST() {
		hr.selectNonGSTCompany();
	}

}
