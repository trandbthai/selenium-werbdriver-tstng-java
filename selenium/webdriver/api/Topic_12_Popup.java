package webdriver.api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import sun.lwawt.macosx.CThreading;

import java.util.concurrent.TimeUnit;

public class Topic_12_Popup {
	WebDriver driver;
	Actions action;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", "./browserDriver/geckodriver");
		driver = new FirefoxDriver();

		action = new Actions(driver);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}


	public void TC_01_Popup_Fixed() {
		driver.get("https://tiki.vn/");

		//Hover mouse
		action.moveToElement(driver.findElement(By.xpath("//img[@class='profile-icon']"))).perform();

		//Click on Dangnhap button
		action.click(driver.findElement(By.xpath("//button[text()='Đăng nhập']"))).perform();

		//Verify popup is displayed
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Đăng nhập hoặc Tạo tài khoản']")).isDisplayed());

		sleepInSeconds(2);
		//Close popup
		driver.findElement(By.xpath("//button[@class='btn-close']")).click();

		//Verify POPUP is not dislayed (Not in DOM)
		//ko dùng đc assertFalse. isDisplayed trong trg hop nay vi popup ko co torng
		//Assert.assertFalse(driver.findElement(By.xpath("//p[text()='Đăng nhập hoặc Tạo tài khoản']")).isDisplayed());
		Assert.assertEquals(driver.findElements(By.xpath("//p[text()='Đăng nhập hoặc Tạo tài khoản']")).size(), 0);
	}


	public void TC_02_Popup_In_DOM() {
		driver.get("https://bni.vn/");

		//verify popup is displayed
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='sgpb-popup-dialog-main-div']")).isDisplayed());

		//Close popup
		driver.findElement(By.xpath("//img[@class='sgpb-popup-close-button-1']")).click();

		//verify popup is not displayed
		Assert.assertFalse(driver.findElement(By.xpath("//div[@id='sgpb-popup-dialog-main-div']")).isDisplayed());
	}

	@Test
	public void TC_02_Popup_In_DOM_Condition() {
		//Thao tac vs truong hop muon tat popup trc khi kiem element khac trong trang
		System.out.println("Step 1");
		driver.get("https://blog.testproject.io/");

		// 1- If popup is showing up => close and move to step 3
		// 2- If popup is not showing up => move to step 3

		// TH1: Always existing in DOM even showing up or not
		if(driver.findElement(By.xpath("//div[@class='mailch-wrap']")).isDisplayed()) {
			System.out.println("Step 2");
			driver.findElement(By.xpath("//div[@id='close-mailch']")).click();
			sleepInSeconds(3);
		}

		// TH2: Showing up -> existing in DOM / Not showing up -> disappear in DOM
		// => cannot work with findElement as usual -> using findElements().size()

		if(driver.findElements(By.xpath("//div[@class='mailch-wrap']")).size() >=1 ){
			System.out.println("Step 2");
			driver.findElement(By.xpath("//div[@id='close-mailch']")).click();
			sleepInSeconds(3);
		}

		System.out.println("Step 3");
		driver.findElement(By.xpath("//section[@id='search-2']//input[@class='search-field']")).sendKeys("Autometion");
		sleepInSeconds(2);

		System.out.println("Step 4");
		driver.findElement(By.xpath("//section[@id='search-2']//span[@class='glass']")).click();
	}

	public void TC_03_Popup_Not_In_DOM () {

	}

	public void sleepInSeconds ( long timeout){
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass () {
		driver.quit();
	}

}
