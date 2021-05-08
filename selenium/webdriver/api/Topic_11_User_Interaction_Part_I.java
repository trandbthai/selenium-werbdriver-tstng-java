package webdriver.api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_User_Interaction_Part_I {
	WebDriver driver;
	Actions action;

	@BeforeClass
	public void beforeClass() {
		//driver = new FirefoxDriver();
		System.setProperty("webdriver.gecko.driver", "./browserDriver/geckodriver");
		driver = new FirefoxDriver();
		
		action = new Actions(driver);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public void TC_01_Hover_Mouse() {
		driver.get("https://jqueryui.com/resources/demos/tooltip/default.html");
		action.moveToElement(driver.findElement(By.xpath("//a[text()='ThemeRoller']"))).perform();

		sleepInSeconds(3);

		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(),
				"ThemeRoller: jQuery UI's theme builder application");

		// div[@class='ui-tooltip-content']
	}
	
	@Test
	public void TC_02_Hover_Mouse() {
		driver.get("https://www.myntra.com/");
		action.moveToElement(driver.findElement(By.xpath("//a[@class='desktop-main' and text()='Kids']"))).perform();
		sleepInSeconds(3);
		driver.findElement(By.xpath("//a[text()='Home & Bath']")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.myntra.com/kids-home-bath");
		
		
	}

	public void TC_03_Hover_Mouse() {
		driver.get("https://hn.telio.vn/");

		action.moveToElement(driver.findElement(By.xpath("//main[@id='maincontent']//span[text()='Đồ uống']")))
				.perform();

		sleepInSeconds(3);

		Assert.assertTrue(driver.findElement(By.xpath("//main[@id='maincontent']//a[text()='Bia']")).isDisplayed());
	}


	public void TC_04_Click_And_Hold() {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");

		// click and hold 1 day
		List<WebElement> number = driver.findElements(By.xpath("//ol[@id='selectable']/li"));

		action.clickAndHold(number.get(0)).moveToElement(number.get(3)).release().perform();

		List<WebElement> numberSelected = driver
				.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));

		Assert.assertEquals(numberSelected.size(), 4);

		for (WebElement n : numberSelected) {
			System.out.println(n.getText());
		}
		
		
	}

	
	public void TC_05_Click_And_Hold_Random() {
		// randomly pick number
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");

		List<WebElement> number = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		
		// to perform this action, we need to hold the Ctrl button
		action.keyDown(Keys.CONTROL).perform();
		
		//Pick number 2 7 9 10
		action.click(number.get(1)).click(number.get(6)).click(number.get(8)).click(number.get(9)).perform();
		
		//release CONTROL button
		action.keyUp(Keys.CONTROL).perform();
		
		List <WebElement> numberSelected = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]")); 
		
		Assert.assertEquals(numberSelected.size(), 4);
		
		for (WebElement n : numberSelected) {
			System.out.println(n.getText());
		}
		// example of sendkeys
		//action.sendKeys(driver.findElement(By.xpath("//input[@id=''email]")),Keys.ENTER).perform();
				
	}
	
	public void TC_06_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")).getText(), "Hello Automation Guys!");
	}

	public void sleepInSeconds(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}