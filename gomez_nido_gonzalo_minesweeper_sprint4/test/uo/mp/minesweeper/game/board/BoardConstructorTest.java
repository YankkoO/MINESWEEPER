package uo.mp.minesweeper.game.board;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Test if Constructor works as expected
 * 1 - squareBoardTest: A square board has the dimension of the matrix 
 * and the number of mines that correspond to what is specified in 
 * the parameters.
 * 2 - rectangularBoardTest: A rectangular (not square) board has the 
 * right dimensions and the number of mines that correspond to it.
 */
public class BoardConstructorTest {

	/**
	 * GIVEN: Parameters for a square board (9x9 -- 12% mines -> 10 mines)
	 * WHEN: Board constructor is called
	 * THEN: Dimensions are correct and mines are calculated rounding up
	 */
	@Test
	public void squareBoardTest() {
		Board board = new Board(9, 9, 12);
		
		Square[][] squares = board.getSquares();
		
		assertEquals(9, squares.length); // rows (height)
		assertEquals(9, squares[0].length); // cols (width)
		assertEquals(10, board.getNumberOfMinesLeft());
	}

	/**
	 * GIVEN: Parameters for a rectangular board (width 10, height 5, 
	 * 15% mines -> 8 mines)
	 * WHEN: Board constructor is called
	 * THEN: Dimensions are correct and mines are calculated rounding up
	 */
	@Test
	public void rectangularBoardTest() {
		Board board = new Board(10, 5, 15);
		
		Square[][] squares = board.getSquares();
		
		assertEquals(5, squares.length); // rows (height)
		assertEquals(10, squares[0].length); // cols (width)
		assertEquals(8, board.getNumberOfMinesLeft());
	}
}