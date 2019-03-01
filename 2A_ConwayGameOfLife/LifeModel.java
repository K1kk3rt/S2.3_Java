import java.util.Random;


public class LifeModel {
	
	boolean[][] grid = new boolean[20][30];
	Random random;
	
	//construct
	public LifeModel() {
		
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
	
	private Boolean IsAlive (int row, int column) {
		//prevent index out of bounds exception; when out of bounce return false -> when at edge of grid
		try {
			return grid [row][column];
		}
		catch(Exception e){
			return false;
		}
	}
	
	public void show() {
		//generate numbers on top
		System.out.printf("%-3s", "");
		for (int i = 0; i < grid[0].length; i++) {
			System.out.printf("%-3s", i);
		}
		System.out.println();
		
		//go trough array and replace true and false with @ and .
		for (int i = 0; i < grid.length; i++) {
			System.out.printf("%-3s", i); //numbers on the left side
		    for (int j = 0; j < grid[0].length; j++) {
		    	
		    	if (IsAlive(i,j)) {
		    		System.out.printf("%-3s", "@");
		    	}
		    	else {
		    		System.out.printf("%-3s", ".");
		    	}
		    }
		    System.out.println();
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
	
	
	public void test() {
		
		Random random = new Random();
		int i = 0;
		
		System.out.println();
		
		//print some neighbor counts to test
		while (i < 5) {
			int row = random.nextInt((20 - 0) + 1) + 0;
			int column = random.nextInt((30 - 0) + 1) + 0;
			System.out.println("row: " + row + ", column: " + column + ", neighbours: " + countNeighbors(row, column));
			i++;
		}
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
		
		//convert boolean to integer
		//int newCells = newCell ? 1 : 0;
		
		return newCell;
	}
	
	public void nextGeneration() {
		boolean[][] nextGenGrid = new boolean[20][30];
		
		//determine isAlive boolean per cell, save in temporary new grid
		for (int i = 0; i < nextGenGrid.length; i++) {
		    for (int j = 0; j < nextGenGrid[0].length; j++) {
		    	boolean isAlive = evolueer(i,j);
		    	nextGenGrid[i][j] = isAlive;
		    }
		}
		
		//copy temporary new grid to old grid
		grid = deepCopy(nextGenGrid);
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
