package uo.mp.minesweeper.game.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import uo.mp.minesweeper.game.board.actions.BlowUpAction;
import uo.mp.minesweeper.game.board.actions.ClearAction;
import uo.mp.minesweeper.game.board.actions.NullAction;
import uo.mp.util.check.ArgumentChecks;

public class Board {

	private int width;
	private int height;
	private int size;
	private int numberOfMines;
	private int remainingFlags;
	private Square[][] squares;
	private boolean exploded;

	/**
	 * Public constructor that initializes the board dimensions, calculates the
	 * number of mines based on a percentage, creates the squares, and assigns their
	 * values and actions.
	 * 
	 * @param width      Width of the board
	 * @param height     Height of the board
	 * @param percentage Desired percentage of mines in the board
	 */
	public Board(int width, int height, int percentage) {

		this.height = height;
		this.width = width;
		this.size = height * width;
		double percent = percentage / 100.0;
		this.numberOfMines = (int) Math.ceil(percent * (this.size));
		this.remainingFlags = this.numberOfMines;
		this.squares = new Square[height][width];

		this.setSquaresToZero();
		this.setRandomMines();
		this.setSquareValue();
		this.setActions();

	}

	/**
	 * Package-protected constructor used for testing to create a board with a
	 * predefined state.
	 * 
	 * @param mines   Number of mines
	 * @param squares 2D array that simulates a board
	 */
	Board(int mines, Square[][] squares) {
		this.numberOfMines = mines;
		this.squares = squares;
		this.height = squares.length;
		this.width = squares[0].length;
		this.remainingFlags = mines;
	}

	/**
	 * Returns whether a mine has been stepped on during the game.
	 * 
	 * @return true if it has exploded; false otherwise.
	 */
	public boolean hasExploded() {
		return this.exploded;
	}

	/**
	 * Executes the stepOn operation on the square at the specified coordinates.
	 * 
	 * @param x X coordinate
	 * @param y Y coordinate
	 */
	public void stepOn(int x, int y) {

		ArgumentChecks.isTrue(x >= 0 && x < width);
		ArgumentChecks.isTrue(y >= 0 && y < height);

		squares[y][x].stepOn();
	}

	/**
	 * Places a flag on the square at the specified coordinates if it is closed and
	 * decreases the remaining flags count.
	 * 
	 * @param x X coordinate
	 * @param y Y coordinate
	 */
	public void flag(int x, int y) {
		ArgumentChecks.isTrue(x >= 0 && x < width);
		ArgumentChecks.isTrue(y >= 0 && y < height);

		if (!squares[y][x].isOpened() && !squares[y][x].isFlagged()) {
			squares[y][x].flag();
			remainingFlags--;
		}
	}

	/**
	 * Removes a flag from the square at the specified coordinates and increases the
	 * remaining flags count.
	 * 
	 * @param x X coordinate
	 * @param y Y coordinate
	 */
	public void unflag(int x, int y) {
		ArgumentChecks.isTrue(x >= 0 && x < width);
		ArgumentChecks.isTrue(y >= 0 && y < height);

		if (squares[y][x].isFlagged()) {
			squares[y][x].unflag();
			remainingFlags++;
		}
	}

	/**
	 * Opens all squares on the board, typically used when the game ends.
	 */
	public void unveil() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				squares[i][j].open();
			}
		}
	}

	/**
	 * Returns the current number of available flags to be placed.
	 * 
	 * @return number of remaining flags
	 */
	public int getNumberOfFlagsLeft() {
		return this.remainingFlags;
	}

	/**
	 * Returns the number of flags remaining (conceptually linked to the number of
	 * mines left to find).
	 * 
	 * @return number of mines remaining
	 */
	public int getNumberOfMinesLeft() {
		return this.remainingFlags;
	}

	/**
	 * Returns a 2D char array representing the visual state of the board for the
	 * UI.
	 * 
	 * @return char array simulating the board
	 */
	public char[][] getState() {
		char[][] res = new char[this.height][this.width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				res[i][j] = squares[i][j].toString().charAt(0);
			}
		}
		return res;
	}

	/**
	 * Returns a copy of the current board
	 * 
	 * @return
	 */
	public Square[][] getSquares() {
		Square[][] copy = new Square[height][width];

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				copy[i][j] = squares[i][j];
			}
		}
		return copy;
	}

	/**
	 * Sets the board's status to exploded; called by BlowUpAction.
	 */
	public void markAsExploded() {
		this.exploded = true;
	}

	public int getNumberOfRows() {
		return this.height;
	}

	public int getNumberOfColumns() {
		return this.width;
	}

	public void uncoverWelcomeArea() {
		Random rand = new Random();
		boolean correct = false;
		
		while(!correct) {
			int row = rand.nextInt(height);
			int col = rand.nextInt(width);
			
			//Corner check and 0 value check
			if((squares[row][col].getValue() == 0) &&
					!(row == 0 && col == 0) &&
					!(row == 0 && col == width - 1) &&
					!(row == height - 1 && col == 0) &&
					!(row == height - 1 && col == width - 1)) {
				stepOn(col, row);
				correct = true;
			}
		}
	}

	/*
	 * AUXILIARY METHODS
	 */

	/**
	 * Fills the board matrix with initial Square objects with a value of zero.
	 */
	private void setSquaresToZero() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				squares[i][j] = new Square(0);
			}
		}
	}

	/**
	 * Randomly distributes the calculated number of mines across the board.
	 */
	private void setRandomMines() {
		int auxMines = this.numberOfMines;
		Random rand = new Random();

		while (auxMines != 0) {
			int XCoord = rand.nextInt(width);
			int YCoord = rand.nextInt(height);
			if (!squares[YCoord][XCoord].hasMine()) {
				squares[YCoord][XCoord] = new Square(-1);
				auxMines--;
			}
		}
	}

	/**
	 * Calculates and assigns the numerical value to each non-mine square based on
	 * adjacent mines.
	 */
	private void setSquareValue() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (!squares[i][j].hasMine()) {
					squares[i][j] = new Square(adjacentMines(i, j));
				}
			}
		}
	}

	/**
	 * Helper method that counts how many mines are surrounding a specific
	 * coordinate.
	 * 
	 * @param row Row to check
	 * @param col Col to check
	 * @return number of mines in the contiguous squares
	 */
	private int adjacentMines(int row, int col) {
		int mineCount = 0;

		for (int i = row - 1; i <= row + 1; i++) {
			for (int j = col - 1; j <= col + 1; j++) {
				if (i >= 0 && i < height && j >= 0 && j < width) {
					if (squares[i][j].hasMine()) {
						mineCount++;
					}
				}
			}
		}
		return mineCount;
	}

	/**
	 * Checks if the board is in a winning state
	 * 
	 * @return True if it is a winning state; false otherwise.
	 */
	public boolean isVictory() {
		int closedSquares = 0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (!squares[i][j].isOpened()) {
					closedSquares++;
				}
			}
		}
		return closedSquares == this.numberOfMines;
	}

	/**
	 * Iterates through the board and assigns the corresponding Action object to
	 * each square.
	 */
	private void setActions() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				Square s = squares[i][j];

				if (s.hasMine()) {
					s.setAction(new BlowUpAction(this));
				} else if (s.getValue() > 0) {
					s.setAction(new NullAction());
				} else {
					s.setAction(new ClearAction(this.getNeighbors(i, j)));
				}
			}
		}
	}

	/**
	 * Identifies and returns a list of all existing neighbor squares for a given
	 * position.
	 * 
	 * @param row The row index
	 * @param col The column index
	 * @return A list of neighbor Square objects
	 */
	private List<Square> getNeighbors(int row, int col) {
		List<Square> neighbors = new ArrayList<>();

		for (int x = row - 1; x <= row + 1; x++) {
			for (int y = col - 1; y <= col + 1; y++) {

				if (x >= 0 && x < height && y >= 0 && y < width) {
					neighbors.add(squares[x][y]);
				}
			}
		}
		return neighbors;
	}

}
