
import java.awt.image.BufferedImage;

public class CrawlingEnemy extends Enemy 
{
	private static final int DAMAGE_DONE = 10;

	public CrawlingEnemy(int x, int y, BufferedImage[] spriteImages) 
	{
		super(x, y, spriteImages);
	}

	public int getDamageDone() 
	{
		return DAMAGE_DONE;
	}

	@Override
	public void jump() {}

	@Override
	public void moveRight() {}

	@Override
	public void moveLeft() {}

	@Override
	public void moveUp() {}

	@Override
	public void turnOnGravity() {}

}
