package com.webtrix24.rental.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.webtrix24.rental.base.BasePage;

public class UserProfile extends BasePage {

	public UserProfile(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//a[text()='Request a Feature ↗']")
	WebElement requestFeature;

	@FindBy(xpath = "//button[text()='User Settings']")
	WebElement userSettings;

	@FindBy(xpath = "//button[text()='Logout']")
	WebElement logout;

	@FindBy(xpath = "//a[@href='https://www.webtrix24.com/blogs']")
	WebElement blogs;

	@FindBy(xpath = "//a[text()='About']")
	WebElement about;

	@FindBy(xpath = "//a[text()='Privacy']")
	WebElement privacy;

	@FindBy(xpath = "//a[text()='FAQs']")
	WebElement faqs;

	public void requestFeature() {
		requestFeature.click();
	}

	public void userSettings() throws Exception {
		userSettings.click();
		Thread.sleep(2000);
	}

	public void logout() {
		logout.click();
	}

	public void blogs() {
		blogs.click();
	}

	public void about() {
		about.click();
	}

	public void privacy() {
		privacy.click();
	}

	public void faqs() {
		faqs.click();
	}
}
