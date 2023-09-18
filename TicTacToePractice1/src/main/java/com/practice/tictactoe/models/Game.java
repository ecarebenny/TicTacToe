package com.practice.tictactoe.models;

import com.practice.tictactoe.strategies.GameWinningStrategy;
import com.practice.tictactoe.strategies.OrderOneWinningStrategy;
import exceptions.InvalidDimensionException;
import exceptions.InvalidMoveException;
import exceptions.InvalidNumberOfPlayers;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;

    private List<Move> moves;
    private GameStatus gameStatus;
    private int nextPlayerIndex;
    private Player Winner;
    private GameWinningStrategy gameWinningStrategy;

    public static GameBuilder getBuilder() {
        return new GameBuilder();
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public Player getWinner() {
        return Winner;
    }

    public void setWinner(Player winner) {
        Winner = winner;
    }

    public GameWinningStrategy getGameWinningStrategy() {
        return gameWinningStrategy;
    }

    public void setGameWinningStrategy(GameWinningStrategy gameWinningStrategy) {
        this.gameWinningStrategy = gameWinningStrategy;
    }

    public void displayBoard() {
        this.getBoard().displayBoard();
    }

    public void executeNextMove() throws  InvalidMoveException {
        //get next player to move
        Player currentMovePlayer = players.get(nextPlayerIndex);

        //player should decide the move
        Move move = currentMovePlayer.decideMove(this.getBoard());

        //validate the move
        int row = move.getCell().getRow();
        int col= move.getCell().getCol();
        boolean isValidMove = this.getBoard().isValidMove(row,col);


        //if valid make the move
        if(isValidMove){
            board.getBoard().get(row).get(col).setCellState(CellState.FILLED);
            board.getBoard().get(row).get(col).setPlayer(currentMovePlayer);
        }
        else
        {
            throw new InvalidMoveException("Invalid Move");
        }

        //add this move in the final list of moves
        moves.add(move);

        //check if player has won the game
        if(gameWinningStrategy.checkWinner(board,currentMovePlayer,move.getCell()))
        {
            gameStatus = GameStatus.ENDED;
            Winner = currentMovePlayer;
        }

        //move to the next player
        nextPlayerIndex +=1;
        nextPlayerIndex %= players.size();

    }

    public static class GameBuilder {


        public GameBuilder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }


        public GameBuilder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        private int dimension;
        private List<Player> players;

        private boolean isValid() throws InvalidDimensionException, InvalidNumberOfPlayers {
            if(this.dimension < 3)
            {
                throw new InvalidDimensionException("dimension less than 3");
            }
            if (this.players.size() != dimension - 1) {
                throw new InvalidNumberOfPlayers("Number of players should be 1 less than the game dimension");
            }
            //Check if each player has different symbol or not.

            return true;
        }

        public Game Build() throws InvalidNumberOfPlayers, InvalidDimensionException {
            isValid();
            Game game = new Game();
            game.setBoard(new Board(dimension));
            game.setGameStatus(GameStatus.IN_PROGRESS);
            game.setPlayers(players);
            game.setMoves(new ArrayList<>());
            game.setNextPlayerIndex(0);
            game.setGameWinningStrategy(new OrderOneWinningStrategy(dimension));

            return game;

        }
    }

}
