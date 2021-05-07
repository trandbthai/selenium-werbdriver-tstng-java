package webdriver.api;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;


	
public class Topic_05_Element_Method_Part_I {
	WebDriver driver;
	
	@BeforeClass 
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		
	}
	
	@Test
	public void TC_01_Element_Command() {
	// 1- Thao tác trực tiếp trên element
	// Chỉ dùng 1 lần duy nhất thì ko cần khai báo biến

	// 2 - Khai báo biến rồi mới thao tác (action:  click/ sendkey/ getText/ select…)
	// Khi cần dùng element nhiều lần thì nên khai báo biến để đỡ viết lại code.

	// Cách gọi biến element

	WebElement element = driver.findElement(By.xpath(""));

	//Xoá dữ liệu đã nhập trong textbox/ textarea/ dropdown (editable)
	element.clear(); //**

	//Nhập dữ liệu vào 1 textbox/ textarea/ dropdown (editable)
	element.sendKeys(""); //**

	//Click chuột
	element.click(); //**

	//Lấy ra giá trị nằm trong 1 attribute
	element.getAttribute("placeholder"); //**
	// Search entire store here

	//Lấy ra style của 1 element (ít dùng) (font/ color/ size/ background)
	element.getCssValue("background");
	element.getCssValue("color");
	element.getCssValue("font-size");

	//GUI (ít dùng)
	element.getLocation();
	element.getRect();
	element.getSize();


	//Tạo biến lấy value từ kết quả của các hàm get

	//String emailTexboxTagname = element.getTagname();
	//Assert.assertEquals(emailTextboxTagname, "input");

	//driver.findElement(By.xpath("//" + emailTextboxTagname + "[@id='email']"));

	//Lấy ra text của 1 element bất kỳ (label/ header/ span/ div … ) - text ko nằm trong attribute.
		element.getText(); //**

	//Các hàm bắt đầu bằng is sẽ trả lại kết quả boolean

	//Kiểm tra element có đang hiển thị hay ko
	Assert.assertTrue(element.isDisplayed()); //**

	// Kiểm tra element có thể thao tác được
	Assert.assertTrue(element.isEnabled()); //**

	// Kiểm tra element đã được chọn thành công (radio/ checkbox)
	Assert.assertTrue(element.isSelected()); //**

	//Enter vào trong 1 form (chỉ dùng được vs form)
	//element.submit();

	}
	@AfterClass 
	public void afterClass() {
		driver.quit();
	}
	}
