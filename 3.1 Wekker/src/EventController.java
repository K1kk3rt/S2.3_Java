import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

import javax.swing.Timer;

public class EventController implements ActionListener {
	
	View view;
	Model model;
	Timer tick;
	
	//construct
	public EventController(View view, Model model) {
		this.view = view;
		this.model = model;
		
		model.addObserver(view);
	}

	//implement ActionListener
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == view.BPlus) {
			BPlus();
		}
		if(e.getSource() == view.BMinus) {
			BMinus();
		}
		if(e.getSource() == view.BStart) {
			BStart();
		}
		if(e.getSource() == view.BStop) {
			BStop();
		}
	}
	
	//methods
	private void BStart() {
		tick = new Timer(1000, new TimerController(model));
		
		//determine color
		if(model.minutes < 0 || model.seconds < 0) {
			view.LTimer.setForeground(Color.RED);
		}
		else {
			view.LTimer.setForeground(Color.GREEN);
		}
		
        tick.start();
	}
	
	private void BStop() {
		tick.stop();
		view.LTimer.setForeground(Color.BLACK);
	}
	
	private void BMinus() {
		model.SubtractMinuteToStartTime();
	}
		
	private void BPlus() {
		model.AddMinuteToStartTime();
	}
}
