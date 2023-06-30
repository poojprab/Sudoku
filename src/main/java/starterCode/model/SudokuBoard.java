package starterCode.model;

import java.util.ArrayList;

public class SudokuBoard {

    Cell[][]  board;

    Cell[][] columns;
    Cell[][] rows;

    ArrayList<ArrayList<Cell>> squares;
    public SudokuBoard() {
        this.columns = new Cell[9][9];
        this.rows = new Cell[9][9];
        this.squares = new ArrayList<>();
    }

    public void setBoard(Cell[][] board) {
        this.board = board;
        this.innitBoard();
    }

    public void innitBoard() {
        for (int i = 0; i < 9; i ++) {
            this.rows[i] = this.board[i];
            for (int j = 0; j < 9; j++) {
                this.columns[i][j] = this.board[j][i];
            }
        }
    }

    public void logSquares(ArrayList<Cell> arrayList) {
        this.squares.add(arrayList);
    }

    public boolean is_valid(int i, int j, int number) {
        for (int x = 0; x < this.rows[i].length; x++) {
            if (this.rows[i][x].getCell() == number) {
                return false;
            }
        }
        for (int x = 0; x < this.columns[j].length; x++) {
            if (this.columns[j][x].getCell() == number) {
                return false;
            }
        }
        for (ArrayList<Cell> square : this.squares) {
            if (square.contains(this.board[i][j])) {
                for (Cell cell : square) {
                    if (cell.getCell() == number) {
                        return false;
                    }
                }
                break;
            }
        }
        return true;
    }
}
