import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;

public class View extends JFrame implements Observer{
	
	JPanel P;
	JPanel PBottom;
	
	JLabel LTitle;
	JLabel LTimer;
	
	JButton BPlus;
	JButton BMinus;
	JButton BStart;
	JButton BStop;
	
	EventController eventController;
	Model model;
	
	
	//construct
	public View(Model model) {
		this.model = model;
		createWindow();
	}
	
	//implement observer
	@Override
	public void update(Observable arg0, Object arg1) {
		LTimer.setText(model.minutes + ":" + model.seconds);
		
	}

	private void createWindow() {
		
		setSize(400,200);
		
		//set location of window in middle of screen
		Toolkit tk = Toolkit.getDefaultToolkit();	
		Dimension dim = tk.getScreenSize();
		int xPos = (dim.width / 2) - (this.getWidth() / 2);
		int yPos = (dim.height / 2) - (this.getHeight() / 2);
		setLocation(xPos, yPos);
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Alarm clock");
		
		eventController = new EventController(this, model);
		
		//panel
		P = new JPanel(new BorderLayout());
		
		createContent();
		
		//add panel to window
		add(P);
		
		setVisible(true);
	}
	
	private void createContent() {
		
		//content for top
		LTitle = new JLabel("I'm an alarm clock :)");
		LTitle.setHorizontalAlignment(SwingConstants.CENTER);
		P.add(LTitle, BorderLayout.PAGE_START);
		
		//content for middle
		BPlus = new JButton("+");
		BPlus.addActionListener(eventController);
		BMinus = new JButton("-");
		BMinus.addActionListener(eventController);
		
		LTimer = new JLabel("press start");
		LTimer.setFont(new Font("Arial", Font.BOLD, 50));
		LTimer.setHorizontalAlignment(SwingConstants.CENTER);
		
		P.add(LTimer, BorderLayout.CENTER);
		P.add(BPlus, BorderLayout.LINE_END);
		P.add(BMinus, BorderLayout.LINE_START);
		
		//content for bottom
		PBottom = new JPanel();
		BStart = new JButton("Start");
		BStart.addActionListener(eventController);
		BStop = new JButton("Stop");
		BStop.addActionListener(eventController);
		PBottom.add(BStop);
		PBottom.add(BStart);
		
		P.add(PBottom, BorderLayout.PAGE_END);
		
	}
}
