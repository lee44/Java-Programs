import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
 
public class Tiles
{
	private int tileX, tileY, speedX, type,i=0;
	public BufferedImage tileImage;
	private Background bg = new Background();
	private Rectangle r;
	private Mario mario = new Mario();
	
public Tiles(int x, int y, int t) throws IOException
{
	tileX = x * 40;
	tileY = y * 40;
	type = t;
	
	if(type == 8)
		tileImage = ImageIO.read(getClass().getResource("/Sprite_Images/grass.png"));
	if(type == 7)
		tileImage = ImageIO.read(getClass().getResource("/Sprite_Images/dirt.png"));
	if(type == 6)
		tileImage = ImageIO.read(getClass().getResource("/Sprite_Images/brick.png"));
	if(type == 5)
		tileImage = ImageIO.read(getClass().getResource("/Sprite_Images/Tiles.png"));
	if(type == 4)
		tileImage = ImageIO.read(getClass().getResource("/Sprite_Images/mystery_box.png"));
	if(type==0)
		type=0;
	
	r = new Rectangle();
}
public void update() 
{
	speedX = bg.getSpeed() * 3;
	tileX += speedX;
	
	r.setBounds(tileX, tileY, 40, 40);
    
	if (r.intersects(Mario.yellowRed) && type != 0) 
	{
		checkCollision();
	}
}
public void checkCollision()
{
	if (Mario.rectbot.intersects(r) && type == 8) 
	{
        mario.setJumped(false);
		mario.setSpeedY(0);
		mario.setCenterY(0);
    }
	if (Mario.recttop.intersects(r) && type == 4) 
	{
		mario.setCenterY(-160);
		mario.setSpeedY(-1);
		Game.state = Game.GameState.Dead;
	}
	else if(Mario.rectbot.intersects(r) && type == 4) 
	{
		mario.setJumped(false);
		mario.setSpeedY(0);
		mario.setCenterY(-r.y);
    } 
	if(Mario.rectbot.intersects(r) && type == 6) 
	{
		mario.setJumped(false);
		mario.setSpeedY(0);
		mario.setCenterY(-r.y);
    }
	else if (Mario.recttop.intersects(r) && type == 6) 
	{
		mario.setCenterY(-160);
		mario.setSpeedY(-1);
    }
	else if (Mario.rectright.intersects(r) && type == 6)
	{
		Mario.centerX = (int) r.getX() - 40;
	}
	else if (Mario.rectleft.intersects(r) && type == 6)
	{
		Mario.centerX = (int) r.getX()+40;
	}
	
	if (Mario.rectleft.intersects(r) && type == 5)
	{
		Mario.centerX = (int) r.getX()+47;
	}
	else if (Mario.rectright.intersects(r) && type == 5)
	{
		mario.stop();
		Mario.centerX = (int) r.getX()-47;
	}
	else if (Mario.rectbot.intersects(r) && type == 5)
	{
		mario.setJumped(false);
		mario.setSpeedY(0);
		Mario.centerY = (int) r.getY()-480;
	}
	else if (Mario.recttop.intersects(r) && type == 5)
	{
		Mario.centerY = (int) r.getY()-400;
		mario.setSpeedY(-1);
	}
}
public BufferedImage getTileImage()
{
	return tileImage;
}
public int getTileX()
{
	// TODO Auto-generated method stub
	return tileX;
}
public int getTileY()
{
	// TODO Auto-generated method stub
	return tileY;
}
}
