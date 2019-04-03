package connectFour;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class ConnectFourConsoleView implements Observer{
	
	private GameModel game;
	
	//construct
	public ConnectFourConsoleView(GameModel game) {
		this.game = game;
		
		updateConnectFour();
	}
	
	//implement interfaces
	@Override
	public void update(Observable o, Object arg) {	
		updateConnectFour();
	}
	
	private void updateConnectFour() {
		
		printGame();
		
		if(game.getGewonnen()) {
			gewonnen();
		}
		else {
			getInput();
		}
		
	}
	
	private void printGame() {
		System.out.println();
		
		for (int rij = 0; rij<game.getGrid().length; rij++){
		     for (int kolom = 0; kolom<game.getGrid()[rij].length; kolom++){
		    	 displayVakje(rij, kolom);
		     }
		     System.out.println();
		}
		
		for(int i = 0; i < game.getKolommen(); i++) {
			System.out.printf("%-3s", i);
		}
		
		System.out.println();
		System.out.println();
	}
	
	private void displayVakje(int rij, int kolom) {
		
		switch(game.getStatusVanVakje(rij, kolom)) {
		  case isEmpty:
			  System.out.printf("%-3s", ".");
		    break;
		  case player1:
			  System.out.printf("%-3s", "@");
		    break;
		  case player2:
			  System.out.printf("%-3s", "#");
			break;
		  default:
			  System.out.printf("%-3s", "x");
		}
	}
	
	private void getInput() {
		System.out.println(displayPlayer() +  "Geef een kolom: ");
		Scanner scanner = new Scanner(System.in);
		
		try {
			int kolom = scanner.nextInt();
			
			if(kolom >= 0 && kolom <= game.getKolommen()) {
				game.insertMuntje(kolom, game.getPlayer());
			}
			else {
				System.out.println("Voer een getal in tussen de 0 en " + game.getKolommen() + "!");
				updateConnectFour();
			}
		}
		catch(Exception e) {
			updateConnectFour();
		}
	}
	
	private String displayPlayer() {
		String player = "x";
		
		switch(game.getPlayer()) {
		  case player1:
			  player = "P1 @: ";
		    break;
		  case player2:
			  player = "P2 #: ";
			break;
		  default:
			  player = "x : ";
		}
		
		return player;
	}
	
	private void gewonnen() {
		System.out.println(displayPlayer() + "heeft gewonnen!");
	}
}
