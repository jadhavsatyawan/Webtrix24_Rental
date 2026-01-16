package com.webtrix24.rental.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomGenerationUtils {

	// Generate random alphabetic string
	public static String randomString() {
		return RandomStringUtils.randomAlphabetic(5);
	}

	// Generate random numeric string
	public static String randomNumber(int length) {
		return RandomStringUtils.randomNumeric(length);
	}

	// Generate random alphanumeric string
	public static String randomAlphaNumeric(int length) {
		return RandomStringUtils.randomAlphanumeric(length);
	}

	// Generate random alphanumeric suffix
	public static String generateUniqueSuffix(int length) {
		return RandomStringUtils.randomAlphanumeric(length).toUpperCase();
	}

	// Combine base serial with random suffix
	public static String generateUniqueSerial(String baseSerial, int randomLength) {
		return baseSerial + generateUniqueSuffix(randomLength);
	}

	// Generate random Numeric suffix
	public static String generateUniqueNumberSuffix(int length) {
		return RandomStringUtils.randomNumeric(length);
	}

	// Combine base serial with random suffix
	public static String generateUniqueNumeric(String baseSerial, int randomLength) {
		return baseSerial + generateUniqueNumberSuffix(randomLength);
	}

}
