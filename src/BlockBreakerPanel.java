import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class BlockBreakerPanel extends JPanel implements KeyListener{
	ArrayList<Block> blocks = new ArrayList <Block>();
	ArrayList<Block> ball = new ArrayList<Block>();
	ArrayList<Block> powerup = new ArrayList<Block>();
	
	Block paddle;
	Thread thread;
	Animate animate;
	Animate1 animate1;
	Animate2 animate2;
	
	int size = 25;
	static int mainLevel;
	
	BlockBreakerPanel(int level){
		
		mainLevel = level;
		
		paddle = new Block(175, 480, 150, 25, "paddle.png");
		for(int i = 0; i < 8; i++){
			blocks.add(new Block((i*60+2), 0, 60, 25, "blue.png"));
		}
		for(int i = 0; i < 8; i++){
			blocks.add(new Block((i*60+2), 26, 60, 25, "green.png"));
		}
		for(int i = 0; i < 8; i++){
			blocks.add(new Block((i*60+2), 52, 60, 25, "green2.png"));
		}
		for(int i = 0; i < 8; i++){
			blocks.add(new Block((i*60+2), 78, 60, 25, "orange.png"));
		}
		for(int i = 0; i < 8; i++){
			blocks.add(new Block((i*60+2), 104, 60, 25, "red.png"));
		}
		Random random = new Random();
		blocks.get(random.nextInt(32)).powerup = true;
		blocks.get(random.nextInt(32)).powerup = true;
		blocks.get(random.nextInt(32)).powerup = true;
		blocks.get(random.nextInt(32)).powerup = true;
		blocks.get(random.nextInt(32)).powerup = true;
		blocks.get(random.nextInt(32)).powerup = true;
		ball.add(new Block(237,437,25,25,"ball.png"));
		addKeyListener(this);
		setFocusable(true);
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(Block b: blocks){
			b.draw(g, this);
			paddle.draw(g, this);
		}
		for(Block b: ball){
			b.draw(g, this);
		}
		for(Block p: powerup){
			p.draw(g, this);
			if(p.intersects(paddle) && !p.destroyed){
				p.destroyed = true;
				ball.add(new Block(paddle.dx+75, 437, 25,25,"ball.png"));
			}
		}
	}
	public void update(){
		for (Block p: powerup)
			p.y += 1;
		for (Block ba: ball)
		{
			ba.x += ba.dx;
			if(ba.x > (getWidth()- size) && ba.dx > 0 || ba.x < 0)		// this if is for the ball to remain inside the frame
				ba.dx *= -1;
			
			if(ba.y < 0 || ba.intersects(paddle))
				ba.dy *= -1;
			ba.y += ba.dy;
			if (ba.y > 580)
			{
				if(ball.size() <= 1)
				{
					int option = JOptionPane.showConfirmDialog(this, "Do you want to try again?", "Confirm Dialogue", JOptionPane.YES_NO_OPTION);
					
					
					if (option == 0)
					{
						this.hide();
						new firstActivity();
					}
						
						
					else
						this.hide();
					thread.stop();
				}
			}
			for (Block b: blocks){
				if((b.left.intersects(ba)||b.right.intersects(ba)) && !b.destroyed){
					ba.dx *= -1;
					b.destroyed = true;
					if(b.powerup)
						powerup.add(new Block(b.x, b.y, 25, 19, "extra.png"));
				}
				else if(ba.intersects(b)&& !b.destroyed){
					b.destroyed = true;
					ba.dy *= -1;
					if(b.powerup)
					powerup.add(new Block(b.x, b.y, 25, 19, "extra.png"));
				}
			}
		}
		repaint();
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			if(mainLevel == 1)
			{
			animate = new Animate(this);
			thread = new Thread(animate);
			thread.start();
			}
			else if(mainLevel == 2)
			{
				animate1 = new Animate1(this);
				thread = new Thread(animate1);
				thread.start();
			}
			else
			{
				animate2 = new Animate2(this);
				thread = new Thread(animate2);
				thread.start();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT && paddle.x >0){
			paddle.x -= 15;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT && paddle.x < (getWidth()-paddle.width)){
			paddle.x += 15;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
/*
 Initially when multiple balls appeared from powerups, the game would end if any of those balls exited the frame
 so i checked the size of the arraylist 'balls' and would like to end the thread only if there is one ball left
 When multiple balls appear from power ups after this was implemented, the game did not end after multiple spawning of the balls
 this is because the arraylist's size is dynamically altered from 1 to multiple occurrences of balls created.
 */
 