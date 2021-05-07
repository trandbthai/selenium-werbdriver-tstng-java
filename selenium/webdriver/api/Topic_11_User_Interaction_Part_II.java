package webdriver.api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sun.corba.se.impl.encoding.CodeSetConversion.BTCConverter;

import org.openqa.selenium.support.Color;


public class Topic_11_User_Interaction_Part_II {
	WebDriver driver;
	Actions action;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", "./browserDriver/geckodriver");		
		driver = new FirefoxDriver();
		action = new Actions(driver);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	
	public void TC_01_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		//Right click
		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
		
		//Verify Quit is displayed
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]")).isDisplayed());
		
		//Hover on Quit
		action.moveToElement(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]"))).perform();
			
		// Verify Quit (visible + hover)
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit') and contains(@class,'context-menu-visible')and contains(@class,'context-menu-hover')]")).isDisplayed());
		
		
		// Click to Quit
		action.click(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]"))).perform();
		
		Assert.assertEquals(driver.switchTo().alert().getText(),"clicked: quit");
		
		driver.switchTo().alert().accept();
		
		//Verify Quit is not displayed
		Assert.assertFalse(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]")).isDisplayed());
	}
	
	@Test
	public void TC_02_Drag_And_Drop_HTML4() {
		driver.get("https://demos.telerik.com/kendo-ui/dragdrop/index");
		
		//drag and drop
		action.dragAndDrop(driver.findElement(By.id("draggable")), driver.findElement(By.id("droptarget"))).perform();
		sleepInSeconds(2);
		
		//verify by text
		Assert.assertEquals(driver.findElement(By.id("droptarget")).getText(), "You did great!");
		
		//verify by background color
		Assert.assertEquals(getHexValue(driver.findElement(By.id("droptarget")).getCssValue("background-color")), "#03a9f4");
		
	}

	
	public void TC_03_Drag_And_Drop_HTML5() {
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		
		WebElement sourceRectangle = driver.findElement(By.id("column-a"));
		WebElement targetRectangle = driver.findElement(By.id("column-b"));
		
		action.dragAndDrop(sourceRectangle, targetRectangle).perform();
		sleepInSeconds(3);
		
		
		
	}
	
	
	public void sleepInSeconds(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getRBGValue(String hexValue) {
		return Color.fromString(hexValue).asRgb();
	}
	
	public String getHexValue(String rgbValue) {
		return Color.fromString(rgbValue).asHex();
	}
	
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}