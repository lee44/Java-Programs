import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.awt.geom.*; 
import java.awt.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JPanel;

public class Game extends JPanel implements Runnable,KeyListener
{
enum GameState
{
	Running, Dead
}
static GameState state = GameState.Running;
	
private Sprites s;
private static Background b;
private static Mario mario;
private ArrayList<Tiles> tilearray = new ArrayList<Tiles>();
private BrownEnemy e,e2,e3;
private Turtle t,t2;
private Coins c, c2, c3, c4 ,c5,c6,c7,c8,c9,c10,c11,c12,c13;
private final int SCREENHEIGHT = 410;
static int lives = 3;
private Thread gameThread,SoundThread;
private Ending_Credits credits;
private Sound sound;
private Rectangle rend = new Rectangle(0,0,0,0);
public Game() throws IOException, InterruptedException
{
	rend.setBounds(1500,410,50,50);
	e = new BrownEnemy(450);
	e2 = new BrownEnemy(1250);
	e3 = new BrownEnemy(1450);
	t = new Turtle(750);
	t2 = new Turtle(1050);
	c = new Coins(500,SCREENHEIGHT-195);c2 = new Coins(470,SCREENHEIGHT-195);c3 = new Coins(440,SCREENHEIGHT-195);c4 = new Coins(410,SCREENHEIGHT-195);
	c5 = new Coins(600,SCREENHEIGHT-195);c6 = new Coins(630,SCREENHEIGHT-195);c7 = new Coins(660,SCREENHEIGHT-195);c8 = new Coins(690,SCREENHEIGHT-195);
	c9 = new Coins(1000,SCREENHEIGHT+45);c10 = new Coins(1030,SCREENHEIGHT+45); c11 = new Coins(1060,SCREENHEIGHT+45);c12 = new Coins(1090,SCREENHEIGHT+45);
	c13 = new Coins(1120,SCREENHEIGHT+45);
	b = new Background();
	s=new Sprites();
	mario=new Mario();
		
	addKeyListener(this);
	setFocusable(true);
	loadMap("C:/Users/Lee/Desktop/Java/Super Mario/src/data/map1.txt");	
}
public void run()
{
	 sound = new Sound(getClass().getResource("/Sounds/smb2.wav"));
	 SoundThread = new Thread(sound);
	 SoundThread.start();	
	 while(state == GameState.Running)
	 {	 
		 mario.update();
		 e.update();
		 e2.update();
		 e3.update();
		 b.update();
		 updateTiles();
		 t.update();
		 t2.update();
		 c.update();c2.update();c3.update();c4.update();
		 c5.update();c6.update();c7.update();c8.update();
		 c9.update();c10.update();c11.update();c12.update();c13.update();
		 try
		 {
			 //1 second=1000 milliseconds so 1000/17milliseconds = 60 frames per second
			 Thread.sleep(17);
		 }
		 catch(InterruptedException e)
		 {
			 e.printStackTrace();
		 }
		 repaint(); 
		 
		 if(mario.getCenterY() > 580)
			 state = GameState.Dead;
		 
		 if(state == GameState.Dead)
		 {
			 SoundThread.suspend();
			 sound.stop();
			 try
			{
				Thread.sleep(5000);
			} catch (InterruptedException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 
			 credits = new Ending_Credits();
			 gameThread = new Thread(credits);
			 gameThread.start();
			 
			 Main.frame.setContentPane(credits);
			 Main.frame.invalidate();
			 Main.frame.validate(); 
		 }
	 }		 
}
public void paintComponent(Graphics g)
{
	super.paintComponent(g);
	
	g.drawImage(s.getBackground().get(0),b.getBgX(),0,getWidth()+1500,getHeight(),null);
	
	if(c.end)
		g.drawImage(c.getImage(), c.getCenterX(), c.getCenterY(),25,25,null);
	if(c2.end)
		g.drawImage(c2.getImage(), c2.getCenterX(), c2.getCenterY(),25,25,null);
	if(c3.end)
		g.drawImage(c3.getImage(), c3.getCenterX(), c3.getCenterY(),25,25,null);
	if(c4.end)
		g.drawImage(c4.getImage(), c4.getCenterX(), c4.getCenterY(),25,25,null);
	if(c5.end)
		g.drawImage(c5.getImage(), c5.getCenterX(), c5.getCenterY(),25,25,null);
	if(c6.end)
		g.drawImage(c6.getImage(), c6.getCenterX(), c6.getCenterY(),25,25,null);
	if(c7.end)
		g.drawImage(c7.getImage(), c7.getCenterX(), c7.getCenterY(),25,25,null);
	if(c8.end)
		g.drawImage(c8.getImage(), c8.getCenterX(), c8.getCenterY(),25,25,null);
	if(c9.end)
		g.drawImage(c9.getImage(), c9.getCenterX(), c9.getCenterY(),25,25,null);
	if(c10.end)
		g.drawImage(c10.getImage(), c10.getCenterX(), c10.getCenterY(),25,25,null);
	if(c11.end)
		g.drawImage(c11.getImage(), c11.getCenterX(), c11.getCenterY(),25,25,null);
	if(c12.end)
		g.drawImage(c12.getImage(), c12.getCenterX(), c12.getCenterY(),25,25,null);
	if(c13.end)
		g.drawImage(c13.getImage(), c13.getCenterX(), c13.getCenterY(),25,25,null);
			
	g.drawImage(mario.getImage(),mario.getCenterX(),(int)(getHeight()*.75)+30+mario.getCenterY(),40,40,null);
	
	for (int i = 0; i < tilearray.size(); i++)
	{
		Tiles t = (Tiles) tilearray.get(i);
		g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY(),40,40,null);
	}
	
	if(e.state == BrownEnemy.EnemyState.Alive)
		g.drawImage(e.getImage(),e.getCenterX(),(int)(getHeight()*.75)+36,35,36,null);
	if(e2.state == BrownEnemy.EnemyState.Alive)
		g.drawImage(e.getImage(),e2.getCenterX(),(int)(getHeight()*.75)+36,35,36,null);
	if(e3.state == BrownEnemy.EnemyState.Alive)
		g.drawImage(e.getImage(),e3.getCenterX(),(int)(getHeight()*.75)+36,35,36,null);
	if(t.state == Turtle.EnemyState.Alive)
		g.drawImage(t.getImage(),t.getCenterX()-15,(int)(getHeight()*.75)+22,55,56,null);
	if(t2.state == Turtle.EnemyState.Alive)
		g.drawImage(t.getImage(),t2.getCenterX()-15,(int)(getHeight()*.75)+22,55,56,null);
	
	g.setColor(Color.BLACK);
	g.setFont(new Font("Dialog",Font.BOLD,25));
	g.drawString("Coins x "+Coins.numofCoins, (int)getWidth()-150, 20);
	/*
	g.drawRect((int)rend.getX(),(int)rend.getY(),(int)rend.getWidth(),(int)rend.getHeight());
	g.setColor(Color.RED);
	g.drawRect((int)Mario.rectbot.getX(), (int)Mario.rectbot.getY(), (int)Mario.rectbot.getWidth(), (int)Mario.rectbot.getHeight());
	g.setColor(Color.BLACK);
	g.drawRect((int)Mario.recttop.getX(), (int)Mario.recttop.getY(), (int)Mario.recttop.getWidth(), (int)Mario.recttop.getHeight());
	g.setColor(Color.BLUE);
	g.drawRect((int)Mario.rectright.getX(), (int)Mario.rectright.getY(), (int)Mario.rectright.getWidth(), (int)Mario.rectright.getHeight());
	g.drawRect((int)Mario.rectleft.getX(), (int)Mario.rectleft.getY(), (int)Mario.rectleft.getWidth(), (int)Mario.rectleft.getHeight());
	g.setColor(Color.YELLOW);
	g.drawRect((int)Mario.yellowRed.getX(), (int)Mario.yellowRed.getY(), (int)Mario.yellowRed.getWidth(), (int)Mario.yellowRed.getHeight());
	//g.setColor(Color.BLACK);
	//g.drawRect((int)e.rEnemy.getX(), (int)e.rEnemy.getY(), (int)e.rEnemy.getWidth(), (int)e.rEnemy.getHeight());
	//g.drawRect((int)e2.rEnemy.getX(), (int)e2.rEnemy.getY(), (int)e2.rEnemy.getWidth(), (int)e2.rEnemy.getHeight());
	//g.drawRect((int)t.rEnemy.getCenterX()-20, (int)t.rEnemy.getY(), (int)t.rEnemy.getWidth(), (int)t.rEnemy.getHeight());
	*/
	if(lives == 0 || state ==GameState.Dead)
	{
		state = GameState.Dead;
		g.setColor(Color.black);
		g.fillRect(0, 0, 800, 580);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Dialog",Font.BOLD,30));
		g.drawString("Game Over", 325, 240);
		
		
		
	}
	Graphics2D g2d = (Graphics2D)g;
	   
    Rectangle rect1 = new Rectangle(30, -10, 30, 30);
    Rectangle rectoutline1 = new Rectangle(27, -13, 36, 36);
    Rectangle rect2 = new Rectangle(61, -40, 30, 30);
    Rectangle rectoutline2 = new Rectangle(60, -42, 36, 36);
    Rectangle rect3 = new Rectangle(90, -70, 30, 30);
    Rectangle rectoutline3 = new Rectangle(87, -73, 36, 36);
    g2d.rotate(Math.toRadians(45));
    
    g2d.setColor(Color.BLACK);g2d.draw(rectoutline1); g2d.draw(rectoutline2); g2d.draw(rectoutline3);g2d.fill(rectoutline1);g2d.fill(rectoutline2);g2d.fill(rectoutline3);
   
	
    if(lives == 3)
    {
    	 g2d.setColor(Color.WHITE);g2d.fill(rect1);g2d.draw(rect1);g2d.fill(rect2);g2d.draw(rect2);g2d.fill(rect3);g2d.draw(rect3);
    }
    else if(lives == 2)
    {
    	 g2d.setColor(Color.WHITE);g2d.fill(rect1);g2d.draw(rect1);g2d.fill(rect2);g2d.draw(rect2);
    }
    else if(lives == 1)
    {
    	g2d.setColor(Color.WHITE);g2d.fill(rect1);g2d.draw(rect1);;
    }
    
}
	public static synchronized void playSound(final URL url)
	{
		new Thread(new Runnable()
		{
			public void run()
			{
				try
				{
					Clip clip = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(url);
					clip.open(inputStream);
					clip.start();
					Thread.sleep(9000);
					clip.close();

				} 
				catch (Exception e)
				{
					System.err.println(e.getMessage());
				}
			}
		}).start();
	}
public void keyPressed(KeyEvent e)
{
	switch (e.getKeyCode()) 
	{
		case KeyEvent.VK_RIGHT:
			mario.setRight(true);
			mario.moveRight();
			break;
		case KeyEvent.VK_LEFT:
			mario.setRight(false);
			mario.moveLeft();
			break;
		case KeyEvent.VK_SPACE:
			mario.jump();
			break;
		case KeyEvent.VK_D:
			mario.setRight(true);
			mario.moveRight();
			break;
		case KeyEvent.VK_A:
			mario.setRight(false);
			mario.moveLeft();
			break;
		case KeyEvent.VK_W:
			mario.jump();
			break;
	}
}
@Override
public void keyReleased(KeyEvent e)
{
	switch (e.getKeyCode()) 
	{
		case KeyEvent.VK_RIGHT:
			mario.stop();
			break;
		case KeyEvent.VK_LEFT:
			mario.stop();
			break;
		case KeyEvent.VK_D:
			mario.stop();
			break;
		case KeyEvent.VK_A:
			mario.stop();
			break;
	}
}

public void keyTyped(KeyEvent e){}
private void updateTiles()
{
	for (int i = 0; i < tilearray.size(); i++)
	{
		Tiles t = (Tiles) tilearray.get(i);
		t.update();
	}
}
public void loadMap(String filename) throws IOException
{
	ArrayList<String> lines = new ArrayList<String>();
	int width = 0;
	int height = 0;

	BufferedReader reader = new BufferedReader(new FileReader(filename));
	while (true)
	{
		String line = reader.readLine();
		// no more lines to read
		if (line == null)
		{
			reader.close();
			break;
		}
		if (!line.startsWith("!"))
		{
			lines.add(line);
			width = Math.max(width, line.length());
		}
	}
	height = lines.size();

	for (int j = 0; j < 14; j++)
	{
		String line = (String) lines.get(j);
		for (int i = 0; i < width; i++)
		{
			if (i < line.length())
			{
				char ch = line.charAt(i);
				Tiles t = new Tiles(i, j, Character.getNumericValue(ch));
				tilearray.add(t);
			}
}}}
}