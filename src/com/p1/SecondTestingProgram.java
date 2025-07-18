package com.gspann;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SecondTestingProgram {
	
	WebDriver driver;

	@BeforeMethod
	public void setup() throws InterruptedException{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
	}
	
	
	@Test(priority=1)
	public void dropDownUsingSelect()
	{
		driver.get("https://the-internet.herokuapp.com/dropdown");
		
		Select dpdn=new Select(driver.findElement(By.id("dropdown")));
		dpdn.selectByValue("1");
		Assert.assertEquals(dpdn.getFirstSelectedOption().getText().trim(), "Option 1");
		
		dpdn.selectByIndex(2);
		Assert.assertEquals(dpdn.getFirstSelectedOption().getText().trim(), "Option 2");
		
	}
	
	@Test(priority=2)
	public void fileUploader()
	{
		driver.get("https://the-internet.herokuapp.com/upload");
		String path=System.getProperty("user.dir")+File.separator+"gspann.jpeg";
		System.out.println(path);
		driver.findElement(By.id("file-upload")).sendKeys(path);
		driver.findElement(By.id("file-submit")).click();
		
		WebElement uploadedMessage=driver.findElement(By.tagName("h3"));
		Assert.assertEquals(uploadedMessage.getText(), "File Uploaded!");
	}
	
	@Test(priority=3)
	public void frames()
	{
		driver.get("https://the-internet.herokuapp.com/nested_frames");
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-top']")));
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-left']")));
		WebElement leftText = driver.findElement(By.xpath("//body[contains(text(), 'LEFT')]"));
	    Assert.assertTrue(leftText.getText().contains("LEFT"));
		
		driver.switchTo().parentFrame();
		driver.switchTo().frame(driver.findElement(By.name("frame-middle")));
		WebElement middleText = driver.findElement(By.id("content"));
	    Assert.assertEquals(middleText.getText(), "MIDDLE");
		
	}
	
	@Test(priority=4)
	public void testHovers()
	{
		driver.get("https://the-internet.herokuapp.com/hovers");
		Actions actions=new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath("//div[@class='example']/descendant::img[1]")))
        .click(driver.findElement(By.xpath("//a[@href='/users/1']")))
        .build()
        .perform();
		Assert.assertTrue(driver.getCurrentUrl().contains("/users/1"), "User profile page not loaded.");
	}
	
	
	@Test(priority=5)
	public void keyPresses() {
		driver.get("https://the-internet.herokuapp.com/key_presses");
		
		Actions actions = new Actions(driver);
		actions.keyDown(driver.findElement(By.id("target")), Keys.SHIFT).keyUp(driver.findElement(By.id("target")), Keys.SHIFT).build().perform();
		
		 WebElement result = driver.findElement(By.id("result"));
		 Assert.assertTrue(result.getText().contains("SHIFT"), "Key press result not detected correctly.");
	}
	
	
	@Test(priority=6)
    public void testMultipleWindows() {
		driver.get("https://the-internet.herokuapp.com/windows");
		
		
        String mainWindow = driver.getWindowHandle();
        driver.findElement(By.linkText("Click Here")).click();
        Set<String> allWindows = driver.getWindowHandles();
        Assert.assertEquals(allWindows.size(), 2, "Expected 2 windows to be open");
        
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(mainWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        WebElement heading = driver.findElement(By.tagName("h3"));
        Assert.assertEquals(heading.getText(), "New Window", "New window content is incorrect");
	}
	
	@Test(priority=7)
	public void testCaptureScreenshot() throws IOException {
		driver.get("https://the-internet.herokuapp.com/");
		
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String path=System.getProperty("user.dir")+File.separator+"Screenshot.png"; 
		File dest = new File(path);
	    FileUtils.copyFile(src, dest);
	 
	    Assert.assertTrue(dest.exists(), "Screenshot was not saved successfully.");
	}
	
	@Test(priority=8)
	public void clickUsingJS() {
		driver.get("https://www.hyrtutorials.com/p/waits-demo.html");
	    WebElement addBtn = driver.findElement(By.xpath("//*[@id='btn1']"));
	    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", addBtn);
	    Assert.assertEquals(true, addBtn.getText().contains("Add Textbox1"));
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
