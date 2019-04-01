import java.util.Timer;
import java.util.TimerTask;

public class LifeApplication {

	public static void main(String[] args) throws InterruptedException {
		
		LifeModel lifeModel = new LifeModel();
		
		Timer timer = new Timer();
        timer.schedule(new TimerTask() {
	        public void run() {
	        	lifeModel.show();
	    		lifeModel.nextGeneration();
	            }}, 0,50);

	}

}
