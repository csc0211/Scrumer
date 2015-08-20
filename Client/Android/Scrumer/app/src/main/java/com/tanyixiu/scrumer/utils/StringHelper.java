package com.tanyixiu.scrumer.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Mimo on 2015/8/18.
 */
public class StringHelper {
    public static String toMD5(String input) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] inputByteArray = input.getBytes();
            byte[] resultByteArray = messageDigest.digest();
            messageDigest.update(inputByteArray);
            return byteArrayToHex(resultByteArray);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String byteArrayToHex(byte[] byteArray) {
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] resultCharArray = new char[byteArray.length * 2];
        int index = 0;
        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b & 0xf];
        }
        return new String(resultCharArray);
    }

    public static String getCurrentTime() {
        long time = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date(time));
    }

}
