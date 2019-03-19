import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ZoneClickListener implements MouseListener{
	BoardPanelView View;
	
	//construct
	public ZoneClickListener(BoardPanelView view) {
		View = view;
	}

	public void mouseClicked(MouseEvent e) {
		
		Zone zone = null;
		
		if (e.getSource() instanceof Zone) {
			zone = (Zone)e.getSource();
		}
		
		if(e.getButton() == MouseEvent.BUTTON1) {
            //System.out.println("Left Click!");
			
			//game over for clicked mine
			if(zone.IsMine && !zone.IsMarked) {
				View.gameOver();
			}
			View.revealZones(zone);
          }
          if(e.getButton() == MouseEvent.BUTTON3) {
        	  //System.out.println("Right Click!");
        	  
        	  View.markZone(zone);
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

	/*@Override
	public void actionPerformed(ActionEvent e) {
		
		Zone zone = null;
		
		if (e.getSource() instanceof Zone) {
			zone = (Zone)e.getSource();
		}
		
		
		//game over for clicked mine
		if(zone.IsMine) {
			View.gameOver();
		}
		
		View.revealZones(zone);
	}*/
}
