package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import common.TestBase;
import common.Utils;

public class TextBox extends TestBase {

	private final String path = "/text-box";
	private final String validName = "John";
	private final String validEmail = "johndoe@mail.com";
	private final String validCurrentAddress = "18, California, USA.";
	private final String validPermanentAddress = "54, New Delhi, India.";
	private final String invalidEmail = "john-mail.com";

	WebElement wFullName, wEmail, wCurrentAddress, wPermanentAddress, wSubmitButton, wOutput;

	@BeforeTest
	public void beforeTest() throws Exception {
		super.beforeTest();
		chromeDriver.get(baseUrl + path);
		Thread.sleep(3000);
		wFullName = chromeDriver.findElement(By.xpath("//*[@id=\"userName\"]"));
		wEmail = chromeDriver.findElement(By.id("userEmail"));
		wCurrentAddress = chromeDriver.findElement(By.id("currentAddress"));
		wPermanentAddress = chromeDriver.findElement(By.id("permanentAddress"));
		wSubmitButton = chromeDriver.findElement(By.id("submit"));
		wOutput = chromeDriver.findElement(By.xpath("//*[@id=\"output\"]/div"));
	}

	@BeforeMethod
	public void befroeEachTest() {
		wFullName.clear();
		wEmail.clear();
		wCurrentAddress.clear();
		wPermanentAddress.clear();
	}

	@Test(priority = 1)
	public void validEmailSubmit() throws InterruptedException {
		wEmail.sendKeys(validEmail);
		Utils.moveToElement(wEmail, chromeDriver);
		Thread.sleep(2000);
		wSubmitButton.click();
		String validationMassege = wEmail.getAttribute("validationMessage");
		Assert.assertEquals(validationMassege, "");
	}

	@Test(priority = 2)
	public void invalidEmailSubmit() throws InterruptedException {
		wEmail.sendKeys(invalidEmail);
		Utils.moveToElement(wEmail, chromeDriver);
		Thread.sleep(2000);
		wSubmitButton.click();
		String output = wEmail.getAttribute("validationMessage");
		Assert.assertNotEquals(output, "");
	}

	@Test(priority = 3)
	public void allInputValuesValid() throws InterruptedException {
		// Waiting for loading
		Utils.moveToElement(wFullName, chromeDriver);
		wFullName.sendKeys(validName);
		wEmail.sendKeys(validEmail);
		wCurrentAddress.sendKeys(validCurrentAddress);
		wPermanentAddress.sendKeys(validPermanentAddress);
		Utils.moveToElement(wSubmitButton, chromeDriver);
		Thread.sleep(2000);
		wSubmitButton.click();
		String output = wOutput.getText();
		Assert.assertTrue(output.contains(validName));
	}

	@Test(priority = 4)
	public void noValueSubmit() throws InterruptedException {
		Utils.moveToElement(wSubmitButton, chromeDriver);
		Thread.sleep(2000);
		wSubmitButton.click();
		String output = wOutput.getText();
		Assert.assertEquals(output, "");
	}

}
