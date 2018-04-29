package Controllers;

import Derivative.Derivative;
import Parser.MatchParser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller2 {
    @FXML TextField DataFor2 ;
    @FXML TextField DataTo2 ;
    @FXML TextField interval12 ;
    @FXML Button Add2;
    @FXML Button remove2;
    @FXML TextField Name2;
    @FXML VBox VboxLab12;
    @FXML Button help2;
    @FXML TextField function2;
    @FXML TextField PointDer;

    XYChart.Series series = new XYChart.Series();
    XYChart.Series series3 = new XYChart.Series();
    XYChart.Series series2 = new XYChart.Series();
    XYChart.Series ser = new XYChart.Series();
    NumberAxis xAxis = new NumberAxis();
    NumberAxis yAxis = new NumberAxis();
    LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
    LineChart<Number,Number> lineChart2 = new LineChart<Number,Number>(new NumberAxis(),new NumberAxis());
    Derivative derivative = new Derivative();

    public void setSeries(){
        ser = series;
        series = new XYChart.Series();
    }

    public void initialize() {
        lineChart.setCreateSymbols(false);
        VboxLab12.getChildren().addAll(lineChart,lineChart2);
    }

    public void readInform() throws Exception {
        MatchParser Parser = new MatchParser();
        String DataFor = DataFor2.getText();
        String DataTo = DataTo2.getText();
        String Interval = interval12.getText();
        String Name = Name2.getText();
        String function = function2.getText();
        Double Point = Double.parseDouble(PointDer.getText());
        series.setName(Name);
        series3.setName("derivative "+Name+" in x = "+Double.toString(Point));

        double i;
        double axeX = 1;

        for (double dy:derivative.diff(Point,function)){
            series2.getData().add(new XYChart.Data(axeX, dy));
            axeX = axeX/10;
        }
        series2.setName("convergence function  f'(x0) = "+Double.toString(derivative.getLimit()));
        lineChart2.getData().add(series2);
        series2 = new XYChart.Series();
        double lim = derivative.getLimit();
        double DerInPoint = Parser.solution(function,Point);
        for ( i=Double.parseDouble(DataFor);i<Double.parseDouble(DataTo);i+=Double.parseDouble(Interval)){
            Parser.setVariable("x",i);
            try {
                series3.getData().add(new XYChart.Data(i, Parser.solution(
                        Double.toString(lim)+"*(x-"+Double.toString(Point)+")"+"+"+Double.toString(DerInPoint),i)));
                series.getData().add(new XYChart.Data(i, Parser.solution(function,i)));
            }catch (Exception e){}
        }

        try {
            series.getData().add(new XYChart.Data(Double.parseDouble(DataTo), Parser.solution(function,Double.parseDouble(DataTo))));
        }catch (Exception e){}

        lineChart.getData().add(series);
        lineChart.getData().add(series3);
        series = new XYChart.Series();
        series3 = new XYChart.Series();
    }
    public void removeGraph(){
        series = new XYChart.Series();
        VboxLab12.getChildren().removeAll(lineChart,lineChart2);
        lineChart= new LineChart<Number,Number>(new NumberAxis(),new NumberAxis());
        lineChart2= new LineChart<Number,Number>(new NumberAxis(),new NumberAxis());
        lineChart.setCreateSymbols(false);
        VboxLab12.getChildren().addAll(lineChart,lineChart2);
    }
    public void help() throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("/Fxml/Help.fxml"));
        Scene scene = new Scene(root2,800,600);
        Stage secondstage = new Stage();
        secondstage.setResizable(false);
        secondstage.setScene(scene);
        secondstage.show();
    }

 }


