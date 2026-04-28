package uo.mp.minesweeper.game.square;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import uo.mp.minesweeper.game.board.Square;

/**
 * Test if unflag() works as expected
 * 1 - openedUnflagTest: Call unflag() over an opened square -> does nothing.
 * 2 - closedUnflagTest: Call unflag() over a closed square -> does nothing.
 * 3 - flaggedUnflagTest: Call unflag() over a flagged square -> changes to closed.
 */
public class SquareUnflagTest {

	/**
	 * GIVEN: An opened square
	 * WHEN: unflag() is called
	 * THEN: Does nothing
	 */
	@Test
	public void openedUnflagTest() {
		Square sq = new Square(0);
		sq.open();
		
		assertTrue(sq.isOpened());
		assertFalse(sq.isFlagged());
		
		sq.unflag();
		
		assertTrue(sq.isOpened());
		assertFalse(sq.isFlagged());
	}
	
	/**
	 * GIVEN: A closed square
	 * WHEN: unflag() is called
	 * THEN: Does nothing
	 */
	@Test
	public void closedUnflagTest() {
		Square sq = new Square(0);
		
		assertFalse(sq.isOpened());
		assertFalse(sq.isFlagged());
		
		sq.unflag();
		
		assertFalse(sq.isOpened());
		assertFalse(sq.isFlagged());
	}
	
	/**
	 * GIVEN: A flagged square
	 * WHEN: unflag() is called
	 * THEN: square is closed
	 */
	@Test
	public void flaggedUnflagTest() {
		Square sq = new Square(0);
		sq.flag();
		
		assertTrue(sq.isFlagged());
		assertFalse(sq.isOpened());
		
		sq.unflag();
		
		assertFalse(sq.isFlagged());
		assertFalse(sq.isOpened());
	}
	
}