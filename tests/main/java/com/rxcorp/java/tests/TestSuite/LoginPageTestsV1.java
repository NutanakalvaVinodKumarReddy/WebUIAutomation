package com.rxcorp.java.tests.TestSuite;


import com.rxcorp.java.tests.Objects.*;
import utils.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.util.concurrent.TimeUnit;

public class LoginPageTestsV1 {
    static WebDriver driver;
    static LoginPage obj;
    static ExtentReports extent;
    static ExtentTest tests;
    static ExtentReportClass eobj;

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

    @DataProvider(name = "DataProvider")
    public Object[][] testData() {
        Object[][] data = {{"Admina", "admin123"}, {"", "admin123"}, {"Admin", ""}, {"", ""}, {"Admin", "admin123"}};
        return data;
    }

    @Test(priority = 0, dataProvider = "DataProvider")
    void invalidLoginTest1(String usrNm, String pwd) {
        tests = extent.createTest("Login Page Test Cases", "TestCases");
        tests.log(Status.INFO, "Entering UN");
        obj.sendUserName(usrNm);
        tests.log(Status.INFO, "Entering PW");
        obj.sendPassword(pwd);
        tests.log(Status.INFO, "Click Ok");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ie) {
        }


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


    @AfterSuite
    public void tearDown() {
        extent.flush();
        driver.close();
    }

}
