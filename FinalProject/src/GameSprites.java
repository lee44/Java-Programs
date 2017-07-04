
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class GameSprites 
{
	public static final int MOVE_SIDE_LENGTH = 1;
	public static final int GRAVITY = 7;
	public static final int TOP_RECT=0, BOTTOM_RECT=1, LEFT_RECT=2, RIGHT_RECT=3;
	private int x;
	private int y;
	private int height;
	private int width;
	private BufferedImage[] spriteImages;
	private BufferedImage activeImage;
	private Rectangle spriteRect;
	private int activeImagePosition;
	private boolean DisableCol=false,enableColBoxs=false;
	private Rectangle[] subRects = new Rectangle[5];

	public GameSprites() 
	{}
	
	public GameSprites(int x, int y, BufferedImage[] spriteImages) 
	{
		this.x = x;
		this.y = y;
		this.spriteImages = spriteImages;
		activeImage = spriteImages[0];
		width = activeImage.getWidth();
		height = activeImage.getHeight();
		spriteRect=new Rectangle();
		for (int i=0; i < 4; i++)
			subRects[i]=new Rectangle();

	}
	
	public void ToggleColBox()
	{
		if(enableColBoxs)
			enableColBoxs=false;
		else
			enableColBoxs=true;
	}
	public void draw(Graphics g) 
	{
		g.drawImage(activeImage, x, y, width, height, null);
		if (enableColBoxs)
		{		
		g.setColor(Color.red);
		g.drawRect((int)subRects[0].getX(), (int)subRects[0].getY(), (int)subRects[0].getWidth(), (int)subRects[0].getHeight());
		
		g.setColor(Color.green);
		g.drawRect((int)subRects[1].getX(), (int)subRects[1].getY(), (int)subRects[1].getWidth(), (int)subRects[1].getHeight());
		
		g.setColor(Color.blue);
		g.drawRect((int)subRects[2].getX(), (int)subRects[2].getY(), (int)subRects[2].getWidth(), (int)subRects[2].getHeight());
		
		g.setColor(Color.white);
		g.drawRect((int)subRects[3].getX(), (int)subRects[3].getY(), (int)subRects[3].getWidth(), (int)subRects[3].getHeight());
		}
	}
	
	public int getAIP()
	{
		return activeImagePosition;
	}

	public Rectangle getRect()
	{
		if(DisableCol)
		spriteRect.setRect(-100, -500, 1, 1);
		else
		{
			spriteRect.setRect(x, y, width, height);
		}
		return spriteRect;
	}
	
	public void DisableCol()
	{
		DisableCol=true;
	}
	
	public boolean isDisabledCol()
	{
		return DisableCol;
	}
	public void EnableCol()
	{
		DisableCol=false;
	}
	
	public boolean checkCollision(GameSprites object) 
	{
		spriteRect.setRect(x, y, width, height);
		if (spriteRect.intersects(object.getRect()))
		{
			return true;
		}
		return false;
	}
	
	public boolean checkCollision(int INDEX_RECT,GameSprites object) 
	{
		if (!DisableCol){
		subRects[TOP_RECT].setRect(x + (width/4), y, width/2, height/4);
		subRects[BOTTOM_RECT].setRect(x + (width/4), y+(height)-(height/4), width/2, height/4);
		subRects[LEFT_RECT].setRect(x, y+ (height/4), width/4, height/2);
		subRects[RIGHT_RECT].setRect(x+(width)-(width/4), y+(height/4), width/4, height/2);
		
		if (subRects[INDEX_RECT].intersects(object.getRect()))
		{
			return true;
		}
		}
		return false;
	}
	public int getX() 
	{
		return x;
	}

	public void setX(int x) 
	{
		this.x = x;
	}

	public int getY() 
	{
		return y;
	}

	public void setY(int y) 
	{
		this.y = y;
	}

	public int getHeight() 
	{
		return height;
	}

	public void setHeight(int height) 
	{
		this.height = height;
	}

	public int getWidth() 
	{
		return width;
	}

	public void setWidth(int width) 
	{
		this.width = width;
	}
	
	public BufferedImage[] getSpriteImages() 
	{
		return spriteImages;
	}

	public void setSpriteImages(BufferedImage[] spriteImages) 
	{
		this.spriteImages = spriteImages;
	}

	public void setActiveImage(BufferedImage activeImage) 
	{
		this.activeImage = activeImage;
	}
	
	public void setActiveImage(int arrayPosition) 
	{
		this.activeImage = spriteImages[arrayPosition];
		activeImagePosition=arrayPosition;
	}

	public int getActiveImageWidth()
	{
		return activeImage.getWidth()/2;
	}
}
