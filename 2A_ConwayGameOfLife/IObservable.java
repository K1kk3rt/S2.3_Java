
public interface IObservable {
	void setChanged();
	void notifyObservers();
	void addObserver(LifeConsoleView view);
}
