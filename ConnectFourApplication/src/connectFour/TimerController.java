package connectFour;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

public class TimerController implements ActionListener{
	
	private GameModel model;
	private ConnectFourGraphicView view;
	private int seconds;
	private int minutes;

	//construct
	public TimerController(GameModel model, ConnectFourGraphicView view) {
		
		this.model = model;
		this.view = view;
		seconds = 0;
		minutes = 0;
		
	}
	
	public void startTimer() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(model.getRonde() != 0) {
			seconds++;
			
			if(seconds == 60) {
				minutes++;
				seconds = 0;
			}
			
			view.setLabelTijd(minutes, seconds);
		}
		
	}

}
