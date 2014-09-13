import javax.swing.JInternalFrame;


public class ScheduleForm extends JInternalFrame {

	ScheduleForm() {
		super("Schedule", 
				true, // resizeble
				true, // closeble
				true, // maximizable
				true);// iconifiable

		setSize(700, 400);
	}
}