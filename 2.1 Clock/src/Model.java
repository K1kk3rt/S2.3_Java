import java.util.ArrayList;

public class Model implements IObservable{
	int hours = 0;
	int minutes = 0;
	ArrayList<IObserver> observers;
	
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void SetChanged() {
		// TODO Auto-generated method stub
		
	}
	
	//methods
	private void nextMinute() {
		minutes++;
		
		if(minutes == 60) {
			hours++;
			minutes = 0;
		}
	}
}
