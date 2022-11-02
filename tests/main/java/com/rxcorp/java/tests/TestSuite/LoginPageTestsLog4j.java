package com.rxcorp.java.tests.TestSuite;

import com.rxcorp.java.tests.Objects.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import utils.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LoginPageTestsLog4j {
    static WebDriver driver;
    static LoginPage obj;
    static ExtentReports extent;
    static ExtentTest tests;
    static ExtentReportClass eobj;
    static Logger logger = LogManager.getLogger(LoginPageTestsLog4j.class);


    @BeforeSuite
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        obj = new LoginPage(driver);
        eobj = new ExtentReportClass(extent);
        extent = eobj.extentReport();

    }

    @Test(priority = 0)
    void invalidLoginTest1() {
        logger.info("Entering UN");
        tests = extent.createTest("FirstTestCase", "Invalid test cases");
        tests.log(Status.INFO, "Entering UN");
        logger.info("Entering UN");
        obj.sendUserName("Admin");
        tests.log(Status.INFO, "Entering PW");
        logger.info("Entering PW");
        obj.sendPassword("admin");
        tests.log(Status.INFO, "Click Ok");
        logger.info("Click on OK");
        obj.clickLoginButton();
        String errMessage = obj.getErrorMessage();
        if (errMessage.equalsIgnoreCase("Invalid credentials")) {
            tests.log(Status.PASS, "Actual and Expected matched");
        } else {
            tests.log(Status.FAIL, "Actual and Expected are NOT matched");
            tests.log(Status.FAIL, "Actual: " + errMessage);
            tests.log(Status.FAIL, "Expected: Invalid credentials");
        }
    }

    @Test(priority = 1)
    void invalidLoginTest2() {
        tests = extent.createTest("SecondTestCase", "Invalid test cases");
        tests.log(Status.INFO, "Entering UN");
        logger.info("Entering UN");
        obj.sendUserName("");
        tests.log(Status.INFO, "Entering PW");
        logger.info("Entering PW");
        obj.sendPassword("admin");
        tests.log(Status.INFO, "Click Ok");
        logger.info("Click on OK");
        obj.clickLoginButton();
        String errMessage = obj.getErrorMessage();
        if (errMessage.equalsIgnoreCase("Invalid credentialss")) {
            tests.log(Status.PASS, "Actual and Expected matched");
        } else {
            tests.log(Status.FAIL, "Actual and Expected are NOT matched");
            tests.log(Status.FAIL, "Actual: " + errMessage);
            tests.log(Status.FAIL, "Expected: Invalid credentials");
        }
    }

    @Test(priority = 2)
    void invalidLoginTest3() {
        tests = extent.createTest("ThirdTestCase", "Invalid test cases");
        tests.log(Status.INFO, "Entering UN");
        logger.info("Entering UN");
        obj.sendUserName("Admin");
        tests.log(Status.INFO, "Entering PW");
        logger.info("Entering PW");
        obj.sendPassword("");
        tests.log(Status.INFO, "Click Ok");
        logger.info("Click on OK");
        obj.clickLoginButton();
        String errMessage = obj.getErrorMessage();
        if (errMessage.equalsIgnoreCase("Invalid credentialss")) {
            tests.log(Status.PASS, "Actual and Expected matched");
        } else {
            tests.log(Status.FAIL, "Actual and Expected are NOT matched");
            tests.log(Status.FAIL, "Actual: " + errMessage);
            tests.log(Status.FAIL, "Expected: Invalid credentials");
        }
    }

    @Test(priority = 3)
    void invalidLoginTest4() {
        tests = extent.createTest("FourthTestCase", "Invalid test cases");
        tests.log(Status.INFO, "Entering UN");
        logger.info("Entering UN");
        obj.sendUserName("");
        tests.log(Status.INFO, "Entering PW");
        logger.info("Entering PW");
        obj.sendPassword("");
        tests.log(Status.INFO, "Click Ok");
        logger.info("Click on OK");
        obj.clickLoginButton();
        String errMessage = obj.getErrorMessage();
        Assert.assertEquals(errMessage, "Invalid credentials");
        if (errMessage.equalsIgnoreCase("Invalid credentials")) {
            tests.log(Status.PASS, "Actual and Expected matched");
        } else {
            tests.log(Status.FAIL, "Actual and Expected are NOT matched");
            tests.log(Status.FAIL, "Actual: " + errMessage);
            tests.log(Status.FAIL, "Expected: Invalid credentials");
        }
    }

    @Test(priority = 4)
    void invalidLoginTest() {
        tests = extent.createTest("FifithTestCase", "Valid test cases");
        tests.log(Status.INFO, "Entering UN");
        logger.info("Entering UN");
        obj.sendUserName("Admin");
        tests.log(Status.INFO, "Entering PW");
        logger.info("Entering PW");
        obj.sendPassword("admin123");
        tests.log(Status.INFO, "Click Ok");
        logger.info("Click on OK");
        obj.clickLoginButton();
        String loginUser = driver.findElement(By.xpath("//a[contains(text(),'Reports')]")).getText();
        if (loginUser.equalsIgnoreCase("Reports")) {
            Assert.assertEquals(loginUser, "Reports");
            tests.log(Status.PASS, "Actual and Expected matched");
        } else {
            String errMessage = obj.getErrorMessage();
            Assert.assertEquals(errMessage, "Invalid credentials");
            tests.log(Status.FAIL, "Actual and Expected are NOT matched");
            tests.log(Status.FAIL, "Actual: " + errMessage);
            tests.log(Status.FAIL, "Expected: Invalid credentials");
        }
    }

    @AfterSuite
    public void tearDown() {
        extent.flush();
        driver.close();
    }

}
