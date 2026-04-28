package uo.mp.minesweeper.game.square;

import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

import uo.mp.minesweeper.game.board.Board;
import uo.mp.minesweeper.game.board.Square;
import uo.mp.minesweeper.game.board.actions.BlowUpAction;

/**
 * Test if stepOn works as expected
 * 1 - stepOnDoesNothingIfFlaggedTest: Calling stepOn on a flagged square 
 * does not open the square nor executes its action.
 */
public class StepOnDoesNothingIfFlaggedTest {

	/**
	 * GIVEN: A closed square with a flag and an associated BlowUpAction
	 * WHEN: stepOn() is called
	 * THEN: The square remains closed and the action is not executed (board not exploded)
	 */
	@Test
	public void stepOnDoesNothingIfFlaggedTest() {
		Board board = new Board(5, 5, 12);
		Square square = new Square(-1);
		BlowUpAction action = new BlowUpAction(board);
		square.setAction(action);
		
		square.flag();
		square.stepOn();
		
		assertFalse(square.isOpened());
		assertFalse(board.hasExploded());
	}

}