package com.practice.tictactoe.models;


import java.util.ArrayList;
import java.util.List;

public class Board {


    private List<List<Cell>> board;

    public Board(int dimension) {
        this.board = new ArrayList<>();

        for (int i = 0; i < dimension; i++) {
            this.board.add(new ArrayList<>());
            for (int j = 0; j < dimension; j++) {
                this.board.get(i).add(new Cell(i, j));
            }
        }

    }

    public List<List<Cell>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Cell>> board) {
        this.board = board;
    }

    public void displayBoard() {
        for (int i = 0; i < this.board.size(); i++) {
            for (int j = 0; j < this.board.size(); j++) {
                if (this.getBoard().get(i).get(j).getCellState().equals(CellState.EMPTY)) {
                    System.out.print("|  |");
                } else {
                    System.out.print("| " + this.getBoard().get(i).get(j).getPlayer().getSymbol() + " |");
                }
            }
            System.out.println();
        }
    }

    public boolean isValidMove(int row,int col) {
        boolean isValid = false;
        for (int i = 0; i < this.board.size(); i++) {
            if (i == row) {
                for (int j = 0; j < this.board.size(); j++) {
                    if (j == col) {
                        if (this.getBoard().get(i).get(j).getCellState().equals(CellState.EMPTY)) {
                            isValid = true;
                            return isValid;
                        }

                    }

                }
            }

        }
        return isValid;
    }
}
