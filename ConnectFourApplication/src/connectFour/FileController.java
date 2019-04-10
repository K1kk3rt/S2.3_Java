package connectFour;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class FileController {
	
	private GameModel game;
	private TimerController timer;
	private final String SAVEGAMEFILE = "connectfour.txt";
	
	//construct
	public FileController(GameModel game, TimerController timer) {
		this.game = game;
		this.timer = timer;
		
	}
	
	//getters
	
	
	//methods
	public void saveGameToFile() {
		int[][] intGrid = game.convertEnumGridToIntGrid(game.getGrid());
		
		try {
			BufferedWriter outputWriter = new BufferedWriter(new FileWriter(SAVEGAMEFILE));
			
			for (int rij = 0; rij<game.getGrid().length; rij++){
				for (int kolom = 0; kolom<game.getGrid()[rij].length; kolom++){
					outputWriter.write(Integer.toString(intGrid[rij][kolom]) + " ");
				}
				outputWriter.newLine();
			}
			
			//save ronde in file
			outputWriter.newLine();
			outputWriter.write(Integer.toString(game.getRonde()));
			
			//save minutes in file
			outputWriter.newLine();
			outputWriter.write(Integer.toString(timer.getMinutes()));
			
			//save seconds in file
			outputWriter.newLine();
			outputWriter.write(Integer.toString(timer.getSeconds()));
			
			outputWriter.flush();  
			outputWriter.close(); 
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public boolean checkIfSaveGameExists() {
		boolean exists = false;
		
		File f = new File(SAVEGAMEFILE);
		if(f.exists() && !f.isDirectory()) { 
		    exists = true;
		}
		
		return exists;
	}
	
		public void loadGameFromFile() {
				
				try {
					int[][] a = new int[game.getRijen()][game.getKolommen()];
					Scanner input = new Scanner(new File(SAVEGAMEFILE));
					for(int rij = 0; rij < game.getRijen(); rij++)
					{
					    for(int kolom = 0; kolom < game.getKolommen(); kolom++)
					    {
					        if(input.hasNextInt())
					        {
					            a[rij][kolom] = input.nextInt();
					        }
					    }
					}
					
					//lees ronde from file
					if(input.hasNextInt()) {
						game.setRonde(input.nextInt());
					}
					
					//lees seconden from file
					if(input.hasNextInt()) {
						timer.setSeconds(input.nextInt());
					}
					
					//lees minuten from file
					if(input.hasNextInt()) {
						timer.setMinutes(input.nextInt());
					}
					
					game.convertIntGridToGameGrid(a);
					
					input.close();
				}
				catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}
}
