package uo.mp.minesweeper.game.board.actions;

import java.util.List;

import uo.mp.minesweeper.game.board.Square;
import uo.mp.util.check.ArgumentChecks;

public class ClearAction implements Action {

    private List<Square> neighbors;

    public ClearAction(List<Square> neighbors) {
        ArgumentChecks.isNotNull(neighbors, "The list of neighbors cannot be null");
        this.neighbors = neighbors;
    }

    @Override
    public void execute() {
        for (Square neighbor : neighbors) {
            neighbor.stepOn();
        }
    }
}