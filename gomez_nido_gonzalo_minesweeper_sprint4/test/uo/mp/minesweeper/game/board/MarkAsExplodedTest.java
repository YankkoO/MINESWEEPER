package uo.mp.minesweeper.game.board;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * Test if markAsExploded works as expected
 * 1 - markAsExplodedTest: Calling markAsExploded changes the board 
 * status to exploded.
 */
public class MarkAsExplodedTest {

	/**
	 * GIVEN: A valid Board
	 * WHEN: markAsExploded() is called
	 * THEN: hasExploded() returns true
	 */
	@Test
	public void markAsExplodedTest() {
		Board board = new Board(5, 5, 10);
		
		board.markAsExploded();
		
		assertTrue(board.hasExploded());
	}

}