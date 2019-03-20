import java.awt.Color;
import java.util.Observable;
import java.util.Random;

public class BoardGame extends Observable{
	
	private BoardPanelView View;
	private Zone[] GameGrid;
	private final int AmountOfBombs = 150;
	private final int Columns = 30;
	private final int Rows = 20;
	
	//getters
	public Zone[] getGameGrid() {return GameGrid;}
	public int getAmoundOfBombs() {return AmountOfBombs;}
	public int getColumns() {return Columns;}
	public int getRows() {return Rows;}
	
	//construct
	public BoardGame(BoardPanelView view) {
		
		View = view;
		
		//generate game grid
		GameGrid = new Zone[Rows * Columns];
	}
	
	//methods
	public void createGame() {
		
		//loop through grid and create instance of zone for every Zone item in grid
		int i = 0; 
		for(int y = 0; y < Rows; y++) {
			for (int x = 0; x < Columns; x++) {
				GameGrid[i] = new Zone(x,y, i);
				GameGrid[i].addMouseListener(new ZoneClickListener(this));
				View.add(GameGrid[i]);
				i++;
			}
		}
		
		//pick available bomb spots, save in array
		i=0;
		boolean bombOptions[][] = new boolean[Columns][Columns];
		for(int y = 0; y < Rows; y++) {
			for (int x = 0; x < Columns; x++) {
				bombOptions[x][y] = true;
			}
		}
		
		//loop through bombs and define bomb placement
		i=0;
		while (i < AmountOfBombs) {
			Random rand = new Random();
			int x = rand.nextInt(Columns);
			int y = rand.nextInt(Rows);
			
			if(bombOptions[x][y]) {
				GameGrid[getGridIndex(x,y)].setIsMine(true);
				bombOptions[x][y] = false;
			}
			
			i++;
		}

		//loop through grid and define neighbor count
		for(i = 0; i < GameGrid.length; i++) {
			GameGrid[i].setNeighbours(countNeighbours(i));
		}
		
		System.out.println(GameGrid);
	}
	
	private int countNeighbours(int i) {

		int total = 0;
		
		for (int xoff = -1; xoff <= 1; xoff++) {
			for (int yoff = -1; yoff <= 1; yoff++) {
				if(i < -1) {
					int x = GameGrid[i].getX() + xoff;
					int y = GameGrid[i].getY() + yoff;
					i = getGridIndex(x, y);
					
					if(x>-1 && x < Columns && y > -1 && y < Rows) {
						if (GameGrid[i].getIsMine()) {
							total++;
						}
					}					
				}
			}
		}
		
		return total;
	}

	public void revealZones(Zone zone) {
		//get clicked grid index position
		int i = zone.getI();
		
		//reveal zone
		GameGrid[i].setIsRevealed(true);
		
		//reveal more zones if it doesn't have neighbor mines
		if(GameGrid[i].getNeighbours() == 0) {
			revealNeighbourZones(zone.getI());
		}
		
		//update board
		setChanged();
		notifyObservers(GameGrid[i]);
	}
	public void revealNeighbourZones(int i) {
		
		for (int xoff = -1; xoff <= 1; xoff++) {
			for (int yoff = -1; yoff <= 1; yoff++) {
				if(i > -1) {
					int x = GameGrid[i].getX() + xoff;
					int y = GameGrid[i].getY() + yoff;
					i = getGridIndex(x, y);
					
					if(x>-1 && x < Columns && y > -1 && y < Rows) {
						if(!GameGrid[i].getIsMine() && !GameGrid[i].getIsRevealed()) {
							revealZones(GameGrid[i]);
						}
					}
				}
			}
		}
	}
	
	public void markZone(Zone zone) {
		int i = zone.getI();
		
		if(!GameGrid[i].getIsRevealed()) {
			GameGrid[i].setIsMarked(true);
			GameGrid[i].setIsRevealed(true);
		}
		
	}
	
	public void unMarkZone(Zone zone) {
		int i = zone.getI();
		
		if(GameGrid[i].getIsRevealed()) {
			GameGrid[i].setIsMarked(false);
			GameGrid[i].setIsRevealed(false);
			GameGrid[i].setBackground(new Color(47, 48, 52));
		}
	}
	
	public void gameOver() {
		//loop through game grid and set isRevealed true
		for(Zone item : GameGrid) {
			item.setIsRevealed(true);
		}
	}
	
	private int getGridIndex(int xPos, int yPos) {
		int i = 0; 
		for(int y = 0; y < Rows; y++) {
			for (int x = 0; x < Columns; x++) {
				if(GameGrid[i].getX() == xPos && GameGrid[i].getY() == yPos) {
					return i;
				}
				i++;
			}
		}
		
		return -1;
	}

}
