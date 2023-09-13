package com.practice.tictactoe.models;

import java.util.Scanner;

public class Player {


    private String name ;


    private char symbol;

    public Player (String name , char symbol)
    {
        this.name = name;
        this.symbol = symbol;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public Move decideMove(Board board)
    {
        System.out.println(this.getName() + " is deciding the move");
        //Get the input from the user, where to make the move.
        Scanner scanner = new Scanner(System.in);


        System.out.println(this.getName() + "'s turn, " + "Please give the row to make the move: ");
        int row = scanner.nextInt();

        System.out.println(this.getName() + "'s turn, " + "Please give the col to make the move: ");
        int col = scanner.nextInt();

        return new Move(this, new Cell(row, col));

    }
}
