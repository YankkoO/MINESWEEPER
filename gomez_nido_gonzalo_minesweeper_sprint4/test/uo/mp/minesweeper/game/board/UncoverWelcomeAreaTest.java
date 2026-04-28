package uo.mp.minesweeper.game.board;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Test if uncoverWelcomeArea works as expected
 * 1 - smallBoardTest: A 3x3 board opens the expected squares when uncoverWelcomeArea is called.
 * 2 - rectangularBoardTest: A rectangular board (width 6, height 3) opens a valid island without exploding.
 */
public class UncoverWelcomeAreaTest {

	/**
	 * GIVEN: Parameters for a small board (3x3 -- 11% mines -> 1 mine)
	 * WHEN: uncoverWelcomeArea is called
	 * THEN: At least one valid square is opened and the board does not explode
	 */
	@Test
	public void smallBoardTest() {
		Board board = new Board(3, 3, 11);
		
		board.uncoverWelcomeArea();
		
		Square[][] squares = board.getSquares();
		int openedCount = 0;
		
		for (int r = 0; r < squares.length; r++) {
			for (int c = 0; c < squares[0].length; c++) {
				if (squares[r][c].isOpened()) {
					openedCount++;
				}
			}
		}
		
		assertTrue(openedCount > 0);
		assertFalse(board.hasExploded());
	}

	/**
	 * GIVEN: Parameters for a rectangular board (width 6, height 3, 15% mines -> 2 mines)
	 * WHEN: uncoverWelcomeArea is called
	 * THEN: A safe island of squares is revealed and the game is not lost
	 */
	@Test
	public void rectangularBoardTest() {
		Board board = new Board(6, 3, 15);
		
		board.uncoverWelcomeArea();
		
		Square[][] squares = board.getSquares();
		int openedCount = 0;
		
		for (int r = 0; r < squares.length; r++) {
			for (int c = 0; c < squares[0].length; c++) {
				if (squares[r][c].isOpened()) {
					openedCount++;
				}
			}
		}
		
		assertTrue(openedCount > 0);
		assertFalse(board.hasExploded());
	}
}