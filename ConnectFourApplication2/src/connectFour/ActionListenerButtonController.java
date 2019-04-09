package connectFour;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ActionListenerButtonController implements ActionListener{
	
	private GameModel game;
	private ConnectFourGraphicView view;
	
	//construct
	public ActionListenerButtonController(ConnectFourGraphicView view, GameModel game) {
		this.game = game;
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() instanceof JButton) {
			
			if(e.getSource() == view.getSaveButton()) {
				game.saveGameToFile();
			}
			else if(e.getSource() == view.getLoadButton()) {
				game.loadGameFromFile();
			}
			else if(e.getSource() == view.getResetButton()) {
				game.restartGame();
			}
			
		}
		
	}
	
}
