package com.gspann;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class TestFirstProgram {

	WebDriver driver;

	@BeforeMethod
	public void setup() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://the-internet.herokuapp.com/key_presses");
	}
	
	@Test
	public void testDropDown() throws InterruptedException {
		Select dropDown = new Select(driver.findElement(By.id("dropdown")));
		dropDown.selectByVisibleText("Option 1");
		dropDown.selectByContainsVisibleText("2");
		WebElement opt2 = driver.findElement(By.xpath("//option[contains(text(),'Option 2')]"));
		Assert.assertTrue(opt2.isSelected());
	}
	
	@Test
	public void testFileUpload() throws InterruptedException {
		String path = System.getProperty("user.dir")+File.separator+"MyCapture.png";
		System.out.println(path);
		driver.findElement(By.id("file-upload")).sendKeys(path);
		driver.findElement(By.id("file-submit")).click();
		String expectedTxt = "File Uploaded!";
		String actual = driver.findElement(By.xpath("//div[@class='example']/h3")).getText();
		Assert.assertEquals(expectedTxt, actual);
	}
	
	@Test
	public void testFrames() throws InterruptedException {
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-top']")));
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-left']")));
		String txt = driver.findElement(By.xpath("//body[contains(text(),'LEFT')]")).getText();
		//System.out.println(txt);
		driver.switchTo().parentFrame();
		driver.switchTo().frame(driver.findElement(By.name("frame-middle")));
		String secText = driver.findElement(By.id("content")).getText();
		Assert.assertEquals(secText, "MIDDLE");
		//System.out.println(secText);
		
	}
	
	@Test
	public void testHovers() throws InterruptedException {
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath("//div[@class='example']/descendant::img[2]")))
		.click(driver.findElement(By.xpath("//a[@href='/users/2']"))).build().perform();
		Assert.assertTrue(driver.getCurrentUrl().contains("/users/2"));
	}
	
	@Test
	public void testJS() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebElement btn = driver.findElement(By.xpath("//*[@id='btn1']"));
		((JavascriptExecutor)driver).executeScript("document.querySelector('#btn1').click();",btn);
		Assert.assertTrue(driver.findElement(By.id("txt1")).isDisplayed());
	}
	
	@Test
	public void testWindowSwitch() throws InterruptedException {
		driver.findElement(By.linkText("Click Here")).click();
		Set<String> handels = driver.getWindowHandles();
		for(String window : handels) {
			driver.switchTo().window(window);
			if(driver.getTitle().contains("New Window")) {
				break;
			}
		}
		System.out.println(driver.getCurrentUrl());
		Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/windows/new");
	}
	
	@Test
	public void testKeyPress() throws InterruptedException {
		Actions action = new Actions(driver);
		action.keyDown(driver.findElement(By.id("target")),Keys.CONTROL).keyUp(driver.findElement(By.id("target")),Keys.CONTROL).build().perform();
		String actual = driver.findElement(By.xpath("//p[@id='result']")).getText();
		Assert.assertEquals("You entered: CONTROL", actual);
	}
	
	@Test
	public void testTakeScreenshot() throws IOException {
		File src =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(System.getProperty("user.dir")+File.separator+"Screenshot.png"));
	}
	
	

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}


}
