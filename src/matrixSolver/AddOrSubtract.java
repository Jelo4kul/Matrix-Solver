/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrixSolver;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;

/**
 *
 * @author Jelo4kul
 */
public class AddOrSubtract {

    private ArrayList<Character> operation = new ArrayList<>();
    
    AddOrSubtract(ArrayList<Character> operation){
        this.operation=operation;
    }

    public  void addButtonClicked(Button addBtn, final FlowPane flowPane) {
        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                operation.add('+');
                flowPane.getChildren().add(new Text("+"));
            }
        });
    }

    public  void subButtonClicked(Button subBtn, final FlowPane flowPane) {
        subBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                operation.add('-');
                flowPane.getChildren().add(new Text("-"));
            }
        });

    }

}
