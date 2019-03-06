import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;
import java.util.Observable;

public class View extends JFrame{
	JPanel P;
	JPanel PBottom;
	
	JLabel LTitle;
	JLabel LTimer;
	
	JButton BPlus;
	JButton BMinus;
	JButton BStart;
	JButton BStop;
	
	
	//construct
	public View() {
			
		createWindow();
	}
	
	private void createWindow() {
		
		this.setSize(400,200);
		
		//set location of window in middle of screen
		Toolkit tk = Toolkit.getDefaultToolkit();	
		Dimension dim = tk.getScreenSize();
		int xPos = (dim.width / 2) - (this.getWidth() / 2);
		int yPos = (dim.height / 2) - (this.getHeight() / 2);
		setLocation(xPos, yPos);
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Alarm clock");
		
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
		BMinus = new JButton("-");
		BPlus.addActionListener(new Model());
		
		LTimer = new JLabel("I'm the timer!");
		LTimer.setHorizontalAlignment(SwingConstants.CENTER);
		
		P.add(LTimer, BorderLayout.CENTER);
		P.add(BPlus, BorderLayout.LINE_END);
		P.add(BMinus, BorderLayout.LINE_START);
		
		//content for bottom
		PBottom = new JPanel();
		BStart = new JButton("Start");
		BStop = new JButton("Stop");
		PBottom.add(BStop);
		PBottom.add(BStart);
		
		P.add(PBottom, BorderLayout.PAGE_END);
		
	}
}
