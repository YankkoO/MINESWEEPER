package uo.mp.minesweeper.game.board;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test if flag() works as expected
 * 1 - alreadyFlaggedTest: Flag on an already marked square -> has no effect.
 * 2 - closedSquareFlagTest: Flag on a closed square -> gets flagged.
 * 3 - twiceConsecutivelyFlagTest: Flag on a square twice consecutively -> remains flagged.
 */
public class BoardFlagTest {

	private Square[][] squares;

	/**
	 * Creates a 2x2 empty board for testing
	 */
	@BeforeEach
	public void setUp() {
		squares = new Square[2][2];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				squares[i][j] = new Square(0);
			}
		}
	}

	/**
	 * GIVEN: A board with an already marked (flagged) square
	 * WHEN: flag(x, y) is called on that coordinate
	 * THEN: Has no effect (remains flagged)
	 */
	@Test
	public void alreadyFlaggedTest() {
		squares[0][0].flag(); 
		Board board = new Board(0, squares);
		
		board.flag(0, 0);
		
		Square[][] result = board.getSquares();
		assertTrue(result[0][0].isFlagged());
		assertFalse(result[0][0].isOpened());
	}

	/**
	 * GIVEN: A board with a closed square
	 * WHEN: flag(x, y) is called on that coordinate
	 * THEN: The square gets flagged
	 */
	@Test
	public void closedSquareFlagTest() {
		Board board = new Board(0, squares);
		
		board.flag(0, 0);
		
		Square[][] result = board.getSquares();
		assertTrue(result[0][0].isFlagged());
		assertFalse(result[0][0].isOpened());
	}

	/**
	 * GIVEN: A board with a closed square
	 * WHEN: flag(x, y) is called twice consecutively on that coordinate
	 * THEN: The square gets flagged and remains flagged
	 */
	@Test
	public void twiceConsecutivelyFlagTest() {
		Board board = new Board(0, squares);
		
		board.flag(0, 0);
		board.flag(0, 0);
		
		Square[][] result = board.getSquares();
		assertTrue(result[0][0].isFlagged());
		assertFalse(result[0][0].isOpened());
	}

}