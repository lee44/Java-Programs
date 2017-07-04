
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;

import javax.swing.Timer;

public class JumpingEnemy extends Enemy {

	private static final int JUMP_SPEED = 80;
	private static final int MAX_JUMP_HEIGHT = 10;
	private double nextImage = 0;
	
	

	private Timer jumpTimer = new Timer(JUMP_SPEED, new ActionListener() {
		private int jumpCount = 0;
		@Override
		public void actionPerformed(ActionEvent e) {
			if(nextImage < ArtImages.NUM_JUMP_E_PIC -1){
				setActiveImage((int)nextImage);
				nextImage += .5;
			}else{
				nextImage = 0;
			}
			if (jumpCount==0)
			{
				mainChar.EnableCol();
			}
			jumpCount++;
			
			
			if(getX() < 0 || getY() > GamePanel.WINDOW_HEIGHT){
				setX((int)(GamePanel.WINDOW_WIDTH*1.5) +(int)(Math.random()*400));
				setY(0);
			}

			if (jumpCount != MAX_JUMP_HEIGHT) {
				setY(getY());
			} else {
				jumpCount = 0;
				mainChar.EnableCol();
				jumpTimer.stop();
			}

		}
	});

	
	
	public JumpingEnemy(int x, int y, BufferedImage[] spriteImages)
			throws FileNotFoundException {
		super(x, y, spriteImages);
		
		setHeight((int)(getHeight()/1.5));
		setWidth((int)(getWidth()/1.5));
		
		
		

	}
	
	private boolean floorColliding() {

		
		for (int i = 0; i < brickArray.size(); i++) {
			for (int j = 0; j < brickArray.get(i).size(); j++) {
				if (this.checkCollision(brickArray.get(i).get(j))) {
					
					setActiveImage(ArtImages.NUM_JUMP_E_PIC - 1);
					return true;
				}
			}
		}

		return false;
	}


	@Override
	public void jump() {
		
		if ((!jumpTimer.isRunning() && floorColliding() ) || checkHit()) {
			jumpTimer.start();
		}
	}
	
	public boolean checkHit(){
		boolean hitDetect=false;
		if (this.checkCollision(mainChar))
			{
			mainChar.DisableCol();
			this.moveLeft();
			hitDetect=true;
			}
		if (this.checkCollision(mainChar))
			{
			mainChar.DisableCol();
			this.moveRight();
			this.moveRight();
			hitDetect=true;
			}
		return hitDetect;
	}

	@Override
	public void moveRight() {
		//System.out.println(getX());
		setX((int) (getX() + (GameSprites.MOVE_SIDE_LENGTH))); 

	}

	@Override
	public void moveLeft() {
		setX((int) (getX() - (GameSprites.MOVE_SIDE_LENGTH)));
	}

	@Override
	public void moveUp() {
		setY(getY() - 1);
	}

	@Override
	public void turnOnGravity() {
		if (!jumpTimer.isRunning() && !floorColliding() && !checkHit()) {
			setY(getY() + 1);
			
			if(nextImage < ArtImages.NUM_JUMP_E_PIC - 1){
				setActiveImage((int)nextImage);
				nextImage += .2;
			}else{
				nextImage = 0;
			}
		}else{
			moveUp();
		}
	}

}
