package webdriver.api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Element_Method_Part_II {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

	}

//	@Test
//	public void TC_01_Displayed() {
//		driver.get("https://automationfc.github.io/basic-form/index.html");
//		boolean emailTextboxStatus = driver.findElement(By.cssSelector("#mail")).isDisplayed();
//		boolean under18ButtonStatus = driver.findElement(By.cssSelector("#under_18")).isDisplayed();
//		boolean eduTextboxStatus = driver.findElement(By.cssSelector("#edu")).isDisplayed();
//		boolean user5AvatarStatus = driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed();
//		
//		if (emailTextboxStatus) {
//			driver.findElement(By.cssSelector("#mail")).sendKeys("Automation Testing");
//			System.out.println("Email textbox is Displayed");
//		}
//		else System.out.println("Email textbox is not Displayed");
//		
//		if (under18ButtonStatus) {
//			driver.findElement(By.cssSelector("#under_18")).click();
//			System.out.println("Under 18 button is Displayed");
//		}
//		else System.out.println("Under 18 button is not Displayed");
//		
//		if (eduTextboxStatus) {
//			driver.findElement(By.cssSelector("#edu")).sendKeys("Automation Testing");
//			System.out.println("Education textbox is Displayed");
//		}
//		else System.out.println("Education textbox is not Displayed");
//		
//		if (user5AvatarStatus) {
//			System.out.println("User5 avatar is Displayed");
//		}
//		else System.out.println("User5 avatar is not Displayed");
//		
//	}

//	@Test
//	public void TC_02_Enabled() {
//		driver.get("https://automationfc.github.io/basic-form/index.html");
//		boolean emailTextboxStatus = driver.findElement(By.cssSelector("#mail")).isEnabled();
//		boolean under18ButtonStatus = driver.findElement(By.cssSelector("#under_18")).isEnabled();
//		boolean eduTextareaStatus = driver.findElement(By.cssSelector("#edu")).isEnabled();
//		boolean job1DropdownStatus = driver.findElement(By.cssSelector("#job1")).isEnabled();
//		boolean job2DropdownStatus = driver.findElement(By.cssSelector("#job2")).isEnabled();
//		boolean developmentCheckboxStatus = driver.findElement(By.xpath("//label[text()='Development']")).isEnabled();
//		boolean slider1InputStatus = driver.findElement(By.cssSelector("#slider-1")).isEnabled();
//		
//		boolean passTextboxStatus = driver.findElement(By.cssSelector("#password")).isEnabled();
//		boolean slider2InputStatus = driver.findElement(By.cssSelector("#slider-2")).isEnabled();
//		
//		if (emailTextboxStatus) {
//			System.out.println("Email textbox is enabled");
//		}
//		else System.out.println("Email textbox is disabled");
//		
//		if (slider1InputStatus) {
//			System.out.println("Slider input 1 is enabled");
//		}
//		else System.out.println("Slider input 1 is disabled");
//		
//		if (slider2InputStatus) {
//			System.out.println("Slider input 2 is enabled");
//		}
//		else System.out.println("Slider input 2 is disabled");
//		
//		
//	}
//	
//	
//	@Test
//	public void TC_03_Selected() {
//		driver.get("https://automationfc.github.io/basic-form/index.html");
//		WebElement under18Button = driver.findElement(By.cssSelector("#under_18"));
//		WebElement javaCheckbox = driver.findElement(By.cssSelector("#java"));
//		
//		// Click to Under_18 Button and Java language
//		under18Button.click();
//		javaCheckbox.click();
//		
//		// Verify element is Selected
//		Assert.assertTrue(under18Button.isSelected());
//		Assert.assertTrue(javaCheckbox.isSelected());
//		
//		// Click to Java language
//		javaCheckbox.click();
//		
//		//Verify Java language is de-selected 
//		Assert.assertFalse(javaCheckbox.isSelected());
//		
//		// console log status
//		if (under18Button.isSelected()) {
//			System.out.println("Under 18 Button is selected");
//		}
//		else System.out.println("Under 18 Button is de-selected");
//		
//		if (javaCheckbox.isSelected()) {
//			System.out.println("Java Checkbox is selected");
//		}
//		else System.out.println("Java Checkbox is de-selected");
//	}

	@Test
	public void TC_04_Register_Function() {
		driver.get("https://login.mailchimp.com/signup/");

		// Input Email/User name
		driver.findElement(By.cssSelector("#email")).sendKeys("automation123@gmail.com");
		driver.findElement(By.cssSelector("#new_username")).sendKeys("automation123");

		//

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
