import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class LifeApplication {

	public static void main(String[] args) throws InterruptedException {
		
		LifeModel lifeModel = new LifeModel();
		
		Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
            	lifeModel.show();
    			lifeModel.nextGeneration();
            }}, 0,50);
		
		
//		int i = 0;
//		while(i<50) {
//			lifeModel.show();
//			lifeModel.nextGeneration();
//			
//			//wait 4 seconds
//			TimeUnit.SECONDS.sleep(4);
//			
//			i++;
//		}

	}

}
