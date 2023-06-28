package starterCode.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import starterCode.model.Cell;
import starterCode.model.SudokuBoard;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class SudokuControllerImpl implements SudokuController{

    Stage primaryStage;

    @FXML
    GridPane SUDOKU;

    @FXML
    Button solveButton;

    @FXML
    Button setButton;

    Map<TextField, Cell> gridToBoard;

    Cell[][] board;

    SudokuBoard sudokuBoard;

    public SudokuControllerImpl(Stage stage) {
        this.primaryStage = stage;
        this.gridToBoard = new LinkedHashMap<>();
        this.board = new Cell[9][9];
        this.sudokuBoard = new SudokuBoard();
    }
    @Override
    public void run() {
        this.innitBoard();
        this.sudokuBoard.setBoard(board);
        this.sudokuBoard.innitBoard();
    }

    public void innitBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                // Create the child GridPane
                GridPane childGridPane = new GridPane();

                // Add the child GridPane to the parent GridPane
                SUDOKU.add(childGridPane, i, j);

                this.addTextBoxes(childGridPane, i, j);
                childGridPane.setGridLinesVisible(true);
            }
        }
        setButton.setOnAction(e -> {this.getAllValues();});
        solveButton.setOnAction(e -> {sudokuBoard.solve();});
    }

    private void addTextBoxes(GridPane temp, int x, int y) {
        ArrayList<Cell> arrayList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                TextField textField = new TextField();
                textField.setPrefHeight(53);

                Cell cell = new Cell();
                arrayList.add(cell);
                this.board[(3 * y) + j][(x * 3) + i] = cell;

                temp.add(textField, i, j);
                this.gridToBoard.put(textField, cell);
            }
        }
        this.sudokuBoard.logSquares(arrayList);
    }

    public void getAllValues() {
        for (Map.Entry<TextField, Cell> entry : gridToBoard.entrySet()) {
            TextField key = entry.getKey();
            Cell value = entry.getValue();
            try {
                int i = Integer.parseInt(key.getText());
                if (i > 9 || i < 1) {
                    key.setStyle("-fx-background-color: #EADDCA;");
                } else {
                    key.setStyle("-fx-background-color: #ffffff;");
                    value.setCell(Integer.parseInt(key.getText()));
                }
            } catch (NumberFormatException e) {
                key.setText("");
                key.setStyle("-fx-background-color: #ffffff;");
            }
        }
    }
}
