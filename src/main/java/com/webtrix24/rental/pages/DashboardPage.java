package com.webtrix24.rental.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.webtrix24.rental.base.BasePage;

public class DashboardPage extends BasePage {

	public DashboardPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//button[text()='Quick Links']")
	WebElement quickLinks;

	@FindBy(xpath = "//button[text()='Inventory View']")
	WebElement inventoryView;

	@FindBy(xpath = "//button[@class='inline-flex items-center gap-2 rounded-lg bg-[#0F2B46] px-3 py-2 text-white text-sm shadow-sm']")
	WebElement watchVideo;

	@FindBy(xpath = "//button[text()='×']")
	WebElement closeVideo;

	@FindBy(xpath = "//button[text()='Add']")
	WebElement addAsset;

	@FindBy(xpath = "(//button[text()='Create'])[1]")
	WebElement createDelivery;

	@FindBy(xpath = "(//button[text()='Create'])[2]")
	WebElement createInvoice;

	@FindBy(xpath = "//button[text()='Review']")
	WebElement checkProductStatus;

	@FindBy(xpath = "//input[@id='customer']")
	WebElement selectCustomer;

	@FindBy(xpath = "//div/input[@placeholder='Start date']")
	WebElement startDate;

	@FindBy(xpath = "//div/input[@placeholder='End date']")
	WebElement endDate;

	public void clickOnQuickLinks() {
		quickLinks.click();
	}

	public void clickOnInventoryView() {
		inventoryView.click();
	}

	public void watchVideo() throws Exception {
		watchVideo.click();
		Thread.sleep(3000);
	}

	public void addProduct() {
		addAsset.click();
	}

	public void createDelivery() {
		createDelivery.click();
	}

	public void createInvoice() {
		createInvoice.click();
	}

	public void checkProductStatus() {
		checkProductStatus.click();
	}

	public void selectCustomer() {
		selectCustomer.click();
	}

	public void startDate() {
		startDate.click();
	}

	public void endDate() {
		endDate.click();
	}
}
