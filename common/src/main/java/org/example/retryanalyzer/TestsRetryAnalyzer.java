package org.example.retryanalyzer;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class TestsRetryAnalyzer implements IRetryAnalyzer {

    private static final int API_TEST_MAX_RETRY_COUNT = 2;
    private int retryCounter = 0;

    @Override
    public boolean retry(ITestResult iTestResult) {
        return API_TEST_MAX_RETRY_COUNT > retryCounter++;
    }

    public int getRetryCounter() {
        return retryCounter;
    }
}