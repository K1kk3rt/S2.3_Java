import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

public class LifePanelView extends JPanel implements Observer{
	
	LifeModel model;
	private CustomButton[][] grid;
	protected JPanel panel;
	CellController cellController;

	//construct
	public LifePanelView(LifeModel model) {
		this.model = model;
		panel = new JPanel();
		panel.setLayout(new GridLayout(model.getGridRows(), model.getGridColumns()));
		
		cellController = new CellController(this, model);
		
		//create button grid array
		grid = new CustomButton[model.getGridRows()][model.getGridColumns()];
		
		//create button for each cell
		for (int i = 0; i < model.getGrid().length; i++) {
		    for (int j = 0; j < model.getGrid()[0].length; j++) {
		    	grid[i][j] = new CustomButton(i,j);
		    	grid[i][j].setMargin(new Insets(0,0,0,0));
		    	grid[i][j].addActionListener(cellController);
		    	panel.add(grid[i][j]);
		    }
		}
		
		model.addObserver(this);
		
	}

	//implement interface
	@Override
	public void update(Observable arg0, Object arg1) {
		refresh();
		
	}
	
	//methods
	private void displayCell(int row, int column) {
		if (model.IsAlive(row,column)) {
			grid[row][column].setBackground(Color.GREEN);
    	}
    	else {
    		grid[row][column].setBackground(Color.RED);
    	}
		
	}
	
	public void refresh() {
		//go trough array and replace true and false with @ and .
		for (int i = 0; i < model.getGrid().length; i++) {
		    for (int j = 0; j < model.getGrid()[0].length; j++) {
		    	displayCell(i,j);		    	
		    }
		}
	}
}
