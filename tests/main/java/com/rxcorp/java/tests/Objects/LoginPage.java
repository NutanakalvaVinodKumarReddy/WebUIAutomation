package com.rxcorp.java.tests.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    By userNmTxt = By.xpath("//body/div[@id='app']/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/form[1]/div[1]/div[1]/div[2]/input[1]");
    By passwordTxt = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/form[1]/div[2]/div[1]/div[2]/input[1]");
    By loginButton = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/form[1]/div[3]/button[1]");
    By errorMessage = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/p[1]");

    public void sendUserName(String usrNm) {
        driver.findElement(userNmTxt).sendKeys(usrNm);
    }

    public void sendPassword(String pwd) {
        driver.findElement(passwordTxt).sendKeys(pwd);
    }

    public void clickLoginButton() {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
        driver.findElement(loginButton).click();
    }

    public String getErrorMessage() {
        String errMsg = driver.findElement(errorMessage).getText();
        System.out.println("Invalid Login Credentials");
        return errMsg;
    }
}
