package uo.mp.minesweeper.game.board;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * Test if stepOn works as expected
 * 1 - stepOnDelegatesToSquareTest: Calling stepOn on the board at specific 
 * coordinates correctly delegates the call to the square at that position.
 */
public class stepOnDelegatesToSquare {

	/**
	 * GIVEN: A board of 5x5
	 * WHEN: stepOn(2, 2) is called on the board
	 * THEN: The square at position (2, 2) becomes opened
	 */
	@Test
	public void stepOnDelegatesToSquareTest() {
		Board board = new Board(5, 5, 0); // Board with no mines for simplicity
		
		board.stepOn(2, 2);
		
		Square[][] squares = board.getSquares();
		assertTrue(squares[2][2].isOpened());
	}

}