import javax.swing.JFrame;


public class Main extends JFrame {
	
	Main(int level) {
		JFrame frame = new JFrame("Block Breaker");
		
		BlockBreakerPanel panel = new BlockBreakerPanel(level);
		frame.getContentPane().add(panel);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
		frame.setSize(490, 600);
		
		frame.setResizable(false);
	}
}
