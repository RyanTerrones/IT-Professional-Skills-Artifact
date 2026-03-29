package converter;
 
import javax.swing.SwingUtilities;
 
public class Main {
 
	public static void main(String[] args) {
		// Run GUI on the Event Dispatch Thread - Swing best practice
		SwingUtilities.invokeLater(() -> {
			ConverterApp app = new ConverterApp();
			app.show();
		});
	}
 
}
