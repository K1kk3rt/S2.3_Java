package nl.inholland;

import java.io.Serializable;
import java.util.Observable;
import java.util.Random;
import java.lang.StringBuffer;

public class GameModel extends Observable implements Serializable{
	
	//default serialVersion id
    private static final long serialVersionUID = 1L;
	
	
	private final int MATRIX = 4;
	private int[][] matrix;
	private boolean gameStarted;
	private boolean gameWon;
	private int correctTiles;
	private int slides;
	

	//construct
	public GameModel() {
		
		//make matrix
		matrix = new int[MATRIX][MATRIX];
		

		//set game started
		gameStarted = false;
		
		//set game won
		gameWon = false;
		
		//set correct Tiles to 0, set slides to 0
		correctTiles = 0;
		slides = 0;
		
		//prepare game
		initGame();
		shuffleTiles();
		
		//set game started
		gameStarted = true;

		//make view
		new GraphicView(this);
		
	}
	
	//getters
	public int getMATRIX() {
		return MATRIX;
	}
	public int[][] getMatrix() {
		return matrix;
	}
	public boolean getGameStarted() {
		return gameStarted;
	}
	public boolean getGameWon() {
		return gameWon;
	}
	public int getCorrectTiles() {
		return correctTiles;
	}
	public int getSlides() {
		return slides;
	}
	
	//setters
	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}
	public void setGameStarted(boolean gameStarted) {
		this.gameStarted = gameStarted;
	}
	public void setGameWon(boolean gameWon) {
		this.gameWon = gameWon;
	}
	public void setCorrectTiles(int correctTiles) {
		this.correctTiles = correctTiles;
	}
	public void setSlides(int slides) {
		this.slides = slides;
	}

	//methods
	private void initGame() {
		
		int num = 1;
		
		//save number for each tile, last tile is 0
		for (int row = 0; row<MATRIX; row++){
		     for (int col = 0; col<MATRIX; col++){
		    	 
		    	 if(row == MATRIX-1 && col == MATRIX-1) {
		    		 matrix[row][col] = 0;
		    	 }
		    	 else {
		    		 matrix[row][col] = num;		    		 
		    	 }
		    	 
		    	 num++;
		     }
		}
		
	}
	
	private void shuffleTiles() {
		//shuffle tiles by making 50 random moves
		int i = 0;
		while(i < 100) {
			Random random = new Random();
			int move = random.nextInt(4); 
			
			switch(move) {
			case 0:
				moveTileFromUp();
				break;
			case 1:
				moveTileFromLeft();
				break;
			case 2:
				moveTileFromDown();
				break;
			case 3:
				moveTileFromRight();
				break;
			}
			i++;
		}
		
		//count correct tiles
		checkCorrectTiles();
	}
	
	public void checkCorrectTiles() {
		int i = 1;
		int counter = 1;
		
		//count if tiles are in the right position
		for (int row = 0; row<MATRIX; row++){
		     for (int col = 0; col<MATRIX; col++){ 
		    	 if(matrix[row][col] == i) {
		    		 counter++;
		    	 }
		    	 
		    	 i++;
		     }
		}
		
		if (counter == 15) {
			gameWon = true;
		}
		
		correctTiles = counter;
	}
	
	public void restartGame() {
		
		//set gameStarted false so that observers don't get updated
		gameStarted = false;
		
		shuffleTiles();
		
		//make updating observers possible
		gameStarted = true;
		
		setChanged();
		notifyObservers();
	}
	
	public void moveTile(int row, int col) {
		
		//get empty tile location
		int[] emptyTiles = findEmptyTile();
		int emptyRow = emptyTiles[0];
		int emptyCol = emptyTiles[1];
		
		//find side of tile to move	
		//up
		if(emptyRow == row+1 && emptyCol == col) {
			moveTileFromUp();
		}
		else {
			//right
			if(emptyRow == row && emptyCol == col-1) {;
				moveTileFromRight();
			}
			else {
				//down
				if(emptyRow == row-1 && emptyCol == col) {
					moveTileFromDown();
				}
				else {
					//left
					if(emptyRow == row && emptyCol == col+1) {
						moveTileFromLeft();
					}
				}
			}
		}
	}
	
	public int[] findEmptyTile() {
		//empty row,column
		int[] emptyTiles = new int[2];
		
		for (int row = 0; row<MATRIX; row++){
		     for (int col = 0; col<MATRIX; col++){ 
		    	 if(matrix[row][col] == 0) {
		    		 emptyTiles[0] = row;
		    		 emptyTiles[1] = col;
		    	 }
		     }
		}
		
		return emptyTiles;
	}
	
	public void moveTileFromUp() {
		
		//get empty tile location
		int[] emptyTiles = findEmptyTile();
		int emptyRow = emptyTiles[0];
		int emptyCol = emptyTiles[1];
		
		//swap up tile to empty tile
		if(emptyRow-1 >= 0) {
			matrix[emptyRow][emptyCol] = matrix[emptyRow-1][emptyCol];
			matrix[emptyRow-1][emptyCol] = 0;			
		}
		
		if (gameStarted) {
			//add 1 to slides
			slides++;
			
			setChanged();
			notifyObservers();
		}
		
	}
	
	public void moveTileFromDown() {
			
			//get empty tile location
			int[] emptyTiles = findEmptyTile();
			int emptyRow = emptyTiles[0];
			int emptyCol = emptyTiles[1];
			
			//swap down tile to empty tile
			
			if (emptyRow+1 < MATRIX) {			
				matrix[emptyRow][emptyCol] = matrix[emptyRow+1][emptyCol];
				matrix[emptyRow+1][emptyCol] = 0;
			}
			
			if (gameStarted) {
				//add 1 to slides
				slides++;
				
				setChanged();
				notifyObservers();
			}
		}

	public void moveTileFromLeft() {
		
		//get empty tile location
		int[] emptyTiles = findEmptyTile();
		int emptyRow = emptyTiles[0];
		int emptyCol = emptyTiles[1];
		
		//swap left tile to empty tile
		if(emptyCol-1 > 0) {			
			matrix[emptyRow][emptyCol] = matrix[emptyRow][emptyCol-1];
			matrix[emptyRow][emptyCol-1] = 0;
		}
		if (gameStarted) {
			//add 1 to slides
			slides++;
			
			setChanged();
			notifyObservers();
		}
	}

	public void moveTileFromRight() {
		
		//get empty tile location
		int[] emptyTiles = findEmptyTile();
		int emptyRow = emptyTiles[0];
		int emptyCol = emptyTiles[1];
		
		//swap right tile to empty tile
		if(emptyCol+1 < MATRIX) {
			matrix[emptyRow][emptyCol] = matrix[emptyRow][emptyCol+1];
			matrix[emptyRow][emptyCol+1] = 0;			
		}
		
		if (gameStarted) {
			//add 1 to slides
			slides++;
			
			setChanged();
			notifyObservers();
		}
	}
	
	@Override
    public String toString() {
        return new StringBuffer("gameStarted: " + String.valueOf(this.gameStarted))
                .append("gameWon: " + String.valueOf(this.gameWon))
                .append("correctTiles: " + Integer.toString(this.correctTiles))
                .append("slides: " + Integer.toString(this.slides))
                .append("matrix: ").append(this.matrix).toString();
    }
	
	

}
