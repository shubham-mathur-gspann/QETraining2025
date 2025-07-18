package day1;

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
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Testing {

	WebDriver driver;

	@BeforeMethod
	public void setup() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		new WebDriverWait(driver, Duration.ofSeconds(5));
		
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void testDropdown()
	{
		driver.get("https://the-internet.herokuapp.com/dropdown");
		
		Select dropdown=new Select(driver.findElement(By.id("dropdown")));
		dropdown.selectByValue("1");
		Assert.assertTrue(driver.findElement(By.id("dropdown")).getText().contains("Option 1"));
		dropdown.selectByContainsVisibleText("Option 2");
		
		Assert.assertTrue( driver.findElement(By.xpath("//select[@id='dropdown']/option[3]")).getText().contains("Option 2"));
		
			
	}
	
	@Test
	public void testFileUpload() throws InterruptedException
	{
		driver.get("https://the-internet.herokuapp.com/upload");
		String path=System.getProperty("user.dir")+File.separator+"Screenshots.PNG";
		driver.findElement(By.id("file-upload")).sendKeys(path);
		driver.findElement(By.id("file-submit")).click();
		
	    Assert.assertTrue(driver.findElement(By.id("uploaded-files")).getText().contains("Screenshots.PNG"));
		
	}
	@Test
	public void testFrames()
	{
		driver.get("https://the-internet.herokuapp.com/nested_frames");

		
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-top']")));
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-left']")));
		
		//driver.switchTo().defaultContent();
		Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("LEFT"));
		driver.switchTo().parentFrame();
		
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-middle']")));
		Assert.assertTrue(driver.findElement(By.id("content")).getText().contains("MIDDLE"));
		driver.switchTo().parentFrame();
		
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-right']")));
		Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("RIGHT"));
	   }
	@Test
	public void Howers()
	{
		driver.get("https://the-internet.herokuapp.com/hovers");
		WebElement img = driver.findElement(By.xpath("//div[@class='example']/descendant::img[1]"));
	    WebElement profileLink = driver.findElement(By.xpath("//a[@href='/users/1']"));
	    Actions actions = new Actions(driver);
	    actions.moveToElement(img).click(profileLink).build().perform();
	    
	    Assert.assertTrue(driver.getCurrentUrl().contains("/users/1"));
	    
		
	}
	@Test
	public void ClickJs() throws InterruptedException
	{
		driver.get("https://www.hyrtutorials.com/p/waits-demo.html");
		
		WebElement pressButton = driver.findElement(By.xpath("//*[@id='btn1']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();",pressButton);
	
		Assert.assertTrue(driver.findElement(By.id("btn1")).getText().contains("Add Textbox1"));
		 
	}
	
	@Test
	public void SwitchWindow() throws InterruptedException
	{
        driver.get("https://the-internet.herokuapp.com/windows");

		
		    String WindowHandle = driver.getWindowHandle();
		    driver.findElement(By.linkText("Click Here")).click();
		    Set<String> TotalWindowHandles = driver.getWindowHandles(); 
//		    for (String ID : TotalWindowHandles) 
//		    {
//		        driver.switchTo().window(ID);
//		        if (driver.getTitle().contains("new")) {
//		            break;
//		        }
//		    }
		    for (String ID : TotalWindowHandles) 
		    {
		        if (ID.equals(WindowHandle)==false) 
		        {
		        	driver.switchTo().window(ID);
		            break;
		        }
		    }
		    System.out.println(driver.getCurrentUrl());
		    
		   Assert.assertTrue(driver.getCurrentUrl().contains("new")); 
		    
	}

	
	@Test
	public void KeyPresses() throws InterruptedException
	{
		driver.get("https://the-internet.herokuapp.com/key_presses");

		
		Actions actions = new Actions(driver);
		actions.keyDown(driver.findElement(By.id("content")), Keys.ENTER).keyUp(driver.findElement(By.id("target")), Keys.ENTER).build().perform();
		
		Assert.assertTrue(driver.findElement(By.id("result")).getText().contains("You entered: ENTER"));
		
	}
	@Test
	public void ScreenShots() throws IOException
	{
		driver.get("https://www.google.com/");
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(System.getProperty("user.dir")+File.separator+"Screenshots.PNG"));
	
	}
	
	
}
