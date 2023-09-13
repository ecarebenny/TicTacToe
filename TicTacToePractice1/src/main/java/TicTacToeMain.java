import com.practice.tictactoe.controllers.GameController;
import com.practice.tictactoe.models.Game;
import com.practice.tictactoe.models.GameStatus;
import com.practice.tictactoe.models.Player;
import exceptions.InvalidDimensionException;
import exceptions.InvalidNumberOfPlayers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameController gameController = new GameController();

        System.out.println("What should be the dimension of the game ?");
        int dimension = scanner.nextInt();

        int numberOfPlayers = dimension - 1;
        List<Player> players = new ArrayList<>();

        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.println("Enter the name of the player");
            String name = scanner.next();

            System.out.println("Enter the player symbol");
            String symboll = (scanner.next());
            char symbol = symboll.charAt(0);

            players.add(new Player(name, symbol));

        }


        Game game;
        try {
            game = gameController.createGame(dimension, players);
        } catch (InvalidNumberOfPlayers e) {
            throw new RuntimeException(e);
        } catch (InvalidDimensionException e) {
            throw new RuntimeException(e);
        }

        while (gameController.getGameStatus(game).equals(GameStatus.IN_PROGRESS)) {

            //display the board
            System.out.println("This is the current Board");
            gameController.displayBoard(game);
            gameController.executeNextMove(game);
        }
            if(gameController.getGameStatus(game).equals(GameStatus.ENDED))
            {
                game.displayBoard();
                System.out.println("Winner is "+ gameController.getWinner(game).getName());
            }
            else
            {
                if (gameController.getGameStatus(game).equals(GameStatus.DRAW)) {
                    System.out.println("Game Draw");
                }
            }

        }
    }

