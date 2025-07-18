package com.gspann1;


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
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class TestForge {
	WebDriver driver;

	@BeforeMethod
	public void setup() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://the-internet.herokuapp.com/checkboxes");
	}

	@Test
	public void testCheckbox() {
		driver.get("https://the-internet.herokuapp.com/checkboxes");
		WebElement btn1 = driver.findElement(By.xpath("//form[@id='checkboxes']/child::input[1]"));

		if (btn1.isSelected() == false)
			btn1.click();
		Assert.assertTrue(btn1.isSelected(), "Not Clicked");

		WebElement btn2 = driver.findElement(By.xpath("//form[@id='checkboxes']/child::input[2]"));

		if (btn2.isSelected() == false)
			btn2.click();
		Assert.assertTrue(btn2.isSelected(), "Not Clicked");

	}

	@Test
	public void testDragAndDrop() {
		driver.get("https://the-internet.herokuapp.com/drag_and_drop");
		WebElement dragndrop = driver.findElement(By.xpath("//div[@id='column-a']"));
		WebElement dragndrop2 = driver.findElement(By.xpath("//div[@id='column-b']"));
		Actions action = new Actions(driver);
		action.dragAndDrop(dragndrop, dragndrop2).build().perform();
		String Bdrop = driver.findElement(By.xpath("//div[@id='column-a']/header")).getText();
		Assert.assertEquals("B", Bdrop);

	}

	@Test
	public void testJS() throws InterruptedException {
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
		String ex = driver.findElement(By.xpath("//p[@id='result']")).getText();
		Assert.assertEquals(actual, ex);

		driver.findElement(By.xpath("//*[contains(text(),'Click for JS Confirm')]")).click();
		a.dismiss();
		String actualt = "You clicked: Cancel";
		String exp = driver.findElement(By.xpath("//p[@id='result']")).getText();
		Assert.assertEquals(actualt, exp);

		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		Alert ab = driver.switchTo().alert();
		ab.sendKeys("Rashika");
		ab.accept();
		String actualtext = "I am Rashika Sharma";
		String expect = driver.findElement(By.xpath("//p[@id='result']")).getText();
		Assert.assertEquals(actualtext, expect);

	}

	@Test
	public void testContext() throws InterruptedException {
		driver.get("https://the-internet.herokuapp.com/context_menu");
		WebElement b = driver.findElement(By.xpath("//div[@id='hot-spot']"));
		Actions a = new Actions(driver);
		a.contextClick(b).build().perform();
		Alert ab = driver.switchTo().alert();
		ab.accept();

	}
	@Test
	
	public void testScroll() throws InterruptedException {
		driver.get("https://the-internet.herokuapp.com/infinite_scroll");
		Thread.sleep(2000);
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,document.body.scrollHeight);");
	
	}
 

	@Test
	public void testDropDown() throws InterruptedException {
		driver.get("https://the-internet.herokuapp.com/dropdown");
		Select dropdown=new Select(driver.findElement(By.xpath("//select[@id='dropdown']")));
		dropdown.selectByValue("1");
	    Assert.assertEquals(dropdown.getFirstSelectedOption().getText(), "Option 1");
		Thread.sleep(2000);
		dropdown.selectByValue("2");
		Assert.assertEquals(dropdown.getFirstSelectedOption().getText() ,"Option 2");
	}
	@Test
	public void testFileUpload() throws InterruptedException {
		String path=System.getProperty("user.dir")+File.separator+"git sheet.webp";
		driver.get("https://the-internet.herokuapp.com/upload");
		WebElement choosebtn=driver.findElement(By.xpath("//input[@id='file-upload']"));
		choosebtn.sendKeys(path);
		driver.findElement(By.id("file-submit")).click();
		WebElement successMsg = driver.findElement(By.tagName("h3"));
		Assert.assertEquals(successMsg.getText(), "File Uploaded!");	
	}
	@Test
	
	public void testFrame() throws InterruptedException {
		driver.get("https://the-internet.herokuapp.com/nested_frames");
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-top']")));
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-left']")));
		WebElement leftframe = driver.findElement(By.tagName("body"));
		Assert.assertEquals(leftframe.getText().trim(), "LEFT");
		driver.switchTo().parentFrame();
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-middle']")));
		WebElement middleframe = driver.findElement(By.xpath("//div[@id='content']"));
		String text=middleframe.getText();
		Assert.assertEquals(text, "MIDDLE");
	}
 
	
	@Test
	public void testHover() throws InterruptedException {
		driver.get("https://the-internet.herokuapp.com/hovers");
		WebElement hover1=driver.findElement(By.xpath("//div[@class='figure']/img[1]"));
		Actions action=new Actions(driver);
		action.moveToElement(hover1).build().perform();
		WebElement viewPr=driver.findElement(By.xpath("//a[text()='View profile']"));
		viewPr.click();
		String text=driver.findElement(By.tagName("h1")).getText();
		Assert.assertEquals(text,"Not Found");
		
	}
 

@Test
public void testHover2() throws InterruptedException {
	driver.get("https://the-internet.herokuapp.com/hovers");
	WebElement hover1=driver.findElement(By.xpath("//div[@id='content']/div/div[2]/div/a"));
	Actions action=new Actions(driver);
	action.moveToElement(hover1).build().perform();
	WebElement viewPr=driver.findElement(By.xpath("//div[@class='figure'][2]/img"));
	viewPr.click();
	String text=driver.findElement(By.tagName("h1")).getText();
	Assert.assertEquals(text,"Not Found");
	
	
}

@Test
public void JSClickTest() throws InterruptedException {
	driver.get("https://www.hyrtutorials.com/p/waits-demo.html");
	((JavascriptExecutor)driver).executeScript("document.querySelector('#btn2').click();");
	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	 WebElement box2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Textbox2']")));
	Assert.assertEquals(box2.isDisplayed(), true);
	
}

@Test
public void testSwitchwindows() throws InterruptedException {
	driver.get("https://the-internet.herokuapp.com/windows");
	String parentWin=driver.getWindowHandle();
	WebElement clickbtn=driver.findElement(By.xpath("//a[contains(text(),'Click')]"));
	clickbtn.click();
	Set<String> allWindows=driver.getWindowHandles();
	for(String i:allWindows) {
		if (!i.equals(parentWin)) {
            driver.switchTo().window(i);
            String actualTitle = driver.getTitle();
            Assert.assertEquals(actualTitle, "New Window", "Switched window title does not match");
            break;
    }
	}
}





@Test
public void KeyPresstest() {
    driver.get("https://the-internet.herokuapp.com/key_presses");

    WebElement inputField = driver.findElement(By.id("target"));
    inputField.sendKeys("r");  

    WebElement result = driver.findElement(By.id("result"));
    String resultText = result.getText();

    Assert.assertEquals(resultText, "You entered: R", "Key press result mismatch");
}


@Test
public void TakeScreenshotTest() throws InterruptedException, IOException {
	 driver.get("https://the-internet.herokuapp.com/");
	File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(src, new File(System.getProperty("user.dir")+File.separator+"SS.png"));
//	FileUtils.copyFile(src, new File(System.getProperty("user.dir")+File.separator+"SS.png"));
	
}


 
 
 

	@BeforeTest
	public void beforeMethod() {
		driver = new ChromeDriver();

		driver.manage().window().maximize();

	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
