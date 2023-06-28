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

    public void solve(){
        //solve the puzzle!
    }
}
