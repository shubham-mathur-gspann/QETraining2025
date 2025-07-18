package com.gspann;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class PracticeDay2 {
	WebDriver driver;

	@Test
	public void testDropDown() throws InterruptedException {
		Select dropDown = new Select(driver.findElement(By.id("dropdown")));
		dropDown.selectByVisibleText("Option 1");
		dropDown.selectByValue("2");
		dropDown.selectByIndex(1);
		WebElement option1 = driver.findElement(By.xpath("//option[contains(text(),'Option 1')]"));
		Assert.assertTrue(option1.isSelected());
	}
	
	@Test
	public void testFileUpload() throws InterruptedException {
		String path = System.getProperty("user.dir")+"\\picture.png";
		driver.findElement(By.id("file-upload")).sendKeys(path);
		driver.findElement(By.id("file-submit")).click();
		String expectedText = "File Uploaded!";
		String actual = driver.findElement(By.xpath("//div[@class='example']/h3")).getText();
		Assert.assertEquals(expectedText, actual);
	}

	@Test
	public void testFrames() throws InterruptedException {
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-top']")));
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-left']")));
		String leftText = driver.findElement(By.xpath("//body[contains(text(),'LEFT')]")).getText();
		driver.switchTo().parentFrame();
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-middle']")));
		String middleText = driver.findElement(By.id("content")).getText();
		Assert.assertEquals(middleText, "MIDDLE");
	}
	
	@Test
	public void testHovers() {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//div[@class='example']/descendant::img[1]"))).click(driver.findElement(By.xpath(" //a[@href='/users/1']"))).build().perform();
		Assert.assertTrue(driver.getCurrentUrl().contains("/users/1"));
	}
	
	@Test
	public void testJsBtn() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		((JavascriptExecutor) driver).executeScript("document.querySelector('#btn1').click();");
		Assert.assertTrue(driver.findElement(By.id("txt1")).isDisplayed());
 
	}
	
	@Test
	public void testWindowSwitch() {
		String windowHandler = driver.getWindowHandle();
		driver.findElement(By.linkText("Click Here")).click();
		Set<String> windowsHandlers = driver.getWindowHandles();
		for(String windowID: windowsHandlers) {
			if(windowID.equals(windowHandler)==false) {
				driver.switchTo().window(windowID);
				break;
			}
		}
		System.out.println(driver.getCurrentUrl());
		Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/windows/new");
	}
	
	@Test
	public void testKeyPresses() {
		Actions action = new Actions(driver);
		action.keyDown(driver.findElement(By.id("target")), Keys.CONTROL).keyUp(driver.findElement(By.id("target")), Keys.CONTROL).build().perform();
		String exp = driver.findElement(By.xpath("//p[@id='result']")).getText();
		Assert.assertEquals("You entered: CONTROL", exp);
	}
	
	@Test
	public void testScreenshots() throws IOException {
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(System.getProperty("user.dir")+File.separator+"YourScreenshot.png"));
	}
	
	@BeforeMethod
	public void setup() {
		driver = new ChromeDriver();
		driver.get("https://www.hyrtutorials.com/p/waits-demo.html");
		driver.manage().window().maximize();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
