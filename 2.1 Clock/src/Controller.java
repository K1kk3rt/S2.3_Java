import javax.swing.Timer;

public class Controller {

	public static void main(String[] args) {
		
		IObservable model = new Model();
		IObserver view = new View(model);
		
		Timer tick = new Timer(1000, new View(model));
        tick.start();
        
        while(true);

	}

}
