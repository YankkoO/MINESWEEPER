package uo.mp.minesweeper;

import uo.mp.minesweeper.game.Game;
import uo.mp.minesweeper.game.board.Board;
import uo.mp.minesweeper.gui.GuiGameInteractor;

public class MainHard {

	private Game game;

	public static void main(String[] args) {
		new MainHard()
			.configure()
			.run();
	}

	private MainHard configure() {
		Board board = new Board(
				30 /*cols*/, 
				16 /*rows*/, 
				12 /*% of mines*/
			);

		game = new Game( board );
		game.setInteractor( new GuiGameInteractor() );
		return this;
	}

	private void run() {
		game.play();
	}

}
