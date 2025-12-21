package com.base;

public class Hello {

	public static void main(String[] args) {

		System.out.println("Hello, Satyawan....");
		System.out.println(10 + 10);

		int num = 10;

		int num1 = 0;
		int num2 = 1;

		while (num >= num1) {
			System.out.println(num1);
			int num3 = num1 + num2;
			num1 = num2;
			num2 = num3;
		}
	}
}
