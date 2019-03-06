import java.util.ArrayList;
import java.util.Observable;

public class Model extends Observable{
	public int hours = 0;
	public int minutes = 0;
	private ArrayList<View> observers;
	private boolean isChanged = false;
	
	
	//construct
	public Model(View view) {
		//create observer list
		observers = new ArrayList<View>();
		observers.add(view);
	}
	
	//methods
	public void nextMinute() {
		minutes++;
		
		if(minutes == 60) {
			hours++;
			minutes = 0;
		}
		
		setChanged();
		
		if (isChanged) {notify();}
	}
}
