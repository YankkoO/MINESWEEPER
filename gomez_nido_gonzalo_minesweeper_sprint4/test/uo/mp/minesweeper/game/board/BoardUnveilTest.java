package uo.mp.minesweeper.game.board;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test if unveil() works as expected
 * 1 - fullyCoveredUnveilTest: Call unveil() over a fully covered board -> all squares get opened.
 * 2 - flaggedUnveilTest: Call unveil() over a board with a flag -> all squares get opened.
 * 3 - partiallyDiscoveredUnveilTest: Call unveil() over a board with some opened squares -> all squares get opened.
 */
public class BoardUnveilTest {

	private Square[][] squares;

	/**
	 * Creates a 2x2 board for testing
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
	 * GIVEN: A fully covered board
	 * WHEN: unveil() is called
	 * THEN: All squares are opened
	 */
	@Test
	public void fullyCoveredUnveilTest() {
		Board board = new Board(0, squares);
		
		board.unveil();
		
		Square[][] result = board.getSquares();
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				assertTrue(result[i][j].isOpened());
			}
		}
	}

	/**
	 * GIVEN: A board with a flag
	 * WHEN: unveil() is called
	 * THEN: All squares are opened
	 */
	@Test
	public void flaggedUnveilTest() {
		squares[0][0].flag();
		Board board = new Board(0, squares);
		
		board.unveil();
		
		Square[][] result = board.getSquares();
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				assertTrue(result[i][j].isOpened());
				assertFalse(result[i][j].isFlagged());
			}
		}
	}

	/**
	 * GIVEN: A board with some squares already discovered
	 * WHEN: unveil() is called
	 * THEN: All squares are opened
	 */
	@Test
	public void partiallyDiscoveredUnveilTest() {
		squares[0][1].open();
		Board board = new Board(0, squares);
		
		board.unveil();
		
		Square[][] result = board.getSquares();
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				assertTrue(result[i][j].isOpened());
			}
		}
	}
}