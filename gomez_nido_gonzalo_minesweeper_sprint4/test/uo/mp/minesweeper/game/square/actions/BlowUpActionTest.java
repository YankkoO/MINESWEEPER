package uo.mp.minesweeper.game.square.actions;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import uo.mp.minesweeper.game.board.Board;
import uo.mp.minesweeper.game.board.actions.BlowUpAction;

/**
 * Test if BlowUpAction works as expected
 * 1 - executeMarksBoardAsExplodedTest: The execute method marks the associated 
 * board as exploded.
 */
public class BlowUpActionTest {

	/**
	 * GIVEN: A valid Board and a BlowUpAction associated with it
	 * WHEN: execute() is called on the action
	 * THEN: The board is marked as exploded
	 */
	@Test
	public void executeMarksBoardAsExplodedTest() {
		Board board = new Board(5, 5, 12);
		BlowUpAction action = new BlowUpAction(board);
		
		action.execute();
		
		assertTrue(board.hasExploded());
	}

}