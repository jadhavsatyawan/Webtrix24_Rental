package com.webtrix24.rental.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

import com.github.javafaker.Faker;

public class AddressUtil {

    public static Random rand = new Random();
    public static Faker faker = new Faker();

    public static String[] cities = {"Mumbai", "Kolkata", "Hyderabad", "Bangalore", "Thiruvananthapuram"};
    public static String[] states = {"Maharashtra", "West Bengal", "Andhra Pradesh", "Karnataka", "Kerala"};

    public static Map<String, String> cityPinPrefix = new HashMap<>();
    public static Map<String, String> stateGSTCode = new HashMap<>();

    static {
        cityPinPrefix.put("Mumbai", "400");
        cityPinPrefix.put("Kolkata", "700");
        cityPinPrefix.put("Hyderabad", "500");
        cityPinPrefix.put("Bangalore", "560");
        cityPinPrefix.put("Thiruvananthapuram", "695");

        stateGSTCode.put("Maharashtra", "27");
        stateGSTCode.put("West Bengal", "19");
        stateGSTCode.put("Andhra Pradesh", "37");
        stateGSTCode.put("Karnataka", "29");
        stateGSTCode.put("Kerala", "32");
    }

    public static class Address {
        public String city;
        public String state;
        public String fullAddress;
        public String pincode;
        public String gstNumber;
        public String gstStateDropdownText;

        public Address(String city, String state, String fullAddress, String pincode, String gstNumber) {
            this.city = city;
            this.state = state;
            this.fullAddress = fullAddress;
            this.pincode = pincode;
            this.gstNumber = gstNumber;
            this.gstStateDropdownText = stateGSTCode.get(state) + "-" + state;
        }
    }

    // Generate correct GST Number (15 chars)
    public static String generateRandomGSTNumber(String state) {
        String stateCode = stateGSTCode.get(state);
        if (stateCode == null) stateCode = "00";

        String panPart = RandomStringUtils.randomAlphabetic(5).toUpperCase()
                + RandomStringUtils.randomNumeric(4)
                + RandomStringUtils.randomAlphabetic(1).toUpperCase();

        String entityCode = RandomStringUtils.randomAlphanumeric(1).toUpperCase();
        String checksum = RandomStringUtils.randomAlphanumeric(1).toUpperCase();

        String gstNumber = stateCode + panPart + entityCode + "Z" + checksum;

        if (gstNumber.length() != 15) {
            System.out.println("⚠ GST length mismatch: " + gstNumber + " (" + gstNumber.length() + ")");
        }

        return gstNumber;
    }

    public static String getGSTCode(String stateName) {
        if (stateName == null || stateName.isEmpty()) return "";

        // Handle "19-West Bengal" format
        if (stateName.contains("-")) {
            stateName = stateName.split("-", 2)[1].trim();
        }

        String code = stateGSTCode.get(stateName);
        if (code != null) return code;

        System.out.println("⚠ State not found in GST code map: " + stateName);
        return "";
    }

    public static Address getRandomAddress() {
        int idx = rand.nextInt(cities.length);
        String city = cities[idx];
        String state = states[idx];

        String street = faker.address().streetAddress();
        String fullAddress = street + ", " + city + ", " + state;

        String pinPrefix = cityPinPrefix.getOrDefault(city, "000");
        String pincode = pinPrefix + String.format("%03d", rand.nextInt(1000));

        String gstNumber = generateRandomGSTNumber(state);

        return new Address(city, state, fullAddress, pincode, gstNumber);
    }
}
