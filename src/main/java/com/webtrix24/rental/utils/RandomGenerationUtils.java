package com.webtrix24.rental.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;

public class RandomGenerationUtils
{
	  WebDriver driver;

	    public RandomGenerationUtils(WebDriver driver) 
	    {
	        this.driver = driver;
	    }
	    // Generate random alphabetic string
	    public String randomString() {
	        return RandomStringUtils.randomAlphabetic(5);
	    }

	    // Generate random numeric string
	    public String randomNumber(int length) {
	        return RandomStringUtils.randomNumeric(length);
	    }

	    // Generate random alphanumeric string
	    public String randomAlphaNumeric(int length) {
	        return RandomStringUtils.randomAlphanumeric(length);
	    }
	    
	    
	    // Generate random alphanumeric suffix
	    public String generateUniqueSuffix(int length) {
	        return RandomStringUtils.randomAlphanumeric(length).toUpperCase();
	    }
	    
	  

	    // Combine base serial with random suffix
	    public String generateUniqueSerial(String baseSerial, int randomLength) {
	        return baseSerial + generateUniqueSuffix(randomLength);
	    }
	    
	    // Generate random Numeric suffix
	    public String generateUniqueNumberSuffix(int length) 
	    {
	        return RandomStringUtils.randomNumeric(length);
	    }
	    

	    // Combine base serial with random suffix
	    public String generateUniqueNumeric(String baseSerial, int randomLength) {
	        return baseSerial + generateUniqueNumberSuffix(randomLength);
	    }

}
