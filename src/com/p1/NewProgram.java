package NewP;

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

public class NewProgram {

	WebDriver driver;

	@BeforeMethod
	public void setup() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.hyrtutorials.com/p/waits-demo.html");
	}
	
	@Test
	public void infiniteScrollTest() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		for (int i = 0; i < 5; i++) { // Scroll 5 times
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			 // wait for new content to load
		}

		
		 List<WebElement> paragraphs =
		 driver.findElements(By.cssSelector(".jscroll-added"));
		 System.out.println("Paragraphs loaded: " + paragraphs.size()); for
		 (WebElement p : paragraphs) { System.out.println(p.getText()); }
		 
	}
	
	

	@Test
	public void testDropDownUsingSelect() throws InterruptedException {
		WebElement dropdownElement = driver.findElement(By.id("dropdown"));
		Select dropDown = new Select(dropdownElement);

		dropDown.selectByVisibleText("Option 1");
		
		dropDown.selectByIndex(2);
		

		dropDown.selectByValue("1");
		
	}
	
	
	
	@Test
	public void testFileUpload() throws InterruptedException {
		String filePath = System.getProperty("user.dir") + "\\Capture.PNG";
		System.out.println("File Path: " + filePath);

		driver.findElement(By.id("file-upload")).sendKeys(filePath);
		

		driver.findElement(By.id("file-submit")).click();
		
		WebElement uploadedFileName = driver.findElement(By.id("uploaded-files"));
		String uploadedName = uploadedFileName.getText();

		// Assertion: Verify uploaded file name matches
		Assert.assertEquals(uploadedName, "Capture.PNG", "Uploaded file name mismatch!");

		// Assertion: Verify success message
		WebElement successMsg = driver.findElement(By.tagName("h3"));
		Assert.assertEquals(successMsg.getText(), "File Uploaded!", "Upload success message mismatch!");
	}
	
	
	
	

	@Test
	public void testFrames() throws InterruptedException {
		
		
		// Switch to the TOP frame using XPath
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-top']")));
		
		

		// Switch to LEFT frame inside TOP frame
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-left']")));
		
		
		String leftText = driver.findElement(By.xpath("//body[contains(text(),'LEFT')]")).getText().trim();
		System.out.println("Left Frame Text: " + leftText);
		Assert.assertEquals(leftText, "LEFT", "Left frame text is incorrect!");

		
		// Go back to parent (TOP frame)
		driver.switchTo().parentFrame();

		// Switch to MIDDLE frame inside TOP frame
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-middle']")));
		String middleText = driver.findElement(By.xpath("//div[@id='content']")).getText().trim();
		System.out.println("Middle Frame Text: " + middleText);
		Assert.assertEquals(middleText, "MIDDLE", "Middle frame text is incorrect!");

		// Back to default content (main page)
		driver.switchTo().defaultContent();

		// Switch to BOTTOM frame using XPath
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-bottom']")));
		String bottomText = driver.findElement(By.xpath("//body[contains(text(),'BOTTOM')]")).getText().trim();
		System.out.println("Bottom Frame Text: " + bottomText);
		Assert.assertEquals(bottomText, "BOTTOM", "Bottom frame text is incorrect!");
		
	}
	
	
	
	
	@Test
	public void testHoverFirstUser() throws InterruptedException {
		// Locate the first user figure
		WebElement firstUser = driver.findElement(By.xpath("(//div[@class='figure'])[1]"));

		// Perform hover action
		Actions actions = new Actions(driver);
		actions.moveToElement(firstUser).perform();
		 // Just to visually confirm (can be replaced with WebDriverWait)

		// Verify username text becomes visible
		WebElement userName = driver.findElement(By.xpath("(//div[@class='figcaption']/h5)[1]"));
		Assert.assertTrue(userName.isDisplayed(), "Username is NOT visible after hover!");
		System.out.println("Username Text: " + userName.getText());

		// Verify "View profile" link is visible
		WebElement profileLink = driver.findElement(By.xpath("(//div[@class='figcaption']/a)[1]"));
		Assert.assertTrue(profileLink.isDisplayed(), "Profile link is NOT visible!");
		Assert.assertEquals(profileLink.getText().trim(), "View profile", "Profile link text mismatch!");
	}
	
	
	
	@Test
	public void testWindowSwitch() throws InterruptedException {
		String parentWindowHandle = driver.getWindowHandle();
		System.out.println("Parent Window Handle: " + parentWindowHandle);

		driver.findElement(By.linkText("Click Here")).click();

		

		Set<String> allWindowHandles = driver.getWindowHandles();

		for (String winId : allWindowHandles) {
			if (!winId.equals(parentWindowHandle)) {
				driver.switchTo().window(winId);
				break;
			}
		}

		System.out.println("Current URL (New Window): " + driver.getCurrentUrl());
		Assert.assertTrue(driver.getCurrentUrl().contains("windows"), "New window URL is incorrect!");

		driver.close();
		driver.switchTo().window(parentWindowHandle);
		Assert.assertTrue(driver.getTitle().contains("The Internet"), "Main window title is incorrect!");
	}
	
	
	
	@Test
	public void testKeyPress() throws InterruptedException {
		System.out.println("Current URL: " + driver.getCurrentUrl());

		WebElement inputField = driver.findElement(By.id("target"));
		Actions actions = new Actions(driver);

		// Simulate CONTROL key press
		actions.keyDown(inputField, Keys.CONTROL).keyUp(inputField, Keys.CONTROL).build().perform();

		

		String resultText = driver.findElement(By.id("result")).getText();
		Assert.assertEquals(resultText, "You entered: CONTROL", "Key press result is incorrect!");
	}
	
	
	@Test
	public void testScreenshot() throws InterruptedException, IOException {
		 driver.get("https://the-internet.herokuapp.com/key_presses");
		 
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(System.getProperty("user.dir")+File.separator+"SS.png"));
		
	}
	
	@Test
	public void testClickJS() throws InterruptedException {
		//driver.get("https://www.hyrtutorials.com/p/waits-demo.html");
		
		((JavascriptExecutor)driver).executeScript("document.querySelector('#btn2').click();");
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		 WebElement box2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Textbox2']")));
		Assert.assertEquals(box2.isDisplayed(), true);
		
	}
	
	
	
	
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	public WebElement waitForElementVisible(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
}