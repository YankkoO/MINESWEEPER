package uo.mp.minesweeper.game;


public class GameMove {
	private char operation;
	private int row;
	private int column;

	/**
	 * Constructor. Receives an operation ('s', 'f', or 'u') and coordinates.
	 * @param operation The operation character
	 * @param row The row index
	 * @param column The column index
	 */
	public GameMove(char operation, int row, int column) {
		this.operation = operation;
		this.row = row;
		this.column = column;
	}

	/**
	 * @return the character representing the user's operation.
	 */
	public char getOperation() {
		return operation;
	}

	/**
	 * @return the row coordinate.
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @return the column coordinate.
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * Returns a textual representation with the format:
	 * [operation=s, row=1, column=1]
	 */
	@Override
	public String toString() {
		return "GameMove [operation=" + operation + ", row=" + row + ", column=" + column + "]";
	}
}