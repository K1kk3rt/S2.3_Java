import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;

public class LifeApplication extends JFrame{
	
	LifePanelView panelView;
	LifeModel model;
	JPanel bottomPanel;
	
	//construct
	private LifeApplication() {
		
		model = new LifeModel();
		
		//create conway grid in buttons
		panelView = new LifePanelView(model);
		 
        Timer tick = new Timer(1000, new LifeController(model));
        tick.start();
		
		createWindow();
	}
	
	//main
	public static void main(String[] args){
		new LifeApplication();	
	}
	
	//methods
	private void createWindow() {
			
		setSize(1200,800);

		//set location of window in middle of screen
		Toolkit tk = Toolkit.getDefaultToolkit();	
		Dimension dim = tk.getScreenSize();
		int xPos = (dim.width / 2) - (this.getWidth() / 2);
		int yPos = (dim.height / 2) - (this.getHeight() / 2);
		setLocation(xPos, yPos);

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Conway game of kutje");
		
		add(panelView.panel);

		setVisible(true);
	}

}
