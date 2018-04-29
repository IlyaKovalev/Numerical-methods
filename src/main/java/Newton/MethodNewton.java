package Newton;

import Parser.MatchParser;
import java.util.Stack;

public class MethodNewton {

    private final String func;
    private  double a;
    private  double b;
    double h = 0.0001;

    MatchParser parser = new MatchParser();

    public MethodNewton(String func,double a ,double b){
        this.func = func;
        this.a = a;
        this.b = b;
    }

    public double F(double x) throws Exception {
        if (Math.abs(parser.solution(func,x))<=0.001){
            return x;
        }else {
        return F(x - (parser.solution(func,x)*h)/
                (parser.solution(func,x+h)-parser.solution(func,x)));
        }
    }
    public Stack<Double> roots() throws Exception {
        double distance = b-a;
        double dx;
        if (distance<1){
             dx = distance/10;
        }else {
             dx = distance/Math.pow(10,DigitNumber(distance)-1);
        }
        Stack<Double> roots = new Stack<>();
        double Fa = F(a);
        double Fb = F(b);
        roots.add(Fa);
        roots.add(Fb);
        for (int i = 0;i<distance;i+=dx){
            a+=dx;
            b+=dx;
            Fa = F(a);
            Fb = F(b);
            roots.add(Fa);
            roots.add(Fb);
        }
        Stack<Double> production = new Stack<>();

        production.push((double) Math.round(roots.pop()*10000)/10000);
        while (!roots.isEmpty()){
            double addNumber = Math.round(roots.pop()*10000)/10000;
            if(!production.contains(addNumber)){
                production.push(addNumber);
            }
       }

       return production;
    }
    public int DigitNumber(double number){
        String digit = Double.toString(number);
        for (int i=0;i<digit.length();i++){
            if (digit.charAt(i) == '.'){
                return i;
            }
        }

        return digit.length();
    }
}
