package com.webtrix24.rental.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.webtrix24.rental.base.BasePage;



public class ChargersPage extends BasePage
{

	 /************* Constructor ***************/
	
	public ChargersPage(WebDriver driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	/*******************Locators*******************************/
	
	@FindBy(xpath = "//label[text()='Charger Serial No']/following-sibling::input")
	WebElement chargerSerialNo;
	
	}
