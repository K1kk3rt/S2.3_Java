import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CellController implements ActionListener{
	
	LifeModel model;
	LifePanelView view;
	
	//construct
	public CellController(LifePanelView view, LifeModel model) {
		this.model = model;
		this.view = view;
	}

	//implement interface
	@Override
	public void actionPerformed(ActionEvent e) {
		
		CustomButton button = null;
		
		if (e.getSource() instanceof CustomButton) {
			button = (CustomButton) e.getSource();
		}
		
		int row = button.getRowPos();
		int column = button.getColumnPos();
		Toggle(row, column);
		
	}
	
	public void Toggle(int row, int column) {
		if (model.getGrid()[row][column]) {
			model.getGrid()[row][column] = false;
		}
		else {
			model.getGrid()[row][column] = true;
		}
		
		view.refresh();
	}

	
	
}
