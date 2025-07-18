package testing_gspann;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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

public class TestSecond {

	WebDriver driver = new ChromeDriver();

	@BeforeMethod
	public void setup() {

		driver.manage().window().maximize();
		driver.get("https://the-internet.herokuapp.com/key_presses");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void testScroll() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollTo(0,document.body.scrollHeight);");

	}

	@Test
	public void testDropDown() throws InterruptedException {
		Select dropDown = new Select(driver.findElement(By.id("dropdown")));
		dropDown.selectByVisibleText("Option 1");

		WebElement isSelected = driver
				.findElement(By.xpath("//select[@id='dropdown']/descendant::option[contains(text(),'Option 1')]"));


		Assert.assertEquals(isSelected.getAttribute("selected"), "true");


	}

	@Test
	public void testFileUpload() {

		String path = System.getProperty("user.dir") + File.separator + "Lion.jpg";
		System.out.println(driver.getCurrentUrl());
		driver.findElement(By.id("file-upload")).sendKeys(path);
		driver.findElement(By.id("file-submit")).click();

//		System.out.println(driver.getCurrentUrl());
		String file = driver.findElement(By.id("uploaded-files")).getText();

		Assert.assertEquals(file, "Lion.jpg");

	}

	@Test
	public void testLeftAndRightFrame() {
		driver.switchTo().frame(driver.findElement(By.name("frame-top")));
		driver.switchTo().frame(driver.findElement(By.name("frame-left")));
		String leftFrameTxt = driver.findElement(By.xpath("//body[contains(text(),'LEFT')]")).getText();
		Assert.assertEquals(leftFrameTxt, "LEFT");
		driver.switchTo().parentFrame();
		driver.switchTo().frame(driver.findElement(By.name("frame-middle")));
		String rightFrameText = driver.findElement(By.xpath("//div[@id='content']")).getText();
		Assert.assertEquals(rightFrameText, "MIDDLE");
	}

	@Test
	public void testHovers() {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//div[@class='figure']/descendant::img[1]")))
				.click(driver.findElement(By.xpath("//a[@href='/users/1']"))).build().perform();

		System.out.println(driver.getCurrentUrl());

		Assert.assertTrue(driver.getCurrentUrl().contains("/users/1"));
	}

	@Test
	public void testJsBtn() {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		((JavascriptExecutor) driver).executeScript("document.querySelector('#btn2').click();");

		Assert.assertTrue(driver.findElement(By.id("txt1")).isDisplayed());

	}

	@Test
	public void testMultipleWindows() {
		String parentWindow = driver.getWindowHandle();
		driver.findElement(By.xpath("//a[text()='Click Here']")).click();

		Set<String> allWindows = driver.getWindowHandles();

		for (String windowId : allWindows) {
			if (!windowId.equals(parentWindow)) {
				driver.switchTo().window(windowId);
				break;
			}
		}

		String ans = driver.getCurrentUrl();
		Assert.assertTrue(ans.contains("/new"));
	}

	@Test
	public void testKeyPress() {
		Actions action = new Actions(driver);
		action.keyDown(driver.findElement(By.id("target")), Keys.BACK_SPACE)
				.keyUp(driver.findElement(By.id("target")), Keys.ENTER).build().perform();

		String result = driver.findElement(By.id("result")).getText();

		Assert.assertEquals(result, "You entered: BACK_SPACE");

	}

	@Test
	public void testScreenshot() throws IOException {

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(System.getProperty("user.dir") + File.separator + "AbhinavSingh.png"));

	}

}
