package nl.inholland;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class ConsoleView implements Observer{
	
	GameModel game;
	
	public ConsoleView(GameModel game) {
		this.game = game;
		
		game.addObserver(this);
		
		updateView();
		
	}

	@Override
	public void update(Observable o, Object arg) {
		updateView();
		
	}
	
	private void updateView() {
		
		if(!game.checkGameWon()) {
			printGame();
			getInput();			
		}
		else {
			System.out.println("You won!");
		}
	}
	
	private void printGame() {
		System.out.println();
		
		int length = game.getMATRIX();
		
		//print each number
		for (int row = 0; row<length; row++){
		     for (int col = 0; col<length; col++){
		    	 System.out.printf("%-4s", game.getMatrix()[row][col]);
		     }
		     System.out.println();
		}
		
		System.out.println();
		System.out.println();
	}
	
	private void getInput() {
		System.out.println("Press a key to move a tile");
		System.out.println("UP: W");
		System.out.println("LEFT: A");
		System.out.println("DOWN: S");
		System.out.println("RIGHT: D");
		
		Scanner scanner = new Scanner(System.in);
		
		try {
			//determine side to move from, from pressed key
			String move = scanner.nextLine();
			
			switch(move) {
			case "w":
				game.moveTileFromUp();
				break;
			case "a":
				game.moveTileFromLeft();
				break;
			case "s":
				game.moveTileFromDown();
				break;
			case "d":
				game.moveTileFromRight();
				break;
			default:
				System.out.println("Not a valid key. Pleasy retry.");
				updateView();
			}
		}
		catch(Exception e) {
			updateView();
		}
		finally {
			if(scanner != null) {
				scanner.close();
			}
		}
	}

}
