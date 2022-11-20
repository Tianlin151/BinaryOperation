package cbsc.stage2;

import cbsc.stage1.AddEquation;
import cbsc.stage1.Equation;
import cbsc.stage1.Exercise;
import cbsc.stage1.SubEquation;

import java.io.*;
import java.util.Scanner;
import java.util.Set;

@SuppressWarnings(value = "all")
public class ExerciseFileDAO {

    public ExerciseFileDAO() {
    }

    public void writeExerciseToFile(Exercise e) {
        writeExerciseToFile(e, System.getProperty("user.dir") + "\\" + "exercise.csv");
    }

    public Exercise readExerciseFromFile() {
        return readExerciseFromFile(System.getProperty("user.dir") + "\\" + "exercise.csv");
    }

    public void writeExerciseToFile(Exercise e, String fileName) {
        Writer out = null;
        Equation equation;
        try {
            File exFile = new File(fileName);
            File fileParent = exFile.getParentFile();
            if (!fileParent.exists()) {
                fileParent.mkdirs();
            }
            exFile.delete();
            exFile.createNewFile();
            out = new FileWriter(exFile, true);
            for (int i = 0; i < e.getCount(); i++) {
                equation = e.getExercise().get(i);
                out.write(equation.toString2() + ",");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public Exercise readExerciseFromFile(String fileName) {
        File exFile = new File(fileName);
        Exercise exercise = new Exercise();
        String equation;
        try (Scanner in = new Scanner(exFile)) {
            in.useDelimiter(",");
            while (in.hasNext()) {
                equation = in.next().replaceAll("\\s", "");
                if (equation.contains("+")) {
                    exercise.add(new AddEquation(equation));
                } else {
                    exercise.add(new SubEquation(equation));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return exercise;
    }


    /**
     * 读取算式基
     * @param fileName
     * @param number 读取的数量
     * @return
     */
    public Exercise readExerciseFromFile(String fileName, int number) {
        Exercise exercise = new Exercise(number);
        String equation;
        try {
            File exFile = new File(fileName);
            FileReader in = new FileReader(exFile);
            LineNumberReader reader = new LineNumberReader(in);
            String[] split = reader.readLine().split(",");
            Set<Integer> set = Common.randomNumber(number,0,split.length);
            for (Integer i:set){
                equation = split[i].replace("=","").replace(" ","");
                if (equation.contains("+")) {
                    exercise.add(new AddEquation(equation));
                } else {
                    exercise.add(new SubEquation(equation));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return exercise;
    }

    public Exercise readExerciseFromFile(ExerciseBase name, int number){
        if((ExerciseBase.ADD).equals(name)){
            return readExerciseFromFile(System.getProperty("user.dir") + "\\" + "exerciseAddFB.csv",number);
        }else if((ExerciseBase.SUB).equals(name)){
            return readExerciseFromFile(System.getProperty("user.dir") + "\\" + "exerciseSubFB.csv",number);
        }else{
            //若输入错误，则默认使用加减法混合算式基
            return readExerciseFromFile(System.getProperty("user.dir") + "\\" + "exerciseAddAndSubFB.csv",number);
        }
    }
}
