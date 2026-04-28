package uo.mp.minesweeper.console;

import uo.mp.minesweeper.game.GameInteractor;
import uo.mp.minesweeper.game.GameMove;
import uo.mp.minesweeper.game.board.Board;
import uo.mp.util.console.Console;

public class ConsoleGameInteractor implements GameInteractor {

	@Override
    public GameMove askMove(int rows, int columns) {
        char op = Console.readChar("movement (s | f | u)?");
        int row = Console.readInt("row?");
        int col = Console.readInt("column?");
        
        return new GameMove(op, row, col);
    }

	@Override
    public void showGame(long elapsedTime, Board board) {
		
		System.out.println("Time: " + elapsedTime + " seconds");
		System.out.println("Flags left: " + board.getNumberOfFlagsLeft());
		
		int rows = board.getNumberOfRows();
		int cols = board.getNumberOfColumns();
		char[][] state = board.getState();
		
		/*
		 * UPPER NUMBERS
		 */
		System.out.print("    ");
		for(int i = 0; i < cols; i++ ) {
			System.out.print(" " + i + "  ");
		}
		System.out.println(" ");
		
		/*
		 * --SEPARATOR--(j)
		 * --ROW INDEX -- STATE--(j)
		 * --FINAL LINE--
		 */
		for(int k = 0; k < rows; k++) {
			
			System.out.print("   +");
			//SEPARATOR
			for(int j = 0; j < cols; j++) {
				System.out.print("---+");
			}
			
			System.out.println("");
			//STATE
			System.out.print(" " + k + " |");
			
			for(int j = 0; j < cols; j++) {
				System.out.print(" " + state[k][j] + " |");
			}
			
			System.out.println("");
			
		}
		//FINAL LINE
		System.out.print("   +");
		for(int i = 0; i < cols; i++) {
			System.out.print("---+");
		}
		
		System.out.println("");
	}


	@Override
    public void showGameFinished() {
        System.out.println(Ansi.blue("Game over!"));
    }

	@Override
    public void showCongratulations(long elapsedTime) {
        System.out.println(Ansi.green("Congratulations! You have cleared the board in " + elapsedTime + " seconds."));
    }

	@Override
    public void showBooommm() {
        System.out.println(Ansi.red("BOOOOMMMM!!!!")); 
    }

	@Override
    public void showReadyToStart() {
        System.out.println("Ready to start");
    }

}
