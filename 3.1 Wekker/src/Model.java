import java.util.Observable;

public class Model extends Observable{
	
	public int minutes = 0;
	public int seconds = 0;

	//methods
	public void nextSecond() {
		seconds++;
		
		if(seconds == 60) {
			minutes++;
			seconds = 0;
		}
		
		setChanged();
		notifyObservers();
		
	}
	
	public void setTimeToZero() {
		minutes = 0;
		seconds = 0;
		
		setChanged();
		notifyObservers();
	}
	
	public void AddMinuteToStartTime() {
		minutes++;
		
		setChanged();
		notifyObservers();
	}
	
	public void SubtractMinuteToStartTime() {
		minutes--;
		
		setChanged();
		notifyObservers();
	}
}
