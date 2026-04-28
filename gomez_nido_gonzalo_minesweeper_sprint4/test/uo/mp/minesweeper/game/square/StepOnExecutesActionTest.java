package uo.mp.minesweeper.game.square;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import uo.mp.minesweeper.game.board.Board;
import uo.mp.minesweeper.game.board.Square;
import uo.mp.minesweeper.game.board.actions.BlowUpAction;

/**
 * Test if stepOn works as expected
 * 1 - stepOnExecutesActionTest: Calling stepOn on a closed square executes 
 * its associated action.
 */
public class StepOnExecutesActionTest {

	/**
	 * GIVEN: A closed square with an associated BlowUpAction
	 * WHEN: stepOn() is called
	 * THEN: The action is executed and the board is marked as exploded
	 */
	@Test
	public void stepOnExecutesActionTest() {
		Board board = new Board(5, 5, 12);
		Square square = new Square(-1);
		BlowUpAction action = new BlowUpAction(board);
		square.setAction(action);
		
		square.stepOn();
		
		assertTrue(board.hasExploded());
	}

}