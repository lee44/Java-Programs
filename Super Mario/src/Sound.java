import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Sound implements Runnable
{
private URL url;
private Clip clip;

public Sound(URL url)
{
	this.url = url;
}
public void run()
{
	try
	{
		clip = AudioSystem.getClip();
		AudioInputStream inputStream = AudioSystem.getAudioInputStream(url);
		clip.open(inputStream);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		Thread.sleep(9000);
	} 
	catch (Exception e)
	{
		System.err.println(e.getMessage());
	}
	
}
public void stop()
{
	clip.stop();
}
public void close()
{
	clip.close();
}
}
