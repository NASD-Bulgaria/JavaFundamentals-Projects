import javax.swing.JInternalFrame;


public class AboutForm extends JInternalFrame {

	AboutForm() {
		super("About our companies", 
				true, // resizeble
				true, // closeble
				true, // maximizable
				true);// iconifiable

		setSize(200, 200);
	}
}