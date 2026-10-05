
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
     * attempt is succesfull the current player has ended his turn and it is the
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
        if(value == 0)
        {
            board.set(row*3+col, nextPlayer+1);
            nextPlayer = (nextPlayer == 0) ? 1 : 0;
            System.out.println(board);
            return true;
        }
        else {
            return false;
        }

        //TODO Implement this method
        // (condition) ? valueIfTrue : valueIfFalse
    }

    /**
     * Tells us if the game has ended either by draw or by meeting the winning
     * condition.
     *
     * @return true if the game is over, else it will return false.
     */
    public boolean isGameOver()
    {
        //TODO Implement this method
        return false;
    }

    /**
     * Gets the id of the winner, -1 if its a draw.
     *
     * @return int id of winner, or -1 if draw.
     */
    public int getWinner()
    {
        //TODO Implement this method
        return -1;
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
        // when a new game is started it sets the starting player to 0 because X always starts.
        nextPlayer = 0;
        //TODO Implement this method
    }
}
