

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class CongressCenterForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JDesktopPane jDesktopPane;
	
	CongressCenterForm() {
		
		super("Congress Center");
		jDesktopPane = PPanelControler.getDesktopPane();
		jDesktopPane.setBackground(Color.GRAY);
		final ScheduleForm scheduleForm = new ScheduleForm();
		final AboutForm aboutForm = new AboutForm();
//da gi nabutash v handlerite tiq otgore deto sa
		
		JMenu hallMenu = new JMenu("Halls");
		JMenuItem addHall = new JMenuItem("Add");
		hallMenu.add(addHall);
		hallMenu.addSeparator();
		JMenuItem exitItem = new JMenuItem("Exit");
		hallMenu.add(exitItem);
		
		JMenu eventMenu = new JMenu("Events");
		JMenuItem addEvent = new JMenuItem("Add Event");
		eventMenu.add(addEvent);
		
		JMenu schedule = new JMenu("Schedule");
		JMenuItem showSchedule = new JMenuItem("Show Schedule");
		schedule.add(showSchedule);
		JMenuItem editSchedule= new JMenuItem("Edit schedule");
		schedule.add(editSchedule);
		

		JMenu helpMenu = new JMenu("Help");
		JMenuItem aboutMenuItem = new JMenuItem("About our companies");
		helpMenu.add(aboutMenuItem);

		JMenuBar bar = new JMenuBar();
		bar.add(hallMenu);
		bar.add(eventMenu);
		bar.add(schedule);
		bar.add(helpMenu);
		setJMenuBar(bar);
		add(jDesktopPane);
		
		addHall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PHallForm pHallForm = new PHallForm();
				PPanelControler.showPanel(pHallForm);
			}
		});
		
		addEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PEventForm eventForm = new PEventForm();
				PPanelControler.showPanel(eventForm);
			}
		});
		
		showSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!scheduleForm.isVisible()){
					jDesktopPane.add(scheduleForm);
					scheduleForm.setVisible(true);
				}
			}
		});
		
		
		aboutMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!aboutForm.isVisible()){
				jDesktopPane.add(aboutForm);
				aboutForm.setVisible(true);
				}
			}
		});
		
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
	}
}
