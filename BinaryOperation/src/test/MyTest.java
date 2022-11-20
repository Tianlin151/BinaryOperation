package test;

import cbsc.stage1.Exercise;
import cbsc.stage2.Common;
import cbsc.stage2.ExerciseFileDAO;
import org.junit.jupiter.api.Test;

public class MyTest {

    @Test
    public void Test() {
        //生成算式基
        Common.generateAddFormulaBasis();
        //创建文件类对象
        ExerciseFileDAO ex = new ExerciseFileDAO();
        try {
            Exercise exercise = ex.readExerciseFromFile(System.getProperty("user.dir") + "\\" + "exerciseAddFB.csv", 20);
            exercise.printExercise();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
