package matrixSolver;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Jelo4kul
 */
public class MainMenu extends Application {

    private final ScrollPane scrollPane = new ScrollPane();
    protected final FlowPane operationArea = new FlowPane();
    protected int numberOfMatrices, resultFlag;
    protected GridPane resultMatrix;
    protected int size;
    protected ArrayList<Character> operation = new ArrayList<>();
    protected ArrayList array[] = new ArrayList[8];

    @Override
    public void start(Stage primaryStage) {

        Scene scene = new Scene(populateScene(), 1000, 600);
        scene.getStylesheets().add(MainMenu.class.getResource("matrixStyle.css").toExternalForm());

        primaryStage.setTitle("Calculation Menu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public VBox populateScene() {
        VBox root = new VBox();

        StackPane pane = new StackPane();
        pane.setId("pane");

        //createtopPanel and contents
        HBox topPanel = createTopPanelAndContents(pane);

        //create our centerpanel
        HBox centerPanel = createCenterPanel();

        VBox leftcontents = new VBox();
        leftcontents.setPrefHeight(420);
        leftcontents.setPrefWidth(260);
        leftcontents.getStyleClass().add("baseBackground");

        createLeftContents(leftcontents);

        VBox rightContents = new VBox();
        rightContents.setPrefHeight(420);
        rightContents.setPrefWidth(740);

        StackPane opHeader = createHeaderAndContent(rightContents);

        //here we create the matrix area
        VBox matrixArea = createMatrixArea(rightContents);

        //this a panel that provides a list of buttons such as( add or subtract )to chjoose from
        HBox chooserLayout = createChooserPanel();

        //here we create the items contained in the chooser panel
        createChooserPanelItems(chooserLayout);

        //region for our matrix operations
        setFlowPaneProperties();

        //the base layout for our calculate button
        StackPane bottomLayout = createBottomLayout();

        //the button in the bottom layout
        Button btn = createBottomLayoutContent(bottomLayout);

        matrixArea.getChildren().addAll(chooserLayout, scrollPane, bottomLayout);

        rightContents.getChildren().addAll(opHeader, matrixArea);

        centerPanel.getChildren().addAll(leftcontents, rightContents);

        root.getChildren().addAll(pane, centerPanel);
        return root;
    }

    public HBox createTopPanelAndContents(StackPane pane) {
        HBox topPanel = new HBox(20);
        topPanel.setId("topPanel");
        topPanel.setPrefHeight(180);
        pane.getChildren().add(topPanel);

        VBox textPanel = new VBox();
        textPanel.setPadding(new Insets(0, 0, 0, 110));

        //first content of the header
        Text lbl = new Text("MATRIX SOLVER");
        lbl.getStyleClass().add("lbl");
        textPanel.getChildren().add(lbl);

        //second content of the header
        Label lblcontent = new Label("Matrix solver is a student companion in solving simple and "
                + "complex matrix problems with ease. The experience provided by the application is "
                + "beyond comparison.");
        lblcontent.setWrapText(true);
        lblcontent.setPrefWidth(500);
        lblcontent.setId("lblcontent");
        textPanel.getChildren().add(lblcontent);

        //logo for the application
        ImageView imgView = new ImageView();
        Image img = new Image(getClass().getResourceAsStream("logo.png"));
        imgView.setImage(img);

        topPanel.getChildren().addAll(imgView, textPanel);
        return topPanel;
    }

    public HBox createCenterPanel() {
        HBox centerPanel = new HBox();
        centerPanel.setId("centerPanel");
        centerPanel.setPrefHeight(420);
        return centerPanel;
    }

    public void createLeftContents(VBox leftcontents) {

        StackPane pane1 = createPane(leftcontents);
        StackPane pane2 = createPane(leftcontents);
        StackPane pane3 = createPane(leftcontents);
        StackPane pane4 = createPane(leftcontents);
        StackPane pane5 = createPane(leftcontents);

        final Label addSub = addLabels("Add, Subtract");
        addSub.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                //new AddOrSubtract();
            }
        });
        addSub.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                addSub.setScaleX(1.1);
                addSub.setScaleY(1.1);
            }
        });
        addSub.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                addSub.setScaleX(1);
                addSub.setScaleY(1);
            }
        });
        pane1.getChildren().add(addSub);

        Label multiply = addLabels("Multiply, Power");
        pane2.getChildren().add(multiply);

        Label transpose = addLabels("Transpose");
        pane3.getChildren().add(transpose);

        Label determinant = addLabels("Determinant");
        pane4.getChildren().add(determinant);

        Label rank = addLabels("Rank");
        pane5.getChildren().add(rank);

        leftcontents.getChildren().addAll(pane1, pane2, pane3, pane4, pane5);
    }

    public StackPane createHeaderAndContent(VBox rightContents) {
        StackPane opHeader = new StackPane();
        opHeader.setPrefHeight(42);
        opHeader.setPrefWidth(rightContents.getWidth());

        Label opTitle = new Label("Add, Subtract");
        opTitle.setId("operationTitle");
        opTitle.setAlignment(Pos.CENTER);
        opHeader.getChildren().add(opTitle);
        return opHeader;
    }

    public VBox createMatrixArea(VBox rightContents) {
        VBox matrixArea = new VBox();
        matrixArea.setPrefHeight(378);
        matrixArea.setPrefWidth(rightContents.getWidth());
        matrixArea.setPadding(new Insets(0, 10, 0, 10));
        return matrixArea;
    }

    public HBox createChooserPanel() {
        HBox chooserLayout = new HBox(10);
        chooserLayout.setAlignment(Pos.CENTER);
        chooserLayout.setPrefHeight(37);
        chooserLayout.setPrefWidth(100);
        chooserLayout.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 0% 100%, #72706f, #1f1a17 65%, #242331)");
        return chooserLayout;
    }

    public void createChooserPanelItems(HBox chooserLayout) {
        final int tempsize[] = {2, 3, 4, 5};
        ChoiceBox<String> choiceBox = new ChoiceBox<>(FXCollections.observableArrayList("2*2", "3*3", "4*4", "5*5"));
        choiceBox.getStyleClass().add("button");
        choiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number oldval, Number newval) {
                size = tempsize[newval.intValue()];
            }
        });
        Button matrixBtn = new Button("Matrix");
        buttonClicked(matrixBtn, operationArea);

        AddOrSubtract addOp = new AddOrSubtract(operation);
        Button addBtn = new Button("Add");
        addOp.addButtonClicked(addBtn, operationArea);

        Button subtractBtn = new Button("Subtract");
        addOp.subButtonClicked(subtractBtn, operationArea);

        Button btn4 = new Button("Reset");

        chooserLayout.getChildren().addAll(choiceBox, matrixBtn, addBtn, subtractBtn, btn4);

        //the event listeners and handlers of the chooser panel components
        buttonClicked(matrixBtn, operationArea);

    }

    //this is the matrix and operations container
    public void setFlowPaneProperties() {
        operationArea.setId("operationArea");
        operationArea.setHgap(5);
        operationArea.setVgap(5);
        operationArea.setPrefWidth(700);

        scrollPane.setPrefHeight(304);
        scrollPane.setId("operationArea");
        scrollPane.setContent(operationArea);

    }

    public StackPane createBottomLayout() {
        StackPane bottomLayout = new StackPane();
        bottomLayout.setPrefHeight(37);
        bottomLayout.setPrefWidth(100);
        bottomLayout.getStyleClass().add("baseBackground");
        return bottomLayout;
    }

    public Button createBottomLayoutContent(StackPane bottomLayout) {
        Button btn = new Button("Calculate");
        btn.getStyleClass().add("button");
        btn.setAlignment(Pos.CENTER);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {

                try {
                    resultFlag++;

                    int arr[] = new int[size * size];

                    for (int i = 0; i < numberOfMatrices; i++) {

                        //summing up the values in the text field
                        for (int j = 0; j < size * size; j++) {
                            TextField f = (TextField) array[i].get(j);

                            //save the first numbers(matrix) in the array
                            if (i == 0) {
                                arr[j] += Integer.parseInt(f.getText());
                            } //check if the subsequent numbers should be added or subtracted
                            else if (operation.get(i - 1).equals('+')) {
                                arr[j] += Integer.parseInt(f.getText());
                            } else {
                                arr[j] -= Integer.parseInt(f.getText());

                            }

                        }
                    }

                    if (resultFlag == 1) {
                        displayResults(arr);

                    }

                } catch (Exception e) {

                }
            }
        });
        bottomLayout.getChildren().add(btn);
        return btn;
    }
    public void displayResults(int arr[]) {
        //create a result matrix
        resultMatrix = new GridPane();
        int count = 0;

        for (int y = 0; y < size; y++) {

            for (int x = 0; x < size; x++) {

                // Create a new TextField in each Iteration
                TextField tf = createTextFields(arr, count++);
                resultMatrix.add(tf, x, y);

            }
        }

        operationArea.getChildren().addAll(new Text("="), resultMatrix);
    }   public TextField createTextFields(int arr[], int count) {
        TextField tf = new TextField();
        tf.setPrefHeight(50);
        tf.setPrefWidth(50);
        tf.setAlignment(Pos.CENTER);
        tf.setEditable(false);
        tf.setText("" + arr[count]);
        return tf;
    }
    public StackPane createPane(VBox contents) {
        StackPane pane = new StackPane();
        pane.setPrefWidth(contents.getWidth());
        pane.setPrefHeight(40);
        pane.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 0% 100%, #383431, #1f1a17)");
        return pane;
    }

    public Label addLabels(String string) {
        Label addSub = new Label(string);
        addSub.getStyleClass().add("operations");
        addSub.setAlignment(Pos.CENTER);
        return addSub;
    }

    public void buttonClicked(Button btn, final FlowPane flowPane) {

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                //increment the number of matrices
                numberOfMatrices++;
                int length = size;
                int width = size;

                GridPane roots = new GridPane();
                ArrayList<TextField> list = new ArrayList<>();

                for (int y = 0; y < length; y++) {
                    for (int x = 0; x < width; x++) {

                        // Create a new TextField in each Iteration
                        TextField tf = new TextField();
                        tf.setPrefHeight(50);
                        tf.setPrefWidth(50);
                        tf.setAlignment(Pos.CENTER);
                        tf.setEditable(true);

                        //adding the textfields to a list
                        list.add(tf);

                        roots.add(tf, x, y);

                    }
                }
                //here we are adding the list to an array
                array[--numberOfMatrices] = list;
                //incrementing the matrix to its normal value
                numberOfMatrices++;
                flowPane.getChildren().add(roots);
            }
        });

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
