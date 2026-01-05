package com.webtrix24.rental.tests.userprofile;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.webtrix24.rental.core.BaseClass;
import com.webtrix24.rental.pages.Header;
import com.webtrix24.rental.pages.LoginPage;
import com.webtrix24.rental.pages.UserProfile;
import com.webtrix24.rental.pages.UserSettings;
import com.webtrix24.rental.utils.ConfigReader;

public class UserProfilePositiveTest extends BaseClass {

	LoginPage loginPage;
	Header header;
	UserSettings userSettings;
	UserProfile userProfile;

	@BeforeClass
	public void setUpForProfileUpdate() throws Exception {
		loginPage = new LoginPage(driver);
		loginPage.login(ConfigReader.getProperty("app.username"), ConfigReader.getProperty("app.password"));
		Thread.sleep(2000);
		header = new Header(driver);
		userSettings = new UserSettings(driver);
		userProfile = new UserProfile(driver);
	}

	@Test
	public void updateProfilePage() throws Exception {
		header.clickProfileDropdwon();
		userProfile.userSettings();
//		userSettings.setFullName("Sanket Gaikwad");
//		userSettings.setMobileCountryCode("+91");
//		userSettings.setMobileNumber("9865624170");
//		userSettings.setWhatsAppCountryCode("+91");
//		userSettings.setWhatsAppNumber("9865624170");
//		userSettings.setTimezone();
//		userSettings.setLocation("Pune");
		// userSettings.saveChanges();
		userSettings.changePassword("Sanket@123!", "Sanket@123!", "Sanket@123!");
		userSettings.clickUpdatePassword();
		Thread.sleep(6000);
		userSettings.clickLogoutFromAllDevices();
	}
}
