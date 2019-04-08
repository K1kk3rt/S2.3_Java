package connectFour;

import java.util.Observable;

public class GameModel extends Observable{
	
	//fields
	public enum status{
		isEmpty,
		player1,
		player2
	}
	
	private status grid[][];
	private final int RIJEN = 6;
	private final int KOLOMMEN = 7;
	private int ronde;
	private boolean gewonnen;
	private boolean gelijkspel;
	private boolean restart;
	private int currentRij;
	private int currentKolom;
	
	//getters
	public status[][] getGrid() {
		return grid;
	}
	public status getStatusVanVakje(int rij, int kolom) {
		return grid[rij][kolom];
	}
	public int getRijen() {
		return RIJEN;
	}
	public int getKolommen() {
		return KOLOMMEN;
	}
	public status getPlayer() {
		//controleer of ronde even is.
		if (ronde % 2 == 0 || ronde == 0) {
			return status.player1;
		}
		else {
			return status.player2;
		}
	}
	public boolean getGewonnen() {
		return gewonnen;
	}
	public boolean getGelijkspel() {
		return gelijkspel;
	}
	public boolean getRestart() {
		return restart;
	}
	public int getCurrentRij() {
		return currentRij;
	}
	public int getCurrentKolom() {
		return currentKolom;
	}
	
	//construct
	public GameModel() {
		
		//set variabelen
		grid = new status[RIJEN][KOLOMMEN];
		
		ronde = 0;
		gewonnen = false;
		gelijkspel = false;
		restart = false;
		
		//maak het spel klaar om te spelen
		initGame();
		
		//maak een view
		new ConnectFourGraphicView(this);
		//new ConnectFourConsoleView(this);
	}
	
	//methods
	
	//start het spel opnieuw. zet variabelen weer naar hun standaard waarden (behalve restart, deze moet true zijn).
	//maak het spel daarna opnieuw klaar om te spelen, en update de view.
	public void restartGame() {
		restart = true;
		gewonnen = false;
		gelijkspel = false;
		
		initGame();
		
		setChanged();
		notifyObservers();
	}	
	
	//maak het spel klaar om te spelen. loop door de 2D array en zet de status op empty.
	private void initGame() {
		
		for (int rij = 0; rij<grid.length; rij++){
		     for (int kolom = 0; kolom<grid[rij].length; kolom++){
		    	 grid[rij][kolom] = status.isEmpty;
		     }
		}
	}
	
	//parameters: int kolom -> de kolom waar de speler een muntje in wilt gooien, status player -> de speler die het muntje er in gooit.
	//doe een virtueel muntje in het spel. Zet restart op false, zodat het spel niet bij iedere zet opnieuw begint.
	//de speler geeft een kolom op, dus moet de rij nog bepaalt worden (laagste vakje)
	//als de rij en kolom groter of gelijk aan 0 zijn, geef een vakje de status van de speler die aan de buurt is.
	//voeg 1 toe aan de ronde (wordt gebruikt om de speler die aan de buurt is te bepalen).
	//bepaal of er vier op een rij is in alle richtingen, of dat er gelijk spel is.
	//controlleer of er gewonnen is, haal 1 van ronde af zodat je juiste speler wordt weergeven bij winst.
	//zet de huidige rij en kolom zodat alleen deze geupdate hoeft te worden en niet het hele spelbord vanuit de grafische view.
	//update de view
	public void insertMuntje(int kolom, status player) {
		restart = false;
		
		int rij = bepaalRij(kolom);
		
		if(rij >= 0 && kolom >= 0) {
			grid[rij][kolom] = player;
			
			ronde++;
			
			controleerHorizontaalWinst(grid[rij][kolom], rij);
			controleerVerticaalWinst(grid[rij][kolom], kolom);
			controlleerDiagonaalWinstRechts(grid[rij][kolom], rij, kolom);
			controlleerDiagonaalWinstLinks(grid[rij][kolom], rij, kolom);
			controlleerGelijkspel();
			
			if(this.gewonnen) {
				ronde--;
			}
			
			currentRij = rij;
			currentKolom = kolom;
			
			setChanged();
			notifyObservers();
		}
	}
	
	//bepaal de onderste rij waar het muntje in zal vallen aan de hand van de kolom waar de speler op heeft geklikt.
	//loop door de kolom van onderen (dus andersom). wanneer een vakje leeg is, is dat de onderste rij en wordt deze gereturnt.
	private int bepaalRij(int kolom) {
		int rij = 0;
		
		for(rij=RIJEN-1; rij >= 0; rij--) {
			if(grid[rij][kolom] == status.isEmpty) {
				return rij;
			}
			else {
				continue;
			}
		}
		
		return rij;
	}
	
	//controleer of er vier op een rij is, horizontaal. dit aan de hand van voor welke speler er gecontroleert moet worden,
	//en op welke rij. loop door de rij, als de status van een vakje gelijk is aan de speler voor wie er gecontroleert wordt, 
	//verhoog het aantal met 1. als het aantal vier is hoeft er niet verder gecontroleert te worden en staat gewonnen op true.
	//daar zullen andere methoden op reageren (zoals de view)
	private void controleerHorizontaalWinst(status player, int rij) {
		int aantal = 0;
		
		for(int kolom=0; kolom < KOLOMMEN; kolom++){
			if(grid[rij][kolom] == player) {
	    		 aantal++;
	    	}
	    	else {
	    		 aantal = 0;
	    	}
			if (aantal == 4) {
	    		 gewonnen = true;
	    		 return;
	    	 }
		}
	}
	
	//controleer of er vier op een rij is, verticaal. dit aan de hand van voor welke speler er gecontroleert moet worden,
	//en op welke kolom. loop door de kolom, als de status van een vakje gelijk is aan de speler voor wie er gecontroleert wordt, 
	//verhoog het aantal met 1. als het aantal vier is hoeft er niet verder gecontroleert te worden en staat gewonnen op true.
	//daar zullen andere methoden op reageren (zoals de view)
	private void controleerVerticaalWinst(status player, int kolom) {
		int aantal = 0;
		
		for(int rij=0; rij < RIJEN; rij++){
			if(grid[rij][kolom] == player) {
	    		 aantal++;
	    	}
	    	else {
	    		aantal = 0;
	    	}
			if (aantal == 4) {
	    		 gewonnen = true;
	    		 return;
	    	 }
		}
	}
	
	//controleer of er vier op een rij is, diagonaal naar rechts. dit aan de hand van voor welke speler er gecontroleert moet worden,
	//en vanaf welke kolom en rij. loop door de kolom en rij aan de hand van de array offsetRight. in deze array staan alle locaties 
	//die gecontroleert moeten worden voor vier op een rij voor diagonaal naar rechts.
	//als de status van een vakje gelijk is aan de speler voor wie er gecontroleert wordt, 
	//verhoog het aantal met 1. als het aantal vier is hoeft er niet verder gecontroleert te worden en staat gewonnen op true.
	//daar zullen andere methoden op reageren (zoals de view)
	private void controlleerDiagonaalWinstRechts(status player, int rij, int kolom) {
		int aantal = 0;
		
		int[][] offsetRight = {
							    {-3, 3},							
							    		{-2, 2},				
							    				{-1, 1},	
							    						{0, 0}, 
								    							{1, -1},
								    									{2, -2},
								    											{3, -3}
									    												};
		
		for(int[] offset : offsetRight) {
			int r = rij + offset[0];
			int k = kolom + offset[1];
			if(r >= 0 && r <= RIJEN-1 && k >= 0 && k <= KOLOMMEN-1) {
				if(grid[r][k] == player) {
					aantal++;
				}
				else {
					aantal=0;
				}
				if (aantal == 4) {
		    		 gewonnen = true;
		    	 }
			}
			
		}
	}
	
	//controleer of er vier op een rij is, diagonaal naar links. dit aan de hand van voor welke speler er gecontroleert moet worden,
	//en vanaf welke kolom en rij. loop door de kolom en rij aan de hand van de array offsetLeft. in deze array staan alle locaties 
	//die gecontroleert moeten worden voor vier op een rij voor diagonaal naar rechts.
	//als de status van een vakje gelijk is aan de speler voor wie er gecontroleert wordt, 
	//verhoog het aantal met 1. als het aantal vier is hoeft er niet verder gecontroleert te worden en staat gewonnen op true.
	//daar zullen andere methoden op reageren (zoals de view)
	private void controlleerDiagonaalWinstLinks(status player, int rij, int kolom) {
		int aantal = 0;
		
		int[][] offsetLeft = {
									    										{3, 3},
									    								{2, 2},
							    								{1, 1},
							    						{0, 0},
							    				{-1,-1},		
							    		{-2,-2},						
							    {-3,-3},										
			    												};
		

		for(int[] offset : offsetLeft) {
			int r = rij + offset[0];
			int k = kolom + offset[1];
			if(r >= 0 && r <= RIJEN-1 && k >= 0 && k <= KOLOMMEN-1) {
				if(grid[r][k] == player) {
					aantal++;
				}
				else {
					aantal=0;
				}
				if (aantal == 4) {
		    		 gewonnen = true;
		    	 }
			}
		}
	}
	
	//loop door de grid en controleer of alle vakjes niet leeg zijn.
	//als dit zo is, zet gelijkspel op true. hier reageren andere methoden op zoals de view.
	private void controlleerGelijkspel() {
		int aantal = 0;
		
		for (int rij = 0; rij<grid.length; rij++){
		     for (int kolom = 0; kolom<grid[rij].length; kolom++){
		    	 if(grid[rij][kolom] != status.isEmpty) {
		    		 aantal++;
		    	 }
		    	 
		     }
		}
		
		if(aantal == RIJEN*KOLOMMEN) {
			gelijkspel = true;
		}
	}
	
	
}
