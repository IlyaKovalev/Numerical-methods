package Derivative;
import Parser.MatchParser;

import java.util.List;

public class Derivative {

    double Limit = 0;
    double dx[] = {100,50,20,10,1.2,0.5,0.1,0.01,0.001,0.0001,0.00001,0.000001};
    public double getLimit(){return Limit; }
    public void setLimit(double lim){Limit = lim;}

    public double[] diff(double x,String s){
        double[] values=new double[dx.length];
        int i=0;
        MatchParser Parser = new MatchParser();
        for (Double delta:dx) {
            try {
                Double deltaY = Parser.solution(s,x+delta);
                Double argument = Parser.solution(s,x);
                Double derivative = (deltaY-argument)/delta;
                values[i]=derivative;
                ++i;
            } catch (Exception e) {System.out.print("keks");}
        }
        setLimit(values[values.length-1]);
        return values;
    }

}
