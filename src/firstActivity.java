import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class firstActivity extends JFrame implements ActionListener
{
	JButton novice,intermediate,expert;
	JLabel heading;

	firstActivity()
	{
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		this.setSize(490, 600);
		this.setLayout(null);
		
		this.setResizable(false);
		novice = new JButton("Novice");
		novice.setBounds(225, 250, 115, 30);
		this.add(novice);
		
		intermediate = new JButton("Intermediate");
		intermediate.setBounds(225, 290, 115, 30);
		this.add(intermediate);
		
		expert = new JButton("Expert");
		expert.setBounds(225, 330, 115, 30);
		this.add(expert);
		
		heading = new JLabel("Select your difficulty level");
		heading.setBounds(200, 210, 200, 30);
		this.add(heading);
		
		novice.addActionListener(this);
		intermediate.addActionListener(this);
		expert.addActionListener(this);
	this.setVisible(true);}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new firstActivity();
		
	}
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource()== novice)
		{	
		this.hide();
		new Main(1);
		}
		
		if (arg0.getSource()== intermediate)
		{	
		this.hide();
		new Main(2);
		}
		
		if (arg0.getSource()== expert)
		{	
		this.hide();
		new Main(3);
		}

}
}
