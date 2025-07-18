package com.gspann.QETraining;

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
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestFirstProgram {

	WebDriver driver;

	@BeforeMethod
	public void setup() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.hyrtutorials.com/p/waits-demo.html");

	}

	@Test
	public void testCheckboxes() throws InterruptedException {

		WebElement element = driver.findElement(By.xpath("//a[@href='/checkboxes']"));
		element.click();
		WebElement element2 = driver.findElement(By.xpath("//form[@id='checkboxes']/descendant::input[1]"));

		
		element2.click();
		Assert.assertTrue(element2.isSelected(), "sssabcdef");

		

		WebElement element3 = driver.findElement(By.xpath("//form[@id='checkboxes']/descendant::input[2]"));
		element3.click();
		Assert.assertFalse(element3.isSelected(), "abcdeffg");
		
	}

	@Test
	public void testDragAndDrop() throws InterruptedException {

		WebElement element1 = driver.findElement(By.xpath("//a[@href='/drag_and_drop']"));
		element1.click();
		

		WebElement box1 = driver.findElement(By.xpath("//div[@id='columns']/descendant::div[1]"));
		WebElement box2 = driver.findElement(By.xpath("//div[@id='columns']/descendant::div[2]"));
		Actions actions = new Actions(driver);
		
		actions.dragAndDrop(box1, box2).build().perform();
		
		WebElement header1 = driver.findElement(By.xpath("//div[@id='columns']/descendant::div[1]/header"));
		Assert.assertEquals(header1.getText(), "B");

	}

	@Test
	public void testjsAlerts() throws InterruptedException {
		WebElement element1 = driver.findElement(By.xpath("//a[@href='/javascript_alerts']"));
		element1.click();
		driver.findElement(By.xpath("//*[contains(text(),'Click for JS Alert')]")).click();
		
		Alert alert = driver.switchTo().alert();
		alert.accept();
		
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),
				"You successfully clicked an alert");

		driver.findElement(By.xpath("//*[contains(text(),'Click for JS Confirm')]")).click();
		
		alert = driver.switchTo().alert();
		alert.dismiss();
		
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked: Cancel");

		driver.findElement(By.xpath("//*[contains(text(),'Click for JS Prompt')]")).click();
		
		alert = driver.switchTo().alert();
		alert.sendKeys("Karan");
		alert.accept();
		
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You entered: Karan");

	}

	@Test
	public void testContext() throws InterruptedException {

		WebElement element1 = driver.findElement(By.xpath("//a[@href='/context_menu']"));
		element1.click();

		WebElement box = driver.findElement(By.id("hot-spot"));
		
		Actions action = new Actions(driver);
		action.contextClick(box).build().perform();
	
		Alert alert = driver.switchTo().alert();
		alert.accept();
		
	}

	@Test
	public void testInfiniteScroll() throws InterruptedException {
		
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,document.body.scrollHeight);");
		
	}

	@Test
	public void testDropdown() throws InterruptedException {
		Select select = new Select(driver.findElement(By.id("dropdown")));
		
		select.selectByValue("1");
		
		select.selectByVisibleText("Option 2");
		
//		select.deselectByVisibleText("Option 1");
		Assert.assertEquals(driver.findElements(By.xpath("//option[@value='2']")),
				driver.findElements(By.xpath("//option[@selected = 'selected']")));

	}

	@Test
	public void testFileUpload() throws InterruptedException {
		String path = System.getProperty("user.dir") + File.separator + "Capture.PNG";
		System.out.println(path);
		driver.findElement(By.id("file-upload")).sendKeys(path);
		
		driver.findElement(By.id("file-submit")).click();
		
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='example']/h3")).getText(),("File Uploaded!"));

	}

	@Test
	public void testFrames() throws InterruptedException {

		driver.switchTo().frame(driver.findElement(By.name("frame-top")));
		
		driver.switchTo().frame(driver.findElement(By.name("frame-left")));
		

		String text1 = driver.findElement(By.xpath("//body[contains(text(),'LEFT')]")).getText();

		
		System.out.println(text1);
		driver.switchTo().parentFrame();

		driver.switchTo().frame(driver.findElement(By.name("frame-middle")));
		

		String text2 = driver.findElement(By.id("content")).getText();
		System.out.println(text2);
		
		Assert.assertEquals(driver.findElement(By.id("content")).getText(),"MIDDLE" );

	}

	@Test
	public void testHovers() throws InterruptedException {

		

		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//div[@class='example']/descendant::img[1]")))
				.click(driver.findElement(By.xpath("//a[@href='/users/1']"))).build().perform();
		
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/users/1");

	}

	@Test
	public void testUsingJS() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		WebElement addBtn = driver.findElement(By.xpath("//*[@id='btn1']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", addBtn);
		
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		
		Assert.assertEquals(driver.findElement(By.id("txt1")).isDisplayed(), true);
	}

	@Test
	public void testSwitchWindows() throws InterruptedException {
		String parentHandle = driver.getWindowHandle();

		driver.findElement(By.partialLinkText("Click")).click();
		

		Set<String> allWindowHandles = driver.getWindowHandles();

		for (String id : allWindowHandles) {

			if (!(parentHandle.equals(id))) {

				driver.switchTo().window(id);
				break;
			}
		}
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/windows/new");

	}

	@Test
	public void testKeyPresses() throws InterruptedException {
		
		Actions action = new Actions(driver);
		action.keyDown(driver.findElement(By.id("target")), Keys.SHIFT)
				.keyUp(driver.findElement(By.id("target")), Keys.SHIFT).build().perform();
		;
		
		System.out.println(driver.findElement(By.id("result")).getText());

		Assert.assertTrue(driver.findElement(By.id("result")).getText().equals("You entered: SHIFT"));
	}

	@Test
	public void testScreenshots() throws InterruptedException, IOException {

		
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("D:\\eclipse workspace\\QETraining\\myScreenshot.png"));
	}

	@AfterMethod
	public void tearDown() {

		driver.quit();
	}

	public WebElement utilitymethod1(String str) { // not used yet

		WebElement element = driver.findElement(By.xpath(str));
		return element;
	}

}
