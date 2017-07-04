

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Background extends GameSprites implements GameEntities 
{
	private final int FAREST_LAYER = 0;
	private final int MIDDLE_LAYER = 1;
	private final int CLOSEST_LAYER = 2;
	private double farestX;
	private double middleX;
	private double cloestX;
	private BufferedImage[] backgroundImages;
	
	public Background(int x, int y, BufferedImage[] spriteImages) 
	{
		super(x, y, spriteImages);
		setY(0);
		backgroundImages = spriteImages;				
		farestX = x;
		middleX = x;
		cloestX = x;
	}

	public void jump() {}

	public void duck() {}

	@Override 
	public void draw(Graphics g)
	{
		//g.drawImage(backgroundImages[3], 0, 0, null);
		g.drawImage(backgroundImages[FAREST_LAYER], (int)farestX, getY(), null);
		g.drawImage(backgroundImages[MIDDLE_LAYER], (int)middleX, getY() + 50, null);
		g.drawImage(backgroundImages[CLOSEST_LAYER], (int)cloestX, getY() + 88, null);
	}

	@Override
	public void moveRight() 
	{ 
		middleX += GameSprites.MOVE_SIDE_LENGTH*.2;
		cloestX += GameSprites.MOVE_SIDE_LENGTH*.3;
		farestX += GameSprites.MOVE_SIDE_LENGTH*.1;
		if(farestX > 0)	
		{
			farestX=((backgroundImages[FAREST_LAYER].getWidth()/2)*-1);
		}
		if(middleX > 0)	
		{
			middleX=(backgroundImages[MIDDLE_LAYER].getWidth()/2)*-1;
		}
		if(cloestX > 0)	
		{
			cloestX= (backgroundImages[CLOSEST_LAYER].getWidth()/2)*-1;
		}
	}

	@Override
	public void moveLeft() 
	{
		middleX -= GameSprites.MOVE_SIDE_LENGTH*.2;
		cloestX -= GameSprites.MOVE_SIDE_LENGTH*.3;
		farestX -= GameSprites.MOVE_SIDE_LENGTH*.1;
		if(farestX < ((backgroundImages[FAREST_LAYER].getWidth()/2)*-1) )
		{
			farestX=(0);
		}
		if(middleX < ((backgroundImages[MIDDLE_LAYER].getWidth()/2)*-1))	
		{
			middleX=0;
		}
		if(cloestX < ((backgroundImages[CLOSEST_LAYER].getWidth()/2)*-1))	
		{
			cloestX=0;
		} 
	}

	@Override
	public void turnOnGravity() {}

	@Override
	public void moveUp() {}

}
