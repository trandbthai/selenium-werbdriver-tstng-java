package webdriver.api;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_03_Run_On_Browser {
	
	WebDriver driver;
	
	@Test 
	public void TC_01_Run_On_Firefox() {
		driver = new FirefoxDriver();
		driver.get("https://google.com");
		driver.quit();
		
	}
	
	@Test 
	public void TC_02_Run_On_Firefox_After_V47() {
		//geckodriver
		System.setProperty("webdriver.gecko.driver", "./browserDriver/geckodriver");
		driver = new FirefoxDriver();
	}
	
	@Test
	public void TC_03_Run_On_Chrome() {
		System.setProperty("webdriver.chrome.driver","./browserDriver/chromedriver");
		//. = represent for project location
		driver = new ChromeDriver();
		driver.get("https://google.com");
		driver.quit();
	}
	

}
