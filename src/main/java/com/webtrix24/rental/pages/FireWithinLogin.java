package com.webtrix24.rental.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.webtrix24.rental.base.BasePage;

public class FireWithinLogin extends BasePage {

	public FireWithinLogin(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//button[text()='Login']")
	WebElement loginbtn;

	@FindBy(xpath = "//input[@name='email']")
	WebElement userEmail;

	@FindBy(xpath = "//input[@name='password']")
	WebElement userPass;

	@FindBy(xpath = "//button[@type='submit' and text()='Login']")
	WebElement logbtnsubmit;

	@FindBy(xpath = "//button[@title='Profile']")
	WebElement profileIcon;

	@FindBy(xpath = "//button[text()='Logout']")
	WebElement logoutbtn;

	@FindBy(xpath = "//h1[text()='THE FIRE WITHIN']")
	WebElement dashboardTitle;

	public void ClickLogin() {
		loginbtn.click();
	}

	public void setEmail(String em) {
		userEmail.sendKeys(em);
	}

	public void setPassword(String pass) {
		userPass.sendKeys(pass);
	}

	// button[text()='Register Now']
	// input[@name='name']
	// input[@name='email']
	// button[@type='submit' and text()='Register']
	public void login(String um, String pass) throws InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(loginbtn));
		loginbtn.click();
		pause(800);
		setEmail(um);
		setPassword(pass);
		Thread.sleep(2000);
		logbtnsubmit.click();

	}

	public boolean isDashboardVisible() {
		try {
			wait.until(ExpectedConditions.visibilityOf(dashboardTitle));
			return dashboardTitle.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void logout() {
		profileIcon.click();
		wait.until(ExpectedConditions.elementToBeClickable(logoutbtn));
		logoutbtn.click();
	}

}
