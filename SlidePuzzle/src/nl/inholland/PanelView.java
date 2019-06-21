package nl.inholland;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelView extends JPanel implements Observer{

	private GameModel game;
	private GraphicView view;
	
	private final int BUTTONSIZE = 270;
	
	private JButton[][] matrix;

	public PanelView(GameModel game, GraphicView view) {
		this.game = game;
		this.view = view;
		
		//set layout
		setLayout(new GridLayout(game.getMATRIX(), game.getMATRIX()));
		
		matrix = new JButton[game.getMATRIX()][game.getMATRIX()];
		
		initGame();
		loadImages();
		
		game.addObserver(this);
	}
	
	//getters
	public int getButtonSize(){
		return BUTTONSIZE;
	}
	public JButton[][] getMatrix(){
		return matrix;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		loadImages();
		
		if(game.checkGameWon()) {
			view.showDialogWon();
		}
		
	}
	
	private void initGame() {
		
		int length = game.getMATRIX();
		
		//create new button, set actionListener for clicks and add to panel
		for (int row = 0; row<length; row++){
		     for (int col = 0; col<length; col++){
		    	 matrix[row][col] = new JButton();
		    	 matrix[row][col].addActionListener(new ActionListenerController(this.game, this));
		    	 add(matrix[row][col]);
		     }
		}
	}
	
	private void loadImages() {
		try {
			int[][] numMatrix = game.getMatrix();
			int length = game.getMATRIX();
			
			//get images based off the int[][] matrix from gameModel, and put them on buttons
			for (int row = 0; row<length; row++){
			     for (int col = 0; col<length; col++) {
			    	 int tile = numMatrix[row][col];
			    	 BufferedImage image;
			    	 if(tile == 0) {
			    		 image = ImageIO.read(getClass().getResource("/assets/imageEmpty.png"));
			    	 }
			    	 else {
			    		 image = ImageIO.read(getClass().getResource("/assets/image"+ tile +".png"));
			    	 }
			    	 matrix[row][col].setBorder(null);
			    	 matrix[row][col].setIcon(new ImageIcon(image));
			     }
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	
}
