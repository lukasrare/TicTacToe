package dk.easv.tictactoe.bll;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MiniMaxAiGameBoard implements IGameBoard {
    private int nextPlayer = 0;
    private int lastPlayer = 0;
    private boolean gameWon = false;
    private int aiPlacement = 0;
    private List<Integer> board = new ArrayList<>(Collections.nCopies(9, 0));
    private List<Integer> winningLine = new ArrayList<>(Collections.nCopies(3, 0));
    private int AI = 2;
    private int HUMAN = 1;


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
                int move = bestMove();
                setBoardIndex(move);
                aiPlacement = move;
            }
            return true;
        }
        return false;
    }

    private int bestMove() {
        int bestScore = Integer.MIN_VALUE;
        int move = -1;

        for (int i = 0; i < 9; i++) {
            if (board.get(i) == 0) {
                board.set(i, AI); // sets a move for the AI
                int score = minimax(HUMAN); // parent method
                board.set(i, 0); // sets the move back to normal
                if (score > bestScore) {
                    bestScore = score;
                    move = i;
                } else if(score == bestScore) {
                    if (isBetterSquare(i, move)) {
                        move = i;
                    }
                }
            }
        }
        return move;
    }

    // when two moves have the same score this can be used as a tie-breaker.
    private boolean isBetterSquare(int selectedMove, int currentMove) {
        int[] priority = {4,0,2,6,8,1,3,5,7};
        // center is highest priority, then corners, then edges.
        int selectedMoveRank = Integer.MAX_VALUE;
        int currentMoveRank = Integer.MAX_VALUE;
        for (int j = 0; j < priority.length; j++) {
            if(priority[j] == selectedMove) selectedMoveRank = j;
            if(priority[j] == currentMove) currentMoveRank = j;
        }
        return selectedMoveRank < currentMoveRank;
    }

    private int minimax(int currentPlayer) {
        Integer result = checkWinner();
        if (result != null) return result;
        int bestScore = (currentPlayer == AI)
                ? Integer.MIN_VALUE // Ai wants the highest score.
                : Integer.MAX_VALUE; // Human wants the lowest score.
        /*
          The loop creates up to 9 child minimax methods, with each of those children creating more children,
          alternating between AI and human moves until they reach a win, loss or draw. Which bubbles back up to the
           first parent. There can be multiple win, loss or draws, but they get sorted from the loop.
         */
        for (int i = 0; i < 9; i++) { // updates the bestScore from the score the child method gives,
            // the child method also does this with other child methods they have created.
            if (board.get(i) == 0) {
                board.set(i, currentPlayer);
                int score = minimax((currentPlayer == AI) ? HUMAN : AI); // child method
                board.set(i, 0);
                if (currentPlayer == AI)
                    bestScore = Math.max(bestScore, score); // when it's AI's move we want the best move for the AI
                else
                    bestScore = Math.min(bestScore, score); // when it's Humans move we want the best move for the human.
            }
        }

        return bestScore;
    }

    private Integer checkWinner() {
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
                return (a == AI) ? 10 : -10;
            }
        }
        // Check draw
        for (int i = 0; i < 9; i++)
            if (board.get(i) == 0) return null;
        winningLine.set(0, -1); winningLine.set(1, -1); winningLine.set(2, -1);
        return 0; // draw
    }


    private void setBoardIndex(int index) {
        board.set(index, nextPlayer == 0 ? HUMAN : AI);
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
        if(checkWinner() != null) { if(checkWinner() == 0) {
                lastPlayer = -1;
                return true;
            }
        }
        return checkWinner() != null;
    }

    @Override
    public ArrayList<Integer> getWinningLine() {
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
