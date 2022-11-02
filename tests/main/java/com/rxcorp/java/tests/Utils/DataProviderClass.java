package com.rxcorp.java.tests.Utils;

import org.testng.annotations.DataProvider;

public class DataProviderClass {

    @DataProvider(name = "DataProvider")
    public Object[][] testData() {
        Object[][] data = {{"Admin", "admin123"}, {"", "admin123"}, {"Admin", ""}, {"", ""}, {"Admin", "admin123"}};
        return data;
    }
}
