package uo.mp.minesweeper.game.board;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test if unflag() works as expected
 * 1 - minedFlaggedUnflagTest: Call unflag() over a mined square already flagged -> removes the flag.
 * 2 - minedNotFlaggedUnflagTest: Call unflag() over a mined square not flagged -> has no effect.
 * 3 - openedUnflagTest: Call unflag() over an already open square -> has no effect.
 * 4 - closedUnflagTest: Call unflag() over a closed square -> has no effect.
 */
public class BoardUnflagTest {

	private Square[][] squares;

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
	 * GIVEN: A board with a mined square that is flagged
	 * WHEN: unflag(x, y) is called on that coordinate
	 * THEN: The flag is removed 
	 */
	@Test
	public void minedFlaggedUnflagTest() {
		squares[0][0] = new Square(-1);
		squares[0][0].flag();
		Board board = new Board(1, squares);
		
		board.unflag(0, 0);
		
		Square[][] result = board.getSquares();
		assertFalse(result[0][0].isFlagged());
		assertFalse(result[0][0].isOpened());
	}

	/**
	 * GIVEN: A board with a mined square that is NOT flagged (closed)
	 * WHEN: unflag(x, y) is called on that coordinate
	 * THEN: Has no effect (remains closed)
	 */
	@Test
	public void minedNotFlaggedUnflagTest() {
		squares[0][0] = new Square(-1);
		Board board = new Board(1, squares);
		
		board.unflag(0, 0);
		
		Square[][] result = board.getSquares();
		assertFalse(result[0][0].isFlagged());
		assertFalse(result[0][0].isOpened());
	}

	/**
	 * GIVEN: A board with an already opened square
	 * WHEN: unflag(x, y) is called on that coordinate
	 * THEN: Has no effect (remains opened)
	 */
	@Test
	public void openedUnflagTest() {
		squares[0][0].open();
		Board board = new Board(0, squares);
		
		board.unflag(0, 0);
		
		Square[][] result = board.getSquares();
		assertTrue(result[0][0].isOpened());
		assertFalse(result[0][0].isFlagged());
	}

	/**
	 * GIVEN: A board with a closed, unmined square
	 * WHEN: unflag(x, y) is called on that coordinate
	 * THEN: Has no effect (remains closed)
	 */
	@Test
	public void closedUnflagTest() {
		Board board = new Board(0, squares);
		
		board.unflag(0, 0);
		
		Square[][] result = board.getSquares();
		assertFalse(result[0][0].isOpened());
		assertFalse(result[0][0].isFlagged());
	}
}