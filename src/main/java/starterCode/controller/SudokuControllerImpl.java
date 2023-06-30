package starterCode.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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

    Map<Cell, TextField> gridToBoard;

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
        setButton.setOnAction(e -> {this.getAllValues();
            this.sudokuBoard.setBoard(board);});
        solveButton.setOnAction(e -> {
            boolean b = this.solve(0, 0);
            if (b) {
                for (Map.Entry<Cell, TextField> entry : gridToBoard.entrySet()) {
                    Cell key = entry.getKey();
                    TextField value = entry.getValue();
                    value.setText(String.valueOf(key.getCell()));
                }
            } else {
                this.unsolvableMSG();
            }
        });
    }

    private void unsolvableMSG() {
        VBox msg = new VBox();
        HBox okOrCancel = new HBox();
        Button okayButton = new Button("Okay");
        Dialog<Node> dialog = new Dialog<>();
        Label errormsg = new Label("Unsolvable Sudoku :(");
        okayButton.setOnAction(event -> {
            dialog.setResult(new Button("Okay"));
            dialog.close();
        });
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> {
            dialog.setResult(new Button("Cancel"));
            dialog.close();
        });
        okOrCancel.getChildren().addAll(okayButton, cancelButton);
        msg.getChildren().addAll(errormsg, okOrCancel);
        DialogPane dialogPane = new DialogPane();
        dialogPane.setContent(msg);
        dialog.setDialogPane(dialogPane);
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
                this.gridToBoard.put(cell, textField);
            }
        }
        this.sudokuBoard.logSquares(arrayList);
    }

    public void getAllValues() {
        for (Map.Entry<Cell, TextField> entry : gridToBoard.entrySet()) {
            Cell key = entry.getKey();
            TextField value = entry.getValue();
            try {
                int i = Integer.parseInt(value.getText());
                if (i > 9 || i < 1) {
                    value.setStyle("-fx-background-color: #EADDCA;");
                } else {
                    value.setStyle("-fx-background-color: #ffffff;");
                    key.setCell(Integer.parseInt(value.getText()));
                }
            } catch (NumberFormatException e) {
                if (value.getText().equals("")) {
                    value.setStyle("-fx-background-color: #ffffff;");
                } else {
                    value.setStyle("-fx-background-color: #EADDCA;");
                }
            }
        }
    }

    public boolean solve(int r, int c){
        if (r == 9) {
            return true;
        } else if (c == 9) {
            return this.solve(r+1, 0);
        } else if (this.board[r][c].getCell() != 0) {
            return this.solve(r, c+1);
        } else {
            for (int i = 1; i < 10; i++) {
                if (this.sudokuBoard.is_valid(r, c, i)) {
                    this.board[r][c].setCell(i);
             /*       try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        System.out.println("interrupted :(");
                    }*/
                    this.gridToBoard.get(this.board[r][c]).setText(String.valueOf(i));
                    if (this.solve(r, c+1)) {
                        return true;
                    }
                    this.board[r][c].setCell(0);
                }
            } return false;
        }
    }
}
