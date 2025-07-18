package com.gspann;

import org.testng.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestFirstProgram {
	WebDriver driver;

	@BeforeMethod
	public void testDemo() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://the-internet.herokuapp.com/drag_and_drop");

	}

	@Test
	public void Handlecheckbox() {
		WebElement check = driver.findElement(By.xpath("//form[@id='checkboxes']/descendant::input[1]"));
		if (!check.isSelected()) {
			check.click();
			Assert.assertTrue(check.isSelected());
		}
		WebElement check2 = driver.findElement(By.xpath("//form[@id='checkboxes']/descendant::input[2]"));
		if (check2.isSelected()) {
			check2.click();
			Assert.assertFalse(check.isSelected());
		}

	}

	@Test
	public void draganddrop() {
		Actions action = new Actions(driver);
		WebElement source = driver.findElement(By.id("column-a"));
		WebElement target = driver.findElement(By.id("column-b"));
		action.dragAndDrop(source, target).build().perform();
		WebElement dum = driver.findElement(By.xpath("//div[@id='columns']/descendant::div[1]"));
		String sam = dum.getText();
		System.out.println(sam);
		Assert.assertTrue(sam == "B");

	}

	@Test
	public void JsAlert() {
		Alert alert = driver.switchTo().alert();
		WebElement firstAlert = driver.findElement(By.xpath("//*[contains(text(),'Click for JS Alert')]"));
		firstAlert.click();
		alert.accept();
		}
	
	@Test
	public void Confirm() {
		Alert alert = driver.switchTo().alert();
		WebElement secAlert = driver.findElement(By.xpath("//*[contains(text(),'Click for JS Confirm')]"));
		secAlert.click();
		alert.dismiss();
		}
	
	@Test
	public void jsPrompt() {
		
		WebElement thirdAlert = driver.findElement(By.xpath("//*[contains(text(),'Click for JS Prompt')]"));
		Alert alert = driver.switchTo().alert();
		thirdAlert.click();
		alert.sendKeys("Owais Hasan");

	}

	@Test
	public void contextclick() {
		Actions act = new Actions(driver);
		WebElement cc = driver.findElement(By.id("hot-spot"));
		act.contextClick().build().perform();

	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

}
