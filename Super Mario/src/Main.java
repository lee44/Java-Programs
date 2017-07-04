import java.io.IOException;

import javax.swing.JFrame;

public class Main extends JFrame
{
	static Main frame=new Main();
	
	public static void main(String[] args) throws IOException, InterruptedException
	{	
		Game game=new Game();
		//Ending_Credits credits = new Ending_Credits();
		Thread thread=new Thread(game);
		thread.start();
		
		frame.add(game);
		frame.setSize(800,580);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
	}
}
