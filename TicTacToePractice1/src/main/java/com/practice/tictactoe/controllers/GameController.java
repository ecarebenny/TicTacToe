package com.practice.tictactoe.controllers;

import com.practice.tictactoe.models.Game;
import com.practice.tictactoe.models.GameStatus;
import com.practice.tictactoe.models.Player;
import exceptions.InvalidDimensionException;
import exceptions.InvalidNumberOfPlayers;

import java.util.List;

public class GameController {

    public Game createGame(int dimension , List<Player> players) throws InvalidNumberOfPlayers, InvalidDimensionException {
        Game game = Game.getBuilder().setDimension(dimension)
                .setPlayers(players)
                .Build();
        return game;
    }

    public GameStatus getGameStatus(Game game) {
        return game.getGameStatus();
    }

    public void displayBoard(Game game)
    {
        game.displayBoard();
    }

    public Player getWinner(Game game) {
        return game.getWinner();
    }
    public void executeNextMove(Game game)
    {
        game.executeNextMove();
    }
}
