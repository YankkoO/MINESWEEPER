package uo.mp.minesweeper.session;

import uo.mp.minesweeper.ranking.GameRankingEntry;
import uo.mp.util.Collections.List;

public interface SessionInteractor {

	GameLevel askGameLevel();
	
	String askUserName();
	
	int askNextOption();
	
	boolean doYouEantToRegisterYourScore();
	
	void showRanking(List<GameRankingEntry> ranking);
	
	void showPersonalRanking(List<GameRankingEntry> ranking);
	
	void showGoodBye();
	
	void showErrorMessage(String message);
	
	void showFatalErrorMessage(String message);
}
