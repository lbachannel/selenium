package com.atteam.selenium.testng.main;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Main {
	
	private static WebDriver myBrowser;
	private static String driverPath = "test-resources/chromedriver.exe";
	
	@BeforeClass
	public static void setUpClass() {
		System.setProperty("webdriver.chrome.driver", driverPath);
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--incognito"); // ẩn danh
		myBrowser = new ChromeDriver(opt);
		myBrowser.manage().window().maximize();
	}
	
	// test đăng nhập thành công
	@Test
	public void testLoginGivenRightAccountReturnsWell() throws InterruptedException {
		String username = "EMP01";
		String password = "123";

		myBrowser.get("http://localhost:8080/selenium/login");
		
		WebElement userTag = myBrowser.findElement(By.xpath("//input[@id='username']"));
		userTag.sendKeys(username);
		
		Thread.sleep(2000);
		
		WebElement passTag = myBrowser.findElement(By.xpath("//input[@id='password']"));
		passTag.sendKeys(password);
		
		Thread.sleep(2000);
		
		WebElement loginTag = myBrowser.findElement(By.xpath("//button[normalize-space()='Login']"));
		loginTag.click();
		
		Thread.sleep(2000);
		
		WebElement userManagement = myBrowser.findElement(By.xpath("//a[normalize-space()='User Management']"));
		userManagement.click();

		Thread.sleep(5000);
	}
	
	
	// test lời chào user
	@Test
	public void testLoginGivenRightAccountSayHelloReturnsWell() {
		String fullname = "Michael Scofield";
		WebElement helloTag = myBrowser.findElement(By.xpath("//*[@id=\"navbar\"]/ul/li[2]/a")); // lấy được cái <td> chứa câu xin chào, in thử coi
		String actualHelloMsg = helloTag.getText();
		assertEquals("Welcome " + fullname, actualHelloMsg);
	}
	
	@AfterClass
	public static void tearDownClass() {
		myBrowser.quit(); // dọn dẹp rác
	}

}
