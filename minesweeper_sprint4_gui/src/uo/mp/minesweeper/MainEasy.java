package uo.mp.minesweeper;

import uo.mp.minesweeper.game.Game;
import uo.mp.minesweeper.game.board.Board;
import uo.mp.minesweeper.gui.GuiGameInteractor;

public class MainEasy {

	private Game game;

	public static void main(String[] args) {
		new MainEasy()
			.configure()
			.run();
	}

	private MainEasy configure() {
		Board board = new Board(
				9 /*cols*/, 
				9 /*rows*/, 
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
