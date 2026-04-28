package uo.mp.minesweeper.console;

import uo.mp.minesweeper.ranking.GameRankingEntry;
import uo.mp.minesweeper.session.GameLevel;
import uo.mp.minesweeper.session.SessionInteractor;
import uo.mp.util.Collections.List;
import uo.mp.util.check.ArgumentChecks;
import uo.mp.util.console.Console;

public class ConsoleSessionInteractor implements SessionInteractor {

	/**
	 * Asks the user the difficulty level we desires to play
	 * 
	 * @return The chosen difficulty
	 */
	@Override
	public GameLevel askGameLevel() {
		System.out.println("What level are you willing to play?");
		System.out.println("(e)asy / (m)edium / (h)ard: ");
		char level = Console.readChar();
		
		
			switch(level) {
			case 'm': return GameLevel.MEDIUM;
			case 'h': return GameLevel.HARD;
			default : return GameLevel.EASY;
			}
	}

	/**
	 * Asks the user for its username. If no username is passed, default username "LocalUser" is set.
	 * 
	 * @return the username for the current session
	 */
	@Override
	public String askUserName() {
		System.out.println("Introduce your username: ");
		String username = Console.readString();
		
		try {
			ArgumentChecks.isNotBlank(username);
			ArgumentChecks.isNotNull(username);
		} catch (IllegalArgumentException e) {
			System.out.println("Error: " + e.getMessage());
			username = "LocalUser";
		}
		
		return username;
	}

	/**
	 * Asks the user whether they rather play a new game, show the results or exit.
	 * 
	 * @return 1 for a new game, 2 for myResults, 3 for all results, 0 for exit.
	 */
	@Override
	public int askNextOption() {
		System.out.println("Available options: ");
		int option = 0;
		try {
			option = Console.readInt();
	
		} catch (NumberFormatException e) {
			System.out.println("Invalid input. A numerical value within the stablished range must be provided.");
			askNextOption();
		} catch (RuntimeException e) { 
			System.out.println("Unexpected error. Please, try again!");
			askNextOption();
		}
		
		return option;
	}

	@Override
	public boolean doYouEantToRegisterYourScore() {
		System.out.println("Do you want to store your record?");
		System.out.println("(y)es / (n)o : ");
		
		char option = 'n';
		
		try {
			option = Console.readChar();
		} catch (ClassCastException e) {
			System.out.println("Invalid output. Try again!");
			doYouEantToRegisterYourScore();
		} catch (RuntimeException e) {
			System.out.println("Unexpected error.");
		}
		
		
		switch(option) {
		case 'y': return true;
		case 'n': return true;
		}
		
		return false;
		
	}

	@Override
	public void showRanking(List<GameRankingEntry> ranking) {
		for(int i = 0; i < ranking.size(); i++) {
			System.out.println(ranking.get(i));
		}
		
	}

	@Override
	public void showPersonalRanking(List<GameRankingEntry> ranking) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showGoodBye() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showErrorMessage(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showFatalErrorMessage(String message) {
		// TODO Auto-generated method stub
		
	}

}
