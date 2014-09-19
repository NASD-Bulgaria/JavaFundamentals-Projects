
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class PPanelControler {
	
	private static ArrayList<JPanel> panels = new ArrayList<JPanel>();
	public static JDesktopPane jDesktopPane = new JDesktopPane();
	public static CongressCenterForm mainPanel = new CongressCenterForm();
	
	private static void setNewPanel(JPanel panel) {
		mainPanel.remove(jDesktopPane);
		jDesktopPane = new JDesktopPane();
		jDesktopPane.add(panel);
		jDesktopPane.setBackground(Color.GRAY);
		jDesktopPane.setVisible(true);
		panel.setVisible(true);
		panel.setSize(1000, 650);
		mainPanel.add(jDesktopPane);
		mainPanel.revalidate();
	}
	
	public static void showPanel(JPanel panel) {
		panels.clear();
		panels.add(panel);
		setNewPanel(panel);
	}
	
	public static void showNextPanel(JPanel nextPanel) {
		panels.add(nextPanel);
		setNewPanel(nextPanel);
	}
	
	public static void showPrevPanel(JPanel nextPanel) {
		panels.remove(panels.size()-1);
		setNewPanel(panels.get(panels.size()-1));
	}
	
	public static JDesktopPane getDesktopPane() {
		return jDesktopPane;
	}

	public static CongressCenterForm getMainPanel() {
		return mainPanel;
	}

}
