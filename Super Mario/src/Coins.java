import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Coins
{
private int centerX, centerY, x, speedX;
static int numofCoins=0; 
private Thread thread;
private Frames frames;
private Background b;
Rectangle r = new Rectangle();
boolean end = true;
public Coins(int centerX, int centerY)
{
	setCenterX(centerX);
	setCenterY(centerY);
	
	b = new Background();
	frames = new Frames();
	thread = new Thread(frames);
	thread.start();
}
public void update()
{
	centerX += speedX;
	speedX = b.getSpeed()*3;
	r.setBounds(centerX+25,centerY,25,25);
	
	if(Mario.yellowRed.intersects(r))
	{
		checkCollision();
	}
}
public void checkCollision()
{
	if((Mario.rectbot.intersects(r) || Mario.recttop.intersects(r) || Mario.rectright.intersects(r) || Mario.rectleft.intersects(r)) && end)
	{
		Game.playSound(getClass().getResource("/Sounds/coin.wav"));
		numofCoins++;
		end = false;
	}
}
public BufferedImage getImage()
{
	return Sprites.coins.get(x);
}
public int getCenterX()
{
	return centerX;
}
public int getCenterY()
{
	return centerY;
}
public void setCenterX(int centerX)
{
	this.centerX = centerX;
}
public void setCenterY(int centerY)
{
	this.centerY = centerY;
}
class Frames implements Runnable
{
	public void run()
	{
		while(true)
		{
			
		if(x==6)
			x=0;
		x++;	
		
		try
		 {
			 //1 second=1000 milliseconds so 1000/17milliseconds = 60 frames per second
			 Thread.sleep(250);
		 }
		 catch(InterruptedException e)
		 {
			 e.printStackTrace();
		 }
	}
	}
}
}
