
package dk.easv.tictactoe.bll;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author EASV
 */
public class GameBoard implements IGameBoard
{
    private int nextPlayer = 0;
    private int lastPlayer = 0;
    private boolean gameWon = false;
    private List<Integer> board = new ArrayList<>(Collections.nCopies(9, 0));
    /**
     * Returns 0 for player 0, 1 for player 1.
     *
     * @return int Id of the next player.
     */
    public int getNextPlayer()
    {
        return nextPlayer;
    }

    /**
     * Attempts to let the current player play at the given coordinates. It the
     * attempt is successful the current player has ended his turn and it is the
     * next players turn.
     *
     * @param col column to place a marker in.
     * @param row row to place a marker in.
     * @return true if the move is accepted, otherwise false. If gameOver == true
     * this method will always return false.
     */
    public boolean play(int col, int row)
    {
        int value = board.get(row * 3 + col);
        if(value == 0 && !gameWon)
        {
            board.set(row*3+col, nextPlayer+1);
            lastPlayer = nextPlayer;
            nextPlayer = (nextPlayer == 0) ? 1 : 0;
            System.out.println(board);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Tells us if the game has ended either by draw or by meeting the winning
     * condition.
     *
     * @return true if the game is over, else it will return false.
     */
    public boolean isGameOver() {
        int availableSpaces = 0;
        for (int i = 0; i < 3; i++) {
            int a = board.get(i * 3);
            int b = board.get(i * 3 + 1);
            int c = board.get(i * 3 + 2);

            int d = board.get(i);
            int e = board.get(i + 3);
            int f = board.get(i + 6);

            if (a != 0 && a == b && a == c) {
                return true;
            }
            if (d != 0 && d == e && d == f) {
                return true;
            }
        }
        int d00 = board.get(0);
        int d02 = board.get(2);
        int d11 = board.get(4);
        int d20 = board.get(6);
        int d22 = board.get(8);

        if (d00 != 0 && d00 == d11 && d00 == d22) {
            return true;
        } else if (d02 != 0 && d02 == d11 && d02 == d20) {
            return true;
        }

        for (int i = 0; i < 9; i++) {
            int value = board.get(i);
            if (value == 0) {
                availableSpaces++;
            }
        }
        if (availableSpaces == 0) {
            lastPlayer = -2;
            return true;
        }
        return false;
    }

    /**
     * Gets the id of the winner, -1 if its a draw.
     *
     * @return int id of winner, or -1 if draw.
     */
    public int getWinner()
    {
        gameWon = true;
        return lastPlayer+1;
    }

    /**
     * Resets the game to a new game state.
     */
    public void newGame()
    {
        for(int i=0; i<9; i++)
        {
            board.set(i, 0);
        }
        gameWon = false;
        // when a new game is started it sets the starting player to 0 because X always starts.
        nextPlayer = 0;
    }
}
