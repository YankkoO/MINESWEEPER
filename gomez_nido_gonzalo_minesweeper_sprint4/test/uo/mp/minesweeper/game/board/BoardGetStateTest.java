package uo.mp.minesweeper.game.board;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test if getState() works as expected
 * 1 - newlyCreatedBoardTest: getState on a newly created board -> returns all '#' characters.
 * 2 - fullyUncoveredBoardTest: getState on a fully uncovered board -> returns respective characters.
 * 3 - intermediateSituationTest: getState on an intermediate board -> returns mixed characters.
 */
public class BoardGetStateTest {

	private Square[][] squares;

	@BeforeEach
	public void setUp() {
		squares = new Square[2][2];
		squares[0][0] = new Square(-1); 
		squares[0][1] = new Square(0);  
		squares[1][0] = new Square(3);  
		squares[1][1] = new Square(1);  
	}

	/**
	 * GIVEN: A newly created board (all squares closed)
	 * WHEN: getState() is called
	 * THEN: Returns a char matrix with '#' in all positions
	 */
	@Test
	public void newlyCreatedBoardTest() {
		Board board = new Board(1, squares);

		char[][] state = board.getState();

		assertEquals('#', state[0][0]);
		assertEquals('#', state[0][1]);
		assertEquals('#', state[1][0]);
		assertEquals('#', state[1][1]);
	}

	/**
	 * GIVEN: A fully uncovered board
	 * WHEN: getState() is called
	 * THEN: Returns a char matrix with the actual contents of the squares
	 */
	@Test
	public void fullyUncoveredBoardTest() {
		Board board = new Board(1, squares);
		board.unveil(); 

		char[][] state = board.getState();

		assertEquals('@', state[0][0]); 
		assertEquals(' ', state[0][1]); 
		assertEquals('3', state[1][0]); 
		assertEquals('1', state[1][1]); 
	}

	/**
	 * GIVEN: A board in an intermediate game situation 
	 * WHEN: getState() is called
	 * THEN: Returns a char matrix with the corresponding characters
	 */
	@Test
	public void intermediateSituationTest() {
		squares[0][0].flag(); 
		squares[0][1].open(); 
		squares[1][0].open(); 
		
		Board board = new Board(1, squares);

		char[][] state = board.getState();

		assertEquals('¶', state[0][0]);
		assertEquals(' ', state[0][1]);
		assertEquals('3', state[1][0]);
		assertEquals('#', state[1][1]);
	}
}