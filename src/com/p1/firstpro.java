package Mytesting;

import java.awt.Desktop.Action;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class firstpro {
	WebDriver driver;

	@BeforeMethod
	public void beforeMethod() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();

	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

	@Test
	public void testingcheckBox() throws InterruptedException {
		driver.get("https://the-internet.herokuapp.com/checkboxes");
		WebElement firstcheckbox = driver.findElement(By.xpath("//*[contains(@id,'checkboxes')]/input[1]"));
		if (firstcheckbox.isSelected() == false)
			firstcheckbox.click();
		Assert.assertTrue(firstcheckbox.isSelected());
		// Thread.sleep(3000);

		WebElement secondcheckbox = driver.findElement(By.xpath("//*[contains(@id,'checkboxes')]/input[2]"));
		if (secondcheckbox.isSelected() == true)
			secondcheckbox.click();
		Assert.assertFalse(secondcheckbox.isSelected());
		// Thread.sleep(3000);

	}

	@Test
	public void testDragAndDrop() throws InterruptedException {
		driver.get("https://the-internet.herokuapp.com/drag_and_drop");
		WebElement columna = driver.findElement(By.xpath("//*[contains(@id,'column-a')]"));
		WebElement columnb = driver.findElement(By.xpath("//*[contains(@id,'column-b')]"));
		Actions a = new Actions(driver);

		a.dragAndDrop(columna, columnb).perform();
		String headerA = driver.findElement(By.xpath("//*[contains(@id,'column-a')]//header")).getText();
		String headerB = driver.findElement(By.xpath("//*[contains(@id,'column-b')]//header")).getText();

		Assert.assertEquals(headerA, "B", "Column A should now contain header B after drag and drop");
		Assert.assertEquals(headerB, "A", "Column B should now contain header A after drag and drop");

		// Thread.sleep(4000);

	}

	@Test
	public void testingAlerts() throws InterruptedException {
		driver.get("https://the-internet.herokuapp.com/javascript_alerts");
		driver.findElement(By.xpath("//*[contains(text(),'Click for JS Alert')]")).click();
		//Thread.sleep(3000);
		Alert alert = driver.switchTo().alert();
		alert.accept();
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You successfully clicked an alert");

		// Thread.sleep(3000);
		driver.findElement(By.xpath("//*[contains(text(),'Click for JS Confirm')]")).click();
		// Thread.sleep(3000);
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked: Cancel");

		driver.findElement(By.xpath("//*[contains(text(),'Click for JS Confirm')] ")).click();
		// Thread.sleep(3000);
		alert.accept();
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked: Ok");

		driver.findElement(By.xpath("//*[contains(text(),'Click for JS Prompt')]")).click();
		alert.sendKeys("kanishka");
		// Thread.sleep(3000);
		alert.accept();
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: kanishka");

		// Thread.sleep(3000);
		driver.findElement(By.xpath("//*[contains(text(),'Click for JS Prompt')]")).click();
		alert.sendKeys("kanishka");
		// Thread.sleep(3000);
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: null");
		// Thread.sleep(3000);

	}

	@Test
	public void testingContextMenu() throws InterruptedException {

		driver.get("https://the-internet.herokuapp.com/context_menu");
		WebElement box = driver.findElement(By.xpath("//*[@id=\"hot-spot\"]"));
		Actions actions = new Actions(driver);
		actions.contextClick(box).perform();
		// Thread.sleep(3000);
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "You selected a context menu");
		alert.accept();
		// Thread.sleep(3000);

	}

	@Test
	public void testingDropdownUsingSelect() throws InterruptedException {
		driver.get("https://the-internet.herokuapp.com/dropdown");
		Select dropDown = new Select(driver.findElement(By.id("dropdown")));

		dropDown.selectByVisibleText("Option 1");
		Assert.assertEquals(dropDown.getFirstSelectedOption().getText(), "Option 1");

		dropDown.selectByIndex(2);
		Assert.assertEquals(dropDown.getFirstSelectedOption().getText(), "Option 2");
//
//		dropDown.selectByValue("1");
//		Assert.assertEquals(dropDown.getFirstSelectedOption().getText(), "Option 1");
//
//		dropDown.selectByVisibleText("Option 2"); // replacing invalid method
//		Assert.assertEquals(dropDown.getFirstSelectedOption().getText(), "Option 2");
//
	}

	@Test
	public void testingFileUpload() throws InterruptedException {
		driver.get("https://the-internet.herokuapp.com/upload");
		// Thread.sleep(4000);
		String fileLocation = System.getProperty("user.dir") + File.separator + "Screenshot 2025-06-18 095917.png";
		// Thread.sleep(2000);
		driver.findElement(By.id("file-upload")).sendKeys(fileLocation);
		// Thread.sleep(2000);
		driver.findElement(By.id("file-submit")).click();
		Assert.assertTrue(
				driver.findElement(By.id("uploaded-files")).getText().contains("Screenshot 2025-06-18 095917"));
		// Thread.sleep(2000);

	}

	@Test
	public void testingFrames() throws InterruptedException {
		driver.get("https://the-internet.herokuapp.com/frames");
		driver.findElement(By.linkText("Nested Frames")).click();
		// Thread.sleep(2000);
		driver.switchTo().frame("frame-top");
		driver.switchTo().frame("frame-left");
		// Thread.sleep(2000);
		String leftText = driver.findElement(By.tagName("body")).getText();
		System.out.println("Left Frame Text: " + leftText);
		Assert.assertEquals(leftText.trim(), "LEFT", "Left frame should contain text 'LEFT'");

		driver.switchTo().parentFrame();
		driver.switchTo().frame("frame-middle");
		String middleText = driver.findElement(By.id("content")).getText();
		System.out.println("Middle Frame Text: " + middleText);
		Assert.assertEquals(middleText.trim(), "MIDDLE", "Middle frame should contain text 'MIDDLE'");

		driver.switchTo().defaultContent();
	}

	@Test
	public void testingHovers() throws InterruptedException {
		driver.get("https://the-internet.herokuapp.com/hovers");
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath("//div[@class='example']/descendant::img[1]")))
				.click(driver.findElement(By.xpath("//a[@href='/users/1']"))).build().perform();
		Assert.assertTrue(driver.getCurrentUrl().contains("/users/1"));

	}

	@Test
	public void testingclickJS() {
		driver.get("https://www.hyrtutorials.com/p/waits-demo.html");
		WebElement addBtn = driver.findElement(By.xpath("//*[@id='btn1']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click", addBtn);
		Assert.assertEquals(true, addBtn.getText().contains("Add Textbox1"));

	}

	@Test
	public void testingMulWindows() throws InterruptedException {
		driver.get("https://the-internet.herokuapp.com/windows");
		// Thread.sleep(2000);
		String parentWindow = driver.getWindowHandle();
		driver.findElement(By.linkText("Click Here")).click();
		// Thread.sleep(2000);
		Set<String> restAllWindows = driver.getWindowHandles();
		for (String window : restAllWindows) {
			if (window.equals(parentWindow) == false) {
				driver.switchTo().window(window);
				break;
			}
		}
		Assert.assertTrue(driver.getCurrentUrl().contains("/windows/new"));
		System.out.println(driver.getCurrentUrl());
		// Thread.sleep(2000);

	}

	@Test

	public void testKeyPress() throws InterruptedException {
		driver.get("https://the-internet.herokuapp.com/key_presses?");
		Actions actions = new Actions(driver);
		actions.keyDown(driver.findElement(By.id("content")), Keys.ENTER)
				.keyUp(driver.findElement(By.id("target")), Keys.ENTER).build().perform();
		Assert.assertEquals("You entered: ENTER", driver.findElement(By.id("result")).getText());
	}

	@Test
	public void testScreenshots() throws InterruptedException, IOException {

		// Thread.sleep(3000);
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(System.getProperty("user.dir") + File.separator + "YourScreenshot.png"));
	}

}
