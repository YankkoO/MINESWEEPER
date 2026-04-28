package uo.mp.minesweeper.game;

import uo.mp.minesweeper.game.board.Board;

public interface GameInteractor {
	
	GameMove askMove(int rows, int columns);
	
	void showGame(long elapsedTime, Board board);
	
	void showGameFinished();
	
	void showCongratulations(long elapsedTime);
	
	void showBooommm();
	
	void showReadyToStart();

}