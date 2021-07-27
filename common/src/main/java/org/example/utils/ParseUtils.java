package org.example.utils;

public class ParseUtils {
    public static String parseUserName(String login) {
        return login.substring(0, login.indexOf("@")).replace(".", " ");
    }
}
