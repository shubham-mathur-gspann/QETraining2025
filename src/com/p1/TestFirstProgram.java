package com.gspann;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

import java.awt.Desktop.Action;
import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestFirstProgram {
	private static final String prority = null;
	WebDriver driver;

	@BeforeMethod
	public void beforeMethod() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();

	}

	
	@Test(priority=1)
	public void testInfiniteScroll() throws InterruptedException {
		driver.get("https://the-internet.herokuapp.com/infinite_scroll");
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,document.body.scrollHeight);");
		
	}

	@Test(priority=2)
	public void testDropDown() throws InterruptedException {
		driver.get("https://the-internet.herokuapp.com/dropdown");
		Select dropDown = new Select(driver.findElement(By.id("dropdown")));
		dropDown.selectByVisibleText("Option 1");
		Assert.assertEquals("Option 1", dropDown.getFirstSelectedOption().getText());

		dropDown.selectByIndex(2);
		Assert.assertEquals("Option 2", dropDown.getFirstSelectedOption().getText());

		dropDown.selectByValue("1");
		Assert.assertEquals("Option 1", dropDown.getFirstSelectedOption().getText());

		dropDown.selectByContainsVisibleText("2");
		Assert.assertEquals("Option 2", dropDown.getFirstSelectedOption().getText());
	}

	@Test(priority=3)
	public void testFileUploaded() {
		driver.get("https://the-internet.herokuapp.com/upload");
		String path=System.getProperty("user.dir")+File.separator+"Agile.PNG";
		driver.findElement(By.id("file-upload")).sendKeys(path);
		driver.findElement(By.id("file-submit")).click();
		Assert.assertEquals("Agile.PNG",driver.findElement(By.id("uploaded-files")).getText());
				
	}

	
	@Test(priority=4)
	public void testFrames() {
		driver.get("https://the-internet.herokuapp.com/frames");
		driver.findElement(By.xpath("//div[@class='example']/ul/li/a")).click();
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-top']")));
		WebDriver frameleft = driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-left']")));
		Assert.assertTrue( frameleft.findElement(By.tagName("body")).getText().contains("LEFT") );

		driver.switchTo().parentFrame();
		WebDriver framemiddle = driver.switchTo().frame(driver.findElement(By.name("frame-middle")));
		Assert.assertTrue( framemiddle.findElement(By.id("content")).getText().contains("MIDDLE") );
		
		driver.switchTo().parentFrame();
		WebDriver frameright = driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-right']")));
		Assert.assertTrue( frameright.findElement(By.tagName("body")).getText().contains("RIGHT") );
		
		driver.switchTo().defaultContent();
		WebDriver framebottom = driver.switchTo().frame(driver.findElement(By.name("frame-bottom")));
		Assert.assertTrue( framebottom.findElement(By.tagName("body")).getText().contains("BOTTOM") );
	}

	@Test(priority=5)
	public void clickJS() {
		driver.get("https://www.hyrtutorials.com/p/waits-demo.html");
		WebElement addBtn = driver.findElement(By.xpath("//*[@id='btn1']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].click",addBtn);
		Assert.assertEquals(true, addBtn.getText().contains("Add Textbox1"));
		
	}

	@Test(priority=6)
	public void switchWindows() {
		driver.get("https://the-internet.herokuapp.com/windows");
		String mainWindow = driver.getWindowHandle();
        driver.findElement(By.linkText("Click Here")).click();
        
        Set<String> allWindows = driver.getWindowHandles();
        
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(mainWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        
        Assert.assertEquals(true,driver.findElement(By.tagName("h3")).getText().contains("New Window") );
		
	}

	@Test(priority=7)
	public void testKeyPress() {
		driver.get("http://the-internet.herokuapp.com/key_presses");
		Actions actions = new Actions(driver);
		actions.keyDown(driver.findElement(By.id("content")), Keys.BACK_SPACE)
		.keyUp(driver.findElement(By.id("target")), Keys.BACK_SPACE).build().perform();
		
		Assert.assertEquals("You entered: BACK_SPACE", driver.findElement(By.id("result")).getText());
	}

	@Test(priority=8)
	public void testHovers() {
		driver.get("http://the-internet.herokuapp.com/hovers");
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath("(//*[@alt='User Avatar'])[1]")))
				.click(driver.findElement(By.xpath("//a[@href='/users/1']"))).build().perform();
		Assert.assertTrue(driver.getCurrentUrl().contains("/users/1"));
	}

	@Test(priority=9)
	public void testScreenShots() throws IOException {
		driver.get("https://the-internet.herokuapp.com/abtest");
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + File.separator + "Screenshot.png";
		File mySS = new File(path);
		FileUtils.copyFile(src, mySS);
		Assert.assertTrue(mySS.exists());
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
