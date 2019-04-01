import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View implements IObserver, ActionListener{
	IObservable model;
	int hours;
	int minutes;
	
	public View(IObservable model) {
		this.model = model;
		
		model.Add(this);
	}

	//implement interface
	@Override
	public void update(int hours, int minutes) {
		this.hours = hours;
		this.minutes = minutes;
		displayClock();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		model.nextMinute();
		
	}
	
	//methods
	private void displayClock() {
		System.out.println(hours + ":" + minutes);
	}	
}
