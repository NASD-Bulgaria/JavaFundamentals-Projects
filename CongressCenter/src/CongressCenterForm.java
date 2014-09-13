
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class CongressCenterForm extends JFrame {

	private JDesktopPane jDesktopPane;
	
	CongressCenterForm() {
		super("Congress Center");
		jDesktopPane = new JDesktopPane();
		final HallForm hallForm = new HallForm();
		final EventForm eventForm = new EventForm();
		final ScheduleForm scheduleForm = new ScheduleForm();
		final AboutForm aboutForm = new AboutForm();
		
		JMenu fileMenu = new JMenu("File");

		JMenuItem hallsMenuItem = new JMenuItem("Halls");
		JMenuItem eventsMenuItem = new JMenuItem("Events");
		JMenuItem scheduleMenuItem = new JMenuItem("Schedule");

		fileMenu.add(hallsMenuItem);
		fileMenu.add(eventsMenuItem);
		fileMenu.add(scheduleMenuItem);
		fileMenu.addSeparator();
		JMenuItem exitItem = new JMenuItem("Exit");
		fileMenu.add(exitItem);

		JMenu helpMenu = new JMenu("Help");
		JMenuItem aboutMenuItem = new JMenuItem("About our companies");
		helpMenu.add(aboutMenuItem);

		JMenuBar bar = new JMenuBar();
		bar.add(fileMenu);
		bar.add(helpMenu);
		setJMenuBar(bar);
		add(jDesktopPane);
		
		hallsMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!hallForm.isVisible()){
				jDesktopPane.add(hallForm);
				hallForm.setVisible(true);
				}
			}
		});
		
		eventsMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!eventForm.isVisible()){
				jDesktopPane.add(eventForm);
				eventForm.setVisible(true);
				}
			}
		});
		
		scheduleMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
