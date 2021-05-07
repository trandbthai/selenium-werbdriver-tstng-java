package webdriver.api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath_CSS_Part_I {
	WebDriver driver;
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://alada.vn/tai-khoan/dang-ky.html");
	}

	@Test
	public void TC_02_Validate() throws InterruptedException  {
		// Understand HTML elements
		// Why we need to capture element
		// What to do next after finding these elements
		
		// Execute with "DANG KY" button
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		// driver: represent for Selenium WebDriver - call out the library 
		// findElement
		// By.id / className / name / xpath / cssSelector / tagname / linkText / partialLinkText: types of locator
		// click(): type of event
		
		Thread.sleep(3000);
		
		// Input textbox HO TEN
		driver.findElement(By.cssSelector("input[name='txtFirstname']")).sendKeys("Automation FC");
		Thread.sleep(3000);
		
		// Input textbox Password
		driver.findElement(By.id("txtPassword")).sendKeys("123456789");
		Thread.sleep(3000);
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
