package com.yava.util;

import com.hankcs.hanlp.HanLP;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;

/**
 * 此工具类用于计算SimHash和海明距离
 */
public class SimHashUtil {
    /**
     * 传入String，计算出它的hash值，并以字符串形式输出
     *
     * @param str 要计算hash的字符串
     * @return 返回str的hash值
     */
    public static String getHash(String str) {
        try {
            // 这里使用了MD5获得hash值
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            return new BigInteger(1, messageDigest.digest(str.getBytes(StandardCharsets.UTF_8))).toString(2);
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    /**
     * 传入String,计算出它的simHash值，并以字符串形式输出
     *
     * @param str 传入的Srting类型字符串
     * @return 返回str的simHash值
     */
    public static String getSimHash(String str) {
        // 用数组表示特征向量,取128位,从 0 1 2 位开始表示从高位到低位
        int[] v = new int[128];
        // 1、分词
        List<String> keywordList = HanLP.extractKeyword(str, str.length());//取出所有关键词
        // hash
        int size = keywordList.size();
        int i = 0;//以i做外层循环
        for (String keyword : keywordList) {
            // 2、获取hash值
            StringBuilder keywordHash = new StringBuilder(getHash(keyword));
            if (keywordHash.length() < 128) {
                int dif = 128 - keywordHash.length();
                for (int j = 0; j < dif; j++) {
                    keywordHash.append("0");
                }
            }
            // 3、加权、合并
            for (int j = 0; j < v.length; j++) {
                if (keywordHash.charAt(j) == '1') {
                    v[j] +=1;
                } else {
                    v[j] -= 1;
                }
            }
            i++;
        }
        // 4、降维
        StringBuilder simHash = new StringBuilder();// 储存返回的simHash值
        for (int k : v) {
            // 从高位遍历到低位
            if (k <= 0) {
                simHash.append("0");
            } else {
                simHash.append("1");
            }
        }
        return simHash.toString();
    }

    /**
     * 输入两个simHash值，计算它们的海明距离
     *
     * @param simHash1 第一个simHash值
     * @param simHash2 第二个simHash值
     * @return 海明距离
     */
    public static int getHammingDistance(String simHash1, String simHash2) {
        int distance = 0;
        if (simHash1.length() != simHash2.length()) {
            // 出错，返回-1
            distance = -1;
        } else {
            for (int i = 0; i < simHash1.length(); i++) {
                // 每一位进行比较
                if (simHash1.charAt(i) != simHash2.charAt(i)) {
                    distance++;
                }
            }
        }
        return distance;
    }

    /**
     * 输入两个simHash值，输出相似度
     *
     * @param simHash1 第一个simHash值
     * @param simHash2 第二个simHash值
     * @return 相似度
     */
    public static double getSimilarity(String simHash1, String simHash2) {
        // 通过 simHash1 和 simHash2 获得它们的海明距离
        int distance = getHammingDistance(simHash1, simHash2);
        // 通过海明距离计算出相似度，并返回
        return 0.01 * (100 - distance * 100 / 128);
    }
}
