package TestSuite;

import Objects.LoginPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import utils.ExtentReportClass;

import java.util.concurrent.TimeUnit;

public class LoginPageTests {
    static WebDriver driver;
    static LoginPage obj;
    static ExtentReports extent;
    static ExtentReportClass extnObj;
    static ExtentTest tests;

    @BeforeSuite
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        obj = new LoginPage(driver);
        extnObj = new ExtentReportClass(extent);
        extnObj.extentReport();
    }

    @Test(priority = 0)
    void invalidLoginTest1() {
        tests = extnObj.extentTests("FirstTestCase","Invalid test cases");
        tests.log(Status.INFO,"Enter UN");
        obj.sendUserName("Admin");
        tests.log(Status.INFO,"Enter PW");
        obj.sendPassword("admin");
        tests.log(Status.INFO,"Click Ok");
        obj.clickLoginButton();
        String errMessage = obj.getErrorMessage();
        Assert.assertEquals(errMessage, "Invalid credentialss");
        if(errMessage.equalsIgnoreCase("Invalid credentialss")){
        tests.log(Status.PASS,"Actual and Expected matched");
        }
        else {
            tests.log(Status.FAIL,"Actual and Expected are NOT matched");
            tests.log(Status.FAIL,"Actual: "+errMessage);
            tests.log(Status.FAIL,"Expected: Invalid credentials");
        }
    }

    @Test(priority = 1)
    void invalidLoginTest2() {
        tests = extnObj.extentTests("SecondTestCase","Invalid test cases");
        tests.log(Status.INFO,"Enter UN");

        obj.sendUserName("Admin");
        obj.sendPassword("");
        obj.clickLoginButton();
        String errMessage = obj.getErrorMessage();
        Assert.assertEquals(errMessage, "Invalid credentials");
    }

    @Test(priority = 2)
    void invalidLoginTest3() {
        obj.sendUserName("");
        obj.sendPassword("admin");
        obj.clickLoginButton();
        String errMessage = obj.getErrorMessage();
        Assert.assertEquals(errMessage, "Invalid credentials");
    }

    @Test(priority = 3)
    void invalidLoginTest4() {
        obj.sendUserName("");
        obj.sendPassword("admin");
        obj.clickLoginButton();
        String errMessage = obj.getErrorMessage();
        Assert.assertEquals(errMessage, "Invalid credentials");
    }

    @Test(priority = 4)
    void invalidLoginTest() {
        obj.sendUserName("Admin");
        obj.sendPassword("admin123");
        obj.clickLoginButton();
        String loginUser = driver.findElement(By.xpath("//a[contains(text(),'Reports')]")).getText();

        if (loginUser.equalsIgnoreCase("Reports")) {
            Assert.assertEquals(loginUser, "Reports");
        } else {
            String errMessage = obj.getErrorMessage();
            Assert.assertEquals(errMessage, "Invalid credentials");
        }
    }

    @AfterSuite
    public void tearDown() {
        extent.flush();
        driver.close();
    }

}
