import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View implements IObserver, ActionListener{
	IObservable model;
	
	public View(IObservable model) {
		this.model = model;
		
		model.Add(this);
	}

	//implement interface
	@Override
	public void update() {
		actionPerformed(null);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		displayClock();
		
	}
	
	//methods
	private void displayClock() {
		System.out.println("clock");
	}	
}
