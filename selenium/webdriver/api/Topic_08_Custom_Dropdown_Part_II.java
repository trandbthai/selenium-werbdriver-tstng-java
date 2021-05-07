package webdriver.api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

public class Topic_08_Custom_Dropdown_Part_II {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;

	String[] firstMonths = { "March", "May", "September", "November" };
	String[] secondMonths = { "March", "May", "September" };

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "./browserDriver/chromedriver");
		driver = new ChromeDriver();

		// always add after driver = new driver()
		explicitWait = new WebDriverWait(driver, 30);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "12");
		Assert.assertEquals(
				driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(),
				"12");

		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "1");
		Assert.assertEquals(
				driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(),
				"1");

		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "5");
		Assert.assertEquals(
				driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(),
				"5");
	}
	/*
	 * -Hanh vi cua 1 dropdown -Click vào dropdown -Chờ cho các items được hiển thị
	 * ra -Tìm các items đc chọn Bấm vào -Kiểm tra xem chọn đúng chưa. xem text sau
	 * khi chon xong hien ra o dau thi verify o do
	 */

	public void TC_02_Practice() {
		driver.get("https://demo.nopcommerce.com/register");

		selectItemInCustomDropdown("//select[@name='DateOfBirthDay']", "//select[@name='DateOfBirthDay']/option", "18");

		selectItemInCustomDropdown("//select[@name='DateOfBirthMonth']", "//select[@name='DateOfBirthMonth']/option",
				"July");

		selectItemInCustomDropdown("//select[@name='DateOfBirthYear']", "//select[@name='DateOfBirthYear']/option",
				"1994");

	}

	public void TC_03_Angular() {
		driver.get(
				"https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
		selectItemInCustomDropdown("//ejs-dropdownlist[@id='games']", "//ul[@id='games_options']/li", "Basketball");
		sleepInSeconds(1);

		selectItemInCustomDropdown("//ejs-dropdownlist[@id='games']", "//ul[@id='games_options']/li", "Snooker");
		sleepInSeconds(1);

		selectItemInCustomDropdown("//ejs-dropdownlist[@id='games']", "//ul[@id='games_options']/li",
				"American Football");
		sleepInSeconds(1);
	}

	public void TC_04_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

		selectItemInCustomDropdown("//div[@role='listbox']", "//div[@role='option']/span", "Elliot Fu");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "Elliot Fu");

		selectItemInCustomDropdown("//div[@role='listbox']", "//div[@role='option']/span", "Christian");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "Christian");

		selectItemInCustomDropdown("//div[@role='listbox']", "//div[@role='option']/span", "Jenny Hess");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "Jenny Hess");

	}

	public void TC_05_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectItemInCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "First Option");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "First Option");

		selectItemInCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a",
				"Second Option");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Second Option");

		selectItemInCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "Third Option");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Third Option");

	}

	public void TC_06_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

		selectItemInEditableDropdown("//input[@class='search']", "Andorra");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "Andorra");

		selectItemInEditableDropdown("//input[@class='search']", "Bahrain");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "Bahrain");

		selectItemInEditableDropdown("//input[@class='search']", "Belgium");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "Belgium");

	}

	@Test
	public void TC_07_Multiple_Items() {
		driver.get("https://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");

		selectMultipleItemsInCustomDropdown("(//button[@class='ms-choice'])[1]",
				"(//div[@class='ms-drop bottom'])[1]//li//span", firstMonths);
		Assert.assertTrue(areItemSelected(firstMonths));

		driver.navigate().refresh();

		selectMultipleItemsInCustomDropdown("(//button[@class='ms-choice'])[1]",
				"(//div[@class='ms-drop bottom'])[1]//li//span", secondMonths);
		Assert.assertTrue(areItemSelected(secondMonths));

	}

	public void selectItemInCustomDropdown(String parentXpath, String allItemXpath, String expectedText) {
		// CLick to dropdown
		driver.findElement(By.xpath(parentXpath)).click();
		sleepInSeconds(1);

		// Wail until all items appeared
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));

		// Collect all Items into 1 list
		List<WebElement> allItem = driver.findElements(By.xpath(allItemXpath));

		// Use for loop to check each item
		for (WebElement item : allItem) {
			// Verify each item and getText()
			// If that item is the text that we want -> click on that item
			// Exit for loop

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

	public String getAngularDropdownSelectedText() {
		return (String) jsExecutor
				.executeScript("return document.querySelector(\"select[name='games']>option[selected]\").text");
	}

	public void selectItemInEditableDropdown(String parentXpath, String expectedText) {
		driver.findElement(By.xpath(parentXpath)).clear();
		driver.findElement(By.xpath(parentXpath)).sendKeys(expectedText);
		sleepInSeconds(1);

		driver.findElement(By.xpath(parentXpath)).sendKeys(Keys.TAB);
	}

	public void selectMultipleItemsInCustomDropdown(String parentXpath, String allItemXpath, String[] months) {
		// CLick to dropdown
		driver.findElement(By.xpath(parentXpath)).click();
		sleepInSeconds(1);

		// Wail until all items appeared
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));

		// Collect all Items into 1 list
		List<WebElement> allItems = driver.findElements(By.xpath(allItemXpath));

		// Use for loop to check each item
		for (String month : months) {
			for (WebElement item : allItems) {
				// Verify each item and getText()
				// If that item is the text that we want -> click on that item
				// Exit for loop

				if (item.getText().equals(month)) {
					item.click();
					sleepInSeconds(1);
					break;
				}
			}
		}
	}

	public boolean areItemSelected(String[] listNeeded) {
		List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//span"));

		// Print number of selected items
		int numberItemSelected = itemSelected.size();

		// Text display after selecting
		String textDisplay = driver.findElement(By.xpath("(//button[@class='ms-choice'])[1]/span")).getText();
		System.out.println("Text selected:" + textDisplay);

		if (numberItemSelected > 0 && numberItemSelected <= 3) {
			int i = 0;
			for (String item : listNeeded) {
				if (textDisplay.contains(item)) {
					i++;
					if (i == numberItemSelected) {
						return true;
					}
				}
			}
			return false;
		} else if (numberItemSelected > 3 && numberItemSelected < 12) {
			return driver.findElement(By.xpath(
					"(//button[@class='ms-choice'])[1]/span[text()='" + numberItemSelected + " of 12 selected']"))
					.isDisplayed();
		} else if (numberItemSelected >= 12) {
			return driver.findElement(By.xpath("(//button[@class='ms-choice'])[1]/span[text()='All selected']"))
					.isDisplayed();
		} else {
			new RuntimeException("No selected month!");
			return false;
		}

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}