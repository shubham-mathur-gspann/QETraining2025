package com.testng;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

public class TestPractice {
        WebDriver driver;
        @BeforeMethod
        public void setup(){
            driver = new ChromeDriver();
            driver.manage().window().maximize();
//            driver.get("https://the-internet.herokuapp.com/dropdown");
//            driver.get("https://the-internet.herokuapp.com/upload");
//            driver.get("https://the-internet.herokuapp.com/nested_frames");
//            driver.get("https://the-internet.herokuapp.com/hovers");
//            driver.get("https://www.hyrtutorials.com/p/waits-demo.html");
            driver.get("https://the-internet.herokuapp.com/windows");
//            driver.get("https://the-internet.herokuapp.com/key_presses");
        }
        @Test
        public void testDropDown(){
            WebElement dropDown = driver.findElement(By.id("dropdown"));
            Select select = new Select(dropDown);
            select.selectByValue("1");
            WebElement option1 = driver.findElement(By.xpath("//option[contains(text(),'Option 1')]"));
            Assert.assertTrue(option1.isSelected());
        }
        @Test
        public void testFileUpload(){
            String path = System.getProperty("user.dir") + File.separator + "File1.png";
            driver.findElement(By.id("file-upload")).sendKeys(path);
            driver.findElement(By.id("file-submit")).click();
            String expectedText = "File Uploaded!";
            String actual = driver.findElement(By.xpath("//div[@class='example']/h3")).getText();
            Assert.assertEquals(expectedText, actual);
        }
    @Test
    public void testFrames() {
       driver.switchTo().frame("frame-top");
       driver.switchTo().frame("frame-left");
       driver.switchTo().parentFrame();
       driver.switchTo().frame("frame-middle");
        String expectedText = "MIDDLE";
        String actual = driver.findElement(By.id("content")).getText();
        Assert.assertEquals(expectedText, actual);
    }

    @Test
    public void testHover(){
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//div[@class='example']/descendant::img[1]")))
                .click(driver.findElement(By.xpath("//a[@href='/users/1']"))).build().perform();
        Assert.assertTrue(driver.getCurrentUrl().contains("/users/1"));
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
    public void testKeyPress(){
            Actions actions = new Actions(driver);
            actions.keyDown(driver.findElement(By.id("target")),Keys.SHIFT).keyUp(driver.findElement(By.id("target")),Keys.SHIFT).build().perform();
        String expectedText = "You entered: SHIFT";
        String actual = driver.findElement(By.id("result")).getText();
        Assert.assertEquals(expectedText, actual);
    }

    @Test
    public void testScreenshot() throws IOException {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File(System.getProperty("user.dir")+File.separator+"Screenshot.png"));
    }
        @AfterMethod
        public void tearDown() {
            driver.quit();
        }
    }

