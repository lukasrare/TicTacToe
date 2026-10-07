package dk.easv.tictactoe.bll;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author EASV
 */
public class GameBoard implements IGameBoard {
    private int nextPlayer = 0;
    private int lastPlayer = 0;
    private boolean gameWon = false;
    private List<Integer> board = new ArrayList<>(Collections.nCopies(9, 0));
    private List<Integer> winningLine = new ArrayList<>(Collections.nCopies(3, 0));

    /**
     * Returns 0 for player 0, 1 for player 1.
     *
     * @return int Id of the next player.
     */
    @Override
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
    @Override
    public boolean play(int col, int row) {
        int value = board.get(row * 3 + col);
        if(value == 0 && !gameWon) {
            setBoardIndex(row*3+col);
            return true;
        }
        return false;
    }

    private void setBoardIndex(int index) {
        board.set(index, nextPlayer + 1);
        lastPlayer = nextPlayer;
        nextPlayer = (nextPlayer == 0) ? 1 : 0;
        System.out.println(board);
    }

    @Override
    public int getAiPlacement() {
        return -1;
    }

    /**
     * Tells us if the game has ended either by draw or by meeting the winning
     * condition.
     *
     * @return true if the game is over, else it will return false.
     */
    @Override
    public boolean isGameOver() {
        return checkWinner();
    }

    private boolean checkWinner() {
        int[][] wins = {
                {0,1,2},{3,4,5},{6,7,8}, // rows
                {0,3,6},{1,4,7},{2,5,8}, // columns
                {0,4,8},{2,4,6} // diagonals
        };
        for (int[] w : wins) {
            int a = board.get(w[0]);
            int b = board.get(w[1]);
            int c = board.get(w[2]);
            if (a != 0 && a == b && b == c) {
                winningLine.set(0, w[0]); winningLine.set(1, w[1]); winningLine.set(2, w[2]);
                return true;
            }
        }
        // Check draw
        for (int i = 0; i < 9; i++)
            if (board.get(i) == 0) return false;
        winningLine.set(0, -1); winningLine.set(1, -1); winningLine.set(2, -1);
        lastPlayer = -1;
        return true;
    }

    @Override
    public ArrayList<Integer> getWinningLine () {
        return (ArrayList<Integer>) winningLine;
    }

    /**
     * Gets the id of the winner, -1 if its a draw.
     *
     * @return int id of winner, or -1 if draw.
     */
    @Override
    public int getWinner() {
        gameWon = true;
        return lastPlayer;
    }

    /**
     * Resets the game to a new game state.
     */
    @Override
    public void newGame() {
        // sets all the indexes in the board arraylist to 0
        for(int i=0; i<9; i++) {
            board.set(i, 0);
        }
        gameWon = false;
        nextPlayer = 0;
    }
}
