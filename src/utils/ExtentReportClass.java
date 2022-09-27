package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportClass {
    ExtentReports extent;

    public void extentReport() {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("C:\\D Drive\\Vinod\\JavaTesting\\UI Project\\WebUIAutomation\\reports\\QA_POM_Report.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    public ExtentReportClass(ExtentReports extnt) {
        this.extent = extnt;
    }

    public ExtentTest extentTests(String testNm,String testDesc){
        ExtentTest test = extent.createTest(testNm,testDesc);
        return test;
    }

}
