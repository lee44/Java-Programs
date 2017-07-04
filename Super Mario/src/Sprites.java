import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Sprites
{
static ArrayList<BufferedImage> Mario;
static ArrayList<BufferedImage> ReverseMario;
static ArrayList<BufferedImage> brown;
static ArrayList<BufferedImage> Reversebrown;
static ArrayList<BufferedImage> turtle;
static ArrayList<BufferedImage> Reverseturtle;
private ArrayList<BufferedImage> background;
static ArrayList<BufferedImage> coins;

public Sprites() throws IOException
{
	Mario = new ArrayList<BufferedImage>();
	for(int i = 1; i < 5; i++)
	{
		Mario.add(ImageIO.read(getClass().getResource("/Sprite_Images/Mario"+i+".png")));
	}
	ReverseMario = new ArrayList<BufferedImage>();
	for(int i = 1; i < 5; i++)
	{
		ReverseMario.add(ImageIO.read(getClass().getResource("/Sprite_Images/MarioFlip"+i+".png")));
	}
	brown = new ArrayList<BufferedImage>();
	for(int i = 4; i < 7; i++)
	{
		brown.add(ImageIO.read(getClass().getResource("/Sprite_Images/Enemy"+i+".png")));
	}
	Reversebrown = new ArrayList<BufferedImage>();
	for(int i = 1; i < 4; i++)
	{
		Reversebrown.add(ImageIO.read(getClass().getResource("/Sprite_Images/Enemy"+i+".png")));
	}
	background = new ArrayList<BufferedImage>();
	background.add(ImageIO.read(getClass().getResource("/Sprite_Images/background.png")));
	
	turtle = new ArrayList<BufferedImage>();
	for(int i = 1; i < 4; i++)
	{
		turtle.add(ImageIO.read(getClass().getResource("/Sprite_Images/Koopa"+i+".png")));
	}
	Reverseturtle = new ArrayList<BufferedImage>();
	for(int i = 1; i < 4; i++)
	{
		Reverseturtle.add(ImageIO.read(getClass().getResource("/Sprite_Images/ReverseKoopa"+i+".png")));
	}
	coins = new ArrayList<BufferedImage>();
	for(int i = 1; i < 8; i++)
	{
		coins.add(ImageIO.read(getClass().getResource("/Coins/Coin_"+i+".png")));
	}
}
public static ArrayList<BufferedImage> getReverseturtle()
{
	return Reverseturtle;
}
public ArrayList<BufferedImage> getMario()
{
	return Mario;
}
public ArrayList<BufferedImage> getBrown()
{
	return brown;
}
public ArrayList<BufferedImage> getTurtle()
{
	return turtle;
}
public ArrayList<BufferedImage> getBackground()
{
	return background;
}
public ArrayList<BufferedImage> getReverseMario()
{ 
	return ReverseMario;
}
public ArrayList<BufferedImage> getReversebrown()
{
	return Reversebrown;
}

}
