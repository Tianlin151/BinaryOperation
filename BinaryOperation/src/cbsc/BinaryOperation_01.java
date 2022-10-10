package cbsc;

import java.util.Random;

/**
 * 代码2.1：一个Java程序，产生并逐行输出50道100以内的加减法算式
 */
public class BinaryOperation_01 {
    public static void main(String[] args) {
        short m=0,n=0,ov=0;
        char o='+';
        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            //随机生成0或1,0：-，1：+
            ov = (short)random.nextInt(2);
            //随机生成算式左边的数
            m = (short)random.nextInt(101);
            //随机生成算式右边的数
            n = (short)random.nextInt(101);
            if(ov==1){
                o='+';
            }else{
                o='-';
            }
            //逐行输出
            System.out.println(""+(i+1)+":\t"+m+o+n+"=");
        }
    }
}
