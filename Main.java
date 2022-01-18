package com.company;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addExtensions(new File("C:/Users/guptshu2/Desktop/sample-extn.crx"));
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\guptshu2\\Desktop\\server\\chromedriver.exe");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        WebDriver driver = new ChromeDriver(capabilities);
        driver.get("https://www.cirquedusoleil.com/ka");
        driver.findElement(By.cssSelector("[id=\"onetrust-accept-btn-handler\"]")).click();
        driver.navigate().refresh();

        Thread.sleep(3000);
        JavascriptExecutor js = ((JavascriptExecutor)driver);
        String data = (String)js.executeScript("return localStorage.getItem(\"allCookies\")");

        JSONArray arr = new JSONArray(data);

        System.out.println(arr);

        driver.quit();
    }
}
