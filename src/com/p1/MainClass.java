package com.gspann;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap.KeySetView;

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

public class firstTest {
	WebDriver driver;

	@BeforeMethod
	public void setup() {

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://the-internet.herokuapp.com/windows");
	}

	@Test
	public void checkBoxes() {
		WebElement firstCheckBox = driver.findElement(By.xpath("//*[@id = 'checkboxes']/descendant::input[1]"));
		if (firstCheckBox.isSelected() == false)
			firstCheckBox.click();
		Assert.assertTrue(firstCheckBox.isSelected());

	}

	@Test
	public void dragAndDrop() throws InterruptedException {
		WebElement source = driver.findElement(By.id("column-a"));
		WebElement target = driver.findElement(By.id("column-b"));
		Actions action = new Actions(driver);
		action.dragAndDrop(source, target).build().perform();
		;

		WebElement results = driver.findElement(By.xpath("//div[@id='column-a']/header"));
		System.out.println(results.getText());

		Assert.assertEquals(results.getText(), "B");
		Thread.sleep(7000);

	}

	@Test
	public void jsAlert() throws InterruptedException {
		// Click for JS Alert
//		driver.findElement(By.xpath("//*[contains(text(),'Click for JS Alert')]")).click();
//		Thread.sleep(4000);
//		Alert alert = driver.switchTo().alert();
//		alert.accept();
//		WebElement results = driver.findElement(By.id("result"));
//		System.out.println(results.getText());
//		Thread.sleep(2000);
//		Assert.assertEquals(results.getText(), "You successfully clicked an alert");

//		
		driver.findElement(By.xpath("//*[contains(text(),'Click for JS Confirm')]")).click();
		Thread.sleep(2000);
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
		WebElement results = driver.findElement(By.id("result"));
		System.out.println(results.getText());
		Thread.sleep(2000);
		Assert.assertEquals(results.getText(), "Click for JS Confirm");

//		driver.findElement(By.xpath("//*[contains(text(),'Click for JS Prompt')]")).click();
//		Thread.sleep(2000);
//		Alert alert = driver.switchTo().alert();
//		alert.sendKeys("Rohan Singh");
//		alert.accept();
//		WebElement results = driver.findElement(By.id("result"));
//		System.out.println(results.getText());
//		Thread.sleep(2000);
//		Assert.assertEquals(results.getText(), "You entered: Rohan Singh");

	}

	@Test
	public void contextMenu() throws InterruptedException {
		Actions actions = new Actions(driver);
		WebElement box = driver.findElement(By.id("hot-spot"));
		actions.contextClick(box).build().perform();
		Alert alert = driver.switchTo().alert();
		Thread.sleep(2000);
		alert.accept();
	}

	@Test
	public void testScroller() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,document.body.scrollHeight);");

	}

	@Test
	public void testDropDown() throws InterruptedException {
		Select dropDown = new Select(driver.findElement(By.id("dropdown")));
		dropDown.selectByValue("2");
		WebElement option2 = driver.findElement(By.xpath("//select[@id='dropdown']/descendant::option[3]"));
		Assert.assertTrue(option2.isSelected());
	}
	
	@Test
	public void testFileUpload() throws InterruptedException {
		String path = System.getProperty("user.dir")+ File.separator + ("capture.png");
		driver.findElement(By.id("file-upload")).sendKeys(path);
		
		driver.findElement(By.id("file-submit")).click();
		
		WebElement results = driver.findElement(By.xpath("//*[contains(text(),'File Uploaded!')]"));
        System.out.println(results.getText());
    
       Assert.assertEquals(results.getText(), "File Uploaded!");
		
		
	}
	
	@Test
	public void testframes() throws InterruptedException {
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name ='frame-top']")));
	
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name ='frame-left']")));
		String text =driver.findElement(By.xpath("//body[contains(text(),'LEFT')]")).getText();
		System.out.println(text);
		driver.switchTo().parentFrame();
		driver.switchTo().frame(driver.findElement(By.name("frame-middle")));
		
		String secondText = driver.findElement(By.id("content")).getText();
		System.out.println(secondText);
		Assert.assertEquals(secondText, "MIDDLE");	
	}
	
	@Test
	public void testHover() throws InterruptedException {
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath("//div[@class='example']/descendant::img[1]")))
		.click(driver.findElement(By.xpath("//a[@href = '/users/1']")))
		.build().perform();
		Assert.assertTrue(driver.getCurrentUrl().contains("/users/1"));
		
		
	}
	@Test
	public void testSwitchWindow() throws InterruptedException {
	     String parentWindow = driver.getWindowHandle();
	    driver.findElement(By.xpath("//a[contains(text(),'Click Here')]")).click();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.numberOfWindowsToBe(2));
	    Set<String> allWindows = driver.getWindowHandles();
	    System.out.println(allWindows);
	    for(String w1 : allWindows){
	        if(w1.equals(parentWindow)==false) {
	            driver.switchTo().window(w1);
	            break;

	        }

	    }
	    System.out.println(driver.getCurrentUrl());
	    Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/windows/new");
	}
	
	@Test
	public void testKeyPresses() throws InterruptedException {
		Actions action = new Actions(driver);
		action.keyDown(driver.findElement(By.id("target")),Keys.ENTER)
		.keyUp(driver.findElement(By.id("target")),Keys.ENTER)
		.build().perform();
		WebElement results = driver.findElement(By.id("result"));
        System.out.println(results.getText());
        //Thread.sleep(2000);
    
      Assert.assertEquals(results.getText(), "You entered: ENTER");
		
	}
	@Test
	public void testScreenshot() throws IOException {
	        TakesScreenshot ts = (TakesScreenshot) driver;
	        File source = ts.getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(source, new File(System.getProperty("user.dir")+File.separator+"Screenshot.png"));
	}
	
	@Test
	public void testDemoJS(){
	    
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    WebElement btn = driver.findElement(By.xpath("//button[@id='btn1']"));
	    JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
	    jsExecutor.executeScript("arguments[0].click()", btn);
	    WebElement textBox = driver.findElement(By.id("txt1"));
	    Assert.assertTrue(textBox.isDisplayed());
	}
	
	

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
