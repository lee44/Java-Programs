
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Enemy extends GameSprites implements GameEntities 
{

	protected final int JUMP_HEIGHT = 0;
	protected final int MAX_RIGHT = 0;
	protected final int MAX_LEFT = 0;
	protected final int MOVE_RIGHT_IMAGE = 0;
	protected final int MOVE_LEFT_IMAGE = 0;
	protected final int JUMP_IMAGE = 0;
	protected Dehkhoda mainChar;
	protected ArrayList<ArrayList<Brick>> brickArray;

	public Enemy(int x, int y, BufferedImage[] spriteImages) 
	{
		super(x, y, spriteImages);		
	}
	
	public void setBrickArray(ArrayList<ArrayList<Brick>> brickArray){
		this.brickArray = brickArray;
	}
	
	public void setMainChar(Dehkhoda mainChar)
	{
		this.mainChar = mainChar;
	}
}