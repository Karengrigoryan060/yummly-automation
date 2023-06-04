package com.yummly.listeners;

import lombok.extern.log4j.Log4j2;
import org.testng.IHookable;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
@Log4j2
public class TestListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult iTestResult) {
        log.info("-----------------------------------------------------------------------");
        log.info("Starting to run " + iTestResult.getMethod().getQualifiedName() + " test method");
        log.info("-----------------------------------------------------------------------");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        log.info("-----------------------------------------------------------------------");
        log.info("PASSED " + iTestResult.getMethod().getQualifiedName() + " test method");
        log.info("-----------------------------------------------------------------------");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        log.info("-----------------------------------------------------------------------");
        log.info("FAILED " + iTestResult.getMethod().getQualifiedName() + " test method");
        log.info("-----------------------------------------------------------------------");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        log.info("-----------------------------------------------------------------------");
        log.info("SKIPPED " + iTestResult.getMethod().getQualifiedName() + " test method");
        log.info("-----------------------------------------------------------------------");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        //TODO log failed percentage
        log.info("Test Failed Within Success Percentage");
    }

    @Override
    public void onStart(ITestContext context) {
        log.info("Staring to run test suite with " + context.getAllTestMethods().length + " tests");
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("Finished running test suite with " + context.getAllTestMethods().length + " tests");
    }
}
