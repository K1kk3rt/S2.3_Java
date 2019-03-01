import java.util.Timer;
import java.util.TimerTask;

public class LifeApplication {

	public static void main(String[] args) throws InterruptedException {
		
		LifeModel model = new LifeModel();
		IObserver view = new LifeConsoleView(model);
		
		Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
            	model.setChanged();
            }}, 0,50);
	}

}
