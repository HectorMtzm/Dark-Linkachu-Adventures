import utility.GameFrame;

import javax.swing.WindowConstants;

public class Main {

	public static void main(String[] args) {
		GameFrame frame = new GameFrame();
		frame.setSize(960, 720);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setup();
		frame.run();
	}
}





