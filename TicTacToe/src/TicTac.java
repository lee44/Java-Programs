import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TicTac extends JFrame 
{
	JTextField f=new JTextField("Player 1 Turn");
public TicTac()
{
	JPanel p=new JPanel(new BorderLayout());
	Click c=new Click();
	Font font = new Font("Courier", Font.BOLD,20);
	
	f.setEditable(false);
	f.setFont(font);
	f.setHorizontalAlignment(JTextField.CENTER);
	p.add(f,BorderLayout.NORTH);
	p.add(c,BorderLayout.CENTER);
	add(p);
}

public static void main(String args[])
{
	TicTac frame=new TicTac();
	frame.setSize(306,365);
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);
	frame.setResizable(false);
}

class Click extends JPanel implements MouseListener
{
	boolean x=false,o=true,enabled=true;	 
	ArrayList<X> xObjects=new ArrayList<X>();
	ArrayList<Circle> c=new ArrayList<Circle>();
	int []array=new int[20];
		
public Click()
{
	for(int z=0;z<20;z++)
		array[z]=5;
	addMouseListener(this);
}
protected void paintComponent(Graphics g)
{
	super.paintComponent(g);
	
	g.drawLine(100, 0, 100, 300);
	g.drawLine(200, 0, 200, 300);
	g.drawLine(0, 100, 300,100);
	g.drawLine(0, 200, 300,200);
	
	for(int i=0;i<xObjects.size();i++)
	{
		g.drawLine(xObjects.get(i).x, xObjects.get(i).y, xObjects.get(i).x1, xObjects.get(i).y1);
		g.drawLine(xObjects.get(i).x+100, xObjects.get(i).y, xObjects.get(i).x1-100, xObjects.get(i).y1);
	}
	for(int i=0;i<c.size();i++)
	{
		g.drawOval(c.get(i).x, c.get(i).y, c.get(i).width-1, c.get(i).height-1);
	}
}
public void mouseClicked(MouseEvent e) 
{
	if(enabled)
	{
	if(e.getX()<100 && e.getY()<100)
		if(x)
		{
			xObjects.add(new X(0,0,100,100));
			array[0]=1;
		}
		else
		{
			c.add(new Circle(0,0,100,100));
			array[0]=0;
		}
	if(e.getX()>100 && e.getY()<100 && e.getX()<200)
		if(x)
		{
			xObjects.add(new X(100,0,200,100));
			array[1]=1;
		}
		else
		{
			c.add(new Circle(100,0,100,100));
			array[1]=0;
		}
	if(e.getX()>200 && e.getY()<100)
		if(x)
		{
			xObjects.add(new X(200,0,300,100));
			array[2]=1;
		}
		else
		{
			c.add(new Circle(200,0,100,100));
			array[2]=0;
		}
	//Second Row
	if(e.getX()<100 && e.getY()>100 && e.getY()<200)
		if(x)
		{
			xObjects.add(new X(0,100,100,200));
			array[3]=1;
		}
		else
		{
			c.add(new Circle(0,100,100,100));
			array[3]=0;
		}
	if(e.getX()>100 && e.getX()<200 && e.getY()>100 && e.getY()<200)
		if(x)
		{
			xObjects.add(new X(100,100,200,200));
			array[4]=1;
		}
		else
		{
			c.add(new Circle(100,100,100,100));
			array[4]=0;
		}
	if(e.getX()>200 && e.getY()>100 && e.getY()<200)
		if(x)
		{
			xObjects.add(new X(200,100,300,200));
			array[5]=1;
		}
		else
		{
			c.add(new Circle(200,100,100,100));
			array[5]=0;
		}
	//Third Row
	if(e.getX()<100 && e.getY()>200 && e.getY()<300)
		if(x)
		{
			xObjects.add(new X(0,200,100,300));
			array[6]=1;
		}
		else
		{
			c.add(new Circle(0,200,100,100));
			array[6]=0;
		}
	if(e.getX()>100 && e.getX()<200 && e.getY()>200 && e.getY()<300)
		if(x)
		{
			xObjects.add(new X(100,200,200,300));
			array[7]=1;
		}
		else
		{
			c.add(new Circle(100,200,100,100));
			array[7]=0;
		}
	if(e.getX()>200 && e.getY()>200)
		if(x)
		{
			xObjects.add(new X(200,200,300,300));
			array[8]=1;
		}
		else
		{
			c.add(new Circle(200,200,100,100));
			array[8]=0;
		}
		
	if(o)
	{
		x=true;
		o=false;
		f.setText("Player 2 Turn");
	}
	else
	{
		o=true;
		x=false;
		f.setText("Player 1 Turn");
	}
	
	if(array[0]==1 && array[1]==1 && array[2]==1)
	{
		f.setText("Player 1 Wins!!!");enabled=false;
	}
	else if(array[3]==1 && array[4]==1 && array[5]==1)
	{
		f.setText("Player 1 Wins!!!");enabled=false;
	}
	else if(array[6]==1 && array[7]==1 && array[8]==1)
	{
		f.setText("Player 1 Wins!!!");enabled=false;
	}
	else if(array[0]==1 && array[3]==1 && array[6]==1)
	{
		f.setText("Player 1 Wins!!!");enabled=false;
	}
	else if(array[1]==1 && array[4]==1 && array[7]==1)
	{
		f.setText("Player 1 Wins!!!");enabled=false;
	}
	else if(array[2]==1 && array[5]==1 && array[8]==1)
	{
		f.setText("Player 1 Wins!!!");enabled=false;
	}
	else if(array[0]==1 && array[4]==1 && array[8]==1)
	{
		f.setText("Player 1 Wins!!!");enabled=false;
	}
	else if(array[2]==1 && array[4]==1 && array[6]==1)
	{
		f.setText("Player 1 Wins!!!");
		enabled=false;
	}
	
	if(array[0]==0 && array[1]==0 && array[2]==0)
	{
		f.setText("Player 2 Wins!!!");enabled=false;
	}
	else if(array[3]==0 && array[4]==0 && array[5]==0)
	{
		f.setText("Player 2 Wins!!!");enabled=false;
	}
	else if(array[6]==0 && array[7]==0 && array[8]==0)
	{
		f.setText("Player 2 Wins!!!");enabled=false;
	}
	else if(array[0]==0 && array[3]==0 && array[6]==0)
	{
		f.setText("Player 2 Wins!!!");enabled=false;
	}
	else if(array[1]==0 && array[4]==0 && array[7]==0)
	{
		f.setText("Player 2 Wins!!!");enabled=false;
	}
	else if(array[2]==0 && array[5]==0 && array[8]==0)
	{
		f.setText("Player 2 Wins!!!");enabled=false;
	}
	else if(array[0]==0 && array[4]==0 && array[8]==0)
	{
		f.setText("Player 2 Wins!!!");enabled=false;
	}
	else if(array[2]==0 && array[5]==0 && array[6]==0)
	{
		f.setText("Player 2 Wins!!!");
		enabled=false;
	}
	 
	repaint(); 
	
}		
}

public void mouseEntered(MouseEvent e){}
public void mouseExited(MouseEvent e){}
public void mousePressed(MouseEvent e){}
public void mouseReleased(MouseEvent e){}
}
}