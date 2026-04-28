package uo.mp.minesweeper.game.board;

import uo.mp.minesweeper.game.board.actions.Action;
import uo.mp.util.check.ArgumentChecks;

public class Square {
	
	public final static int MIN_ADJACENT = 0;
	public final static int MAX_ADJACENT = 8;

	public enum SquareStatus{
		CLOSED,
		OPENED,
		FLAGGED
	}
	
	private Action action;
	private SquareStatus status;
	private boolean hasMine;
	private int value;
	
	/**
	 * Public constructor for the Square class where the value of the square is passed as a 
	 * parameter
	 * 
	 * @param value Content of the square (NUMBER, MINE, EMPTY)
	 */
	public Square(int value) {

		ArgumentChecks.isTrue(value <= MAX_ADJACENT, "value can't be higher than 8");
		ArgumentChecks.isTrue(value >= -1, "value can't be lower than -1");

	
		this.status = SquareStatus.CLOSED;
		this.value = value;
		this.hasMine = (value == -1);
		
	}

	public void setAction(Action action) {
		this.action = action;
	}

	/**
	 * Reveals the content of the square.
	 * Only available for CLOSED squares.
	 */
	public void stepOn() {
		if(this.status == SquareStatus.CLOSED) {
			this.open();
			if(this.action != null) {
				this.action.execute();
			}
		}
			
	}
	
	/**
	 * Marks the square with a flag.
	 * Only available for CLOSED squares.
	 */
	public void flag() {
		if(this.status == SquareStatus.CLOSED) {
			this.status = SquareStatus.FLAGGED;
		}
	}
	
	/**
	 * Removes a flag from a square that had already been flagged and sets it to CLOSED.
	 * Only available for FLAGGED squares.
	 */
	public void unflag() {
		if(this.status == SquareStatus.FLAGGED) {
			this.status = SquareStatus.CLOSED;
		}
	}
	
	/**
	 * No matter what the situation is, the square gets OPENED.
	 */
	public void open() {
		this.status = SquareStatus.OPENED;
	}
	
	/**
	 * It returns a String with the character that represents the box 
	 * graphically according to its value and current state.
	 * 
	 * @return The string representing the square.
	 */
	@Override
	public String toString() {
		if(status == SquareStatus.CLOSED){
			return "#";
		}

		if(status == SquareStatus.FLAGGED){
			return "¶";
		}

		if(status == SquareStatus.OPENED && hasMine()){
			return "@";
		}
		
		if(status == SquareStatus.OPENED && this.value == 0 ) {
			return " ";
		}
		
		

		return String.valueOf(getValue());
	}
	
	/**
	 * Returns the numeric value of the square
	 * 
	 * @return Numeric value of the square.
	 */
	public int getValue(){
		return this.value;
	}
	
	/**
	 * It returns whether the square contains a mine or not.
	 * 
	 * @return true if it has a mine. false otherwise.
	 */
	public boolean hasMine(){
		return this.hasMine;
	}

	/**
	 * It returns whether the square is flagged or not.
	 * 
	 * @return true if it is flagged. false otherwise.
	 */
	public boolean isFlagged(){
		if(this.status == SquareStatus.FLAGGED){
			return true;
		}
		return false;
	}

	/**
	 * It returns whether the square is opened or not.
	 * 
	 * @return true if it is opened. false otherwise.
	 */
	public boolean isOpened(){
		if(this.status == SquareStatus.OPENED){
			return true;
		}
		return false;
	}

}
