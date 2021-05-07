package webdriver.api;


import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Textbox_TextArea {
		WebDriver driver;
		String loginPageUrl, userID, password, customerID;
		
		String name, dob, address, city, state, pin, phone, email;
		
		String editAddress, editCity, editState, editPin, editPhone, editEmail;
		
		By nameTextboxBy = By.name("name");
		By genderRadioBy = By.name("gender");
		By dobTextboxBy = By.name("dob");
		By addressTextAreaBy = By.name("addr");
		By cityTextboxBy = By.name("city");
		By stateTextboxBy = By.name("state");
		By pinTextboxBy = By.name("pinno");
		By phoneTextboxBy = By.name("telephoneno");
		By emailTextboxBy = By.name("emailid");
		By passwordTextboxBy = By.name("password");
	
		Random rand = new Random();
		
		@BeforeClass 
		public void beforeClass() {
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			driver.get("http://demo.guru99.com/v4");
			
			name = "John Kennedy";
			dob = "1960-01-01";
			address = "564 Suitable Address";
			city = "New York";
			state = "California";
			pin = "999666";
			phone = "0985692999";
			email = "helloworld" +rand.nextInt(999999) + "@gmail.com";
			
			editAddress = "789 PO Address";
			editCity = "California";
			editState = "New York";
			editPin = "986896";
			editPhone = "0985333777";
			editEmail = "helloworld" +rand.nextInt(999999) + "@gmail.com";
			
			
			
		}

		@Test
		public void TC_01_Register() {
			
			//Save login page url 
			loginPageUrl = driver.getCurrentUrl();
			driver.findElement(By.xpath("//a[text()='here']")).click();
			
			//This is the email is typing manually, later on we will learn how to create fake credentials for testing
			driver.findElement(By.name("emailid")).sendKeys(email);
			driver.findElement(By.name("btnLogin")).click();
			
			//Collect userID and password from the website
			userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
			password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
			
		}
		
		@Test
		public void TC_02_Login() {
			
			//Back to Login Page
			driver.get(loginPageUrl);
			
			//Input userID and password from TC01
			driver.findElement(By.name("uid")).sendKeys(userID);
			driver.findElement(By.name("password")).sendKeys(password);
			
			driver.findElement(By.name("btnLogin")).click();
			
			//Verify that this is the correct userID after login
			Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Manger Id : " + userID + "']")).isDisplayed());

		}
		
		@Test
		public void TC_03_New_Customer() {
			driver.findElement(By.xpath("//a[text()='New Customer']")).click();
			
			//Input
			driver.findElement(nameTextboxBy).sendKeys(name);
			driver.findElement(dobTextboxBy).sendKeys(dob);
			driver.findElement(addressTextAreaBy).sendKeys(address);
			driver.findElement(cityTextboxBy).sendKeys(city);
			driver.findElement(stateTextboxBy).sendKeys(state);
			driver.findElement(pinTextboxBy).sendKeys(pin);
			driver.findElement(phoneTextboxBy).sendKeys(phone);
			driver.findElement(emailTextboxBy).sendKeys(email);
			driver.findElement(passwordTextboxBy).sendKeys(password);
			
			driver.findElement(By.name("sub")).click();
				

			//Server process + Response (Output)
			//Verify outputs are correct to input
			Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Customer Registered Successfully!!!']")).isDisplayed());
			Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
			Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dob);
			Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address);
			Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
			Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
			Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
			Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phone);
			Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);
		
			customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
			
		}
		
		@Test
		public void TC_04_Edit_Customer() {
			driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
			
			driver.findElement(By.name("cusid")).sendKeys(customerID);
			driver.findElement(By.name("AccSubmit")).click();
			
			//Verify Name, Gender, DOB are disabled
			Assert.assertFalse(driver.findElement(nameTextboxBy).isEnabled());
			Assert.assertFalse(driver.findElement(genderRadioBy).isEnabled());
			Assert.assertFalse(driver.findElement(dobTextboxBy).isEnabled());

			Assert.assertEquals(driver.findElement(nameTextboxBy).getAttribute("value"), name);
			Assert.assertEquals(driver.findElement(dobTextboxBy).getAttribute("value"), dob);
			Assert.assertEquals(driver.findElement(addressTextAreaBy).getText(), address);
			Assert.assertEquals(driver.findElement(cityTextboxBy).getAttribute("value"), city);
			Assert.assertEquals(driver.findElement(stateTextboxBy).getAttribute("value"), state);
			Assert.assertEquals(driver.findElement(pinTextboxBy).getAttribute("value"), pin);
			Assert.assertEquals(driver.findElement(phoneTextboxBy).getAttribute("value"), phone);
			Assert.assertEquals(driver.findElement(emailTextboxBy).getAttribute("value"), email);
			
			//Edit Customer
			driver.findElement(addressTextAreaBy).clear();
			driver.findElement(addressTextAreaBy).sendKeys(editAddress);
			driver.findElement(cityTextboxBy).clear();
			driver.findElement(cityTextboxBy).sendKeys(editCity);
			driver.findElement(stateTextboxBy).clear();
			driver.findElement(stateTextboxBy).sendKeys(editState);
			driver.findElement(pinTextboxBy).clear();
			driver.findElement(pinTextboxBy).sendKeys(editPin);
			driver.findElement(phoneTextboxBy).clear();
			driver.findElement(phoneTextboxBy).sendKeys(editPhone);
			driver.findElement(emailTextboxBy).clear();
			driver.findElement(emailTextboxBy).sendKeys(editEmail);
			
			driver.findElement(By.name("sub")).click();
			
			//Verify outputs are correct to input
			Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Customer details updated Successfully!!!']")).isDisplayed());
			Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
			Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dob);
			
			Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), editAddress);
			Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), editCity);
			Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), editState);
			Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), editPin);
			Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), editPhone);
			Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), editEmail);
			Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText(), customerID);

		}
		
		
		@AfterClass 
			public void afterClass() {
				driver.quit();
			}
		
	}


