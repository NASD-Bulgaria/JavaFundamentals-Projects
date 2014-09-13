import javax.swing.JInternalFrame;

public class HallForm extends JInternalFrame {

	HallForm() {
		super("Halls", 
				true, // resizeble
				true, // closeble
				true, // maximizable
				true);// iconifiable

		setSize(500, 300);
	}
}
