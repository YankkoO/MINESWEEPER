package uo.mp.minesweeper.game.square;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import uo.mp.minesweeper.game.board.Square;

/**
 * Test if flag() works as expected
 * 1 - openedFlagTest: Call flag() over an opened square -> does nothing.
 * 2 - closedFlagTest: Call flag() over a closed square -> changes to flagged.
 * 3 - flaggedFlagTest: Call flag() over a flagged square -> does nothing.
 */
public class SquareFlagTest {

	/**
	 * GIVEN: An opened square
	 * WHEN: flag() is called
	 * THEN: Does nothing
	 */
	@Test
	public void openedFlagTest() {
		Square sq = new Square(0);
		sq.open();
		
		assertTrue(sq.isOpened());
		assertFalse(sq.isFlagged());
		
		sq.flag();
		
		assertTrue(sq.isOpened());
		assertFalse(sq.isFlagged());
	}
	
	/**
	 * GIVEN: A closed square
	 * WHEN: flag() is called
	 * THEN: square is flagged
	 */
	@Test
	public void closedFlagTest() {
		Square sq = new Square(0);
		
		assertFalse(sq.isOpened());
		assertFalse(sq.isFlagged()); // Implicitly CLOSED
		
		sq.flag();
		
		assertTrue(sq.isFlagged());
		assertFalse(sq.isOpened());
	}
	
	/**
	 * GIVEN: A flagged square
	 * WHEN: flag() is called
	 * THEN: Does nothing
	 */
	@Test
	public void flaggedFlagTest() {
		Square sq = new Square(0);
		sq.flag();
		
		assertTrue(sq.isFlagged());
		assertFalse(sq.isOpened());
		
		sq.flag(); // Trying to flag an already flagged square
		
		assertTrue(sq.isFlagged());
		assertFalse(sq.isOpened());
	}
	
}