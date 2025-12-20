package com.webtrix24.rental.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.webtrix24.rental.base.BasePage;

public class ProductsFilters extends BasePage
{

	public ProductsFilters(WebDriver driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}
	//button[@class="p-2 bg-gray-100 rounded"]
	
	@FindBy(xpath = "//button[@class='p-2 bg-gray-100 rounded' and @data-state='closed']")
	WebElement clickFilter;
}
