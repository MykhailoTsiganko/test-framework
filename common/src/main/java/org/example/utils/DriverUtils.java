package org.example.utils;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import lombok.extern.java.Log;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

@Log
public final class DriverUtils {

    private static final String SESSION_TIMED_OUT_OR_NOT_FOUND = "Session timed out or not found";

    public static long getDocumentElementsSize() {
        String script = "\tvar begin = document.all;\n" +
                "\treturn begin.length;";
        return Long.valueOf(Selenide.executeJavaScript(script));
    }

    public static void clearBrowserLocalStorageAndCookies() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            Selenide.clearBrowserLocalStorage();
            Selenide.clearBrowserCookies();
        }
    }

    public static void stop() {
        log.info(String.format("Trying to close WebDriver in thread [%s]", getCurrentThreadId()));
        WebDriverRunner.closeWebDriver();
        log.info(String.format("Closed WebDriver in thread [%s]", getCurrentThreadId()));
    }

    private static long getCurrentThreadId() {
        return Thread.currentThread().getId();
    }

    public static byte[] getScreenshot() {
        log.info(String.format("Trying to get screenshot in thread [%s]", getCurrentThreadId()));
        if (WebDriverRunner.hasWebDriverStarted()) {
            log.info(String.format("WebDriver is opened in thread [%s], trying to get screenshot", getCurrentThreadId()));
            byte[] screenshotBytes = ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
            log.info(String.format("Successfully got screenshot in thread [%s]", getCurrentThreadId()));
            return screenshotBytes;
        } else {
            log.info(String.format("WebDriver is not opened in thread [%s], returning empty screenshot", getCurrentThreadId()));
            return new byte[]{};
        }
    }
}
