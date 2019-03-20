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
		Zone zone = null;

		if (e.getSource() instanceof Zone) {
			zone = (Zone) e.getSource();
		}

		// left click
		if (e.getButton() == MouseEvent.BUTTON1) {

			// game over for clicked mine
			if (zone.getIsMine() && !zone.getIsMarked()) {
				Game.gameOver();
			}
			Game.revealZones(zone);
		}
		// right click
		if (e.getButton() == MouseEvent.BUTTON3) {
			// System.out.println("Right Click!");

			if (!zone.getIsMarked()) {
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
