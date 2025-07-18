package testing.gspann;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

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
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class InfiniteScroll {
	WebDriver driver;

	@Test
	public void testScroll() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,document.body.scrollHeight);");
	}

	@Test
	public void testDropDown() {
		Select dropDown = new Select(driver.findElement(By.id("dropdown")));
		dropDown.selectByVisibleText("Option 1");
		WebElement isSelected = driver
				.findElement(By.xpath("//select[@id='dropdown']/descendant::option[contains(text(),'Option 1')]"));

		Assert.assertEquals(isSelected.getAttribute("selected"), "true");
//			Assert.assertEquals(ans,"selected");
//			dropDown.selectByVisibleText("Option 2");
	}

	@Test
	public void testFileUpload() {
		String path = System.getProperty("user.dir") + File.separator + "xyz.jpg";
		System.out.println(path);
		driver.findElement(By.id("file-upload")).sendKeys(path);
		driver.findElement(By.id("file-submit")).click();
		WebElement result = driver.findElement(By.xpath("//div[@id=\"uploaded-files\"]"));
		Assert.assertEquals(result.getText(), "xyz.jpg");
	}

	@Test
	public void testFrames() {
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-top']")));
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-left']")));
		String textLeft = driver.findElement(By.xpath("//body[contains(text(),'LEFT')]")).getText();
		System.out.println(textLeft);
		Assert.assertEquals(textLeft, "LEFT");
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-top']")));
		driver.switchTo().frame(driver.findElement(By.name("frame-middle")));
		String secondText = driver.findElement(By.id("content")).getText();
		System.out.println(secondText);
		Assert.assertEquals(textLeft, "MIDDLE");
	}

	@Test
	public void testHovering() {

		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//div[@class='figure']/descendant::img[1]")))
				.click(driver.findElement(By.xpath("//a[@href='/users/1']"))).build().perform();

		System.out.println(driver.getCurrentUrl());

		Assert.assertTrue(driver.getCurrentUrl().contains("/users/1"));
	}

	@Test
	public void testClickUsingJS() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		((JavascriptExecutor) driver).executeScript("document.querySelector('#btn2').click();");

		Assert.assertTrue(driver.findElement(By.id("txt1")).isDisplayed());
	}

	@Test
	public void testSwitchingWindows() {
		String parentWindow = driver.getWindowHandle();
		System.out.println(parentWindow);
		driver.findElement(By.linkText("Click Here")).click();
		Set<String> allWindowHandles = driver.getWindowHandles();
		for (String id : allWindowHandles) {
			if (id.equals(parentWindow) == false) {
				driver.switchTo().window(id);
				break;
			}
		}
		String ans = driver.getCurrentUrl();
		Assert.assertTrue(ans.contains("/new"));
	}

	@Test
	public void testKeyPresses() {
		Actions action = new Actions(driver);
		action.keyDown(driver.findElement(By.id("target")), Keys.BACK_SPACE)
				.keyUp(driver.findElement(By.id("target")), Keys.ENTER).build().perform();

		String result = driver.findElement(By.id("result")).getText();

		Assert.assertEquals(result, "You entered: BACK_SPACE");
	}

	@Test
	public void testTakingScreenshot() throws IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(System.getProperty("user.dir") + File.separator + "Pranshu.png"));
	}

	@BeforeMethod
	public void beforeMethod() {
		driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/key_presses");
		driver.manage().window().maximize();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}