package cbsc.stage1;

import java.util.Random;

@SuppressWarnings(value = "all")
public abstract class Equation {

    private int MAX = 99;
    private int MIN = 0;
    //private final int COUNT = 50;
    private int left, right, result;
    private char op;


    public int getMAX() {
        return MAX;
    }

    public void setMAX(int MAX) {
        this.MAX = MAX;
    }

    public int getMIN() {
        return MIN;
    }

    public void setMIN(int MIN) {
        this.MIN = MIN;
    }

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

    public Equation() {
    }

    /**
     * 生成指定范围内的随机数
     * @param min
     * @param max
     * @return
     */
    private int generateRandom(int min, int max) {
        Random r = new Random();
        return r.nextInt(max - min + 1) + min;
    }

    /**
     * 判断结果是否在指定范围内
     * @param value
     * @param min
     * @param max
     * @return
     */
    private boolean isBetween(int value, int min, int max) {
        return value >= min && value <= max;
    }

    //用于计算的抽象方法
    protected abstract int calculate();

    /**
     * 判断算式对象是否相同
     * @param e
     * @return
     */
    public boolean isEqual(Equation e) {
        if (e.getOp() != this.getOp()) {
            return false;
        } else {
            return e.getLeft() == this.getLeft() && e.getRight() == this.getRight();
        }
    }

    @Override
    public String toString() {
        return intToString(this.getLeft()) + this.getOp() + intToString(this.getRight());
    }

    //输出格式化，个位数前拼接空格
    private String intToString(int i) {
        String s = String.valueOf(i);
        if (s.length() == 2) {
            return s;
        } else {
            return " " + s;
        }
    }

    public String toString2() {
        return this + "=";
    }

    public String toString3() {
        return toString2() + intToString(this.getResult());
    }

    /**
     * 生成算式
     * @param op
     */
    public void gengerateEquation(char op) {
        do {
            left = generateRandom(MIN, MAX);
            right = generateRandom(MIN, MAX);
            result = calculate();
        } while (!isBetween(result, MIN, MAX));
        this.setOp(op);
    }

}
