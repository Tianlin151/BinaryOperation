package cbsc.stage1;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * 代码1.3：一个Java程序，产生并逐行输出50道100以内的加减法算式
 * 实现：
 *    在代码1.2的基础上，使用面向对象构造技术，对存放数据的实体类进行封装
 *    定义生成加法、减法、加减法混合的方法
 *    定义验证运算数、运算结果值范围的方法
 *    通过循环输出加法、减法、加减法混合的50道题
 */
//使代码元素内部的某些警告保持静默
@SuppressWarnings(value = "all")
public class BinaryOperation_1_3 {
    public static void main(String[] args) {
        BinaryOperation_1_3 e = new BinaryOperation_1_3();
        Exercise exercise;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printHeader();
            System.out.print("请选择：");
            int value = scanner.nextInt();
            switch (value) {
                case 0:
                    System.exit(0);
                case 1:
                    exercise = new Exercise();
                    exercise.generateAddExercise();
                    exercise.printExercise();
                    break;
                case 2:
                    exercise = new Exercise();
                    exercise.generateSubExercise();
                    exercise.printExercise();
                    break;
                case 3:
                    exercise = new Exercise();
                    exercise.generateExercise();
                    exercise.printExercise();
                    break;
                default:
                    System.out.println("请输入符合条件的指令！");
                    break;
            }
        }
    }

    private static void printHeader() {
        System.out.println("-------------------------------------------------------");
        System.out.println("- 程序输出50道100以内的加减法算式的习题 -");
        System.out.println("- 每次运行程序可得到一套50道题的习题及答案 -");
        System.out.println("-------------------------------------------------------");
        System.out.println("1.50道加法算式题");
        System.out.println("2.50道减法算式题");
        System.out.println("3.50加减法混合算式题");
        System.out.println("0.退出");
    }
}
