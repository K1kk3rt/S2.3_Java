import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerController implements ActionListener {

	Model model;
	View view;
	
	public TimerController(Model model, View view) {
		this.model = model;
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//determine color
				if(model.minutes < 0 || model.seconds < 0) {
					view.LTimer.setForeground(Color.RED);
				}
				else {
					view.LTimer.setForeground(Color.GREEN);
				}
		
		model.nextSecond();
	}
	
}
