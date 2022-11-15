package cbsc.stage1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 代码1.2：一个Java程序，产生并逐行输出50道100以内的加减法算式
 * 实现：
 *      在代码1.1的基础上，将打印算式的逻辑封装成了一个个独立的方法
 *      通过类对象存放算式，利用数组存储50道算术题
 *      通过遍历进行打印算式及答案，通过生成随机数的范围，将结果限值在0-100之间
 */
//使代码元素内部的某些警告保持静默
@SuppressWarnings(value = "all")
public class BinaryOperation_1_2 {
    public static List<Equation> operations;
    public static void main(String[] args) {
        //打印说明
        printHeader();
        //生成50道算术题，存入数组并打印
        generateEquations();
        //打印说明--结果
        printExercise();
        //打印答案
        printCalculations();
    }

    //打印结果
    private static void printCalculations() {
        int i = 0;
        for (Equation e : operations){
            System.out.print(""+(++i)+":\t"+e.value+"\t");
            //每行打印五个算式
            if (i % 5 == 0) {
                System.out.println();
            }
        }
    }

    private static void printExercise() {
        System.out.println("-------------------------------------------------------");
        System.out.println("- 下面是习题的参考答案 -");
        System.out.println("-------------------------------------------------------");
    }

    //生成50道算术题，并存入数组
    private static void generateEquations() {
        operations = new ArrayList<>();
        Random random = new Random();
        Equation equation;
        short vo=0;
        //生成50道算式
        for (int i = 0; i < 50; i++) {
            equation = new Equation();
            vo = (short) random.nextInt(2);
            //生成左边的算式
            equation.left_operand=(short) random.nextInt(100);
            if(vo==1){
                equation.operator='+';
                //生成右边的算式
                equation.right_operand=(short) random.nextInt(100-equation.left_operand);
                equation.value = (short) (equation.left_operand+ equation.right_operand);
            }else{
                equation.operator='-';
                //生成右边的算式
                equation.right_operand=(short) random.nextInt(equation.left_operand+1);
                equation.value = (short) (equation.left_operand- equation.right_operand);
            }
            //存入数组
            operations.add(equation);
            System.out.print(""+(i+1)+":\t"+equation.left_operand+equation.operator+equation.right_operand+"="+"\t");
            //每行打印五个算式
            if ((i + 1) % 5 == 0) {
                System.out.println();
            }
        }
    }

    private static void printHeader() {
        System.out.println("-------------------------------------------------------");
        System.out.println("- 程序输出50道100以内的加减法算式的习题 -");
        System.out.println("- 每次运行程序可得到一套50道题的习题及答案 -");
        System.out.println("-------------------------------------------------------");
    }

    //算式对象
    static class Equation{
        short left_operand =0,right_operand =0;
        char operator = '+';
        short value =0;
    }
}
