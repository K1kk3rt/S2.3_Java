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
	
	//getters
	public status[][] getGrid() {
		return grid;
	}
	public status getStatusVanVakje(int rij, int kolom) {
		return grid[rij][kolom];
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
	
	//construct
	public GameModel() {
		grid = new status[RIJEN][KOLOMMEN];
		
		ronde = 0;
		gewonnen = false;
		
		initGame();
		
		new ConnectFourConsoleView(this);
	}
	
	
	//methods
	private void initGame() {
		
		for (int rij = 0; rij<grid.length; rij++){
		     for (int kolom = 0; kolom<grid[rij].length; kolom++){
		    	 grid[rij][kolom] = status.isEmpty;
		     }
		}
	}
	
	public void insertMuntje(int kolom, status player) {
		
		int rij = bepaalRij(kolom);
		
		grid[rij][kolom] = player;
		
		controleerHorizontaalWinst(grid[rij][kolom], rij);
		controleerVerticaalWinst(grid[rij][kolom], kolom);
		//controlleerDiagonaalWinst(grid[rij][kolom], rij, kolom);
		
		if(!this.gewonnen) {
			ronde++;
		}
		
		setChanged();
		notify();
	}
	
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
	
	private void controlleerDiagonaalWinst(status player, int rij, int kolom) {
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
		int[][] offsetLeft = {
									    										{3, 3},
									    								{2, 2},
							    								{1, 1},
							    						{0, 0},
							    				{-1,-1},		
							    		{-2,-2},						
							    {-3,-3},										
			    												};
		
		for(int[] offset : offsetRight) {
			int r = rij + offset[0];
			int k = kolom + offset[1];
			if(r >= 0 && r <= RIJEN && k >= 0 && k <= KOLOMMEN) {
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
		
		for(int[] offset : offsetLeft) {
			int r = rij + offset[0];
			int k = kolom + offset[1];
			if(r >= 0 && r <= RIJEN && k >= 0 && k <= KOLOMMEN) {
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
}
