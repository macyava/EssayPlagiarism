package com.yava.util;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 此工具类用于读取和写入文件
 */
public class FileUtil {
    /**
     * 该函数为读取文件并返回文件内容字符串，可能抛出文件不存在，记得try/catch处理
     *
     * @param filePath 要读取的文件的绝对路径
     * @return 返回该文件的读取到的字符串
     */
    public static String readFile(String filePath) {
        //1.读取文件转换为byte数组
        File file = new File(filePath);
        String stringBuilder;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            int size = fis.available();
            char[] chatBuffer = new char[size];
            isr.read(chatBuffer);
            stringBuilder = String.valueOf(chatBuffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return stringBuilder;
    }

    public static void writeFile(String content) {
        File file = new File("D:\\Java\\result.txt");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            if (!file.exists()) {
                file.createNewFile();
            }
            byte[] contentInBytes = content.getBytes();
            fileOutputStream.write(contentInBytes);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}