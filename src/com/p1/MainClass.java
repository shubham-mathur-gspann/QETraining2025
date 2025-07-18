package com.gspann; 
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
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
 
public class SecondProgramTest{
	WebDriver driver;

//  @Test
//   public void testScroll() throws InterruptedException {
//	  ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,document.body.scrollHeight);"); 
//	  Thread.sleep(2000);
//  }

  @BeforeMethod
public void beforeMethod() {
     driver = new ChromeDriver();
      driver.get("https://the-internet.herokuapp.com/key_presses");
      driver.manage().window().maximize();
}
  
//  @Test
//  public void testDropDownUsingSelect() throws InterruptedException {
//      Select dropDown = new Select(driver.findElement(By.id("dropdown")));
//      dropDown.selectByVisibleText("Option 1");
//      dropDown.selectByIndex(2); 
//      dropDown.selectByValue("1"); 
//      Assert.assertFalse(dropDown.isMultiple(), "Dropdown should not allow multiple selections");
//  }
  

//  @Test
//  public void testFileUpload(){
//      String path = System.getProperty("user.dir") + File.separator + "GIT NOTES.txt";
//      driver.findElement(By.id("file-upload")).sendKeys(path);
//      driver.findElement(By.id("file-submit")).click();
//      String expectedText = "File Uploaded!";
//      String actual = driver.findElement(By.xpath("//div[@class='example']/h3")).getText();
//      Assert.assertEquals(expectedText, actual);
//  }
//  

//  @Test
//  public void testFrames() throws InterruptedException{
//	  Thread.sleep(1000);
//	  driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-top']")));
//	  Thread.sleep(1000);
//	  driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-left']")));
//	  Thread.sleep(1000);
//	  String text = driver.findElement(By.xpath("//body[contains(text(),'LEFT')]")).getText();
//	  Thread.sleep(1000);
//	  System.out.println(text);
//	  driver.switchTo().parentFrame();
//	  Thread.sleep(1000);
//	  driver.switchTo().frame(driver.findElement(By.name("frame-middle")));
//	  String secondText = driver.findElement(By.id("content")).getText();
//	  System.out.println(secondText);
//  
//  }
//  @Test
//  public void testFrames() {
//     driver.switchTo().frame("frame-top");
//     driver.switchTo().frame("frame-left");
//     driver.switchTo().parentFrame();
//     driver.switchTo().frame("frame-middle");
//      String expectedText = "MIDDLE";
//      String actual = driver.findElement(By.id("content")).getText();
//      Assert.assertEquals(expectedText, actual);
//  }
//  
//  @Test
//  public void testMouseHovers() throws InterruptedException {
//	  Actions actions = new Actions(driver);
//	  actions.moveToElement(driver.findElement(By.xpath("//div[@class='example']/descendant::img[1]")))
//	  .click(driver.findElement(By.xpath("//a[@href='/users/1']")))
//	  .build().perform();
//	  Assert.assertTrue(driver.getCurrentUrl().contains("/users/1")); 
//  } 
  
//  @Test
//  public void testWindowSwitch() throws InterruptedException {
//	  String parentWindowhandle=driver.getWindowHandle();
//	  System.out.println(parentWindowhandle);
//	  
//	  driver.findElement(By.linkText("Click Here")).click();
//	  Set<String> allWindowHandles = driver.getWindowHandles();
//	  
//	  for(String winId:allWindowHandles) {
//		  if(winId.equals(parentWindowhandle)==false) {
//			  driver.switchTo().window(winId);
//			  break;
//		  }
//	  }
//	  Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/windows/new", "URL mismatch in new window");
//  }
//  
//  @Test
//  public void testKeyPress(){
//          Actions actions = new Actions(driver);
//          actions.keyDown(driver.findElement(By.id("target")),Keys.SHIFT).keyUp(driver.findElement(By.id("target")),Keys.SHIFT).build().perform();
//      String expectedText = "You entered: SHIFT";
//      String actual = driver.findElement(By.id("result")).getText();
//      Assert.assertEquals(expectedText, actual);
//  }
  
  
//  @Test
//  public void testScreenshot() throws IOException {
//          TakesScreenshot ts = (TakesScreenshot) driver;
//          File source = ts.getScreenshotAs(OutputType.FILE);
//      FileUtils.copyFile(source, new File(System.getProperty("user.dir")+File.separator+"Screenshot.png"));
//  }
  
  
  
  @AfterMethod
  public void afterMethod() {
	  driver.quit();

  }
 
}

 