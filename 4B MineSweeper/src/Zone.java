import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class Zone extends JButton{
	
	//fields
	public boolean IsMine;
	public boolean IsRevealed;
	public boolean IsMarked;
	public int Neighbours;
	public int X;
	public int Y;
	
	//construct
	public Zone(int x, int y) {
		
		X = x;
		Y = y;
		
		//set defaults
		IsMine=false;
		IsRevealed=false;
		IsMarked=false;
		Neighbours = 0;
		
		//set zone properties
		setPreferredSize(new Dimension(35, 35));
		setBackground(new Color(203, 204, 206));
		setBorder(new LineBorder(Color.WHITE, 1));
		setFont(new Font("Arial", Font.BOLD, 22));
	}
}
