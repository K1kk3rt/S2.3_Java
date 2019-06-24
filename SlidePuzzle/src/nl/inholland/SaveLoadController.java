package nl.inholland;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveLoadController {

	private GameModel game;
	private GraphicView graphicView;
	private PanelView panelView;
	private final String FILE = "slidePuzzleGame.dat";
	
	public SaveLoadController(GameModel game, GraphicView GraphicView, PanelView panelView) {
		this.game = game;
		this.graphicView = graphicView;
		this.panelView = panelView;
	}
	
	public void saveGame() {
		try{
			ObjectOutputStream uit = new ObjectOutputStream(new FileOutputStream(FILE));
			uit.writeObject(game);
			uit.close();
            System.out.println("The Object  was succesfully written to a file");
		}
		catch( IOException e ) 
		{ 
			e.printStackTrace();
		} 
	}
	
	public void loadGame() {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE));
			GameModel loadedGame = (GameModel) in.readObject();
			in.close();
			
			//deep copy loaded object to current object
			game.setMatrix(loadedGame.getMatrix());
			game.setGameStarted(loadedGame.getGameStarted());
			game.setGameWon(loadedGame.getGameWon());
			game.setCorrectTiles(loadedGame.getCorrectTiles());
			game.setSlides(loadedGame.getSlides());
			
			//update view
			panelView.loadImages();			
			
		}catch(IOException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkIfSaveGameExists() {
		boolean exists = false;
				
		File f = new File(FILE);
		if(f.exists() && !f.isDirectory()) { 
		    exists = true;
		}
		
		return exists;
	}
	
	
}
