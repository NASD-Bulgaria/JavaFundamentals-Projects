import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class EventForm extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	
	private JLabel name;
	private JLabel duration;
	private JLabel startDate;
	private JLabel endDate;
	private JLabel type;
	private JLabel description;
	private JLabel equipment;
	private JLabel arrangement;
	private JLabel promotionalMaterials;
	
	private JTextField eventName;
	private JTextField eventDuration;
	private JTextField eventStartDate;
	private JTextField eventEndDate;
	private JTextField eventType;
	private JTextArea eventDescription;
	//set the equipment chooser
	//set the way that u want to choose the arrangement of the hall
	//set the way to select if promotional materials are selected and the way that u want to choose them
	
	private JTable eventsTable;
	
	
	

	EventForm() {
		super("Events", 
				true, // resizeble
				true, // closeble
				true, // maximizable
				true);// iconifiable

		setLayout(null);
		name = new JLabel("Name");
		//name
		
		setSize(400, 200);
	}
}
