package Integral;

import Parser.MatchParser;

public class Integral {

    double[] stack = {};

    public void setStack(double[] s){
        stack = s;
    }
    public double[] getStack(){return stack;}

    public double[] integ(String func,double a,double b) throws Exception {

        double A = a;
        double interval = b-a;
        double interval2 = interval;
        int count = 0;
        if (interval>1) {
            while (interval2 > 1) {
                interval2/= 10;
                count++;
            }
        }else{
            while (interval2<1) {
                interval2*=10;
                count--;
            }

        }
        double sum = 0;
        int iter=0;
        MatchParser Parser = new MatchParser();
        double[] values = new double[9];
        double[] sta = {count - 4, count - 3, count - 2, count - 1, count, count + 1, count + 2, count + 3, count+4 };
        setStack(sta);
        for (double i :stack){
            double dx = interval/Math.pow(10,i);
            for (int c = 0;c<=Math.pow(10,i);c++){
                A+=dx;
                double y = dx*Parser.solution(func,A);
                sum+=y;
            }
            A=a;
            values[iter] = sum;
            sum = 0;
            iter++;
        }
        for (double erx:values) {
            System.out.println(erx);
        }
        return values;


    }
}
