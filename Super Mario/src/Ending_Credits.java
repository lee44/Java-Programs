import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;


public class Ending_Credits extends JPanel implements Runnable
{
	private int y;
	Sound sound = new Sound(getClass().getResource("/Sounds/bird.wav"));
	private Thread thread = new Thread(sound);
	
public void paintComponent(Graphics g)
{
	super.paintComponent(g);
	
	setBackground(Color.BLACK);
	g.setColor(Color.WHITE);
	g.setFont(new Font("Dialog",Font.BOLD,20));
	g.drawString("Created By Joshua Lee", getWidth()/3, getHeight()-y);
	g.drawString("Directed By Joshua Lee", getWidth()/3, 100+getHeight()-y);
	g.drawString("Graphics Designer", getWidth()/3, 200+getHeight()-y);
	g.drawString("Joshua Lee", getWidth()/3+40, 230+getHeight()-y);
	g.drawString("Lead Programmer", getWidth()/3, 330+getHeight()-y);
	g.drawString("Joshua Lee", getWidth()/3 +40, 360+getHeight()-y);
	g.drawString("Producer ", getWidth()/3+40 , 460+getHeight()-y);
	g.drawString("Joshua Lee ", getWidth()/3+20 , 490+getHeight()-y);
}
public void run()
{
	thread.start();
	while(true)
	{
		y++;
		try
		{
			Thread.sleep(20);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		repaint();
	}
	
}
}
