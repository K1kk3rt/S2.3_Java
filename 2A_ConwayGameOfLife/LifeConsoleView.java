
public class LifeConsoleView {
	LifeModel lifeModel;
	
	public LifeConsoleView(LifeModel model) {
		lifeModel = model;
	}
	
	private void displayCell(int row, int column) {
		if (lifeModel.IsAlive(row,column)) {
    		System.out.printf("%-3s", "@");
    	}
    	else {
    		System.out.printf("%-3s", ".");
    	}
	}
	
	public void refresh() {
		//generate numbers on top
		//System.out.printf("%-3s", "");
//		for (int i = 0; i < lifeModel.grid[0].length; i++) {
//			System.out.printf("%-3s", i);
//		}
//		System.out.println();
		
		//go trough array and replace true and false with @ and .
		for (int i = 0; i < lifeModel.grid.length; i++) {
			//System.out.printf("%-3s", i); //numbers on the left side
		    for (int j = 0; j < lifeModel.grid[0].length; j++) {
		    	displayCell(i,j);		    	
		    }
		    System.out.println();
		}
		
		lifeModel.nextGeneration();
	}
}
