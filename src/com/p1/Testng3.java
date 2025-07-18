package TestNg;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;
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
import org.apache.commons.io.FileUtils;



public class Testng3 {
	WebDriver driver;


	@BeforeMethod
	public void Setup() throws InterruptedException {
		driver = new ChromeDriver();
		driver.manage().window().maximize();

	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}
@Test(priority = 1)
	public void dropdown() {
		driver.get("https://the-internet.herokuapp.com/dropdown");
	Select dropdown=new Select(	driver.findElement(By.id("dropdown")));
	dropdown.selectByValue("1");
	String selectedoption=dropdown.getFirstSelectedOption().getText();
	Assert.assertEquals(selectedoption,"Option 1");
		
	}
	
@Test (priority =2)
public void fileUpload() throws InterruptedException
{
	driver.get("https://the-internet.herokuapp.com/upload");
	String path=System.getProperty("user.dir")+File.separator+"Picture.PNG";
	driver.findElement(By.id("file-upload")).sendKeys(path);
	driver.findElement(By.id("file-submit")).click();
	Assert.assertTrue(driver.findElement(By.id("uploaded-files")).getText().contains("Picture"));
}
	
	@Test(priority =3)
	public void frame() throws InterruptedException
	{
		driver.get("https://the-internet.herokuapp.com/frames");
	
			driver.findElement(By.linkText("Nested Frames")).click();
			driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-top']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-left']")));
			String text = driver.findElement(By.xpath("//body[contains(text(),'LEFT')]")).getText();
			System.out.println(text);
			Assert.assertEquals(text, "LEFT", "Left frame text mismatch");
			driver.switchTo().parentFrame();
			driver.switchTo().frame(driver.findElement(By.name("frame-middle")));
			String middleText = driver.findElement(By.id("content")).getText();
			System.out.println(middleText); 
			Assert.assertEquals(middleText, "MIDDLE", "Middle frame text mismatch");
			//driver.switchTo().defaultContent(); 
	
	
	}	
	
	@Test(priority =4)
	public void hover()
	{
		driver.get("https://the-internet.herokuapp.com/hovers");
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath("//div[@class='example']/descendant::img[1]")))
		.click(driver.findElement(By.xpath("//a[@href='/users/1']"))).build().perform();
		Assert.assertTrue(driver.getCurrentUrl().contains("/users/1"));
 
	}
	
	@Test(priority =5)
	public void switchingWindows()
	{
		String parentwindow=driver.getWindowHandle();
		driver.get("https://the-internet.herokuapp.com/windows");
		driver.findElement(By.xpath("//*[contains(text(),'Click Here')]")).click();
		Set<String> allwindows= driver.getWindowHandles();
		for (String windowid : allwindows) {
			if (windowid.equals(parentwindow)==false) {
				driver.switchTo().window(windowid);
				Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/windows/new");
			}
		}
	}
	@Test (priority =6)
	public void keypress()
	{
		 driver.get("https://the-internet.herokuapp.com/key_presses");
		Actions actions = new Actions(driver);
		actions.keyDown(driver.findElement(By.id("target")), Keys.SHIFT)
		.keyUp(driver.findElement(By.id("target")), Keys.SHIFT).build().perform();
		
	    WebElement result = driver.findElement(By.xpath("//*[@class=\"example\"]//p[2]"));
	    Assert.assertTrue(result.getText().contains("SHIFT"), "Key press Does not Found.");
	}

      
	

			
		
	@Test(priority =7)
	public void testCaptureScreenshot() throws IOException, InterruptedException
{
		driver.get("https://the-internet.herokuapp.com/infinite_scroll");
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(System.getProperty("user.dir")+File.separator+"MyScreenshot.png"));
	}
	
 @Test (priority =8)
 public void javscriptExecutor()
 {
	 driver.get("https://www.hyrtutorials.com/p/waits-demo.html");
	 WebElement addBtn = driver.findElement(By.xpath("//*[@id='btn1']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();",addBtn);
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	    WebElement textBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txt1")));
	    Assert.assertTrue(textBox.isDisplayed(), "Textbox was not displayed after clicking Add Button");
 }
	
}
	

		
	
	
	
	
	
	
	

