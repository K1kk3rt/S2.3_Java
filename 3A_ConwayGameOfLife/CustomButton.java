import javax.swing.JButton;

public class CustomButton extends JButton{
	private int rowPos;
	private int columnPos;
	
	//getters
	public int getRowPos() {return rowPos;}
	public int getColumnPos() {return columnPos;}
	
	//construct
	public CustomButton(int row, int column) {
		rowPos = row;
		columnPos = column;
	}
}
