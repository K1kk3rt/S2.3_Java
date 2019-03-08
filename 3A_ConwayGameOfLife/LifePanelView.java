import javax.swing.JButton;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

public class LifePanelView extends JPanel implements Observer{
	
	LifeModel model;
	private JButton[][] grid;
	protected JPanel panel;

	//construct
	public LifePanelView(LifeModel model) {
		this.model = model;
		panel = new JPanel();
		panel.setLayout(new GridLayout(model.getGridRows(), model.getGridColumns()));
		
		//create button grid array
		grid = new JButton[model.getGridRows()][model.getGridColumns()];
		
		//create button for each cell
		for (int i = 1; i < model.getGrid().length-1; i++) {
		    for (int j = 1; j < model.getGrid()[0].length-1; j++) {
		    	JButton button = new JButton();
		    	grid[i][j] = button;
		    	grid[i][j].setMargin(new Insets(0,0,0,0)); 
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
			JButton button = new JButton();
			button = grid[row][column];
			button.setBackground(Color.GREEN);
    	}
    	else {
    		JButton button = new JButton();
    		button = grid[row][column];
			button.setBackground(Color.RED);
    	}
		
	}
	
	private void refresh() {
		//go trough array and replace true and false with @ and .
		for (int i = 0; i < model.getGrid().length; i++) {
		    for (int j = 0; j < model.getGrid()[0].length; j++) {
		    	displayCell(i,j);		    	
		    }
		}
	}
}
