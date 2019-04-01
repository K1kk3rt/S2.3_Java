import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LifeController implements ActionListener {
	LifeModel lifeModel;
	
	public LifeController (LifeModel model) {
		lifeModel = model;

	}

	//implement interface
	@Override
	public void actionPerformed(ActionEvent arg0) {
		lifeModel.nextGeneration();
		
	}
}
