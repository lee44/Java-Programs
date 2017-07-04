

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

public class Dehkhoda extends GameSprites implements GameEntities 
{
	private static final int MAX_JUMP_HEIGHT = 75; /* Max Jump Height */
	private static final int JUMP_SPEED = 1; /* How Fast he jumps */
	public static final int SCREEN_MOVE_POSITION_RIGHT=400, SCREEN_MOVE_POSITION_LEFT=100;
	private double health = 100; /* Total Health */
	private double nextMoveImage = 0; /* Double which sets the next Image */
	private boolean onFloor = true; /* Boolean Represents if character is on the Floor */
	private boolean isJumping=false;
	private int jumpCount = 0;
	private Timer jumpTimer = new Timer(JUMP_SPEED, new ActionListener() 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			setY(getY()-2);
			jumpCount++;
			if (jumpCount > MAX_JUMP_HEIGHT)
			{
				jumpCount=0;
				stopJump();
				jumpTimer.stop();
			}
		} 
	});

	public Dehkhoda(int x, int y, BufferedImage[] spriteImages, int WINDOW_WIDTH) 
	{
		super(x, y, spriteImages);
		this.setHeight(getHeight()/2);
		this.setWidth(getWidth()/2);
	}
	
	public boolean isJumping()
	{
		return isJumping;
	}
	
	public boolean isOnFloor()
	{
		return onFloor;
	}
	
	public double getHealth() 
	{
		return health;
	}

	public void setHealth(double health) 
	{
		this.health = health;
	}
	
	public void setJumping(boolean bool)
	{
		isJumping=bool;
	}

	@Override
	public void jump() 
	{
		if (onFloor)
			{
			isJumping=true;
			onFloor=false;
			jumpTimer.start();
			}
	}

	public void stopJump()
	{
		isJumping=false;
		jumpTimer.stop();
	}
	
	public void setOnFloor(boolean onFloor) 
	{
		this.onFloor = onFloor;
	}

	@Override
	public void moveRight() 
	{
		if (getX() < SCREEN_MOVE_POSITION_RIGHT)
			setX(getX() + GameSprites.MOVE_SIDE_LENGTH);
		nextMoveImage += .16;
		if (nextMoveImage >= ArtImages.NUM_DEHKHODA_PIC-2) 
		{
			nextMoveImage = 0;
		}
		setActiveImage((int) nextMoveImage);
		this.setWidth(getActiveImageWidth());
	}

	@Override
	public void moveLeft() 
	{
		if (getX() > SCREEN_MOVE_POSITION_LEFT)
			setX(getX() - GameSprites.MOVE_SIDE_LENGTH);
		nextMoveImage -= .16;
		if (nextMoveImage <= 0) 
		{
			nextMoveImage = ArtImages.NUM_DEHKHODA_PIC - 2;
		}
		setActiveImage((int) nextMoveImage);
		this.setWidth(getActiveImageWidth());
	}

	@Override
	public void turnOnGravity() 
	{
		if (!isJumping)
			setY(getY() + GameSprites.GRAVITY/4);
	}

	@Override
	public void moveUp() 
	{
		setY(getY() - GameSprites.GRAVITY/4);
	}

}
