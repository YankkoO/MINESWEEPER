package uo.mp.minesweeper.game.board;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test if stepOn() works as expected
 * 1 - alreadyDiscoveredStepOnTest: stepOn in a square already discovered -> has no effect.
 * 2 - flaggedWithMineStepOnTest: stepOn in a square with flag with mine -> has no effect.
 * 3 - flaggedWithoutMineStepOnTest: stepOn in a square with flag without mine -> has no effect.
 * 4 - minedStepOnTest: stepOn in a square with lead -> square gets opened.
 * 5 - numericalClueStepOnTest: stepOn in a square with numerical clue -> square gets opened.
 * 6 - emptySquareStepOnTest: stepOn in on an empty square -> square gets opened.
 */
public class BoardStepOnTest {

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
	 * GIVEN: A board with a square already discovered
	 * WHEN: stepOn(x, y) is called on that coordinate
	 * THEN: Has no effect (remains opened)
	 */
	@Test
	public void alreadyDiscoveredStepOnTest() {
		squares[0][0].open();
		Board board = new Board(0, squares);
		
		board.stepOn(0, 0);
		
		Square[][] result = board.getSquares();
		assertTrue(result[0][0].isOpened());
	}

	/**
	 * GIVEN: A board with a flagged mined square
	 * WHEN: stepOn(x, y) is called on that coordinate
	 * THEN: Has no effect 
	 */
	@Test
	public void flaggedWithMineStepOnTest() {
		squares[0][0] = new Square(-1);
		squares[0][0].flag();
		Board board = new Board(1, squares);
		
		board.stepOn(0, 0);
		
		Square[][] result = board.getSquares();
		assertTrue(result[0][0].isFlagged());
		assertFalse(result[0][0].isOpened());
	}

	/**
	 * GIVEN: A board with a flagged square without a mine
	 * WHEN: stepOn(x, y) is called on that coordinate
	 * THEN: Has no effect 
	 */
	@Test
	public void flaggedWithoutMineStepOnTest() {
		squares[0][0].flag();
		Board board = new Board(0, squares);
		
		board.stepOn(0, 0);
		
		Square[][] result = board.getSquares();
		assertTrue(result[0][0].isFlagged());
		assertFalse(result[0][0].isOpened());
	}

	/**
	 * GIVEN: A board with a closed mined square (lead)
	 * WHEN: stepOn(x, y) is called on that coordinate
	 * THEN: The square gets opened 
	 */
	@Test
	public void minedStepOnTest() {
		squares[0][0] = new Square(-1);
		Board board = new Board(1, squares);
		
		board.stepOn(0, 0);
		
		Square[][] result = board.getSquares();
		assertTrue(result[0][0].isOpened());
	}

	/**
	 * GIVEN: A board with a closed square containing a numerical clue
	 * WHEN: stepOn(x, y) is called on that coordinate
	 * THEN: The square gets opened
	 */
	@Test
	public void numericalClueStepOnTest() {
		squares[0][0] = new Square(3);
		Board board = new Board(0, squares);
		
		board.stepOn(0, 0);
		
		Square[][] result = board.getSquares();
		assertTrue(result[0][0].isOpened());
	}

	/**
	 * GIVEN: A board with a closed empty square
	 * WHEN: stepOn(x, y) is called on that coordinate
	 * THEN: The square gets opened
	 */
	@Test
	public void emptySquareStepOnTest() {
		Board board = new Board(0, squares);
		
		board.stepOn(0, 0);
		
		Square[][] result = board.getSquares();
		assertTrue(result[0][0].isOpened());
	}
}