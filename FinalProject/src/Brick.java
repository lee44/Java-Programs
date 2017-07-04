
import java.awt.image.BufferedImage;

public class Brick extends GameSprites
{
    public static final int YELLOW_BRICK=1;
    public static final int BLUE_BRICK=2;
    public static final int BLACK_BRICK=3;
    public static final int GREEN_BRICK=4;
    public static final int BLANK_BRICK=5;
    public static final int BRICK_HEIGHT = (int)(26/1.5);
    public static final int BRICK_WIDTH = (int)(50/1.5);

    public Brick(int x, int y,  BufferedImage[] spriteImages)
    {
    	super(x,y,spriteImages);
    	setHeight((int)(getHeight()/1.5));
    	setWidth((int)(getWidth()/1.5));
    }
    
    public Brick(){}
    
    public int getType()
    {
    	return getAIP();
    }

}
