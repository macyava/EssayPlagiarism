package com.yava;

import com.yava.util.FileUtil;
import com.yava.util.SimHashUtil;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;


public class main {
    public static void main(String[] args) throws FileNotFoundException {

        //"D:\\a.txt"
        while (true) {
            while (true) {
                // 初始化两个文件的SimHash值变量 作为全局变量 方便后续调用
                String firstFileHashValue;
                String secondFileHashValue;
                String[] secondFileContent = {"C:\\Users\\Yava\\Desktop\\测试文本\\orig_0.8_add.txt",

                "C:\\Users\\Yava\\Desktop\\测试文本\\orig_0.8_del.txt",

                "C:\\Users\\Yava\\Desktop\\测试文本\\orig_0.8_dis_1.txt",

                "C:\\Users\\Yava\\Desktop\\测试文本\\orig_0.8_dis_10.txt",

                "C:\\Users\\Yava\\Desktop\\测试文本\\orig_0.8_dis_15.txt"};
                double[] similarity = new double[5];
                PrintStream ps = new PrintStream("D:\\process\\ans.txt");


                int i = 0;
                // 读取第一个文件的SimHash
//                System.out.println("文件一绝对路径：");
//                Scanner scanner = new Scanner(System.in);

                try {
                    // 获取第一个文件的内容
//                    String firstFileContent = FileUtil.readFile(scanner.next());
                    // 把文件的内容转为SimHash值
                    firstFileHashValue = SimHashUtil.getSimHash("C:\\Users\\Yava\\Desktop\\测试文本\\orig.txt");
                } catch (Exception e) {
                    // 找不到指定的文件
                    System.out.println("该路径不存在");
                    break;
                }
                // 读取第二个文件的SimHash
//                System.out.println("剩下文件的绝对路径：");
                while(i < 5) {
//                    System.out.println("文件"+ i+1 +"绝对路径：");
                    try {
                        // 获取第二个文件的内容
//                        secondFileContent[i] = FileUtil.readFile(scanner.next());
                        // 把文件的内容转为SimHash值
                        secondFileHashValue = SimHashUtil.getSimHash(secondFileContent[i]);
                    } catch (Exception e) {
                        System.out.println("该路径不存在");
                        break;
                    }

                    similarity[i] = SimHashUtil.getSimilarity(firstFileHashValue, secondFileHashValue);
                    i++;
                }

//                try {
//                    FileUtil.writeFile(String.valueOf(similarity));
//                    System.out.println("结果已写入result文件");
//                } catch (Exception e) {
//                    //暂时不处理
//                }
//                System.out.println("程序执行完毕，请按回车");
                System.setOut(ps);
                for(i = 0; i<5; i++) {
                    System.out.println("第" + (i+1) + "个文本查重率为：" + similarity[i]);
//                    System.out.println("两份文件重复率为：" + similarity);
                }

                ps.close();
                System.exit(0);
            }
        }
    }
}


//class Test {
//    public static void main(String[] args) throws FileNotFoundException {
//        // 创建一个打印输出流，输出的目标是D盘下的1.txt文件
//        PrintStream ps = new PrintStream("D:\\process\\hello.txt");
//        //可能会出现异常，直接throws就行了
//        System.setOut(ps);
//        //把创建的打印输出流赋给系统。即系统下次向 ps输出
//        System.out.println("看看我在哪里？");
//        System.out.println("==============");
//        System.out.println("我在D盘下的1.txt文件中去了。");
//        ps.close();
//    }
//}


