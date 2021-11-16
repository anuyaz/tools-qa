package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

public class TestBase {

	public WebDriver chromeDriver;
	public String baseUrl = "https://demoqa.com";

	public void beforeTest() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\bin\\chromeDriver.exe");
		chromeDriver = new ChromeDriver();
		chromeDriver.manage().window().maximize();
	}

	@AfterTest
	public void afterTest() throws Exception {
		Thread.sleep(1000);
		chromeDriver.close();
	}

}