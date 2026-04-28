package uo.mp.minesweeper.game.board.actions;

import uo.mp.minesweeper.game.board.Board;
import uo.mp.util.check.ArgumentChecks;

public class BlowUpAction implements Action {

	private Board board;
	
	/**
	 * Public constructor for the class.
	 * Receives a board as a parameter and it blows up.
	 * @param board Board that will blow up.
	 */
	public BlowUpAction(Board board) {
		ArgumentChecks.isNotNull(board);
		this.board = board;
	}
	
	/**
	 * Mark that board has exploded.
	 */
	@Override
	public void execute() {
		board.markAsExploded();
	}

}
