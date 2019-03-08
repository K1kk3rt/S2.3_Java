import java.util.Observable;
import java.util.Random;


public class LifeModel extends Observable {
	
	//attributes
	private boolean[][] grid;
	private Random random;
	private int gridRows=29;
	private int gridColumns=30; 
	
	//getters
	protected int getGridRows() {
		return gridRows;
	}

	protected int getGridColumns() {
		return gridColumns;
	}
	protected boolean[][] getGrid() {
		return grid;
	}

	//construct
	public LifeModel() {
		//create grid
		grid = new boolean[getGridRows()][getGridColumns()];
		
		//create random to fill grid
		random = new Random();
		
		//fill grid with random boolean, all edges are false
		for (int i = 1; i < grid.length-1; i++) {
		    for (int j = 1; j < grid[0].length-1; j++) {
		    	boolean randomBool = random.nextBoolean();
		        grid[i][j] = randomBool;
		    }
		} 
	}
	
	//methods
	public Boolean IsAlive (int row, int column) {
		//prevent index out of bounds exception; when out of bounce return false -> when at edge of grid
		try {
			return grid [row][column];
		}
		catch(Exception e){
			return false;
		}
	}
	
	private int countNeighbors(int row, int column) {
		
		int aliveNeighbors = 0; 
		
		//offset array
		int[][] neighbors = {
			    {-1, -1}, {-1, 0}, {-1, +1},
			    { 0, -1},          { 0, +1},
			    {+1, -1}, {+1, 0}, {+1, +1}};
		
        //use offset array to determine alive neighbors
		for (int[] offset : neighbors) {
			if (IsAlive(row + offset[0], column + offset[1])) {
				aliveNeighbors++;
        	}
        }
        
        return aliveNeighbors; 
	}
	
	private boolean evolueer(int row, int column) {
		boolean newCell = false;
		
		if(IsAlive(row, column)) {
			//every alive cell with 2 or 3 neighbors stays alive
			if(countNeighbors(row, column) == 2 || countNeighbors(row, column) == 3) {
				newCell = true;
			}
			else {
				newCell = false;
			}
		}
		if(!IsAlive(row, column)) {
			//every alive cell with 3 neighbors will live
			if(countNeighbors(row, column) == 3) {
				newCell = true;
			}
			else {
				newCell = false;
			}
		}
		
		return newCell;
	}
	
	public void nextGeneration() {
		boolean[][] nextGenGrid = new boolean[getGridRows()][getGridColumns()];
		
		//determine isAlive boolean per cell, save in temporary new grid
		for (int i = 0; i < nextGenGrid.length; i++) {
		    for (int j = 0; j < nextGenGrid[0].length; j++) {
		    	boolean isAlive = evolueer(i,j);
		    	nextGenGrid[i][j] = isAlive;
		    }
		}
		
		//copy temporary new grid to old grid
		grid = deepCopy(nextGenGrid);
		
		setChanged();
		notifyObservers();
	}
	
	private boolean[][] deepCopy(boolean[][] A) {
	    boolean[][] B = new boolean[A.length][A[0].length];
	    
	    //deep copy boolean grid by iterating trough it
	    for (int x = 0; x < A.length; x++) {
	      for (int y = 0; y < A[0].length; y++) {
	        if (A[x][y])//write only when necessary
	        {
	          B[x][y] = A[x][y];
	        }
	      }
	    }
	    return B;
	  }	
}
