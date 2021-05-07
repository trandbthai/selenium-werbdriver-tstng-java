package webdriver.api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;

public class Topic_09_Button_Radio_Checkbox {
	WebDriver driver;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	public void TC_01_Button() {

		driver.get("https://www.fahasa.com/customer/account/create?attempt=1");
		sleepInSeconds(2);
		By loginButton = By.cssSelector(".fhs-btn-login");

		// click sign in tab
		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();

		// verify sign in button is disabled
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());

		// input email and password
		driver.findElement(By.id("login_username")).sendKeys("automation12345@gmail.com");
		driver.findElement(By.id("login_password")).sendKeys("automation12345");
		sleepInSeconds(2);

		// Verify sign in button is enabled
		Assert.assertTrue(driver.findElement(loginButton).isEnabled());

		// refresh
		driver.navigate().refresh();

		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();

		// remove disable sign in button
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());

		removeDisabledAttributeByJS(loginButton);
		sleepInSeconds(2);

		Assert.assertTrue(driver.findElement(loginButton).isEnabled());
		driver.findElement(loginButton).click();
		sleepInSeconds(2);

		// verify alert messages
		Assert.assertEquals(driver
				.findElement(By.xpath(
						"//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']"))
				.getText(), "Thông tin này không thể để trống");

		Assert.assertEquals(driver
				.findElement(By.xpath("//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert']"))
				.getText(), "Thông tin này không thể để trống");

	}

	
	public void TC_02_Default_Radio_Checkbox() {
		// Default means <input> tag is used
		driver.get("https://automationfc.github.io/multiple-fields/");

		// All elements are being selected

		List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@class='form-checkbox']"));

		for (WebElement checkbox : checkboxes) {
			checkbox.click();
			Assert.assertTrue(checkbox.isSelected());
		}

		// All elements are being de-selected

		for (WebElement checkbox : checkboxes) {
			checkbox.click();
			Assert.assertFalse(checkbox.isSelected());
		}

		// Select Checkbox
		driver.findElement(By.xpath("//input[@value='Gallstones']")).click();
		sleepInSeconds(1);

		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Gallstones']")).isSelected());

		// De-select checkbox
		driver.findElement(By.xpath("//input[@value='Gallstones']")).click();
		sleepInSeconds(1);

		Assert.assertFalse(driver.findElement(By.xpath("//input[@value='Gallstones']")).isSelected());

		// Select Radio
		driver.findElement(By.xpath("//input[@value='1-2 days']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='1-2 days']")).isSelected());
	}

	public void TC_03_Custom_Radio_Checkbox_I() {
		driver.get("https://material.angular.io/components/radio/examples");
		/*
		// Span tab is clickable but cannot be verified
		driver.findElement(By.xpath("//input[@value='Spring']/preceding-sibling::span[@class='mat-radio-outer-circle']")).click();
		
		// Verify must be used with input tab
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Spring']")).isSelected());
		
		With this solution, we need to design 2 different locators for working with only 1 element => too much code and not good for maintaining.
		THE BEST SOLUTION IS using Click function of JS => We can click and verify with <input> tab.
		
		*/
		
		// Click and Verify with <input> tab by using clickJS function
		clickByJS(By.xpath("//input[@value='Spring']"));
		sleepInSeconds(2);
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Spring']")).isSelected());
		
	}
	
	@Test
	public void TC_04_Custom_Radio_Checkbox_II() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
	
		//Before click
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Đà Nẵng' and @aria-checked='false']")).isDisplayed());
		
		driver.findElement(By.xpath("//div[@aria-label='Đà Nẵng']")).click();
		sleepInSeconds(2);
		
		//After click
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Đà Nẵng' and @aria-checked='true']")).isDisplayed());
		
	}

	public void sleepInSeconds(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void removeDisabledAttributeByJS(By by) {
		WebElement element = driver.findElement(by);
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", element);
	}
	
	// ko care about element is hidden or visible, can work with everything
	public void clickByJS(By by) {
		WebElement element = driver.findElement(by);
		jsExecutor.executeScript("arguments[0].click()", element);
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}