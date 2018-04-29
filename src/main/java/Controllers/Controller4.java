package Controllers;

import Integral.Integral;
import Parser.MatchParser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller4 {

    @FXML TextField DataFor4 ;
    @FXML TextField DataTo4 ;
    @FXML TextField interval4 ;
    @FXML Button Add4;
    @FXML Button remove4;
    @FXML TextField Name4;
    @FXML VBox VboxLab4;
    @FXML Button help4;
    @FXML TextField function4;
    @FXML CheckBox Check;

    XYChart.Series series = new XYChart.Series();
    XYChart.Series series2 = new XYChart.Series();
    NumberAxis xAxis = new NumberAxis();
    NumberAxis yAxis = new NumberAxis();
    AreaChart<Number,Number> areaChart =
            new AreaChart<Number, Number>(xAxis,yAxis);
    LineChart<Number,Number> lineChart =
            new LineChart<Number, Number>(new NumberAxis(),new NumberAxis());


    public void initialize() {
        areaChart.setCreateSymbols(false);
        VboxLab4.getChildren().addAll(areaChart,lineChart);
    }

    public void readInform() throws Exception {
        MatchParser Parser = new MatchParser();
        Integral integral = new Integral();

        String DataFor = DataFor4.getText();
        String DataTo = DataTo4.getText();
        String Interval = interval4.getText();
        String Name = Name4.getText();
        String function = function4.getText();
        Double dataFor = Double.parseDouble(DataFor);
        Double dataTo = Double.parseDouble(DataTo);

        series.setName(Name);

        double i;
        double[] y = integral.integ(function, dataFor, dataTo);
        double[] axeX = integral.getStack();
        for (int iter = 0;iter<y.length;iter++){
             series2.getData().add(new XYChart.Data(axeX[iter], y[iter]));
        }

        if (Math.abs(Double.parseDouble(DataFor)-Double.parseDouble(DataTo))>1) {
            series2.setName("convergence of definite integral = " + Double.toString(y[y.length - 1]));
        }else {
            series2.setName("convergence function  f'(x0) = " + Double.toString(y[0]));
        }

        lineChart.getData().add(series2);
        series2 = new XYChart.Series();
        for ( i=Double.parseDouble(DataFor);i<Double.parseDouble(DataTo);i+=Double.parseDouble(Interval)){
            Parser.setVariable("x",i);
            try {
                series.getData().add(new XYChart.Data(i, Parser.solution(function,i)));
                series.getData().add(new XYChart.Data(Double.parseDouble(DataTo),
                        Parser.solution(function,Double.parseDouble(DataTo))));
            }catch (Exception e){}
        }
        areaChart.getData().add(series);
        series = new XYChart.Series();
    }

    public void removeGraph(){
        series = new XYChart.Series();
        series2 = new XYChart.Series();
        VboxLab4.getChildren().removeAll(lineChart,areaChart);
        lineChart= new LineChart<Number,Number>(new NumberAxis(),new NumberAxis());
        areaChart= new AreaChart<Number, Number>(new NumberAxis(),new NumberAxis());
        areaChart.setCreateSymbols(false);
        VboxLab4.getChildren().addAll(areaChart,lineChart);
    }
    public void help() throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("/Fxml/lab1/Help4.fxml"));
        Scene scene = new Scene(root2,800,600);
        Stage secondstage = new Stage();
        secondstage.setResizable(false);
        secondstage.setScene(scene);
        secondstage.show();
    }

}


