package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import common.TestBase;

public class RadioButton extends TestBase {

	private final String path = "/radio-button";

	WebElement yesButton, impressiveButton, noButton, wOutput;

	@BeforeTest 
	public void beforeTest() throws Exception {
		super.beforeTest();
		chromeDriver.manage().window().maximize();
		chromeDriver.get(baseUrl + path);
		Thread.sleep(3000);
		yesButton = chromeDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[2]/div[1]/div[2]/label"));
		impressiveButton = chromeDriver.findElement(By.id("impressiveRadio"));
		noButton = chromeDriver.findElement(By.id("noRadio"));
	}

	@Test
	public void yes() {
		yesButton.click();
		wOutput = chromeDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[2]/div[1]/p/span"));
		String output = wOutput.getText();
		Assert.assertEquals(output, "Yes");
  }
}
