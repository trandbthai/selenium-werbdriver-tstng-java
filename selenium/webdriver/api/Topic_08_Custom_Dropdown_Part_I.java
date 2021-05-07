package webdriver.api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;



public class Topic_08_Custom_Dropdown_Part_I {
	WebDriver driver;
	WebDriverWait explicitWait;
	

	@BeforeClass
	public void beforeClass() {		
		driver = new FirefoxDriver();
		
		//always add after driver = new driver()
		explicitWait = new WebDriverWait(driver, 30);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		
		selectItemInCustomDropdown("//span[@id='number-button']","//ul[@id='number-menu']//div","12");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "12");
		
		
		selectItemInCustomDropdown("//span[@id='number-button']","//ul[@id='number-menu']//div","1");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "1");
		
		selectItemInCustomDropdown("//span[@id='number-button']","//ul[@id='number-menu']//div","5");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "5");
	}
	/*
	 * -Hanh vi cua 1 dropdown 
	 *-Click vào dropdown 
	 * -Chờ cho các items được hiển thị ra
	 * -Tìm các items đc chọn Bấm vào 
	 * -Kiểm tra xem chọn đúng chưa. xem text sau khi chon xong hien ra o dau thi verify o do
	 */
	
	@Test
	public void TC_02_Practice() {
		driver.get("https://demo.nopcommerce.com/register");
		
		selectItemInCustomDropdown("//select[@name='DateOfBirthDay']","//select[@name='DateOfBirthDay']/option","18");
		
		selectItemInCustomDropdown("//select[@name='DateOfBirthMonth']","//select[@name='DateOfBirthMonth']/option","July");
		
		selectItemInCustomDropdown("//select[@name='DateOfBirthYear']","//select[@name='DateOfBirthYear']/option","1994");
		
	}
	
	public void selectItemInCustomDropdown(String parentXpath, String allItemXpath, String expectedText) {
		//CLick to dropdown
		driver.findElement(By.xpath(parentXpath)).click();
		sleepInSeconds(1);
		
		//Wail until all items appeared
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));
	
		//Collect all Items into 1 list
		List<WebElement> allItem = driver.findElements(By.xpath(allItemXpath));
		
		//Use for loop to check each item
		for (WebElement item: allItem) {
			//Verify each item and getText() 
			//If that item is the text that we want -> click on that item
			//Exit for loop
			
			if (item.getText().equals(expectedText)) {
				item.click();
				sleepInSeconds(1);
				break;
			}
		}
	
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