package cbsc.stage3;

import cbsc.stage1.Exercise;
import cbsc.stage2.*;

import java.util.Scanner;

@SuppressWarnings(value = "all")
public class CommandUI {

    static String[] ui1 = {"退出", "在线练习（随机生成）", "随机练习（算式基）", "批改随机习题", "", "", "", "", ""};

    static String[][] ui2 = {{}, {"返回上一级菜单", "加法", "减法", "混合"}, {"返回上一级菜单", "加法", "减法", "混合"}, {}, {}, {}, {}, {}, {}};

    static Scanner sc = new Scanner(System.in);

    public CommandUI() {
    }

    public static void main(String[] args) {
        Student.init();//初始化算式基
        String command1;
        String command2;
        boolean exit = false;
        while (!exit) {
            print1();
            command1 = sc.nextLine();
            switch (Common.trim(command1)) {
                case "1":
                    while (true) {
                        print2(Integer.parseInt(command1));
                        command2 = sc.nextLine();
                        if ("0".equals(Common.trim(command2))) {
                            break;
                        }
//                        for (int i = 0; i < command2.length(); i++) {
//                            if (!Character.isDigit(command2.charAt(i))) {
//                                b = false;
//                                break;
//                            }
//                        }
                        if (Common.isDigit(Common.trim(command2))) {
                            continue;
                        }
                        int a;
                        if ((a = Integer.parseInt(Common.trim(command2))) > ui2[1].length) {
                            continue;
                        }
                        if (command1_1(a)) {
                            break;
                        }
                    }
                    break;
                case "2":
                    while (true) {
                        print2(Integer.parseInt(Common.trim(command1)));
                        command2 = sc.nextLine();
                        if ("0".equals(Common.trim(command2))) {
                            break;
                        }
                        if (Common.isDigit(Common.trim(command2))) {
                            continue;
                        }
                        int a;
                        if ((a = Integer.parseInt(Common.trim(command2))) > ui2[2].length) {
                            continue;
                        }
                        if (command1_2(a)) {
                            break;
                        }
                    }
                    break;
                case "3":
                    command1_3();
                    break;
                case "0":
                    exit = true;
                    System.out.println("感谢使用本软件，再见");
                    break;
            }
        }
        sc.close();
    }

    public static void print1() {
        System.out.println("口算练习 -- 功能列表");
        for (int i = 1; i < ui1.length; i++) {
            if (ui1[i] != null && ui1[i].length() > 0) {
                System.out.println("" + i + " " + ui1[i]);
            }
        }
        System.out.println("0 " + ui1[0]);
        System.out.println("请选择功能序号");
    }

    public static void print2(int index) {
        System.out.println("您选择了功能：" + index + ui1[index]);
        if (ui2[index] != null && ui2[index].length > 0) {
            System.out.println("请选择习题类型");
            for (int i = 1; i < ui2[index].length; i++) {
                System.out.println("" + i + " " + ui2[index][i]);
            }
            System.out.println("0 " + ui2[index][0]);
        }
    }

    /**
     * 在线练习，从算式基里面随机取出
     * @param index
     * @return
     */
    public static boolean command1_1(int index) {
        System.out.println("您选择了" + index + " " + ui2[1][index]);
        //生成算式，数量由用户输入
        Exercise ex = exercise(new Exercise(count()), index);
        Student student = new Student();
        student.setSc(sc);
        student.setExercise(ex);
        //答题
        student.practiceOneByOne();
        end();
        return false;
    }

    private static Exercise exercise = null;

    /**
     * 批量生成习题，随机生成，并存入文件
     * @param index
     * @return
     */
    public static boolean command1_2(int index) {
        System.out.println("您选择了" + index + " " + ui2[2][index]);
        int count = count();
        //生成算式，数量由用户输入
        exercise = exerciseByBase(new Exercise(count), index);
        ExerciseFileDAO exDao = new ExerciseFileDAO();
        exDao.writeExerciseToFile(exercise);
        exercise.printExercise();
        Answer answer = new Answer();
        answer.scanAnswerFromKeyboard(count);
        answer.writeAnswerToFile();
        end();
        return false;
    }

    /**
     * 批改习题
     */
    public static void command1_3() {
        if (null == exercise || exercise.getExercise().isEmpty()) {
            System.out.println("暂未生成习题");
            end();
            return;
        }
        Check check = new Check();
        Answer answer = new Answer();
        answer.readAnswerFromFile();
        check.check(exercise, answer);
        check.writeCheckToFile();
        check.printCheck();
        end();
    }

    private static int count() {
        String count;
        System.out.println("请输入算式数量");
        count = sc.nextLine();
        while (Common.isDigit(Common.trim(count))) {
            System.out.println("请正确输入算式数量（数字）");
            count = sc.nextLine();
        }
        return Integer.parseInt(Common.trim(count));
    }

    /**
     * 生成算式题，随机生成
     * @param exercise
     * @param index
     * @return
     */
    private static Exercise exercise(Exercise exercise, int index) {
        if (index == 1) {
            exercise.generateAddExercise();
        } else if (index == 2) {
            exercise.generateSubExercise();
        } else {
            exercise.generateExercise();
        }
        return exercise;
    }

    /**
     * 生成算式题，通过算式基
     * @param exercise
     * @param index
     * @return
     */
    private static Exercise exerciseByBase(Exercise exercise, int index) {
        //创建文件类对象
        ExerciseFileDAO ex = new ExerciseFileDAO();
        if (index == 1) {
            //使用算式基生成算式题
            exercise = ex.readExerciseFromFile(ExerciseBase.ADD,exercise.getCount());
        } else if (index == 2) {
            exercise = ex.readExerciseFromFile(ExerciseBase.SUB,exercise.getCount());
        } else {
            exercise = ex.readExerciseFromFile(ExerciseBase.BLEND,exercise.getCount());
        }
        return exercise;
    }

    private static void end() {
        end(1);
    }

    private static void end(int n) {
        System.out.println("输入任意字符继续");
        for (int i = 0; i < n; i++) {
            sc.nextLine();
        }
    }

}
