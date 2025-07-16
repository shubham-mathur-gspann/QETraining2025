package com.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com");
        Thread.sleep(1000);
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("aman@example.com");
        WebElement passwordField = driver.findElement(By.name("pass"));
        passwordField.sendKeys("Aman@12345.");
        WebElement loginButton = driver.findElement(By.className("_42ft"));
        loginButton.click();
        WebElement forgotLink = driver.findElement(By.linkText("Forgotten password?"));
        forgotLink.click();
        WebElement recoveryField = driver.findElement(By.id("identify_email"));
        recoveryField.sendKeys("Aman Kansal");
        WebElement searchButton = driver.findElement(By.name("did_submit"));
        searchButton.click();
        Thread.sleep(3000);
        System.out.println("Title: " + driver.getTitle());
        System.out.println("URL: " + driver.getCurrentUrl());
        driver.quit();
    }
}
