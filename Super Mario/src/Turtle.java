import java.awt.image.BufferedImage;

public class Turtle extends Enemy
{
	private Thread thread;
	private Frames frames;
	private int y=0;
	private boolean hurt = false;

public Turtle(int centerX)
{
	frames = new Frames();
	setCenterX(centerX);
	thread = new Thread(frames);
	thread.start();
}
public void checkCollision()
{
	if(state == EnemyState.Alive)
	if(rEnemy.intersects(Mario.rectbot)&& !(rEnemy.intersects(Mario.rectright) || rEnemy.intersects(Mario.rectleft)))
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
public BufferedImage getImage()
{
	return (isRight) ? Sprites.turtle.get(y) : Sprites.Reverseturtle.get(y);
}
class Frames implements Runnable
{
	public void run()
	{
		while(true)
		{
		if(y==2)
			y=0;
		y++;	
		
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
}
