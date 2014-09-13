import javax.swing.JInternalFrame;


public class EventForm extends JInternalFrame {

	EventForm() {
		super("Events", 
				true, // resizeble
				true, // closeble
				true, // maximizable
				true);// iconifiable

		setSize(400, 200);
	}
}
