package com.practice.tictactoe.strategies;

import com.practice.tictactoe.models.Board;
import com.practice.tictactoe.models.Cell;
import com.practice.tictactoe.models.Player;

public interface GameWinningStrategy {
    boolean checkWinner(Board board,
                        Player player,
                        Cell moveCell);
}
