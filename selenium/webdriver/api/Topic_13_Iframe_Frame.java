package webdriver.api;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import sun.lwawt.macosx.CSystemTray;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_13_Iframe_Frame {
	WebDriver driver;
	Select select;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "./browserDriver/chromedriver");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}


	public void TC_01_Iframe() {
		driver.get("https://automationfc.com/2020/02/18/training-online-automation-testing/");

		//Switch vao FB Iframe
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@title,'Facebook Social Plugin')]")));

		//Get the like number
		String likeNumber = driver.findElement(By.xpath("//a[@title='Automation FC']/parent::div/following-sibling::div")).getText();
		System.out.println(likeNumber);

		//Back to default contentPage
		driver.switchTo().defaultContent();

		//switch vao google doc iframe
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'docs.google.com')]")));

		//find element in google doc
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='KHÓA HỌC SELENIUM AUTOMATION TESTING']")).isDisplayed());
	}

	@Test
	public void TC_02_Iframe_Exercise_Kyna() {
		driver.get("https://kyna.vn/");

		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'facebook.com')]")));

		//Verify fb iframe isDisplayed
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Kyna.vn']")).isDisplayed());

		//verify like number
		Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText(),"169K likes");

		//Back to dafault page
		driver.switchTo().defaultContent();

		//click vao chat iframe
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='cs_chat_iframe']")));
		sleepInSeconds(3);

		driver.findElement(By.xpath("//div[contains(@class,'border_overlay')]")).click();
		sleepInSeconds(3);

		//sendKeys into fields
		driver.findElement(By.xpath("//input[contains(@class,'input_name')]")).sendKeys("John Wick");

		driver.findElement(By.xpath("//input[contains(@class,'input_phone')]")).sendKeys("0909999888");

		//select
		select = new Select(driver.findElement(By.id("serviceSelect")));
		select.selectByVisibleText("TƯ VẤN TUYỂN SINH");
		//Assert.assertTrue(driver.findElement(By.xpath("//option[text()='TƯ VẤN TUYỂN SINH' and @selected='selected']")).isDisplayed());

		driver.findElement(By.xpath("//textarea[@name='message']")).sendKeys("Java Bootcamp");

		sleepInSeconds(2);

		driver.findElement(By.xpath("//input[@value='Gửi tin nhắn']")).click();

		sleepInSeconds(2);
		//verify input is correct
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='field profile_field']//label[contains(@class,'logged_in_name')]")).getText(),"John Wick");

		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='field profile_field']//label[contains(@class,'logged_in_phone')]")).getText(),"0909999888");

		Assert.assertEquals(driver.findElement(By.xpath("//textarea[@name='message']")).getText(),"Java Bootcamp");

		//back to default page
		driver.switchTo().defaultContent();

		//search Excel
		driver.findElement(By.xpath("//input[@id='live-search-bar']")).sendKeys("Excel");
		driver.findElement(By.xpath("//input[@id='live-search-bar']")).sendKeys(Keys.ENTER);
		sleepInSeconds(3);

		//verify open page with keyword "Excel"
		//dang moi can thuc tap them
		List<WebElement> excelText = driver.findElements(By.cssSelector(".content>b4"));

		for (WebElement text : excelText ) {
			Assert.assertTrue(text.getText().contains("Excel"));
		}
	}


	public void TC_03_LoginFormDisplayed() {

	}
	

	public void TC_04_Incorrect() {

	}

	public void sleepInSeconds ( long timeout){
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}