import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class LifeApplication {

	public static void main(String[] args) throws InterruptedException {
		
		LifeModel model = new LifeModel();
		LifeConsoleView view = new LifeConsoleView(model);
		
		Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
            	view.refresh();
            }}, 0,30);
		
		
		

	}

}
