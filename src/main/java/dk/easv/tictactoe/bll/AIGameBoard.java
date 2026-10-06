package dk.easv.tictactoe.bll;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AIGameBoard implements IGameBoard{

    

    @Override
    public int getNextPlayer() {
        return 0;
    }

    @Override
    public boolean play(int col, int row) {
        return false;
    }

    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public ArrayList<Integer> getWinningLine() {
        return null;
    }

    @Override
    public int getWinner() {
        return 0;
    }

    @Override
    public void newGame() {

    }
}
