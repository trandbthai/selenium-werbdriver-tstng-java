package webdriver.javaTester;

import java.util.Random;

public class Topic_05_Random_Number {
	public static void main(String[] args) { 
	
	// Java Class
	Random rand = new Random();
	
	// Random email
	// Format: prefix + random + postfix (web mail server: github/ gmail/ hotmail/ ...)
	
	System.out.println("auto" + rand.nextInt(999999) + "@tiki.vn");


	String email = "helloworld" +rand.nextInt(999999) + "@gmail.com";

	System.out.println(email);
}
}
