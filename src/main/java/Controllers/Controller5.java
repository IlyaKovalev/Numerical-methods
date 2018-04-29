package Controllers;
import Newton.MethodNewton;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.Stack;

public class Controller5 {

    @FXML TextField forField;
    @FXML TextField toField;
    @FXML TextField intervalField;
    @FXML TextField funcField;
    @FXML TextField NameField;
    @FXML Button Compute;
    @FXML Button Clear;
    @FXML VBox Box;
    @FXML ScrollPane scroll;

    public void Compute() throws Exception {

        double a = Double.parseDouble(forField.getText());
        double b = Double.parseDouble(toField.getText());
        String func = funcField.getText();

        MethodNewton method = new MethodNewton(func,a,b);
        Stack<Double> roots = method.roots();
        int i = 0;
        while (!roots.isEmpty()){
                double value = roots.pop();
                Box.getChildren().add(new Label(String.format("x%1$1s= %2$1s",i,value)));
                i++;
        }
        scroll.setContent(Box);
    }

    public void Clear(){
        Box.getChildren().clear();
        scroll.setContent(new HBox());
    }

}
