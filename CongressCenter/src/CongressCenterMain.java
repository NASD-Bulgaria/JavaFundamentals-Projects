import javax.swing.JFrame;


public class CongressCenterMain {

	public static void main(String[] args) {
		CongressCenterForm mainPanel = new CongressCenterForm();
	    mainPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    mainPanel.setSize(1100, 750);
	    mainPanel.setVisible(true);
	}

}
