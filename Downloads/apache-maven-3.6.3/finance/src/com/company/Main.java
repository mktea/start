package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Random rand = new Random(1);
        int n = 100000;
        double a1=0,a2=0;
        double[] y = new double[n+1];
        double sig = 0.3;
        double T = 1.0;
        double r = 0.03;
        double S = 100.0;
        double K = 100.0;
        //一様乱数yをn個用意する
        for(int i=0;i<n/2;++i){
            a1 = rand.nextDouble();
            a2 = rand.nextDouble();
            y[2*i] = Math.sqrt(-2.0*Math.log(a1))*Math.cos(2.0*Math.PI*a2);
            y[2*i+1] = Math.sqrt(-2.0*Math.log(a1))*Math.sin(2.0*Math.PI*a2);
        }
        //原資産１単位のために標準正規乱数
        a1 = rand.nextDouble();
        a2 = rand.nextDouble();
        y[n] = Math.sqrt(-2.0*Math.log(a1))*Math.cos(2.0*Math.PI*a2);

        double[] St = new double[n+1]; //原資産
        double v=0;
        int b=1;//b=0のときはコールオプション、b=1のときはプットオプションを計算する
        for(int i=0;i<n;++i){
            St[i] = S*Math.exp((r-sig*sig/2.0)*T + sig*y[i]*Math.sqrt(T));
            if(b==0) v += (St[i]-K>0)?St[i]-K:0;
            else v += (K-St[i]>0)?K-St[i]:0;
        }
        //原資産価格
        St[n] = S*Math.exp((r-sig*sig/2.0)*T + sig*y[n]*Math.sqrt(T));
        v = v/(double)n*Math.exp(-r*T);
        if(b==0) System.out.println(v);
        else System.out.println("プットオプション " + v + " 、原資産価格 " + St[n]);

    }
}
