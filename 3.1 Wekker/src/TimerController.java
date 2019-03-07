import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerController implements ActionListener {

	Model model;
	
	public TimerController(Model model) {
		this.model = model;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		model.nextMinute();
	}
	
}
