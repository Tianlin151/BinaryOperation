package cbsc.stage2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings(value = "all")
public class Answer {

    private ArrayList<Integer> answer;
    private int index;

    public ArrayList<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(ArrayList<Integer> answer) {
        this.answer = answer;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Answer() {
        answer = new ArrayList<>();
        index = 0;
    }

    public void writeAnswerToFile() {
        writeAnswerToFile(System.getProperty("user.dir") + "\\" + "answer.csv");
    }

    public void readAnswerFromFile() {
        readAnswerFromFile(System.getProperty("user.dir") + "\\" + "answer.csv");
    }

    public void writeAnswerToFile(String fileName) {
        Writer out;
        try {
            File exFile = new File(fileName);
            File fileParent = exFile.getParentFile();
            if (!fileParent.exists()) {
                fileParent.mkdirs();
            }
            exFile.delete();
            exFile.createNewFile();
            out = new FileWriter(exFile, true);
            for (Integer i : answer) {
                out.write(i + ",");
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readAnswerFromFile(String fileName) {
        File exFile = new File(fileName);
        String a;
        answer.clear();
        try (Scanner in = new Scanner(exFile)) {
            in.useDelimiter(",");
            while (in.hasNext()) {
                a = in.next().replaceAll("\\s", "");
                answer.add(Integer.parseInt(a));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void scanAnswerFromKeyboard(int count) {
        Scanner sc = new Scanner(System.in);
        answer.clear();
        System.out.println("请按照题目序号依次输入答案后回车");
        for (int i = 1; i <= count; i++) {
//            String s;
//            while (true) {
//                boolean b = true;
//                System.out.print(intToString(i) + ") ");
//                s = sc.nextLine();
//                for (int j = 0; j < s.length(); j++) {
//                    if (!Character.isDigit(s.charAt(j))) {
//                        b = false;
//                        break;
//                    }
//                }
//                if (b) {
//                    break;
//                }
//            }
//            answer.add(Integer.parseInt(s));
            answer.add(scNextInt(sc, i));
        }
//        sc.close();
    }

    private int scNextInt(Scanner sc, int i) {
        System.out.print(intToString(i) + ") ");
        String count = sc.nextLine();
        while (Common.isDigit(Common.trim(count))) {
            System.out.print("请输入第" + intToString(i) + ") " + "题答案：");
            count = sc.nextLine();
        }
        return Integer.parseInt(Common.trim(count));

    }

    private String intToString(int i) {
        String s = String.valueOf(i);
        if (s.length() == 2) {
            return s;
        } else {
            return " " + s;
        }
    }

    public void reset() {
        index = 0;
    }

    public boolean add(int a) {
        return answer.add(a);
    }

    public boolean hasNext() {
        return index < answer.size();
    }

    public int next() {
        if (index < answer.size()) {
            return answer.get(index++);
        } else {
            return -1;
        }
    }

    public int get(int index) {
        return answer.get(index);
    }

    public void set(int index, int x) {
        answer.set(index, x);
    }

}
