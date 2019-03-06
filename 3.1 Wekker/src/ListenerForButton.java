import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerForButton implements ActionListener{
	Model model;
	
	public ListenerForButton(View view) {
		model = new Model(view);
	}
	
	
	//implement actionListener
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == "BPlus") {
			model.nextMinute();
		}
	}
}
