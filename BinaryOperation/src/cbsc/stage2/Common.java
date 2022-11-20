package cbsc.stage2;

import cbsc.stage1.AddEquation;
import cbsc.stage1.Equation;
import cbsc.stage1.Exercise;
import cbsc.stage1.SubEquation;

import java.util.*;

public class Common {


    public static long startTime;
    public static long endTime;
    public static double time;

    public static void startTime(){
        startTime = System.currentTimeMillis();
    }

    public static void endTime(){
        endTime = System.currentTimeMillis();
    }

    public static double getTime(){
        time = endTime - startTime;
        return time/1000;
    }

    //递归，判断是否为空
    public static boolean isDigit(String s) {
        if (null == s || "".equals(s)) {
            return true;
        }
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    //去空格
    public static String trim(String original) {
        char[] val = original.toCharArray();
        int st = 0;
        int len = val.length;
        //半角空格
        while ((st < len) && (val[st] <= ' ')) {
            st++;
        }
        while ((st < len) && (val[len - 1] <= ' ')) {
            len--;
        }
        //全角空格
        while ((st < len) && (val[st] <= ' ')) {
            st++;
        }
        while ((st < len) && (val[len - 1] <= ' ')) {
            len--;
        }
        return ((st > 0) || (len < val.length)) ? original.substring(st, len) : original;
    }

    //生成加法算式基
    public static void generateAddFormulaBasis(){
        //创建文件类对象
        ExerciseFileDAO ex = new ExerciseFileDAO();
        ArrayList<Equation> equations = new ArrayList<>();
        Equation equation ;
        for (int i = 0; i <= 100; i++) {
            for (int j = 0; j <= 100-i; j++) {
                equation = new AddEquation(i+"+"+j);
                equations.add(equation);
            }
        }
        Exercise exercise = new Exercise(equations.size());
        exercise.setExercise(equations);
        ex.writeExerciseToFile(exercise,System.getProperty("user.dir") + "\\" + "exerciseAddFB.csv");
    }

    //生成减法算式基
    public static void generateSubFormulaBasis(){
        //创建文件类对象
        ExerciseFileDAO ex = new ExerciseFileDAO();
        ArrayList<Equation> equations = new ArrayList<>();
        Equation equation ;
        for (int i = 100; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                equation = new SubEquation(i+"-"+j);
                equations.add(equation);
            }
        }
        Exercise exercise = new Exercise(equations.size());
        exercise.setExercise(equations);
        ex.writeExerciseToFile(exercise,System.getProperty("user.dir") + "\\" + "exerciseSubFB.csv");
    }

    //生成加减法混合算式基
    public static void generateAddAndSubFormulaBasis(){
        //创建文件类对象
        ExerciseFileDAO ex = new ExerciseFileDAO();
        ArrayList<Equation> equations = new ArrayList<>();
        Equation equation ;
        for (int i = 0; i <= 100; i++) {
            for (int j = 0; j <= 100-i; j++) {
                equation = new AddEquation(i+"+"+j);
                equations.add(equation);
            }
        }
        for (int i = 100; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                equation = new SubEquation(i+"-"+j);
                equations.add(equation);
            }
        }
        Exercise exercise = new Exercise(equations.size());
        exercise.setExercise(equations);
        ex.writeExerciseToFile(exercise,System.getProperty("user.dir") + "\\" + "exerciseAddAndSubFB.csv");
    }

    /**
     * 生成指定数量的不重复随机数
     * @param number
     * @return
     */
    public static Set<Integer> randomNumber(int number,int min,int max){
        Set<Integer> set = new HashSet<>();
        while (true){
            Random r = new Random();
            int num = r.nextInt(max - min + 1) + min;
            set.add(num);//Set类型元素不允许有重复元素
            if(set.size()==number){
                break;
            }
        }
        return set;
    }
}
