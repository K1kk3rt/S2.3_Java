import java.awt.Color;
import java.util.Arrays;
import java.util.Observable;
import java.util.Random;

import javax.swing.JButton;

public class BoardGame extends Observable{
	
	private BoardPanelView View;
	private ZoneModel[] GameGrid;
	private final int AmountOfBombs = 150;
	private final int Columns = 30;
	private final int Rows = 20;
	private boolean gameIsStarted;
	
	//getters
	public ZoneModel[] getGameGrid() {return GameGrid;}
	public int getAmoundOfBombs() {return AmountOfBombs;}
	public int getColumns() {return Columns;}
	public int getRows() {return Rows;}
	public boolean getGameIsStarted() {return gameIsStarted;}
	
	//setters
	public void setGameIsStarted(boolean gameIsStarted) {this.gameIsStarted = gameIsStarted;}
	
	//construct
	public BoardGame(BoardPanelView view) {
		
		View = view;
		
		//generate game grid
		GameGrid = new ZoneModel[Rows * Columns];
		
		gameIsStarted = false;
	}
	
	//methods
	public void createGrid() {
		//loop through grid and create instance of zone for every Zone item in grid
		int i = 0;
		
		for(int y = 0; y < Rows; y++) {
			for(int x = 0; x < Columns; x++) {
				GameGrid[i] = new ZoneModel(x, y, i);
				GameGrid[i].addMouseListener(new ZoneClickListener(this));
				View.add(GameGrid[i]);
				i++;
			}
		}
	}
	
	public void createGame(ZoneModel zone) {
		
		//pick available bomb spots, save in array
		int i=0;
		boolean bombOptions[][] = new boolean[Columns][Rows];
		for(int y = 0; y < Rows; y++) {
			for (int x = 0; x < Columns; x++) {
				bombOptions[x][y] = true;
			}
		}
		
		//first clicked zone and its neighbors cannot be bomb
		for (int yoff = -1; yoff <= 1; yoff++) {
			for (int xoff = -1; xoff <= 1; xoff++) {
				int x = zone.X + xoff;
				int y = zone.Y + yoff;
				
				if(x>-1 && x < Columns && y > -1 && y < Rows) {
					bombOptions[x][y] = false;
				}
			}
		}
		
		//loop through bombs and define bomb placement, 
		i=0;
		while (i < AmountOfBombs) {
			Random rand = new Random();
			int x = rand.nextInt(Columns);
			int y = rand.nextInt(Rows);
			
			if(bombOptions[x][y]) {
				GameGrid[getGridIndex(x,y)].IsMine = true;
				bombOptions[x][y] = false;
				i++;
			}
			
		}

		//loop through grid and define neighbor count
		for(i = 0; i < GameGrid.length; i++) {
			GameGrid[i].Neighbours = countNeighbours(i);
		}
	}
	
	private int countNeighbours(int i) {

		int total = 0;
		int x = GameGrid[i].X;
		int y = GameGrid[i].Y;
		
		//offset array
		int[][] neighbors = {
		    {-1, -1}, {-1, 0}, {-1, +1},
		    { 0, -1},          { 0, +1},
		    {+1, -1}, {+1, 0}, {+1, +1}};

		//use offset array to determine alive neighbors
		for (int[] offset : neighbors) {
			int xOff = x + offset[0];
			int yOff = y + offset[1];

			//make sure offset is not out of array bounds
			if(xOff > -1 && xOff < Columns && yOff > -1 && yOff < Rows) {
				if (GameGrid[getGridIndex(xOff, yOff)].IsMine) {
					total++;
			    }
			}

		}
		
		return total;
	}

	public void revealZones(ZoneModel zone) {
		//get clicked grid index position
		int i = zone.I;
		
		//reveal zone
		GameGrid[i].IsRevealed = true;
		System.out.println("Neighbours: " + GameGrid[i].Neighbours + " " + GameGrid[i].X + "," + GameGrid[i].Y);
		
		//reveal more zones if it doesn't have neighbor mines
		if(GameGrid[i].Neighbours == 0 && !GameGrid[i].IsRevealed) {
			revealNeighbourZones(i);
		}
		
		//update board
		setChanged();
		notifyObservers(GameGrid[i]);
	}
	private void revealNeighbourZones(int i) {
		
		for (int yoff = -1; yoff <= 1; yoff++) {
			for (int xoff = -1; xoff <= 1; xoff++) {
				int x = GameGrid[i].X + xoff;
				int y = GameGrid[i].Y + yoff;
				int index = getGridIndex(x, y);
				
				if(x>-1 && x < Columns && y > -1 && y < Rows) {
					if(!GameGrid[index].IsRevealed && !GameGrid[index].IsMine) {
						GameGrid[index].IsRevealed = true;
						setChanged();
						notifyObservers(GameGrid[index]);
						//revealZones(GameGrid[i]);
					}
				}
			}
		}
	}
	
	public void markZone(ZoneModel zone) {
		int i = zone.I;
		
		if(!GameGrid[i].IsRevealed) {
			GameGrid[i].IsMarked = true;
			GameGrid[i].IsRevealed = true;
		}
		
		//update board
		setChanged();
		notifyObservers(GameGrid[i]);

	}
	
	public void unMarkZone(ZoneModel zone) {
		int i = zone.I;
		
		if(GameGrid[i].IsRevealed) {
			GameGrid[i].IsMarked = false;
			GameGrid[i].IsRevealed = false;
			GameGrid[i].setBackground(new Color(47, 48, 52));
		}
		
		//update board
		setChanged();
		notifyObservers(GameGrid[i]);
	}
	
	public void gameOver() {
		//loop through game grid and set isRevealed true
		for(ZoneModel item : GameGrid) {
			item.IsRevealed = true;
		}
		
		//update board
		View.printGame();
	}
	
	private int getGridIndex(int xPos, int yPos) {
		int i = 0; 
		if(xPos < 0) {
			xPos = 0;
		}
		if(yPos < 0) {
			yPos = 0;
		}
		
		for(int y = 0; y < Rows; y++) {
			for (int x = 0; x < Columns; x++) {
				if(GameGrid[i].X == xPos && GameGrid[i].Y == yPos) {
					return GameGrid[i].I;
				}
				i++;
			}
		}
		
		return -1;
	}

}
