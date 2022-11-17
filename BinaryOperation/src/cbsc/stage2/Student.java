package cbsc.stage2;

import cbsc.stage1.Exercise;

import java.util.Scanner;


@SuppressWarnings(value = "all")
public class Student {
    private static Exercise exercise;
    private static Answer answer;
    private static Check check;
    private int column = 5;
    private Scanner sc;

    public static Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        Student.exercise = exercise;
    }

    public Scanner getSc() {
        return sc;
    }

    public void setSc(Scanner sc) {
        this.sc = sc;
    }

    public Student() {
    }

    static{
        //生成算式基
        Common.generateAddFormulaBasis();
        Common.generateSubFormulaBasis();
        Common.generateAddAndSubFormulaBasis();
    }

    public static void main(String[] args) {
        exercise = new Exercise(20);
        //生成20到加减法混合算式题
        exercise.generateExercise();
        //打印算式题
        System.out.println("算式题：");
        exercise.printExercise();
        //创建文件类对象
        ExerciseFileDAO ex = new ExerciseFileDAO();
        System.out.println("加法算式基：");
        Exercise exercise1 = ex.readExerciseFromFile(System.getProperty("user.dir") + "\\" + "exerciseAddFB.csv",20);
        exercise1.printExercise();
        System.out.println("减法算式基：");
        Exercise exercise2 = ex.readExerciseFromFile(System.getProperty("user.dir") + "\\" + "exerciseSubFB.csv",20);
        exercise2.printExercise();
        System.out.println("加减法混合算式基：");
        Exercise exercise3 = ex.readExerciseFromFile(System.getProperty("user.dir") + "\\" + "exerciseAddAndSubFB.csv",20);
        exercise3.printExercise();
        //将算式题写入文件
        ex.writeExerciseToFile(Student.exercise);
        //创建答案类对象
        answer = new Answer();
        //用户填写答案，参数为答题的个数
        answer.scanAnswerFromKeyboard(10);
        //将答案写入到文件
        answer.writeAnswerToFile();
        //读取答案
        answer.readAnswerFromFile();
        //创建检查类对象
        check = new Check();
        //检查用户的答案
        check.check(Student.exercise, answer);
        //将答答题结果写入文件
        check.writeCheckToFile();
        //打印结果
        check.printCheck();
    }

    public static void printExercise() {
        int i = 0;
        while (exercise.hasNext()) {
            i++;
            System.out.print(intToString(i) + ") " + exercise.next().toString3());
            if (i % 5 == 0) {
                System.out.println();
            } else {
                System.out.print("\t");
            }
        }
    }

    private static String intToString(int i) {
        String s = String.valueOf(i);
        if (s.length() == 2) {
            return s;
        } else {
            return " " + s;
        }
    }

    public void practiceOneByOne() {
        exercise.setIndex(0);
        int i = 1;
        System.out.println("输出答案后回车继续下一题");
        while (exercise.hasNext()) {
            String s = exercise.next().toString2();
            System.out.print(intToString(i) + ") " + s);
            answer.add(scNextInt(i, s));
            i++;
        }
        check = new Check();
        check.check(exercise, answer);
        check.printCheck();
    }

    private int scNextInt(int i, String exercise) {
        String count = sc.nextLine();
        while (Common.isDigit(Common.trim(count))) {
            System.out.print("请输入第" + intToString(i) + ") " + "题答案：" + exercise);
            count = sc.nextLine();
        }
        return Integer.parseInt(Common.trim(count));

    }

}
