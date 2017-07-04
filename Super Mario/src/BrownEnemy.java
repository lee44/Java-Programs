import java.awt.image.BufferedImage;


public class BrownEnemy extends Enemy
{
private Thread thread;
private Frames frames;
private int x=0;
private boolean hurt = false;
public BrownEnemy(int centerX)
{
	frames = new Frames();
	thread = new Thread(frames);
	setCenterX(centerX);
	thread.start();
}
public void checkCollision()
{
	if(state == EnemyState.Alive)
	{
	if(rEnemy.intersects(Mario.rectbot) && !(rEnemy.intersects(Mario.rectright) || rEnemy.intersects(Mario.rectleft)))
	{
		state = EnemyState.Dead;
		hurt=true;
	}	
	else if((rEnemy.intersects(Mario.rectright) || rEnemy.intersects(Mario.rectleft)) && !hurt )
	{
		hurt = true;
		Game.lives--;
	}
	if(!(rEnemy.intersects(Mario.rectright) || rEnemy.intersects(Mario.rectleft)))
	{
		hurt = false;
	}
	}
}
public BufferedImage getImage()
{
	return (isRight) ? Sprites.brown.get(x) : Sprites.Reversebrown.get(x);
}
class Frames implements Runnable
{
	public void run()
	{
		while(true)
		{
			
		if(x==2)
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
