package elements;

import org.testng.annotations.Test;

import common.TestBase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

public class Buttons extends TestBase {

	private final String path = "/buttons";

	WebElement outputDoubleClk, outputRightClk, dynamicClk, dynamicClickoutput;

	@BeforeMethod
	public void beforeMethod() throws Exception {
		super.beforeTest();
		chromeDriver.get(baseUrl + path);
		//chromeDriver.navigate().refresh();
	}

	@Test
	public void doubleClick() {
		Actions doubleClk = new Actions(chromeDriver);
		doubleClk.doubleClick(chromeDriver.findElement(By.id("doubleClickBtn"))).build().perform();
		outputDoubleClk = chromeDriver.findElement(By.id("doubleClickMessage"));
		String output = outputDoubleClk.getText();
		Assert.assertEquals(output, "You have done a double click");
	}
	
	@Test
	public void rightClick() {
		Actions rightClk = new Actions(chromeDriver);
		rightClk.contextClick(chromeDriver.findElement(By.id("rightClickBtn"))).perform();
		outputRightClk = chromeDriver.findElement(By.id("rightClickMessage"));
		String output = outputRightClk.getText();
		Assert.assertEquals(output, "You have done a right click");
	}
	
	@Test
	public void dynamicClk() throws InterruptedException {
		dynamicClk = chromeDriver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[1]/div[3]/button"));
		dynamicClk.click();		
		Thread.sleep(2000);
		dynamicClickoutput = chromeDriver.findElement(By.id("dynamicClickMessage"));
		String output = dynamicClickoutput.getText();
		Assert.assertEquals(output, "You have done a dynamic click");
  }
}
