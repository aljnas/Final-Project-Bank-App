package com.promineotech;

import java.util.Random;

public class TestDemo {
	public int addPositive(int a, int b) {
        if (a > 0 && b > 0) {
        	int sum= a + b;
        	System.out.println(sum);
        	return sum;
        } else {
            throw new IllegalArgumentException("Both parameters must be positive!");
        }
    }
	
	
	
	public int restPositive(int a, int b) {
		  if (a > 0 && b > 0) {
	        	int rest= a - b;
	        	System.out.println(rest);
	        	return rest;
	        } else {
	            throw new IllegalArgumentException("Both parameters must be positive!");
	        }
	}
public int randomNumberSquared() {
	int rint = getRandomInt();
	return rint*rint;
	
}
 int getRandomInt() {
	Random random = new Random();
	return random.nextInt(10) + 1;
}
}