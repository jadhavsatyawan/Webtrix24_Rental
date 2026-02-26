package com.webtrix24.rental.tests.Login;

import org.testng.annotations.Test;

import com.webtrix24.rental.core.BaseClass;
import com.webtrix24.rental.pages.LoginPage;
import com.webtrix24.rental.utils.ConfigReader;

public class LoginTest extends BaseClass {

	@Test
	public void verifyLoginWithValidCredentials() throws InterruptedException {

		LoginPage lp = new LoginPage(driver);

		lp.login(ConfigReader.getProperty("app.username"), ConfigReader.getProperty("app.password"));

		System.out.println("Login automation executed successfully");
	}

}
