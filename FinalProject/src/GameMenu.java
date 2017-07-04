import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameMenu extends JPanel implements Runnable
{
	private static final long serialVersionUID = 1L;
	private JLabel startLab;
	private int currentIndex = 0;
	private GamePanel gamePanel;
	private Thread gameThread;
	private JLabel stopLab;
	private boolean hasntStart = true;
	private boolean stopPressed = false;

	public GameMenu() throws IOException
	{
		setLayout(null);
		gameThread = new Thread();

		startLab = new JLabel();
		startLab.setIcon(new ImageIcon(getClass()
				.getResource("/menu/start.png")));
		startLab.setBounds(10, 233, 200, 60);
		add(startLab);
		startLab.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				try
				{
					hasntStart = false;
					gamePanel = new GamePanel();
					gameThread = new Thread(gamePanel);
					GameMenu.this.add(gamePanel);
					gamePanel.requestFocusInWindow();
					(GameMenu.this).remove(startLab);
					(GameMenu.this).remove(stopLab);
					gameThread.start();

				} catch (IOException e1)
				{
					e1.printStackTrace();
				}
			}
		});
		stopLab = new JLabel();
		stopLab.setIcon(new ImageIcon(getClass().getResource("/menu/stop.png")));
		stopLab.setBounds(450, 233, 166, 60);
		add(stopLab);
		stopLab.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				System.exit(0);
			}
		});
	}
	public void run()
	{
		try
		{
			while (!stopPressed)
			{
				while (hasntStart)
				{
					if (currentIndex < ArtImages.NUM_DEHKHODA_PIC - 1)
					{
						currentIndex++;
					} else
					{
						currentIndex = 0;
					}
					Thread.sleep(50);
				}
				if (gameThread != null && !gameThread.isAlive() && !hasntStart)
				{
					Thread.sleep(3000);
					hasntStart = true;
					remove(gamePanel);
					gamePanel = null;
					gameThread = null;
					add(startLab);
					add(stopLab);
					requestFocusInWindow();
				}
			}
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	protected void paintComponent(Graphics g)
	{

		Graphics2D imgGraphics = (Graphics2D) g;
		imgGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		super.paintComponent(g);
		super.paintComponent(imgGraphics);

		BufferedImage backGround;
		BufferedImage runningMan[];
		try
		{
			backGround = ImageIO.read(getClass().getResource(
					"/background/space_back1.jpg"));
			runningMan = ArtImages.getArtImages().getDehkhodaSprites();

			int manWidht = runningMan[currentIndex].getWidth() / 2;
			int manHeight = runningMan[currentIndex].getHeight() / 2;

			imgGraphics.drawImage(backGround, 0, 0, null);
			imgGraphics.drawImage(runningMan[currentIndex], getWidth() / 2
					- manWidht / 2, getHeight() / 2 - manHeight / 2, manWidht,
					manHeight, null);

		} catch (IOException e)
		{

			e.printStackTrace();
		}
	}

}