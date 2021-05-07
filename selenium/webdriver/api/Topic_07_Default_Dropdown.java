package webdriver.api;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Default_Dropdown {
	WebDriver driver;
	Select select;
	String firstName, lastName, emailAddress, password, companyName;
	String date, month, year;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/");

		firstName = "John";
		lastName = "Wick";
		emailAddress = "johnwick" +getRandomNumber() + "@hotmail.com" ;
		password = "1234qtyd!";
		companyName = "Hollywood";
		
		date = "30";
		month = "September";
		year = "1995";
	}

    @Test
	public void TC_01_Register() {
		/* 1 - Open Register page */
		driver.findElement(By.xpath("//a[text()='Register']")).click();

		/* 2 - Fill in all required fields */
		// Gender-male
		checkToCheckboxOrRadio(By.cssSelector("#gender-male"));
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);

		/*
		 * When working with drop down, we need to use Select to create action. 1. Add
		 * Select select; after WebDriver driver; 2. Select = new Select(element) 3.
		 * There are 3 ways to use with drop down: index, value, and visibleText =>
		 * visibleText is the most efficient option to work with drop down.
		 * 
		 */
		// Create select to work with drop down Date
		select = new Select(driver.findElement(By.cssSelector("Select[name='DateOfBirthDay']")));
		select.selectByVisibleText(date);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), date);

		// Create select to work with drop down Month
		select = new Select(driver.findElement(By.cssSelector("Select[name='DateOfBirthMonth']")));
		select.selectByVisibleText("September");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);

		// do not use much - learn to know how to write down List<WebElement> and For
		List<WebElement> allItems = select.getOptions();
		for (WebElement item : allItems) {
			System.out.println(item.getText());
		}

		// Create select to work with drop down Year
		select = new Select(driver.findElement(By.cssSelector("Select[name='DateOfBirthYear']")));
		select.selectByVisibleText("1995");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
		//sleepInSecond(5);

		// Verify that select is not multiple choices
		Assert.assertFalse(select.isMultiple());

		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Company")).sendKeys(companyName);

		checkToCheckboxOrRadio(By.cssSelector("#Newsletter"));

		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		
		/* 3 - Register */

		//driver.findElement(By.id("register-button")).click();
		
		driver.findElement(By.id("register-button")).sendKeys(Keys.ENTER);
		
		/* 4 - Check successful register message */
		
		Assert.assertEquals(driver.findElement(By.cssSelector(".result")).getText(),"Your registration completed");

		/* 5 - Open My account page */
		driver.findElement(By.cssSelector(".ico-account")).click();

		/* 6 - Verify correct information */
		Assert.assertTrue(driver.findElement(By.id("gender-male")).isSelected());
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);
		
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), date);
		
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
		
		Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), emailAddress);
		Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"), companyName);
		Assert.assertTrue(driver.findElement(By.id("Newsletter")).isSelected());
		
	}
	
	@Test
	public void TC_02_Practice() {
		//Access page 
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//job1 is not multiple
		Select job1 = new Select(driver.findElement(By.id("job1")));
		Assert.assertFalse(job1.isMultiple());
		
		//Select Mobile Testing by visible text
		job1.selectByVisibleText("Mobile Testing");	
		//Verify correct value
		Assert.assertEquals(job1.getFirstSelectedOption().getText(), "Mobile Testing");
		
		//Select Manual by Value
		job1.selectByValue("manual");
		//Verify correct value
		Assert.assertEquals(job1.getFirstSelectedOption().getText(), "Manual Testing");
		
		//Select Functional by Index
		job1.selectByIndex(9);
		//Verify correct value
		Assert.assertEquals(job1.getFirstSelectedOption().getText(), "Functional UI Testing");
		
		//Verify drop down list has 10 values
		Assert.assertEquals(job1.getOptions().size(), 10);
		
		//Job 2 is multiple
		Select job2 = new Select(driver.findElement(By.id("job2")));
		Assert.assertTrue(job2.isMultiple());
		
		//Select automation, mobile, desktop
		job2.selectByVisibleText("Automation");
		job2.selectByVisibleText("Mobile");
		job2.selectByVisibleText("Desktop");
		
		//Verify correct values
		List<WebElement> listJob2 = job2.getAllSelectedOptions() ;
		Assert.assertEquals(listJob2.size(), 3);
		
		for (WebElement option: listJob2) {
			System.out.println(option.getText());
		}
		
		//De-select all selected
		job2.deselectAll();
		
		//verify no value is selected
		Assert.assertEquals(job2.getAllSelectedOptions().size(), 0);
	}

	public void checkToCheckboxOrRadio(By by) {
		WebElement element = driver.findElement(by);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void uncheckToCheckbox(By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			element.click();
		}
	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);

	}
		
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}