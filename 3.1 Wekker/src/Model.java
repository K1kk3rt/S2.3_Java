import java.util.Observable;

public class Model extends Observable{
	
	public int hours = 0;
	public int minutes = 0;

	
	//methods
	public void nextMinute() {
		minutes++;
		
		if(minutes == 60) {
			hours++;
			minutes = 0;
		}
		
		setChanged();
		notifyObservers();
		
	}
}
