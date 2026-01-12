package com.webtrix24.rental.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.webtrix24.rental.base.BasePage;

public class UserSettings extends BasePage {

	public UserSettings(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//div[@class='relative w-32 h-32 rounded-full overflow-hidden border-4 border-white shadow cursor-pointer hover:opacity-90 transition']")
	WebElement setProfile;

	@FindBy(xpath = "//label[text()='Full Name']/following-sibling::input")
	WebElement fullName;

	@FindBy(xpath = "(//div[@role='button'])[1]")
	WebElement mobileCountryCode;

	@FindBy(xpath = "(//div[@role='button'])[2]")
	WebElement whatsAppCountryCode;

	@FindBy(xpath = "//input[@class='search-box']")
	WebElement searchCountry;

	@FindBy(xpath = "//li[@role='option' and @data-country-code='au']")
	WebElement selectCountry;

	@FindBy(xpath = "(//input[@name='phone'])[1]")
	WebElement mobileNumber;

	@FindBy(xpath = "(//input[@name='phone'])[2]")
	WebElement whatsAppNumber;

	@FindBy(xpath = "(//select/option[text()='India (IST)'])[2]")
	WebElement timezon;

	@FindBy(xpath = "//label[text()='Location']/following-sibling::input")
	WebElement location;

	@FindBy(xpath = "//button[text()='Save Changes']")
	WebElement saveChanges;

	// Profile updated successfully

	@FindBy(xpath = "//input[@type='password' and @placeholder='Current Password']")
	WebElement currentPassword;

	@FindBy(xpath = "//input[@type='password' and @placeholder='New Password']")
	WebElement newPassword;

	@FindBy(xpath = "//input[@type='password' and @placeholder='Confirm New Password']")
	WebElement confirmNewPassword;

	@FindBy(xpath = "//div/p[text()='Passwords do not match']")
	WebElement passwordDoNotMatch;

	@FindBy(xpath = "//button[text()='Update Password']")
	WebElement updatePassword;

	@FindBy(xpath = "(//button[@type='button' and @class='absolute right-3 top-2.5 text-gray-500'])[1]")
	WebElement eyeIcon1;

	@FindBy(xpath = "(//button[@type='button' and @class='absolute right-3 top-2.5 text-gray-500'])[2]")
	WebElement eyeIcon2;

	@FindBy(xpath = "(//button[@type='button' and @class='absolute right-3 top-2.5 text-gray-500'])[3]")
	WebElement eyeIcon3;

	// Incorrect Old password..!

	// Password Updated.

	@FindBy(xpath = "//button[text()='Logout from All Devices']")
	WebElement logoutFromAllDevices;

	public void setProfilePicture() {
		setProfile.click();
	}

	public void setFullName(String fullName) {
		this.fullName.clear();
		this.fullName.sendKeys(fullName);
	}

	public void setMobileCountryCode(String countryCode) throws Exception {
		mobileCountryCode.click();
		if (selectCountry != null) {
			selectCountry.click();
			Thread.sleep(2000);
		} else {
			mobileCountryCode.sendKeys(countryCode);
			Thread.sleep(2000);
		}
	}

	public void setWhatsAppCountryCode(String countryCode) throws Exception {
		whatsAppCountryCode.click();
		if (selectCountry != null) {
			selectCountry.click();
			Thread.sleep(2000);
		} else {
			whatsAppCountryCode.sendKeys(countryCode);
			Thread.sleep(2000);
		}
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber.sendKeys(mobileNumber);
	}

	public void setWhatsAppNumber(String whatsAppNumber) {
		this.whatsAppNumber.sendKeys(whatsAppNumber);
	}

	public void setTimezone() {
		timezon.click();
	}

	public void setLocation(String location) {
		this.location.clear();
		this.location.sendKeys(location);
	}

	public void saveChanges() {
		saveChanges.click();
	}

	public void changePassword(String oldPass, String newPass, String comfirmNewPass) throws Exception {
		currentPassword.sendKeys(oldPass);
		eyeIcon1.click();
		Thread.sleep(2000);
		newPassword.sendKeys(newPass);
		eyeIcon2.click();
		Thread.sleep(2000);
		confirmNewPassword.sendKeys(comfirmNewPass);
		eyeIcon3.click();
		Thread.sleep(2000);
	}

	public void clickUpdatePassword() {
		updatePassword.click();
	}

	public void clickLogoutFromAllDevices() {
		logoutFromAllDevices.click();
	}
}
