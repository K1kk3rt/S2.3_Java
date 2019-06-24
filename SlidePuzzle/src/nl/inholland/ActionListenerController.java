package nl.inholland;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionListenerController implements ActionListener{
	
	private GameModel game;
	private PanelView panel;
	

	public ActionListenerController(GameModel game, PanelView panel) {
		super();
		this.game = game;
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//determine clicked row and col, and move tile in gameModel
		for (int row = 0; row<game.getMATRIX(); row++){
		     for (int col = 0; col<game.getMATRIX(); col++){
		    	 if (panel.getMatrix()[row][col] == e.getSource()){
		    		 game.moveTile(row, col);
		    		 game.checkCorrectTiles();
		    	 }
			       
		     }
		}
		
	}

}
