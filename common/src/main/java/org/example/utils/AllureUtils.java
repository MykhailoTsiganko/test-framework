package org.example.utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class AllureUtils {

    @Attachment("{name}")
    public static byte[] takeScreenshot(String name) {
        return DriverUtils.getScreenshot();
    }

    public static void attachFile(File file) {
        String[] split = file.getName().split("\\.");
        String name = split[0];
        String extension = split[1];
        try {
            Allure.getLifecycle().addAttachment(
                    name,
                    Files.probeContentType(file.toPath()),
                    extension,
                    Files.readAllBytes(file.toPath())
            );
        } catch (IOException e) {
            throw new RuntimeException("Error while reading file: " + file.getAbsolutePath(), e);
        }
    }
}