
public interface IObservable {
	void Add(IObserver view);
	void Notify();
	void SetChanged();
}
