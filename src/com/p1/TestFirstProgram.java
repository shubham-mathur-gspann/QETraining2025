package day1;

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

public class TestFirstProgram {

	private static final String TakeScreenshot = null;
	WebDriver driver;

	@BeforeMethod
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@Test
	public void CheckBox() throws InterruptedException {
		driver.get("https://the-internet.herokuapp.com/checkboxes");
		WebElement firstbox = driver.findElement(By.xpath("//form[@id='checkboxes']/descendant::input[1]"));
		if (firstbox.isSelected() == false)
			firstbox.click();
		Assert.assertTrue(firstbox.isSelected());
		WebElement secondbox = driver.findElement(By.xpath("//form[@id='checkboxes']/descendant::input[2]"));
		if (secondbox.isSelected() == true)
			secondbox.click();
		Assert.assertFalse(secondbox.isSelected());
	}

	@Test
	public void DragAndDrop() throws InterruptedException {
		driver.get("https://the-internet.herokuapp.com/drag_and_drop");
		WebElement source = driver.findElement(By.id("column-a"));
		WebElement destination = driver.findElement(By.id("column-b"));
		Actions actions = new Actions(driver);
		actions.dragAndDrop(source, destination).build().perform();
		String destination1 = driver.findElement(By.id("column-a")).getText();
		String text=source.getText();
		Assert.assertEquals(text, destination1);	
		
	}

	@Test
	public void JavaScriptAlerts() throws InterruptedException {
		driver.get("https://the-internet.herokuapp.com/javascript_alerts");
		driver.findElement(By.xpath("//*[text()='Click for JS Alert']")).click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		Assert.assertEquals(true,driver.findElement(By.id("result")).getText().contains("You successfully clicked an alert"));		
		driver.findElement(By.xpath("//*[contains(text(),'Click for JS Confirm')]")).click();
		Alert alert2 = driver.switchTo().alert();
		alert2.accept();
		Assert.assertEquals(true,driver.findElement(By.id("result")).getText().contains("You clicked: Ok"));	
		driver.findElement(By.xpath("//*[contains(text(),'Click for JS Confirm')]")).click();
		Alert alert3 = driver.switchTo().alert();
		alert3.dismiss();
		Assert.assertEquals(true,driver.findElement(By.id("result")).getText().contains("You clicked: Cancel"));		
		driver.findElement(By.xpath("//*[contains(text(),'Click for JS Prompt')]")).click();
		Alert alert4 = driver.switchTo().alert();
		alert4.sendKeys("Chanikya");		
		alert4.accept();
		Assert.assertEquals(true,driver.findElement(By.id("result")).getText().contains("Chanikya"));
	}

	@Test
	public void Contextmenu() throws InterruptedException {
		driver.get("https://the-internet.herokuapp.com/context_menu");
		WebElement emptyBox = driver.findElement(By.xpath("//*[@id='hot-spot']"));
		Actions actions = new Actions(driver);
		actions.contextClick(emptyBox).build().perform();
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	@Test
	public void testScrollPage() throws InterruptedException {
		driver.get("https://the-internet.herokuapp.com/infinite_scroll");
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,document.body.scrollHeight);");
	}

	@Test
	public void testDropDown() throws InterruptedException {
		driver.get("https://the-internet.herokuapp.com/dropdown");
		Select dropDown = new Select(driver.findElement(By.xpath("//*[@id='dropdown']")));
		dropDown.selectByVisibleText("Option 1");
		String option1=dropDown.getFirstSelectedOption().getText();
		Assert.assertEquals(option1, "Option 1");
		
		dropDown.selectByIndex(2);
		String option2=dropDown.getFirstSelectedOption().getText();
		Assert.assertEquals(option2, "Option 2");
		
		
	}

	@Test
	public void testFileUpload() throws InterruptedException {
		driver.get("https://the-internet.herokuapp.com/upload");
		String fileLocation = System.getProperty("user.dir") + File.separator + "sign.jpg";
		driver.findElement(By.id("file-upload")).sendKeys(fileLocation);
		driver.findElement(By.id("file-submit")).click();
		Assert.assertTrue(driver.findElement(By.id("uploaded-files")).getText().contains("sign"));
	}

	@Test
	public void testFrames() throws InterruptedException {
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
		driver.switchTo().defaultContent();
	}

	@Test
	public void testHovers() throws InterruptedException {
		driver.get("https://the-internet.herokuapp.com/hovers");
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath("//div[@class='example']/descendant::img[1]")))
				.click(driver.findElement(By.xpath("//a[@href='/users/1']"))).build().perform();
		Assert.assertTrue(driver.getCurrentUrl().contains("/users/1"));
	}

	@Test
	public void testClickingBtnByJS() throws InterruptedException {
		driver.get("https://www.hyrtutorials.com/p/waits-demo.html");
		WebElement button1 = driver.findElement(By.xpath("//*[@id='btn1']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", button1);
	}

	@Test
	public void testSwitchWindows() throws InterruptedException {
		driver.get("https://the-internet.herokuapp.com/windows");
		String parentWindow = driver.getWindowHandle();
		driver.findElement(By.linkText("Click Here")).click();
		Set<String> allRemainingWindows = driver.getWindowHandles();
		for (String window : allRemainingWindows) {
			if (window.equals(parentWindow) == false) {
				driver.switchTo().window(window);
				break;
			}
		}
		Assert.assertTrue(driver.getCurrentUrl().contains("/windows/new"));
		System.out.println(driver.getCurrentUrl());
	}

	@Test
	public void testKeyPresses() throws InterruptedException {
		driver.get("https://the-internet.herokuapp.com/key_presses");
		Actions actions = new Actions(driver);
		actions.keyDown(driver.findElement(By.id("target")), Keys.CONTROL)
				.keyUp(driver.findElement(By.id("target")), Keys.CONTROL).build().perform();
		WebElement output=driver.findElement(By.xpath("//*[@class='example']/p[2]"));
		Assert.assertTrue(output.getText().contains("CONTROL"), "Key press Does not Found");
	}

	@Test
	public void testCaptureScreenshot() throws IOException, InterruptedException {
		driver.get("https://the-internet.herokuapp.com/key_presses");
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(System.getProperty("user.dir") + File.separator + "keyimage.png"));
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
