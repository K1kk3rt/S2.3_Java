import javax.swing.Timer;

public class LifeApplication {

	public static void main(String[] args) throws InterruptedException {
		
		LifeModel model = new LifeModel();
		IObserver view = new LifeConsoleView(model);
		
//		Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            public void run() {
//            	model.setChanged();
//            }}, 0,50);
//        
		
		 
        Timer tick = new Timer(1000, new LifeController(model));
        tick.start();
        
        while(true);
	}

}
