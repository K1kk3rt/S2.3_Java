package connectFour;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import connectFour.GameModel.status;

public class ActionListenerController implements ActionListener {
	
	GameModel game;
	ConnectFourPanel panel;
	
	//construct
	public ActionListenerController(ConnectFourPanel panel, GameModel game) {
		this.game = game;
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(game.getGewonnen()) {
			game.restartGame();
		}
		
		for (int rij = 0; rij<game.getGrid().length; rij++){
		     for (int kolom = 0; kolom<game.getGrid()[rij].length; kolom++){
		    	 if (panel.getButtonGrid()[rij][kolom] == e.getSource()){
		    		 game.insertMuntje(kolom, game.getPlayer());
		    	 }
			       
		     }
		}
	}
}
