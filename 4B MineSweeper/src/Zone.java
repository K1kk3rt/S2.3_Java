import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class Zone extends JButton{
	
	//fields
	private boolean IsMine;
	private boolean IsRevealed;
	private boolean IsMarked;
	private int Neighbours;
	private int X;
	private int Y;
	private int I;
	
	
	//getters
	public boolean getIsMine() {return IsMine;}
	public boolean getIsRevealed() {return IsRevealed;}
	public boolean getIsMarked() {return IsMarked;}
	public int getNeighbours() {return Neighbours;}
	public int getX() {return X;}
	public int getY() {return Y;}
	public int getI() {return I;}
	
	//setters
	public void setIsMine(boolean mine) {IsMine = mine;}
	public void setIsRevealed(boolean revealed) {IsRevealed = revealed;}
	public void setIsMarked(boolean marked) {IsMarked = marked;}
	public void setNeighbours(int neighbours) {Neighbours = neighbours;}
	public void setX(int x) {X=x;}
	public void setY(int y) {Y = y;}
	public void setI(int i) {I = i;}

	//construct
	public Zone(int x, int y, int i) {
		
		X = x;
		Y = y;
		I = i;
		
		//set defaults
		IsMine=false;
		IsRevealed=false;
		IsMarked=false;
		Neighbours = 0;
		
		//set zone properties
		setPreferredSize(new Dimension(35, 35));
		setBackground(new Color(47, 48, 52));
		setBorder(new LineBorder(Color.WHITE, 1));
		setFont(new Font("Arial", Font.BOLD, 22));
	} 

}
