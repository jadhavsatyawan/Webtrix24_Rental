package com.webtrix24.rental.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.webtrix24.rental.base.BasePage;


public class ProductListPage extends BasePage {

	public ProductListPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	
	 // ====== Locators ======
    private By addProductBtn = By.xpath("//button[contains(.,'Add Product')]");
    private By searchInput = By.xpath("//input[@placeholder='Search']");
    private By searchBtn = By.xpath("//button[contains(.,'Search')]");
    
    // Table Locators
    private By tableRows = By.xpath("//table//tbody//tr");
    private By productNameCol = By.xpath("//table//tbody//tr/td[2]"); 
    private By editButtons = By.xpath("//table//tbody//tr//button[contains(.,'Edit')]");
    private By deleteButtons = By.xpath("//table//tbody//tr//button[contains(.,'Delete')]");

    // Pagination
    private By nextPageBtn = By.xpath("//button[contains(.,'Next')]");
    private By prevPageBtn = By.xpath("//button[contains(.,'Previous')]");

    // ====== Page Actions ======

    /** Click Add Product Button */
    public void clickAddProduct() {
        driver.findElement(addProductBtn).click();
    }

    /** Search Product */
    public void searchProduct(String productName) {
        WebElement ele = driver.findElement(searchInput);
        ele.clear();
        ele.sendKeys(productName);
        driver.findElement(searchBtn).click();
    }

    /** Get Total Products Count in Table */
    public int getProductCount() {
        return driver.findElements(tableRows).size();
    }

    /** Get product names from table */
    public List<WebElement> getProductNameColumn() {
        return driver.findElements(productNameCol);
    }

    /** Edit product by row index */
    public void clickEditByRow(int row) {
        driver.findElements(editButtons).get(row).click();
    }

    /** Delete product by row index */
    public void clickDeleteByRow(int row) {
        driver.findElements(deleteButtons).get(row).click();
    }

    /** Click Next */
    public void clickNextPage() {
        driver.findElement(nextPageBtn).click();
    }

    /** Click Previous */
    public void clickPrevPage() {
        driver.findElement(prevPageBtn).click();
    }

    /** Apply Filter (Filter Component Utility) */
    public void applyFilter(String columnName, String filterType, String value) {
        //FilterComponent filter = new FilterComponent(driver);
        ////filter.applyFilter(columnName, filterType, value);
    }

    /** Check if product exists in table */
    public boolean isProductPresent(String productName) {
        List<WebElement> products = getProductNameColumn();
        for (WebElement p : products) {
            if (p.getText().equalsIgnoreCase(productName)) {
                return true;
            }
        }
        return false;
    }

}
