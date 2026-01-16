package com.webtrix24.rental.utils;

import java.util.Locale;
import java.util.Random;

import com.github.javafaker.Faker;

public class TestDataGenerator {
	private static Faker faker = new Faker(new Locale("en", "IN"));

	private static Random random = new Random();

	// private static Faker faker = new Faker();

	// ================= CUSTOMER DATA =================

	// Clean Indian customer name (NO Mr, Sr, dot, special chars)
	/*
	 * public static String getCustomerName() {
	 * 
	 * String rawName = faker.name().firstName() + " " + faker.name().lastName();
	 * 
	 * // Remove anything except alphabets and space String cleanName =
	 * rawName.replaceAll("[^A-Za-z ]", "");
	 * 
	 * return cleanName.trim(); }
	 */
	public static String getCustomerName() {

		String first = faker.name().firstName().replaceAll("[^A-Za-z]", "");
		String last = faker.name().lastName().replaceAll("[^A-Za-z]", "");

		return first + " " + last;
	}

	/*
	 * public static String getCustomerName() { return faker.name().fullName(); }
	 */

	public static String getEmail() {
		return faker.internet().emailAddress();
	}

	public static String getUniqueIndianEmail() {
		return faker.name().firstName().toLowerCase() + System.currentTimeMillis() + "@gmail.com";
	}

	public static String getMobileNumber() {
		// Indian style 10 digit mobile
		return "9" + faker.number().digits(9);
	}

	// ================= PAN NUMBER =================
	// Format: ABCDE1234F

	public static String generatePAN() {

		String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder pan = new StringBuilder();

		// First 5 letters
		for (int i = 0; i < 5; i++) {
			pan.append(letters.charAt(random.nextInt(letters.length())));
		}

		// Next 4 digits
		pan.append(faker.number().digits(4));

		// Last letter
		pan.append(letters.charAt(random.nextInt(letters.length())));

		return pan.toString();
	}

	// ================= GST NUMBER =================
	// Format: 27ABCDE1234F1Z5

	public static String generateGST() {

		String stateCode = String.valueOf(faker.number().numberBetween(10, 38) // valid Indian GST state codes
		);

		String pan = generatePAN(); // PAN inside GST
		String entityCode = String.valueOf(faker.number().numberBetween(1, 9));
		String defaultZ = "Z";
		String checksum = faker.regexify("[A-Z0-9]");

		return stateCode + pan + entityCode + defaultZ + checksum;
	}

	// ================= AADHAAR NUMBER =================
	// Format: 12 digits, cannot start with 0 or 1

	public static String generateAadhaar() {

		StringBuilder aadhaar = new StringBuilder();

		// First digit (2–9 only)
		aadhaar.append(faker.number().numberBetween(2, 9));

		// Remaining 11 digits
		aadhaar.append(faker.number().digits(11));

		return aadhaar.toString();
	}

	// ================= BASIC AADHAAR VALIDATION =================

	public static boolean isValidAadhaar(String aadhaar) {

		// Length check
		if (aadhaar == null || !aadhaar.matches("[2-9]{1}[0-9]{11}")) {
			return false;
		}
		return true;
	}

	public static String getCompanyName() {
		return faker.company().name();
	}

	// ================= ADDRESS DATA =================

	public static String getCity() {
		return faker.address().cityName();
	}

	public static String getZipCode() {
		return faker.number().digits(6);
	}

	public static String getWebsite() {
		return "https://" + faker.internet().domainName();
	}
}
