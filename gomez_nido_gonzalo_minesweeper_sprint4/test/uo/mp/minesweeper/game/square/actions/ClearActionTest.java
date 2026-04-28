package uo.mp.minesweeper.game.square.actions;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import uo.mp.minesweeper.game.board.Square;
import uo.mp.minesweeper.game.board.actions.ClearAction;

/**
 * Test if ClearAction works as expected
 * 1 - executeCallsStepOnOnAllNeighborsTest: Executing the action calls stepOn 
 * on all neighbor squares provided in the constructor.
 * 2 - executeWithNoNeighborsTest: Executing the action with an empty list of 
 * neighbors completes normally without throwing exceptions.
 */
public class ClearActionTest {

	/**
	 * GIVEN: A list of neighbor squares and a ClearAction initialized with them
	 * WHEN: execute() is called on the action
	 * THEN: stepOn() is executed on all neighbors (they become opened)
	 */
	@Test
	public void executeCallsStepOnOnAllNeighborsTest() {
		List<Square> neighbors = new ArrayList<>();
		Square s1 = new Square(0);
		Square s2 = new Square(0);
		Square s3 = new Square(0);
		
		neighbors.add(s1);
		neighbors.add(s2);
		neighbors.add(s3);
		
		ClearAction action = new ClearAction(neighbors);
		
		action.execute();
		
		assertTrue(s1.isOpened());
		assertTrue(s2.isOpened());
		assertTrue(s3.isOpened());
	}

	/**
	 * GIVEN: An empty list of neighbor squares and a ClearAction initialized with it
	 * WHEN: execute() is called on the action
	 * THEN: The execution completes successfully without errors
	 */
	@Test
	public void executeWithNoNeighborsTest() {
		List<Square> neighbors = new ArrayList<>();
		ClearAction action = new ClearAction(neighbors);
		
		action.execute();
		
		// If execution reaches this point without throwing an exception, the test passes
		assertTrue(true); 
	}

}