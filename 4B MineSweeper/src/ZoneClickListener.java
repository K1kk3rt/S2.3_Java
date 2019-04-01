import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ZoneClickListener implements MouseListener {
	
	BoardGame Game;

	// construct
	public ZoneClickListener(BoardGame game) {
		this.Game = game;
	}

	public void mouseClicked(MouseEvent e) {

		// get zone from mouseEvent
		ZoneModel zone = null;

		if (e.getSource() instanceof ZoneModel) {
			zone = (ZoneModel) e.getSource();
		}

		// left click
		if (e.getButton() == MouseEvent.BUTTON1) {
			System.out.println("mine: " + zone.IsMine);
			//on first click start the game
			if(!Game.getGameIsStarted()) {
				Game.createGame(zone);
				Game.setGameIsStarted(false);
				//Game.gameOver();
			}
			
			Game.revealZones(zone);
		}
		// right click
		if (e.getButton() == MouseEvent.BUTTON3) {
			// System.out.println("Right Click!");

			if (!zone.IsMarked) {
				Game.markZone(zone);
			} else {
				Game.unMarkZone(zone);
			}

		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
