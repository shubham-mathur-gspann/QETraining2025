package com.gspann;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.service.DriverService;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestFirstProgram {

	WebDriver driver;

	@BeforeMethod
	public void setup() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@Test
	public void testCheckBox(){
		driver.get("https://the-internet.herokuapp.com/checkboxes");
		WebElement firstCheckbox = driver.findElement(By.xpath("//form[@id='checkboxes']/descendant::input[1]"));
		if (firstCheckbox.isSelected() == false)
			firstCheckbox.click();
		Assert.assertTrue(firstCheckbox.isSelected());

		WebElement secondCheckbox = driver.findElement(By.xpath("//form[@id='checkboxes']/descendant::input[2]"));
		if (secondCheckbox.isSelected() == true)
			secondCheckbox.click();
		Assert.assertFalse(secondCheckbox.isSelected());
	}

	@Test
	public void testDragAndDrop(){
		driver.get("https://the-internet.herokuapp.com/drag_and_drop");
		WebElement source = driver.findElement(By.id("column-a"));
		WebElement target = driver.findElement(By.id("column-b"));
		Actions actions = new Actions(driver);
		actions.dragAndDrop(source, target).build().perform();
	}

	@Test
	public void testJSAlert(){
		driver.get("https://the-internet.herokuapp.com/javascript_alerts");
		driver.findElement(By.xpath("//*[contains(text(),'Click for JS Alert')]")).click();
		Alert a = driver.switchTo().alert();
		a.accept();
		String act = "You successfully clicked an alert";
		String value = driver.findElement(By.xpath("//p[@id='result']")).getText();
		Assert.assertEquals(act, value);

		driver.findElement(By.xpath("//*[contains(text(),'Click for JS Confirm')]")).click();
		a.accept();
		String actual = "You clicked: Ok";
		String expected = driver.findElement(By.xpath("//p[@id='result']")).getText();
		Assert.assertEquals(actual, expected);

		driver.findElement(By.xpath("//*[contains(text(),'Click for JS Confirm')]")).click();
		a.dismiss();
		String actualt = "You clicked: Cancel";
		String exp = driver.findElement(By.xpath("//p[@id='result']")).getText();
		Assert.assertEquals(actualt, exp);

		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		Alert ab = driver.switchTo().alert();
		ab.sendKeys("Prince");
		ab.accept();
		String actualtext = "You entered: Prince";
		String expect = driver.findElement(By.xpath("//p[@id='result']")).getText();
		Assert.assertEquals(actualtext, expect);
	}

	@Test
	public void textContextMenu(){
		driver.get("https://the-internet.herokuapp.com/context_menu");
		WebElement b = driver.findElement(By.id("hot-spot"));
		Actions a = new Actions(driver);
		a.contextClick(b).build().perform();
		Alert ab = driver.switchTo().alert();
		ab.accept();
	}

	@Test
	public void testScrollerUseJS(){
		driver.get("https://the-internet.herokuapp.com/infinite_scroll");
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,document.body.scrollHeight);");
	}

	@Test
	public void testDropDownUsingSelect(){
		driver.get("https://the-internet.herokuapp.com/dropdown");
		Select dropdown = new Select(driver.findElement(By.xpath("//select[@id='dropdown']")));
		dropdown.selectByValue("1");
		Assert.assertEquals(dropdown.getFirstSelectedOption().getText(), "Option 1");
		dropdown.selectByValue("2");
		Assert.assertEquals(dropdown.getFirstSelectedOption().getText(), "Option 2");
	}

	@Test
	public void testFileUpload(){
		String path = System.getProperty("user.dir") + File.separator + "github.PNG";
		driver.get("https://the-internet.herokuapp.com/upload");
		WebElement choosebtn = driver.findElement(By.xpath("//input[@id='file-upload']"));
		choosebtn.sendKeys(path);
		driver.findElement(By.id("file-submit")).click();
		WebElement successMsg = driver.findElement(By.tagName("h3"));
		Assert.assertEquals(successMsg.getText(), "File Uploaded!");
	}

	@Test
	public void testFrames(){
		driver.get("https://the-internet.herokuapp.com/nested_frames");
		driver.switchTo().frame(driver.findElement(By.xpath("//*[@name='frame-top']")));
		driver.switchTo().frame(driver.findElement(By.xpath("//*[@name='frame-left']")));
		String actualTextLeft = "LEFT";
		String expectedTextLeft = driver.findElement(By.xpath("//body[contains(text(),'LEFT')]")).getText();
		System.out.println(expectedTextLeft);
		Assert.assertEquals(actualTextLeft, expectedTextLeft);

		driver.switchTo().parentFrame();
		driver.switchTo().frame(driver.findElement(By.xpath("//*[@name='frame-middle']")));
		String actualTextRight = "MIDDLE";
		String expectedTextRight = driver.findElement(By.id("content")).getText();
		System.out.println(expectedTextRight);
		Assert.assertEquals(actualTextRight, expectedTextRight);
	}

	@Test
	public void testMouseHover(){
		driver.get("https://the-internet.herokuapp.com/hovers");
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath("//*[@class='example']/descendant::img[1]")))
				.click(driver.findElement(By.xpath("//a[@href='/users/1']"))).build().perform();
		Assert.assertTrue(driver.getCurrentUrl().contains("/users/1"));
	}

	@Test
	public void testAddTextBoxBtn() {
		driver.get("https://www.hyrtutorials.com/p/waits-demo.html");
		((JavascriptExecutor) driver).executeScript("document.querySelector('#btn2').click();");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement box2 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Textbox2']")));
		Assert.assertEquals(box2.isDisplayed(), true);
	}

	@Test
	public void testWindowSwitch(){
		driver.get("https://the-internet.herokuapp.com/windows");
		driver.findElement(By.linkText("Click Here")).click();
		Set<String> allWindowsTab = driver.getWindowHandles();
		for (String windowId : allWindowsTab) {
			driver.switchTo().window(windowId);
			if (driver.getTitle().contains("New Window")) {
				break;
			}
		}
		Assert.assertTrue(driver.getCurrentUrl().contains("/new"));
	}

	@Test
	public void testKeyPresses() {
		driver.get("https://the-internet.herokuapp.com/key_presses");
		Actions action = new Actions(driver);
		action.keyDown(driver.findElement(By.id("target")), Keys.CONTROL)
				.keyUp(driver.findElement(By.id("target")), Keys.CONTROL).build().perform();
		String exp = driver.findElement(By.xpath("//p[@id='result']")).getText();
		Assert.assertEquals("You entered: CONTROL", exp);
	}

	@Test
	public void testScreenshot() throws InterruptedException, IOException {
		driver.get("https://the-internet.herokuapp.com/key_presses");
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(System.getProperty("user.dir") + File.separator + "Screenshots.png"));
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
