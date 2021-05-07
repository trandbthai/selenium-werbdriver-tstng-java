package webdriver.api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Browser_Method_Part_I {
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
	}
	
	@Test
	public void TC_01_Browser_Method() {
		//Mở ra 1 ứng dụng web/ page
		driver.get("https://..."); //**

		//Đóng tab đang mở
		driver.close();

		//Đóng hết browser ko quan tâm tab
		driver.quit(); //*

		//Mở ra trang mobile
		driver.findElement(By.xpath("//a[text() = ‘Mobile']")).click();

		//Kiểm tra được cái URL của page mới mở đó có đúng hay ko
		Assert.assertEquals(driver.getCurrentUrl(), "https://….");

		//Lấy ra title của page hiện tại
		driver.getTitle(); //*

		//Lấy ra source code của page hiện tại (ít dùng)
		driver.getPageSource();

		//Lấy ra ID của tab/window hiện tại nó đang đứng (Active)
		driver.getWindowHandle(); //**

		//Lấy ra tất cã ID của tất cả tab/window
		driver.getWindowHandles(); //**

		//30s : 1000ms = 1s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); //**
		driver.manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);
			
			//implicitlyWait: Chờ cho element được xuất hiện để tương tác trong vòng ...s
			// sẽ đc học chuyên sâu, gồm có 4 loại wait:
			//- pageLoadTimeout(30, TimeUnit.SECONDS ) (chờ cho page đc load trong vòng ...s)
			//- setScriptTimeout(30, TimeUnit.SECONDS) 
			// Thường đi kết hợp vs findElement/ findElements

		//Phóng to browser
		driver.manage().window().maximize(); //**

		//F11
		driver.manage().window().fullscreen();

		//Test responsive => selenium ko mạnh về cái này => ko dùng đến
		// Kích thước của trình duyệt
		//driver.manage().setSize();
		//driver.manage().getSize();
		// Vị trí của trình duyệt so vs độ phân giải màn hình hiện tại
		//driver.manage().setPosition();
		//driver.manage().getPosition();

		//Back lại trang trước đó
		driver.navigate().back(); //*

		//Tiếp đến trang trước đó
		driver.navigate().forward(); //*

		//Tải lại trang (F5)
		driver.navigate().refresh(); //*

		//Mở ra 1 URL: tracking history tốt hơn (ít dùng)
		driver.navigate().to(""); //*


		//Thao tác vs Alert (học chuyên sâu sau)
		driver.switchTo().alert(); //**

		//Thao tác vs Frame / IFrame (học chuyên sâu sau)
		driver.switchTo().frame(0); //**

		//Thao tác vs Window/tabs (học chuyên sâu sau)
		driver.switchTo().window(""); //**


	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
}
