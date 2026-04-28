package uo.mp.minesweeper.game;

import uo.mp.minesweeper.game.board.Board;
import uo.mp.util.check.ArgumentChecks;

public class Game {
	
	private GameInteractor interactor;
	private Board board;
	
	public Game(Board board) {
		ArgumentChecks.isNotNull(board, "Board can't be null");
		this.board = board;
	}

	public void setInteractor(GameInteractor interactor) {
		this.interactor = interactor;
	}

	public void play() {
		interactor.showReadyToStart();
		
		board.uncoverWelcomeArea();
		
		long startTime = System.currentTimeMillis();
		
		while(!board.hasExploded() && !board.isVictory()) {
			long currentTime = (System.currentTimeMillis() - startTime) / 1000;
			
			interactor.showGame(currentTime, board);
			
			GameMove move = interactor.askMove(board.getNumberOfRows(), board.getNumberOfColumns());
			
			executeMove(move);
		}
		
		long finalTime = (System.currentTimeMillis() - startTime) / 1000;
	    board.unveil();
	    interactor.showGame(finalTime, board);
	    interactor.showGameFinished();
	    
	    if (board.hasExploded()) {
	        interactor.showBooommm();
	    } else {
	        interactor.showCongratulations(finalTime);
	    }
		
		
		
	}

	/*
	 * AUXILIARY METHODS
	 */
	
	private void executeMove(GameMove move) {
	    char op = move.getOperation();
	    int rd = move.getRow();
	    int cl = move.getColumn();
	    
	    if(op == 's') {
	    	board.stepOn(cl, rd);
	    } else if (op == 'f'){
	    	board.flag(cl, rd);
	    } else {
	    	board.unflag(cl, rd);
	    }
	    
	    
	}

}
