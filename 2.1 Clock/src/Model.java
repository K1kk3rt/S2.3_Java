import java.util.ArrayList;

public class Model implements IObservable{
	public int hours = 0;
	public int minutes = 0;
	private ArrayList<IObserver> observers;
	private boolean isChanged = false;
	
	
	//construct
	public Model() {
		//create observer list
		observers = new ArrayList<IObserver>();
	}
	
	//implement interface
	@Override
	public void Add(IObserver view) {
		observers.add(view);
	}
	@Override
	public void Notify() {
		for (IObserver item : observers) {
			item.update(hours, minutes);
		}
		
	}

	@Override
	public void SetChanged() {
		isChanged = true;
		
	}
	
	@Override
	public void nextMinute() {
		minutes++;
		
		if(minutes == 60) {
			hours++;
			minutes = 0;
		}
		
		SetChanged();
		
		if (isChanged) {Notify();}
	}
}
