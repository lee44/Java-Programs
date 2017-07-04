
public class Background
{
	static int speedX;
	private static int bgX;
		
public void update()
{
	bgX += speedX;
}
public void setSpeed(int speed)
{
	speedX=speed;
}
public int getSpeed()
{
	return speedX;
}
public int getBgX()
{
	return bgX;
}
}
