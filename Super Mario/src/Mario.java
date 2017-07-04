import java.awt.Rectangle;
import java.awt.image.BufferedImage;

//For values that need to be altered from different classes, use static modifier
public class Mario 
{
	static int centerX,centerY,speedX,speedY,x, position;
	private boolean isRight=true;
	static boolean jumped;
	private final int SPEED = 2;
	private final int JUMPSPEED = -23;
	Background bg1;
	private Thread thread;
	private Frames frames;
	public static Rectangle rectbot = new Rectangle(0, 0, 0, 0);
	public static Rectangle recttop = new Rectangle(0, 0, 0, 0);
	public static Rectangle rectright = new Rectangle(0, 0, 0, 0);
	public static Rectangle rectleft = new Rectangle(0, 0, 0, 0);
	public static Rectangle yellowRed = new Rectangle(0, 0, 0, 0);
	
public Mario()
{
	bg1 = new Background();
	frames = new Frames();
	thread = new Thread(frames);
	thread.start();
	thread.suspend();
}
public void update() 
{
	if(speedX < 0)
		centerX += speedX;
	
	if(speedX == 0)
		bg1.setSpeed(0);
	
	if (centerX <= 200 && speedX > 0)
	{
		centerX += speedX;
	}
	
	centerY += speedY;
	speedY += 1;	
	//System.out.print("CenterY : "+centerY+ ", SpeedY: "+speedY+"\n");
	
	if (speedX > 0 && centerX > 100)
		bg1.setSpeed(-1);
	
	// Prevents going beyond X coordinate of 0
	if (centerX + speedX <= 0)
	{
		centerX = 3;
	}
	
	rectbot.setRect(centerX+5, centerY+468 , 34, 13);
	recttop.setRect(centerX+5,centerY+441,37,13);
	rectright.setRect(centerX+33,centerY+455,15,5);
	rectleft.setRect(centerX-5,centerY+455,15,5);
	yellowRed.setRect(centerX-40, 340+centerY, 180, 190);
}
public void jump()
{
	if (jumped == false)
	{
		speedY = JUMPSPEED;
		jumped = true;
	}
}
public boolean isJumped()
{
	return jumped;
}
public void setJumped(boolean j)
{
	jumped = j;
}
public BufferedImage getImage()
{
	return (isRight) ? Sprites.Mario.get(x) : Sprites.ReverseMario.get(x);
}
public void moveLeft()
{
	thread.resume();
	speedX = -SPEED;
}
public void moveRight()
{
	thread.resume();
	speedX = SPEED;
}
@SuppressWarnings("deprecation")
public void stop()
{
	speedX=0;
	thread.suspend();
}

public int getSpeedY()
{
	return speedY;
}
public void setSpeedY(int s)
{
	Mario.speedY = s;
}
public int getCenterX() 
{
	return centerX;
}

public int getCenterY() 
{
	return centerY;
}
public void setRight(boolean b)
{
	isRight=b;
}

static class Frames implements Runnable
{

@Override
public void run()
{
	while(true)
	{
	if(x==3)
		 x=0;
	x++;
	 try
	 {
		 //1 second=1000 milliseconds so 1000/17milliseconds = 60 frames per second
		 Thread.sleep(150);
	 }
	 catch(InterruptedException e)
	 {
		 e.printStackTrace();
	 }	
	}
}
}
public void setCenterY(int c )
{
	centerY=c;
	
}
}
