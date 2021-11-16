package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import common.TestBase;

public class CheckBox extends TestBase {

	private final String path = "/checkbox";

	WebElement wHome, wRootArrow, wCollapseBtn, wFirstTree, wOutput;

	private String[] outputList = { "home", "desktop", "notes", "commands", "documents", "workspace", "react",
			"angular", "veu", "office", "public", "private", "classified", "general", "downloads", "wordFile",
			"excelFile" };

	@BeforeTest
	public void beforeTest() throws Exception {
		super.beforeTest();
		chromeDriver.get(baseUrl + path);
	}

	@BeforeMethod
	public void beforeMethod() throws InterruptedException {
		chromeDriver.navigate().refresh();
		wHome = chromeDriver.findElement(By.xpath("//*[@id=\"tree-node\"]/ol/li/span/label"));
		wRootArrow = chromeDriver.findElement(By.xpath("//*[@id=\"tree-node\"]/ol/li/span/button"));
		wCollapseBtn = chromeDriver.findElement(By.xpath("//*[@id=\"tree-node\"]/div/button[2]"));
	}

	@Test(priority = 1, enabled = false)
	public void selectAllCheck() {
		wHome.click();
		wOutput = chromeDriver.findElement(By.id("result"));
		String output = wOutput.getText();
		for (String item : outputList) {
			Assert.assertTrue(output.contains(item));
		}
	}

	@Test(priority = 2, enabled = false)
	public void expandRootTree() {
		wRootArrow.click();
		wFirstTree = chromeDriver.findElement(By.xpath("//*[@id=\"tree-node\"]/ol/li/ol"));
//		String output = wFirstTree.getText();
//		Assert.assertEquals(output, "Desktop\nDocuments\nDownloads");
		Assert.assertNotNull(wFirstTree);
	}

	@Test(priority = 3)
	public void collapseAll() {
		// click wRootArrow
		wRootArrow.click();
		// assign wFirstTree
		int a = chromeDriver.findElements(By.xpath("//*[@id=\"tree-node\"]/ol/li/ol")).size();
		// AssertNotNull wFirstTree
		Assert.assertNotEquals(a, 0);
		
		// click wCollapseBtn
		wCollapseBtn.click();
		// assign wFirstTree
		int b = chromeDriver.findElements(By.xpath("//*[@id=\"tree-node\"]/ol/li/ol")).size();
		// AssertNull wFirstTree
		Assert.assertEquals(b, 0);
  }
}
