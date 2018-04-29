package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import Parser.MatchParser;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller  {

    @FXML TextField DataFor ;
    @FXML TextField DataTo ;
    @FXML TextField interval1 ;
    @FXML Button Add;
    @FXML Button remove;
    @FXML TextField Name;
    @FXML VBox VboxLab1;
    @FXML Button help;
    @FXML TextField function;
    @FXML TextField TextFieldDecimal;
    @FXML Button Count;
    @FXML ListView<String> list_view;

    XYChart.Series series = new XYChart.Series();
    XYChart.Series ser = new XYChart.Series();
    NumberAxis xAxis = new NumberAxis();
    NumberAxis yAxis = new NumberAxis();
    LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);

     ObservableList<String> list = FXCollections.observableArrayList();

    public void initialize() {
        lineChart.setCreateSymbols(false);
        VboxLab1.getChildren().addAll(lineChart);
    }

    public void setSeries(){
        ser = series;
        series = new XYChart.Series();
    }
    public XYChart.Series getSer (){
        return ser;
    }

    public void read_inform(){

        String dataFor = DataFor.getText();
        String dataTo = DataTo.getText();
        String Interval = interval1.getText();
        String func = function.getText();
        String name = Name.getText();

        series.setName(name);
        double i;
        MatchParser P= new MatchParser();
        for ( i=Double.parseDouble(dataFor);i<Double.parseDouble(dataTo);i+=Double.parseDouble(Interval)){
          P.setVariable("x",i);
           try {
               series.getData().add(new XYChart.Data(i, P.solution(func,i)));
           }catch (Exception e){}
        }
            try {
                series.getData().add(new XYChart.Data(Double.parseDouble(dataTo),
                        P.solution(func,Double.parseDouble(dataTo))));
            }catch (Exception e){}

        lineChart.getData().add(series);
            setSeries();
    }

    public void removeGraph(){
        series = new XYChart.Series();
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        VboxLab1.getChildren().remove(lineChart);
        lineChart= new LineChart<Number,Number>(xAxis,yAxis);
        lineChart.setCreateSymbols(false);
        VboxLab1.getChildren().add(lineChart);
    }


    public void help(ActionEvent event) throws IOException {

        Parent root2 = FXMLLoader.load(getClass().getResource("/Fxml/lab1/helpWindow.fxml"));
        Scene scene = new Scene(root2,425,410);
        Stage secondstage = new Stage();
        secondstage.setResizable(false);
        secondstage.setScene(scene);

        secondstage.show();
    }

    public void round() {
        double dec = Double.parseDouble(TextFieldDecimal.getText());

        int value = TextFieldDecimal.getText().length()-3;
        if (value<=0)value=0;
        long factor = (long) Math.pow(10, 2);
        double dec_round = dec * factor;
        double tmp = Math.round(dec_round);
        dec_round = tmp / factor;
        double absolute_error = Math.abs(dec_round-dec);
        String entry = "Number: "+Double.toString(dec)+" Round: "+Double.toString(dec_round)+
                "Error:"+Double.toString(absolute_error);
        list.add(entry);
        list_view.setItems(list);
    }

}
