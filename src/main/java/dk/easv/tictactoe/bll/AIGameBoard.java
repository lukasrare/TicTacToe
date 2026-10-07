package dk.easv.tictactoe.bll;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AIGameBoard implements IGameBoard{
    private int nextPlayer = 0;
    private int lastPlayer = 0;
    private boolean gameWon = false;
    private int aiPlacement = 0;
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
        if (value == 0 && !gameWon) {
            setBoardIndex(row*3+col);
            boolean canContinue = board.contains(0);
            if(canContinue && !isGameOver()) {
                boolean tookTurn = false;
                while (!tookTurn) {
                    int randomNum = (int) (Math.random() * 9);
                    int value2 = board.get(randomNum);
                    if (value2 == 0 && !gameWon) {
                        setBoardIndex(randomNum);
                        aiPlacement = randomNum;
                        tookTurn = true;
                    }
                }
            }
            return true;
        }
        return false;
    }

    private void setBoardIndex(int index)
    {
        board.set(index, nextPlayer + 1);
        lastPlayer = nextPlayer;
        nextPlayer = (nextPlayer == 0) ? 1 : 0;
        System.out.println(board);
    }

    @Override
    public int getAiPlacement() {
        return aiPlacement;
    }

    /**
     * Tells us if the game has ended either by draw or by meeting the winning
     * condition.
     *
     * @return true if the game is over, else it will return false.
     */
    @Override
    public boolean isGameOver() {
        for (int i = 0; i < 3; i++)
        {
            if(checkRow(i)) return true;
            if(checkColumn(i)) return true;
        }
        if(checkDiagonals()) return true;
        return isDraw();
    }

    private boolean checkRow(int r)
    {
        int a = board.get(r * 3);
        int b = board.get(r * 3 + 1);
        int c = board.get(r * 3 + 2);
        // Check rows
        if (a != 0 && a == b && a == c) {
            winningLine.set(0, r * 3);
            winningLine.set(1, r * 3 + 1);
            winningLine.set(2, r * 3 + 2);
            return true;
        }
        return false;
    }

    private boolean checkColumn(int c)
    {
        int d = board.get(c);
        int e = board.get(c + 3);
        int f = board.get(c + 6);
        // Check columns
        if (d != 0 && d == e && d == f) {
            winningLine.set(0, c);
            winningLine.set(1, c + 3);
            winningLine.set(2, c + 6);
            return true;
        }
        return false;
    }

    private boolean checkDiagonals()
    {
        int d00 = board.get(0);
        int d02 = board.get(2);
        int d11 = board.get(4);
        int d20 = board.get(6);
        int d22 = board.get(8);
        // Check diagonals
        if (d00 != 0 && d00 == d11 && d00 == d22) {
            winningLine.set(0, 0); winningLine.set(1, 4); winningLine.set(2, 8);
            return true;
        } else if (d02 != 0 && d02 == d11 && d02 == d20) {
            winningLine.set(0, 2); winningLine.set(1, 4); winningLine.set(2, 6);
            return true;
        }
        return false;
    }

    private boolean isDraw()
    {
        for (int i = 0; i < 9; i++) {
            int value = board.get(i);
            if (value == 0) {
                return false; // Still playing if there is an empty space
            }
        }
        lastPlayer = -1; // It's a draw
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
        for(int i=0; i<9; i++)
        {
            board.set(i, 0);
        }
        gameWon = false;
        nextPlayer = 0;
    }
}
