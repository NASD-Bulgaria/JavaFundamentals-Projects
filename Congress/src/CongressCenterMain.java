
import java.awt.Color;

import javax.swing.JFrame;


public class CongressCenterMain {

	public static void main(String[] args) {
		CongressCenterForm mainPanel = PPanelControler.getMainPanel();
	    mainPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    mainPanel.setSize(1100, 750);
	    mainPanel.setBackground(Color.GRAY);
	    mainPanel.setVisible(true);
	}

}
