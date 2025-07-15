package com.p1;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com/");
        Thread.sleep(3000);
        String title = driver.getTitle();
        System.out.println("title= "+title);
        String url = driver.getCurrentUrl();
        System.out.println("URL= "+url);
        driver.quit();
    }
}