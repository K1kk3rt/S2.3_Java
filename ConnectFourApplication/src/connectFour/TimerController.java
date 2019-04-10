package connectFour;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

public class TimerController implements ActionListener{
	
	private GameModel model;
	private ConnectFourGraphicView view;
	private int seconds;
	private int minutes;
	
	//getters
	public int getSeconds() {
		return seconds;
	}
	public int getMinutes() {
		return minutes;
	}
	
	//setters
	public void setSeconds(int s) {
		seconds = s;
	}
	public void setMinutes(int m) {
		minutes = m;
	}

	//construct
	public TimerController(GameModel model, ConnectFourGraphicView view) {
		
		this.model = model;
		this.view = view;
		seconds = 0;
		minutes = 0;
		
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
