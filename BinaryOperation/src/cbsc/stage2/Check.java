package cbsc.stage2;

import cbsc.stage1.Exercise;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

@SuppressWarnings(value = "all")
public class Check {

    private int count;//总数
    private int right;//正确数
    private int wrong;//错误数

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getWrong() {
        return wrong;
    }

    public void setWrong(int wrong) {
        this.wrong = wrong;
    }

    public Check() {
        right = 0;
        wrong = 0;
    }

    //检查答案，得出对错数目
    public void check(Exercise ex, Answer an) {
        count = ex.size();
        //wrong用来记录错误的个数，right用来记录正确的个数
        int wrong = 0, right = 0;
        ex.setIndex(0);
        an.reset();
        while (ex.hasNext()) {
            if (ex.next().getResult() == an.next()) {
                right++;
            } else {
                wrong++;
            }
        }
        setRight(right);
        setWrong(wrong);
    }

    public void writeCheckToFile() {
        writeCheckToFile(System.getProperty("user.dir") + "\\" + "check.txt");
    }

    public void writeCheckToFile(String fileName) {
        Writer out = null;
        try {
            File exFile = new File(fileName);
            File fileParent = exFile.getParentFile();
            if (!fileParent.exists()) {
                fileParent.mkdirs();
            }
            exFile.delete();
            exFile.createNewFile();
            out = new FileWriter(exFile, true);
            out.write("算式总数：" + count + ";\r\n");
            out.write("正确：" + right + ";\r\n");
            out.write("错误：" + wrong + ";\r\n");
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void printCheck() {
        System.out.println("本次练习批改结果：");
        System.out.println("算式总数：" + count);
        System.out.println("正确：" + right);
        System.out.println("错误：" + wrong);
    }
}
