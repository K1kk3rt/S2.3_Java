package connectFour;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import connectFour.GameModel.status;

public class ActionListenerController implements ActionListener {
	
	private GameModel game;
	private ConnectFourPanel panel;
	
	//construct
	//geef een panel en gamemodel mee zodat properties en functies van die classes bereikt kunnen worden
	public ActionListenerController(ConnectFourPanel panel, GameModel game) {
		this.game = game;
		this.panel = panel;
	}

	//wordt uitgevoert als er op een knop gedrukt wordt.
	//als gewonnen true is, restart het spel
	//loop door het grid, wanneer we bij het vakje zijn waar op geklikt is, 
	//insert muntje in het gameModel.
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
