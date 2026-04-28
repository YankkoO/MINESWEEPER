package uo.mp.minesweeper.game.square;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import uo.mp.minesweeper.game.board.Square;

/**
 * Test if stepOn() works as expected
 * 1 - closedStepOnTest: Call stepOn() over a closed square -> it gets open.
 * 2 - openStepOnTest: Call stepOn() over an open square -> does nothing.
 * 3 - flaggedStepOnTest: Call stepOn() over a flagged square -> does nothing.
 */
public class SquareStepOnTest {

	/**
	 * GIVEN: A closed square
	 * WHEN: stepOn() is called
	 * THEN: square is opened
	 */
	@Test
	public void closedStepOnTest() {
		Square sq = new Square(0);
		
		assertFalse(sq.isOpened());
		assertFalse(sq.isFlagged());
		
		sq.stepOn();
		
		assertFalse(sq.isFlagged());
		assertTrue(sq.isOpened());
	}
	
	/**
	 * GIVEN: An opened square
	 * WHEN: stepOn() is called
	 * THEN: Does nothing
	 */
	@Test
	public void openedStepOnTest() {
		Square sq = new Square(0);
		
		assertFalse(sq.isOpened());
		assertFalse(sq.isFlagged());
		
		sq.open();
		
		assertTrue(sq.isOpened());
		
		sq.stepOn();
		
		assertTrue(sq.isOpened());
	}
	
	/**
	 * GIVEN: A flagged square
	 * WHEN: stepOn() is called
	 * THEN: Does nothing
	 */
	@Test
	public void flaggedStepOnSquare() {
		Square sq = new Square(0);
		
		sq.flag();
		
		assertTrue(sq.isFlagged());
		
		sq.stepOn();
		
		assertTrue(sq.isFlagged());
	}
	
}
