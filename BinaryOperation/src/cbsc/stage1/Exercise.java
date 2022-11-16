package cbsc.stage1;

import java.util.ArrayList;
import java.util.Random;

@SuppressWarnings(value = "all")
public class Exercise {

    //生成的题目个数
    private int count = 50;
    private int index;
    private ArrayList<Equation> exercise;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public ArrayList<Equation> getExercise() {
        return exercise;
    }

    public void setExercise(ArrayList<Equation> exercise) {
        this.exercise = exercise;
    }

    public Exercise() {
        this.setIndex(0);
        exercise = new ArrayList<>();
    }

    public Exercise(int count) {
        this.setCount(count);
        this.setIndex(0);
        this.setExercise(new ArrayList<>());
    }

    /**
     * 判断算式是否重复
     * @param e
     * @return
     */
    public boolean occursIn(Equation e) {
        boolean b = false;
        for (Equation equation : exercise) {
            if (equation.isEqual(e)) {
                b = true;
                break;
            }
        }
        return b;
    }

    /**
     * 生成加减法混合算式
     */
    public void generateExercise() {
        int i = 0;
        Random r = new Random();
        while (i < count) {
            Equation e;
            if (r.nextInt(2) == 1) {
                e = new AddEquation();
            } else {
                e = new SubEquation();
            }
            if (!occursIn(e)) {
                exercise.add(e);
                i++;
            }
        }
    }

    /**
     * 生成加法算式
     */
    public void generateAddExercise() {
        int i = 0;
        while (i < count) {
            Equation e = new AddEquation();
            if (!occursIn(e)) {
                exercise.add(e);
                i++;
            }
        }
    }

    /**
     * 生成减法算式
     */
    public void generateSubExercise() {
        int i = 0;
        while (i < count) {
            Equation e = new SubEquation();
            if (!occursIn(e)) {
                exercise.add(e);
                i++;
            }
        }
    }

    /**
     * 打印算式
     */
    public void printExercise() {
        int i = 0;
        for (Equation e : exercise) {
            i++;
            System.out.print(intToString(i) + ") " + e.toString());
            if (i % 5 == 0) {
                System.out.println();
            } else {
                System.out.print("\t\t");
            }
        }
        System.out.println();
    }

    private String intToString(int i) {
        String s = String.valueOf(i);
        if (s.length() == 2) {
            return s;
        } else {
            return " " + s;
        }
    }

    public boolean hasNext() {
        return index < exercise.size();
    }

    public Equation next() {
        if (index < exercise.size()) {
            return exercise.get(index++);
        } else {
            return null;
        }
    }

    public boolean add(Equation e) {
        if (index < count) {
            exercise.add(e);
            index++;
            return true;
        } else {
            return false;
        }
    }

    public int size() {
        return exercise.size();
    }
}
