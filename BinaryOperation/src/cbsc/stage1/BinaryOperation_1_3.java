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
    private static class Equation {

        //left算式左边的值，right算式右边的值，result算式的结果
        private int left, right, result;
        //op代表算式运算符号
        char op;

        public int getLeft() {
            return left;
        }

        public void setLeft(int left) {
            this.left = left;
        }

        public int getRight() {
            return right;
        }

        public void setRight(int right) {
            this.right = right;
        }

        public int getResult() {
            return result;
        }

        public void setResult(int result) {
            this.result = result;
        }

        public char getOp() {
            return op;
        }

        public void setOp(char op) {
            this.op = op;
        }

    }

    public static void main(String[] args) {
        BinaryOperation_1_3 e = new BinaryOperation_1_3();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printHeader();
            System.out.print("请选择：");
            int value = scanner.nextInt();
            switch (value) {
                case 0:
                    System.exit(0);
                case 1:
                    e.generateAddExercise();
                    e.printExercise();
                    break;
                case 2:
                    e.generateSubExercise();
                    e.printExercise();
                    break;
                case 3:
                    e.generateExercise();
                    e.printExercise();
                    break;
                default:
                    System.out.println("请输入符合条件的指令！");
                    break;
            }
        }
    }

    //算式数组
    private Equation[] exercise;

    public Equation[] getExercise() {
        return exercise;
    }

    public void setExercise(Equation[] exercise) {
        this.exercise = exercise;
    }

    public String toString(Equation e) {
        return e.getLeft() + "" + e.getOp() + "" + e.getRight() + "=" + e.getResult();
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

    /**
     * 生成随机数
     *
     * @param max
     * @param min
     * @return
     */
    public int generateRandom(int max, int min) {
        Random r = new Random();
        return r.nextInt(max - min + 1) + min;
    }

    /**
     * 检查算式左右两边是否在指定的范围内
     *
     * @param value
     * @param max
     * @param min
     * @return
     */
    public boolean isBetween(int value, int max, int min) {
        return value >= min && value <= max;
    }

    /**
     * 判断两个算式是否相同
     *
     * @param e1
     * @param e2
     * @return
     */
    public boolean isEqual(Equation e1, Equation e2) {
        if (e1.getOp() != e2.getOp()) {
            return false;
        } else {
            return e1.getLeft() == e2.getLeft() && e1.getRight() == e2.getRight();
        }
    }

    /**
     * 进行加法计算
     *
     * @param left
     * @param right
     * @return
     */
    public int calculateAddEquation(int left, int right) {
        return left + right;
    }

    /**
     * 进行减法计算
     *
     * @param left
     * @param right
     * @return
     */
    public int calculateSubEquation(int left, int right) {
        return left - right;
    }

    /**
     * 判断算式是否已经存在
     *
     * @param e
     * @param ex
     * @param n
     * @return
     */
    public boolean occursIn(Equation e, Equation[] ex, int n) {
        boolean b = false;
        for (int i = 0; i < n; i++) {
            if (isEqual(e, ex[i])) {
                b = true;
                break;
            }
        }
        return b;
    }

    /**
     * 生成一个独立的加法算式
     *
     * @return
     */
    public Equation generateAddEquation() {
        Equation e = new Equation();
        int left, right, result;
        while (true) {
            left = generateRandom(100, 0);
            right = generateRandom(100, 0);
            result = calculateAddEquation(left, right);
            if (isBetween(result, 100, 0)) {
                e.setLeft(left);
                e.setRight(right);
                e.setResult(result);
                e.setOp('+');
                break;
            }
        }
        return e;
    }

    /**
     * 生成一个独立的减法算式
     *
     * @return
     */
    public Equation generateSubEquation() {
        Equation e = new Equation();
        int left, right, result;
        while (true) {
            left = generateRandom(100, 0);
            right = generateRandom(100, 0);
            result = calculateSubEquation(left, right);
            if (isBetween(result, 100, 0)) {
                e.setLeft(left);
                e.setRight(right);
                e.setResult(result);
                e.setOp('-');
                break;
            }
        }
        return e;
    }

    /**
     * 随机生成加法算式或者减法算式
     *
     * @return
     */
    public Equation generateEquation() {
        Equation e;
        Random r = new Random();
        if (r.nextInt(2) == 1) {
            e = generateAddEquation();
        } else {
            e = generateSubEquation();
        }
        return e;
    }

    /**
     * 加减法混合算式
     */
    public void generateExercise() {
        exercise = new Equation[50];
        for (int i = 0; i < 50; ) {
            //随机生成加法算式或者减法算式
            Equation e = generateEquation();
            //判断算式是否已经存在，若存在，则不计入数组
            if (occursIn(e, exercise, i)) {
                continue;
            } else {
                exercise[i] = e;
                i++;
            }
        }
    }

    /**
     * 加法算式
     */
    public void generateAddExercise() {
        exercise = new Equation[50];
        for (int i = 0; i < 50; ) {
            //生成一个独立的加法算式
            Equation e = generateAddEquation();
            //判断算式是否已经存在，若存在，则不计入数组
            if (occursIn(e, exercise, i)) {
                continue;
            } else {
                exercise[i] = e;
                i++;
            }
        }
    }

    /**
     * 减法算式
     */
    public void generateSubExercise() {
        exercise = new Equation[50];
        for (int i = 0; i < 50; ) {
            //生成一个独立的减法算式
            Equation e = generateSubEquation();
            //判断算式是否已经存在，若存在，则不计入数组
            if (occursIn(e, exercise, i)) {
                continue;
            } else {
                exercise[i] = e;
                i++;
            }
        }
    }

    /**
     * 打印输出50道算式题
     */
    public void printExercise() {
        for (int i = 0; i < 50; i++) {
            System.out.print("(" + (i + 1) + ")" + toString(exercise[i]) + "\t\t");
            //每行打印五个算式
            if ((i + 1) % 5 == 0) {
                System.out.println();
            }
        }
    }
}
