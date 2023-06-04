package com.yummly.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import static com.yummly.config.Configuration.RETRY_COUNT;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int counter = 0;

    @Override
    public boolean retry(ITestResult result) {
        if (counter < RETRY_COUNT) {
            counter++;
            return true;
        }
        return false;
    }
}