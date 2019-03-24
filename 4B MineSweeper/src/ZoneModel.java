import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class ZoneModel extends JButton{
	
	//fields
	public boolean IsMine;
	public boolean IsRevealed;
	public boolean IsMarked;
	public int Neighbours;
	public int X;
	public int Y;
	public int I;
	
	//construct
	public ZoneModel(int x, int y, int i) {
		
		X = x;
		Y = y;
		I = i;
		
		//set defaults
		IsMine=false;
		IsRevealed=false;
		IsMarked=false;
		Neighbours = 0;
		
		//set zone properties
		setPreferredSize(new Dimension(45, 45));
		setBackground(new Color(47, 48, 52));
		setBorder(new LineBorder(Color.WHITE, 1));
		setFont(new Font("Arial", Font.BOLD, 22));
	} 

}
