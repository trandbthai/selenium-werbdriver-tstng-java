package webdriver.api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Alert {
	WebDriver driver;
	WebDriverWait explicitWait;
	Alert alert;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 10);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		//Verify
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		
		sleepInSeconds(2);
		
		//Accept
		alert.accept();
		
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked an alert successfully");
		
	}

	
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		
		// Cho sau do switch vao => nen dung hon
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		//Verify
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		
		sleepInSeconds(2);
		
		//Cancel
		alert.dismiss();
		
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked: Cancel");
		
	}

	
	public void TC_03_Prompt_Alert() {
		String alertText = "Automation Test 2021";
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		alert.sendKeys(alertText);
		
		sleepInSeconds(2);
		
		alert.accept();
		
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: " + alertText);
		
		
	}
	

	public void TC_04_Authentication_Alert() {
		
	//Selenium By Pass
		
	driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
	
	Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());

	// Special character
	
	//password: p@ssword => phải chuyển thành dạng 
	
	//driver.get("http://admin:p%40ssword%0A@the-internet.herokuapp.com/basic_auth");
	
	
	
	}

	
	public void TC_05_Authentication_Alert() {
		// truong hop ma minh vao trang chu trc. khi nao user click on the link thi moi dan sang trang co alert
		// phai su duch cach lay ra href cua link 
		
		driver.get("http://the-internet.herokuapp.com/");
		
		String url = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		driver.get(getCrendentialsToUrl(url, "admin", "admin"));
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
	}
	
	@Test
	public void TC_06_Authentication_AutoIT() {
		// chỉ dành cho window 
	
	}
	
	
	public void sleepInSeconds(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getCrendentialsToUrl(String url, String username, String password) {
		String[] partOfUrl = url.split("//");
		
		String newUrl = partOfUrl[0] + "//" + username + ":" + password + "@" + partOfUrl[1];
		
		return newUrl;
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}