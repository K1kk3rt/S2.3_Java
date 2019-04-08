package connectFour;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class ConnectFourConsoleView implements Observer{
	
	private GameModel game;
	
	//construct
	//voeg een instantie van het GameModel toe, zodat properties en methodes van met model bereikt kunnen worden
	//voeg deze klasse toe als observer, zodat deze geupdate wordt bij een wijziging (wanneer een speler een zet doet)
	//weergeef het spel met updateConnectFour();
	public ConnectFourConsoleView(GameModel game) {
		this.game = game;
		
		game.addObserver(this);
		
		updateConnectFour();
		
	}
	
	//implement interfaces
	@Override
	public void update(Observable o, Object arg) {	
		updateConnectFour();
	}
	
	//print het spel
	//weergeef wie er gewonnen heeft of gelijkspel als deze waardes true zijn
	//anders vraag de input van een van de players
	private void updateConnectFour() {
		
		printGame();
		
		if(game.getGewonnen()) {
			gewonnen();
		}
		if(game.getGelijkspel()) {
			gelijkspel();
		}
		else {
			getInput();
		}
		
	}
	
	//print het spel
	//loop door de grid en print een vakje, na iedere rij komt een nieuwe regel
	//zet nummers onder het spel, zodat de gebruiker ziet welke kolom welk nummer is
	//print twee nieuwe regels
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
	
	//weergeef een vakje aan de hand van de status van dat vakje
	//geef het vakje ook een standaard breedte.
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
	
	//ontvang de input van een speler
	//weergeef eerst welke speler er aan de buurt is, en maak een scanner om de input te lezen
	//probeer dan de input te lezen en sla het op in kolom. mocht het verkeerd gaan, dan wordt de weergave van het spel gereset via updateconnectfour
	//als de ingevulde waarde tussen het aantal kolommen is, doe insert muntje in het gameModel, anders weergeef een bericht dat het juiste getal ingevoert moet worden,
	//en reset de weergave van het spel via updateconnectfour
	//als laatste, sluit de scanner.
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
		finally {
			if(scanner != null) {
				scanner.close();
			}
		}
	}
	
	//weergeef de speler aan de hand van welke speler er aan de buurt is
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
	
	//geef aan welke speler gewonnen heeft
	private void gewonnen() {
		System.out.println(displayPlayer() + "heeft gewonnen!");
	}
	
	//geef aan dat het gelijkspel is
	private void gelijkspel() {
		System.out.println("gelijkspel!");
	}
}
