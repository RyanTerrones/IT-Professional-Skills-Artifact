package converter;
 
import javax.swing.SwingUtilities;
 
public class Main {
 
	public static void main(String[] args) {
		//run GUI on the Event Dispatch Thread
		SwingUtilities.invokeLater(() -> {
			ConverterApp app = new ConverterApp();
			app.show();
		});
	}
 
}
