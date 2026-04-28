package uo.mp.minesweeper.game.square;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import uo.mp.minesweeper.game.board.Square;

/**
 * Test if stepOn works as expected
 * 1 - stepOnOpensClosedSquareTest: Calling stepOn on a closed square 
 * changes its state to opened.
 */
public class StepOnOpensClosedSquareTest {

	/**
	 * GIVEN: A newly created Square (which is CLOSED by default)
	 * WHEN: stepOn() is called
	 * THEN: The state of the square changes to opened
	 */
	@Test
	public void stepOnOpensClosedSquareTest() {
		Square square = new Square(0);
		
		square.stepOn();
		
		assertTrue(square.isOpened());
	}

}