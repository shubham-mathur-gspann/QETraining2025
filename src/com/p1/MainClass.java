package com.gspann;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
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
import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.chromium.ChromiumDriver;

public class firstProjet {

	WebDriver driver;

	@BeforeMethod
	public void setup() {

		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://the-internet.herokuapp.com");

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void testCheckBoxes() {
		driver.get("https://the-internet.herokuapp.com/checkboxes");

		WebElement checkbox1 = driver.findElement(By.xpath("//input[1]"));

		if (!checkbox1.isSelected())
			checkbox1.click();

		Assert.assertTrue(checkbox1.isSelected());

		WebElement checkbox2 = driver.findElement(By.xpath("//input[2]"));

		boolean isSelectedBtn2 = checkbox2.isSelected();

		if (isSelectedBtn2) {
			checkbox2.click();
			isSelectedBtn2 = checkbox2.isSelected();

		}

		Assert.assertFalse(isSelectedBtn2);
	}

	@Test
	public void dragAndDrop() {
		driver.get("https://the-internet.herokuapp.com/drag_and_drop");
		WebElement source = driver.findElement(By.xpath("//div[@id='column-a']"));
		WebElement destination = driver.findElement(By.xpath("//div[@id='column-b']"));

		Actions actions = new Actions(driver);
		actions.dragAndDrop(source, destination).build().perform();

		WebElement result = driver.findElement(By.xpath("//div[@id='column-a']/header"));
		System.out.println(result.getText());

		Assert.assertEquals(result.getText(), "B");

	}

	@Test
	public void testhandlingAlerts() {
		driver.get("https://the-internet.herokuapp.com/javascript_alerts");
		WebElement alertBtn = driver.findElement(By.xpath("//button[text()='Click for JS Alert']"));
		alertBtn.click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		WebElement result = driver.findElement(By.id("result"));
		Assert.assertEquals(result.getText(), "You successfully clicked an alert");

	}

	@Test
	public void testhandlingAlert2() {
		driver.get("https://the-internet.herokuapp.com/javascript_alerts");
		WebElement alertBtn = driver.findElement(By.xpath("//button[text()='Click for JS Confirm']"));
		alertBtn.click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		// alert.dismiss();

		WebElement result = driver.findElement(By.id("result"));
//			Assert.assertEquals(result.getText(), result;
		Assert.assertEquals(result.getText(), "You clicked: Ok");
	}

	@Test
	public void testhandlingAlert3() {
		driver.get("https://the-internet.herokuapp.com/javascript_alerts");
		WebElement prompt = driver.findElement(By.xpath("//button[text()='Click for JS Prompt']"));
		prompt.click();
		Alert alert = driver.switchTo().alert();
		alert.sendKeys("Sakshi Aggarwal");
		alert.accept();
		WebElement result = driver.findElement(By.id("result"));
		Assert.assertEquals(result.getText(), "You entered: Sakshi Aggarwal");

	}

	@Test
	public void testContectClick() {
		driver.get("https://the-internet.herokuapp.com/context_menu");
		WebElement squareBox = driver.findElement(By.id("hot-spot"));
		Actions action = new Actions(driver);
		action.contextClick(squareBox).build().perform();
		Alert alert = driver.switchTo().alert();
		alert.accept();

	}

	@Test
	public void TestInfiniteScroll() throws InterruptedException {
		driver.get("https://the-internet.herokuapp.com/infinite_scroll");
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");

	}

	@Test
	public void testDropDown() {
		driver.get("https://the-internet.herokuapp.com/dropdown");
		Select dropDown = new Select(driver.findElement(By.id("dropdown")));
		dropDown.selectByVisibleText("Option 1");
		String Option1 = dropDown.getFirstSelectedOption().getText();
		Assert.assertEquals(Option1, "Option 1");
		dropDown.selectByIndex(2);
		String Option2 = dropDown.getFirstSelectedOption().getText();
		Assert.assertEquals(Option2, "Option 2");
	}
	
	@Test
	public void testFileUpload() throws InterruptedException {
		driver.get("https://the-internet.herokuapp.com/upload");
		String path =System.getProperty("user.dir")+File.separator+"Capture.png";
		System.out.println(path);
        driver.findElement(By.id("file-upload")).sendKeys(path);
        driver.findElement(By.id("file-submit")).click();
        String message = driver.findElement(By.tagName("h3")).getText();
        Assert.assertEquals(message, "File Uploaded!");
        
       }
	@Test
	public void testframes() {
		driver.get("https://the-internet.herokuapp.com/nested_frames");
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-top']")));
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-left']")));
		String text =driver.findElement(By.xpath("//body[contains ( text() , 'LEFT')] ")).getText();
		System.out.println(text);
		Assert.assertEquals(text, "LEFT");
		 
		driver.switchTo().parentFrame();
		driver.switchTo().frame(driver.findElement(By.name("frame-middle")));
		String text2 =driver.findElement(By.id("content")).getText();
		System.out.println(text2);
		Assert.assertEquals(text2,"MIDDLE");
	}
	@Test
	public void testHovers() {
		driver.get("https://the-internet.herokuapp.com/hovers");
		
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//div[@class='figure']/descendant::img[1]")))
				.click(driver.findElement(By.xpath("//a[@href='/users/1']"))).build().perform();
 
		System.out.println(driver.getCurrentUrl());
 
		Assert.assertTrue(driver.getCurrentUrl().contains("/users/1"));
	}
	
	@Test
	public void testSwitchWindows() {
		driver.get("https://the-internet.herokuapp.com/windows");
		 String parentWindowHandle =driver.getWindowHandle();
		 System.out.println(parentWindowHandle);
		 driver.findElement(By.linkText("Click Here")).click();
		Set<String> allWindowHandles =driver.getWindowHandles();
		for(String winId : allWindowHandles) {
			if(winId.equals(parentWindowHandle)==false) {
				driver.switchTo().window(winId);
				
			List<WebElement> windowText =driver.findElements(By.tagName("New Window"));
			System.out.println(windowText);
			Assert.assertEquals(windowText,"New Window");
			}
		}
		
	}	
	@Test
	public void testClickUsingJS() {
		driver.get("https://www.hyrtutorials.com/p/waits-demo.html");
         driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		
		((JavascriptExecutor)driver).executeScript("document.querySelector('#btn2').click();");
		
		
       Assert.assertTrue(driver.findElement(By.id("txt1")).isDisplayed());
          
	}
	
	@Test
	public void testKeyPresses() {
		driver.get("https://the-internet.herokuapp.com/key_presses");
		Actions action = new Actions(driver);
		action.keyDown(driver.findElement(By.id("target")), Keys.ARROW_DOWN).keyUp(driver.findElement(By.id("target")), Keys.ENTER).build().perform();
		
	
		String result = driver.findElement(By.id("result")).getText();
		
		
		Assert.assertEquals(result, "You entered: ARROW_DOWN");
	    
	}
	@Test
	public void testScreenshots() throws IOException {
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(System.getProperty("user.dir")+File.separator+"sakshi.png"));
		
	}
		
				
				
			
		
		
		 
	}


