import java.util.Random;


public class LifeModel {
	
	boolean[][] grid = new boolean[20][30];
	Random random;
	
	//construct
	public LifeModel() {
		
		//create random to fill grid
		random = new Random();
		
		//fill grid with random boolean
		for (int i = 1; i < grid.length-1; i++) {
		    for (int j = 1; j < grid[0].length-1; j++) {
		    	boolean randomBool = random.nextBoolean();
		        grid[i][j] = randomBool;
		    }
		}
	    
	}
	
	private Boolean IsAlive (int row, int column) {
		if (row < 0 || row > grid.length || column < 0 || column > grid[row].length) {
			return false;
		}
		else {
			return grid[row][column];
		}
	}
	
	public void Show() {
		
		//go trough array and replace true and false with X and =
		for (int i = 0; i < grid.length; i++) {
		    for (int j = 0; j < grid[0].length; j++) {
		    	if (IsAlive(i,j)) {
		    		System.out.print("X");
		    	}
		    	else {
		    		System.out.print("=");
		    	}
		    }
		    System.out.println();
		}
	}
	
	private int CountNeighbours(int row, int column) {
		
		int aliveNeighbours = 0; 
		
		int[][] neighbours = {
			    {-1, -1}, {-1, 0}, {-1, +1},
			    { 0, -1},          { 0, +1},
			    {+1, -1}, {+1, 0}, {+1, +1}};
		
        		
        		for (int[] offset : neighbours) {
        	        if (IsAlive(row + offset[0], column + offset[1])) {
        	           aliveNeighbours++;
        	        }
        	    }
        
        return aliveNeighbours;
            
	}
	
	public void test() {
		
		Random random = new Random();
		int i = 0;
		
		while (i < 5) {
			int row = random.nextInt((20 - 0) + 1) + 0;
			int column = random.nextInt((30 - 0) + 1) + 0;
			System.out.println("row: " + row + ", column: " + column + ", neighbours: " + CountNeighbours(row, column));
			i++;
		}
		
	}
}
