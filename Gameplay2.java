package Brickbreaker;
import java.util.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.Timer;
@SuppressWarnings({ "unused", "serial"})
public class Gameplay2 extends JPanel implements KeyListener, ActionListener 
{
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int x = screenSize.width;
	private int y = screenSize.height;
	private boolean play = false;
	private boolean win = false;
	private int score = 0;
	private int highscore=-1;
	private int xb;
	private int yb;
	int totalBricks;
	private Timer timer;
	private int delay;
	private int playerX = x/2-100;
	private int ballposX = x/2;
	private int ballposY = y/2;
	private int ballXdir = -1;
	private int ballYdir = 2;
	private MapGenerator map;
	private int count = 0;
	private int k=0;
	private int c=0;
	Main m;
	Graphics2D g;

	public Gameplay2(int x, int y, int d, Main m1)
	{		
		m = m1;
		xb=x;
		yb=y;
		totalBricks=xb*yb;
		delay=d;
		map=new MapGenerator(xb,yb);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer=new Timer(delay,this);
		timer.start();

	}
	public int getHighScore()
	{
		BufferedReader reader=null;
		try 
		{
			reader = new BufferedReader(new FileReader("highscore2.dat"));
			String line = reader.readLine();
			//System.out.println(line);
			return Integer.parseInt(line);
		}
		catch(Exception e)
		{
			return 0;
		}
		finally
		{
			try 
			{
				if(reader!=null)
					reader.close();
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void checkScore()
	{
		BufferedWriter output=null;
		if(score>highscore)
		{
			highscore=score;
			try {
				output = new BufferedWriter(new FileWriter("highscore2.dat"));
				output.write(Integer.toString(highscore));
				output.close();

			} 
			catch (IOException ex1) {
				System.out.println("ERROR writing score to file: %s\n"+ex1);
			}
			finally
			{
				try 
				{
					if(output!=null)
						output.close();
				} 
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}
	public void paint(Graphics g)
	{    		
		// background
		x=screenSize.width;
		y=screenSize.height;
		g.setColor(Color.black);
		g.fillRect(1, 1, x, y);	
		// drawing map
		map.draw((Graphics2D) g, 0);
		// borders
		g.setColor(Color.green);
		g.fillRect(0, 0, 3, y);
		g.fillRect(0, 0, x, 3);
		g.fillRect(x-3, 0, 3, y);
		// the scores 		
		g.setColor(Color.white);
		g.setFont(new Font("serif",Font.BOLD, 25));
		g.drawString(""+score, 9*x/10,30);
		// the paddle
		g.setColor(Color.green);
		g.fillRect(playerX, 9*y/10, 100, 8);
		// the ball
		g.setColor(Color.yellow);
		g.fillOval(ballposX, ballposY, 20, 20);
		if(highscore<0)
			highscore=0;
		else
			highscore=getHighScore();
		//System.out.println(highscore);
		// when you won the game
		if(totalBricks <= 0)
		{
			if(count>1) {
				g.setColor(Color.GREEN);
				g.setFont(new Font("serif",Font.BOLD, 30));
				g.drawString("Congratulations. Game Over", x/3,(int)(y/2.1));
				g.setColor(Color.GREEN);
				g.setFont(new Font("serif",Font.BOLD, 30));
				g.drawString("You're score: "+score, x/3,y/2);
				//JOptionPane.showMessageDialog(null, "Score:"+score+"\nGame Over");
			}
			else {
				play = false;
				ballXdir = 0;
				ballYdir = 0;
				g.setColor(Color.RED);
				g.setFont(new Font("serif",Font.BOLD, 30));
				g.drawString("You Won", x/3,y/2);
				g.setColor(Color.GREEN);
				g.setFont(new Font("serif",Font.BOLD, 30));
				g.drawString("Score = "+score, x/3,(int)(y/1.8));
				g.setColor(Color.GREEN);
				g.setFont(new Font("serif",Font.BOLD, 20));           
				g.drawString("Press (Space) to Main Menu", x/3, (int)(y/1.7));
				win = true;
				//count++;
				checkScore();
				if(score==highscore)
					g.drawString("New Highscore: "+score, x/3, (int)(y/1.8));
				else
					g.drawString("Highscore: "+highscore, x/3, (int)(y/1.8));
			}
			m.a.dispose();
			m.obj.setVisible(true);
		}
		// when you lose the game
		if(ballposY > y)
		{
			play = false;
			win = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.GREEN);
			g.setFont(new Font("serif",Font.BOLD, 30));
			g.drawString("Game Over, Score: "+score, x/3,(int)(y/2.1));    
			g.setColor(Color.GREEN);
			g.setFont(new Font("serif",Font.BOLD, 20));

			g.drawString("Press (Enter) to Restart", x/3, (int)(y/1.8));
			g.setColor(Color.GREEN);
			g.setFont(new Font("serif",Font.BOLD, 20));           
			g.drawString("Press (Space) to Main menu", x/3, (int)(y/1.7));            
			count = 0;
			checkScore();
			if(score==highscore)
				g.drawString("New Highscore: "+score, x/3, (int)(y/1.9));
			else
				g.drawString("Highscore: "+highscore, x/3, (int)(y/1.9));
		}

		g.dispose();
	}
	public void keyPressed(KeyEvent e) 
	{
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{        
			if(playerX > 7*x/8)
			{
				playerX = 7*x/8;
			}
			else
			{
				moveRight();
			}
		}	
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{          
			if(playerX < 10)
			{
				playerX = 10;
			}
			else
			{
				moveLeft();
			}
		}		
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{          
			if(!play)
			{
				if(!win) {
					score = 0;
					play = true;
					ballposX = x/2;
					ballposY = y/2;
					ballXdir = -1;
					ballYdir = 2;
					playerX = x/2-100;
					totalBricks = xb*yb;
					map = new MapGenerator(xb,yb);	
					repaint();
				}
			}

		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			//repaint();
			m.a.dispose();
			m.obj.dispose();
			m.obj.setVisible(true);

		}
	}
	public void keyReleased(KeyEvent e) {
		play = true;
		playerX+=0;
	}
	public void keyTyped(KeyEvent e) {}
	public void moveRight()
	{
		play = true;
		playerX+=70;	
	}
	public void moveLeft()
	{
		play = true;
		playerX-=70;	 	
	}
	public void actionPerformed(ActionEvent e) 
	{
		timer.start();
		if(play)
		{	
			if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 9*y/10, 30, 8)))
			{
				ballYdir = -ballYdir;
				if(ballXdir>0)
					ballXdir = -ballXdir;
			}
			else if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX + 30, 9*y/10, 40, 8)))
			{
				ballYdir = -ballYdir;

			}
			else if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX + 70, 9*y/10, 30, 8)))
			{
				ballYdir = -ballYdir;
				if(ballXdir<0)
					ballXdir = -ballXdir;
			}
			// check map collision with the ball		
			A: for(int i = 0; i<map.map.length; i++)
			{
				for(int j =0; j<map.map[0].length; j++)
				{				
					if(map.map[i][j] > 0)
					{
						//scores++;
						int brickX = j * map.brickWidth + x/4;
						int brickY = i * map.brickHeight + y/20;
						int brickWidth = map.brickWidth;
						int brickHeight = map.brickHeight;

						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);					
						Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
						Rectangle brickRect = rect;

						if(ballRect.intersects(brickRect))
						{					
							if(map.map[i][j] == 1)
							{
								map.setBrickValue(0, i, j);
								score+=5;	
								totalBricks--;
							}
							else if(map.map[i][j] == 2)
							{
								int c=0;
								map.setBrickValue(0, i, j);
								c++;
								if(map.map[i][j-1]==1) {
									map.setBrickValue(0, i, j-1);
									c++;
								}
								if(map.map[i][j+1]==1) {
									map.setBrickValue(0, i, j+1);
									c++;
								}
								if(map.map[i-1][j]==1) {
									map.setBrickValue(0, i-1, j);
									c++;
								}
								if(map.map[i+1][j]==1) {
									map.setBrickValue(0, i+1, j);
									c++;
								}
								if(map.map[i-1][j-1]==1) {
									map.setBrickValue(0, i-1, j-1);
									c++;
								}
								if(map.map[i+1][j+1]==1) {
									map.setBrickValue(0, i+1, j+1);
									c++;
								}
								if(map.map[i-1][j+1]==1) {
									map.setBrickValue(0, i-1, j+1);
									c++;
								}
								if(map.map[i+1][j-1]==1) {
									map.setBrickValue(0, i+1, j-1);
									c++;
								}
								score+=c*5;	
								totalBricks=totalBricks-c;
							}

							// when ball hit right or left of brick
							if(ballRect.x <= brickRect.x - 20 || ballRect.x  >= brickRect.x + brickRect.width)	
							{
								ballXdir = -ballXdir;
							}
							// when ball hits top or bottom of brick
							else
							{
								ballYdir = -ballYdir;				
							}

							break A;
						}
					}
				}
			}

			ballposX += ballXdir;
			ballposY += ballYdir;

			if(ballposX < 0)
			{
				ballXdir = -ballXdir;
			}
			if(ballposY < 0)
			{
				ballYdir = -ballYdir;
			}
			if(ballposX > x-20)
			{
				ballXdir = -ballXdir;
			}
			//map.draw((Graphics2D) g);


			repaint();		
		}

	}
}