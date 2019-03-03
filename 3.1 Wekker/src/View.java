import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;

public class View extends JFrame{

	//construct
	public View() {
		JLabel lbl_time;
		
		//create window
		setBounds( 100, 100, 350, 350 );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setTitle( "alarm clock");
		setVisible( true );
		
		//add content
		lbl_time = new JLabel("im time");
		lbl_time.setPreferredSize(new Dimension(350, 100));
		add(lbl_time, BorderLayout.CENTER);
	}
}
