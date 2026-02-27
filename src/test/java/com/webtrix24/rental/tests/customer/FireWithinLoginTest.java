package com.webtrix24.rental.tests.customer;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.webtrix24.rental.core.BaseClass;
import com.webtrix24.rental.pages.FireWithinLogin;
import com.webtrix24.rental.utils.ConfigReader;
import com.webtrix24.rental.utils.ExcelUtility;

public class FireWithinLoginTest extends BaseClass {

	FireWithinLogin fireWithinLogin;

	@BeforeClass
	public void setUpCustomerCreate() throws InterruptedException {

		fireWithinLogin = new FireWithinLogin(driver);

		/*
		 * fireWithinLogin.login(ConfigReader.getProperty("app.username"),
		 * ConfigReader.getProperty("app.password")); Thread.sleep(2000);
		 */

		// Always start from login page
		driver.get(ConfigReader.getProperty("app.url"));

	}

	@Test
	public void verifyAllUsersLogin() throws Exception {

		String path = System.getProperty("user.dir") + "/testdata/IG_PUNE.xlsx";
		ExcelUtility xl = new ExcelUtility(path);

		for (int i = 1;; i++) {

			String email = xl.getCellData("Sheet1", i, 3);
			String password = xl.getCellData("Sheet1", i, 5);

			if (email == null || email.isBlank())
				break;

			System.out.println("Checking Login for: " + email);

			fireWithinLogin.login(email, password);

			if (fireWithinLogin.isDashboardVisible()) {
				System.out.println(email + " → LOGIN SUCCESS");
				fireWithinLogin.logout();
			} else {
				System.out.println(email + " → LOGIN FAILED");
				// Go back to login page again
				driver.get(ConfigReader.getProperty("app.url"));
			}
		}
	}

}
