import java.util.Observable;
import java.util.Observer;

public class LifeConsoleView implements Observer {
	LifeModel lifeModel;
	
	//construct
	public LifeConsoleView(LifeModel model) {
		lifeModel = model;
		
		lifeModel.addObserver(this);
	}
	
	//implement interface
	@Override
	public void update(Observable arg0, Object arg1) {
		refresh();
		
	}
	
	//methods
	private void displayCell(int row, int column) {
		if (lifeModel.IsAlive(row,column)) {
    		System.out.printf("%-3s", "@");
    	}
    	else {
    		System.out.printf("%-3s", ".");
    	}
	}
	
	private void refresh() {
		//go trough array and replace true and false with @ and .
		for (int i = 0; i < lifeModel.getGrid().length; i++) {
		    for (int j = 0; j < lifeModel.getGrid()[0].length; j++) {
		    	displayCell(i,j);		    	
		    }
		    System.out.println();
		}
	}
}
