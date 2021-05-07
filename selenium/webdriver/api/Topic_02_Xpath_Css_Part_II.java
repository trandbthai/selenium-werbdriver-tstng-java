package webdriver.api;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_02_Xpath_Css_Part_II {

	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	
	public void TC_01_ID() throws InterruptedException {
		driver.findElement(By.id("ctl00_CPHContainer_txtUserLogin")).sendKeys("automationtest@gmail.com");
		driver.findElement(By.id("ctl00_CPHContainer_txtPassword")).sendKeys("123456789");
		
		Thread.sleep(3000);
	}
	
	
	public void TC_02_Class() throws InterruptedException{
		driver.navigate().refresh();
		
		driver.findElement(By.className("common_textbox")).sendKeys("automationtest");
		
		Thread.sleep(3000);
	}
	
	
	public void TC_03_Name() throws InterruptedException {
		
		driver.navigate().refresh();
		driver.findElement(By.name("ctl00$CPHContainer$txtUserLogin")).sendKeys("automationtest@gmail.com");
		driver.findElement(By.name("ctl00$CPHContainer$txtPassword")).sendKeys("123456789");
		
		Thread.sleep(3000);
	}
	
	
	public void TC_04_Tagname() {
		System.out.println("Total links on the current page = " + driver.findElements(By.tagName("a")).size());
	}
	

	public void TC_05_LinkText() {
		// Text of Link - absolute (whole text)
		driver.findElement(By.linkText("Forgot Password ?")).click();
	}
	

	public void TC_06_Partial_LinkText() {
		// Text of Link - Partial (Part of text)
		driver.findElement(By.partialLinkText("Forgot")).click();
	}
	
	public void TC_07_Css_Selector() throws InterruptedException {
		driver.get("http://login.ubuntu.com/");
		
		driver.findElement(By.cssSelector("input[id='id_email']")).sendKeys("automation@gmail.com");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input[id='id_email']")).clear();
		
		driver.findElement(By.cssSelector("input[placeholder='Your email address']")).sendKeys("testing@gmail.com");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input[placeholder='Your email address']")).clear();
		
	}
	
	@Test
	public void TC_08_Xpath()  {
		driver.get("http://login.ubuntu.com/");
		
		driver.findElement(By.xpath("//form[@id='login-form']//input[@id='id_email']")).sendKeys("automation@gmail.com");
		sleepInSecond(2);
		driver.findElement(By.xpath("//form[@id='login-form']//input[@id='id_email']")).clear();
		
		driver.findElement(By.xpath("//label[@for='id_email']/following-sibling::input")).sendKeys("test2@gmail.com");
		sleepInSecond(2);
		driver.findElement(By.xpath("//label[@for='id_email']/following-sibling::input")).clear();
		
		driver.findElement(By.xpath("//a[text() = 'Read More ›']")).click();
	}
	
	public void sleepInSecond(long time)
	{
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
