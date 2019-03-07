import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class EventController implements ActionListener {
	
	View view;
	Model model;
	
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
			System.out.println("BPLUS PRESSED YA BOI");
		}
		if(e.getSource() == view.BMinus) {
			System.out.println("BPLUS PRESSED YA BOI");
		}
		if(e.getSource() == view.BStart) {
			model.nextMinute();
		}
		if(e.getSource() == view.BStop) {
			System.out.println("BPLUS PRESSED YA BOI");
		}
	}
}
