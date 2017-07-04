import java.awt.Rectangle;

public class Enemy
{
	enum EnemyState
	{
		Alive,Dead
	}
	EnemyState state = EnemyState.Alive;
	
protected int speedX = 2, centerX, position, x;
static boolean isRight;
private Background b;
public Rectangle rEnemy = new Rectangle(0,0,0,0);
public Enemy()
{
	b = new Background();
}
public void update()
{
	if(centerX <= x + 100 + position && isRight)
		centerX += speedX + b.getSpeed()*2;
	else 
		isRight = false;
	if(centerX >= x + position && !isRight)
		centerX += -speedX+b.getSpeed()*2;
	else
		isRight = true;
	
	position += b.getSpeed()*3;
	
	rEnemy.setBounds(centerX,440,36,45);
	
	if (rEnemy.intersects(Mario.yellowRed))
	{
		checkCollision();
	}
}
public void checkCollision()
{
	
}
public int getCenterX() 
{
	return centerX;
}
//this.centerX means to assign the value centerX to the this objects centerX
//As a result, BrownEnemy or any class inheriting Enemy class will have its centerX set by this method
public void setCenterX(int centerX)
{
	this.centerX = centerX;
	x = centerX;
}
}