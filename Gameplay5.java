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
public class Gameplay5 extends JPanel implements KeyListener, ActionListener 
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
	private int hit = 1;
	int totalBricks;
	private Timer timer;
	private int delay;
	private int playerX = x/2-100;
	private int ballposX1 = x/2;
	private int ballposY1 = y/2;
	private int ballposX2 = 0;
	private int ballposY2 = 2*y;
	private int ballposX3 = 0;
	private int ballposY3 = 2*y;
	private int ballXdir1 = -1;
	private int ballYdir1 = 2;
	private int ballXdir2 = -1;
	private int ballYdir2 = 2;
	private int ballXdir3 = -1;
	private int ballYdir3 = 2;
	private int count = 0;
	private int k=1;
	private int c=0;
	private MapGenerator map;
	Main m;

	public Gameplay5(int x, int y, int d, Main m1)
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
			reader = new BufferedReader(new FileReader("highscore5.dat"));
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
				output = new BufferedWriter(new FileWriter("highscore5.dat"));
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
		map.draw((Graphics2D) g, k);
		if(k<(107*y/100) && c==0)
			k=k+1;
		else
		{
			c=1;
			k=k-1;
			if(k==1)
				c=0;
		}
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
		if(hit==1) {
			g.setColor(Color.green);
			g.fillRect(playerX, 9*y/10, 100, 8);
			// the ball
			g.setColor(Color.yellow);
			g.fillOval(ballposX1, ballposY1, 20, 20);
		}
		else if(hit==2) {
			g.setColor(Color.green);
			g.fillRect(playerX, 9*y/10, 100, 8);
			g.setColor(Color.blue);
			g.fillRect(playerX-30, 9*y/10, 30, 8);
			g.setColor(Color.blue);
			g.fillRect(playerX+100, 9*y/10, 30, 8);
			// the ball
			g.setColor(Color.yellow);
			g.fillOval(ballposX1, ballposY1, 15, 15);
			g.setColor(Color.yellow);
			g.fillOval(ballposX2, ballposY2, 15, 15);
		}
		else if(hit==3) {
			g.setColor(Color.green);
			g.fillRect(playerX, 9*y/10, 100, 8);
			g.setColor(Color.blue);
			g.fillRect(playerX-30, 9*y/10, 30, 8);
			g.setColor(Color.blue);
			g.fillRect(playerX+100, 9*y/10, 30, 8);
			g.setColor(Color.red);
			g.fillRect(playerX-50, 9*y/10, 30, 8);
			g.setColor(Color.red);
			g.fillRect(playerX+130, 9*y/10, 30, 8);
			// the ball
			g.setColor(Color.yellow);
			if(ballposY1 < y) 
				g.fillOval(ballposX1, ballposY1, 10, 10);
			g.setColor(Color.yellow);
			if(ballposY2 < y)
				g.fillOval(ballposX2, ballposY2, 10, 10);
			g.setColor(Color.yellow);
			g.fillOval(ballposX3, ballposY3, 10, 10);
		}
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
				ballXdir1 = 0;
				ballYdir1 = 0;
				ballXdir2 = 0;
				ballYdir2 = 0;
				ballXdir3 = 0;
				ballYdir3 = 0;
				hit = 1;
				g.setColor(Color.RED);
				g.setFont(new Font("serif",Font.BOLD, 30));
				g.drawString("You Won", x/3,y/2);
				g.setColor(Color.GREEN);
				g.setFont(new Font("serif",Font.BOLD, 30));
				g.setColor(Color.GREEN);
				g.setFont(new Font("serif",Font.BOLD, 20));           
				g.drawString("Press (Enter) to Next Level", x/3, (int)(y/1.7));
				win = true;
				checkScore();
				if(score==highscore)
					g.drawString("New Highscore: "+score, x/3, (int)(y/1.8));
				else
				{
					g.drawString("Score = "+score, x/3,(int)(y/1.9));
					g.drawString("Highscore: "+highscore, x/3, (int)(y/1.8));
				}

			}
			m.a.dispose();
			m.obj.setVisible(true);
		}
		// when you lose the game
		if(ballposY1 > y && ballposY2 >  y && ballposY3 > y)
		{
			play = false;
			win = false;
			ballXdir1 = 0;
			ballYdir1 = 0;
			ballXdir2 = 0;
			ballYdir2 = 0;
			ballXdir3 = 0;
			ballYdir3 = 0;
			hit = 1;
			g.setColor(Color.GREEN);
			g.setFont(new Font("serif",Font.BOLD, 30));
			g.drawString("Game Over, Score: "+score, x/3,(int)(y/2.1));    
			g.setColor(Color.GREEN);
			g.setFont(new Font("serif",Font.BOLD, 20));
			g.drawString("Press (Enter) to Restart", x/3, (int)(y/1.8));
			g.setColor(Color.GREEN);
			g.setFont(new Font("serif",Font.BOLD, 20));           
			g.drawString("Press (Space) to Main menu", x/3, (int)(y/1.7));            
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
					ballposX1 = x/2;
					ballposY1 = y/2;
					ballXdir1 = -1;
					ballYdir1 = 2;
					ballXdir2 = 1;
					ballYdir2 = -2;
					ballXdir3 = -1;
					ballYdir3 = 2;
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
		if(hit == 1) {
			if(play)
			{	
				if(new Rectangle(ballposX1, ballposY1, 20, 20).intersects(new Rectangle(playerX, 9*y/10, 30, 8)))
				{
					ballYdir1 = -ballYdir1;
					if(ballXdir1>0)
						ballXdir1 = -ballXdir1;
				}
				else if(new Rectangle(ballposX1, ballposY1, 20, 20).intersects(new Rectangle(playerX + 30, 9*y/10, 40, 8)))
				{
					ballYdir1 = -ballYdir1;

				}
				else if(new Rectangle(ballposX1, ballposY1, 20, 20).intersects(new Rectangle(playerX + 70, 9*y/10, 30, 8)))
				{
					ballYdir1 = -ballYdir1;
					if(ballXdir1<0)
						ballXdir1 = -ballXdir1;
				}
				// check map collision with the ball		
				A: for(int i = 0; i<map.map.length; i++)
				{
					for(int j =0; j<map.map[0].length; j++)
					{				
						if(map.map[i][j] > 0)
						{
							//scores++;
							int brickX = j * map.brickWidth + k;
							int brickY = i * map.brickHeight + y/20;
							int brickWidth = map.brickWidth;
							int brickHeight = map.brickHeight;

							Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);					
							Rectangle ballRect = new Rectangle(ballposX1, ballposY1, 20, 20);
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
									hit = 2;
									ballposX2 = ballposX1;
									ballposY2 = ballposY1;
									playerX+=60;
								}

								// when ball hit right or left of brick
								if(ballRect.x <= brickRect.x - 20 || ballRect.x  >= brickRect.x + brickRect.width)	
								{
									ballXdir1 = -ballXdir1;
								}
								// when ball hits top or bottom of brick
								else
								{
									ballYdir1 = -ballYdir1;				
								}

								break A;
							}
						}
					}
				}

				ballposX1 += ballXdir1;
				ballposY1 += ballYdir1;

				if(ballposX1 < 0)
				{
					ballXdir1 = -ballXdir1;
				}
				if(ballposY1 < 0)
				{
					ballYdir1 = -ballYdir1;
				}
				if(ballposX1 > x-20)
				{
					ballXdir1 = -ballXdir1;
				}		

				repaint();	
			}

		}

		else if(hit==2) {
			if(play)
			{	
				if(new Rectangle(ballposX1, ballposY1, 15, 15).intersects(new Rectangle(playerX - 45 , 9*y/10, 60, 8)))
				{
					ballYdir1 = -ballYdir1;
					if(ballXdir1>0)
						ballXdir1 = -ballXdir1;
				}
				else if(new Rectangle(ballposX1, ballposY1, 15, 15).intersects(new Rectangle(playerX + 45, 9*y/10, 40, 8)))
				{
					ballYdir1 = -ballYdir1;

				}
				else if(new Rectangle(ballposX1, ballposY1, 15, 15).intersects(new Rectangle(playerX + 85, 9*y/10, 60, 8)))
				{
					ballYdir1 = -ballYdir1;
					if(ballXdir1<0)
						ballXdir1 = -ballXdir1;
				}
				if(new Rectangle(ballposX2, ballposY2, 15, 15).intersects(new Rectangle(playerX -45, 9*y/10, 60, 8)))
				{
					ballYdir2 = -ballYdir2;
					if(ballXdir2>0)
						ballXdir2 = -ballXdir2;
				}
				else if(new Rectangle(ballposX2, ballposY2, 15, 15).intersects(new Rectangle(playerX + 45, 9*y/10, 40, 8)))
				{
					ballYdir2 = -ballYdir2;

				}
				else if(new Rectangle(ballposX2, ballposY2, 15, 15).intersects(new Rectangle(playerX + 85, 9*y/10, 60, 8)))
				{
					ballYdir2 = -ballYdir2;
					if(ballXdir2<0)
						ballXdir2 = -ballXdir2;
				}
				// check map collision with the ball		
				A: for(int i = 0; i<map.map.length; i++)
				{
					for(int j =0; j<map.map[0].length; j++)
					{				
						if(map.map[i][j] > 0)
						{
							//scores++;
							int brickX = j * map.brickWidth + k;
							int brickY = i * map.brickHeight + y/20;
							int brickWidth = map.brickWidth;
							int brickHeight = map.brickHeight;

							Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);					
							Rectangle ballRect1 = new Rectangle(ballposX1, ballposY1, 15, 15);
							Rectangle ballRect2 = new Rectangle(ballposX2, ballposY2, 15, 15);
							Rectangle brickRect = rect;

							if(ballRect1.intersects(brickRect))
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
									//if(hit!=3) {
										hit=3;
										ballposX3 = ballposX1;
										ballposY3 = ballposY1;
										playerX+=40;
									//}
								}

								// when ball hit right or left of brick
								if(ballRect1.x <= brickRect.x - 20 || ballRect1.x  >= brickRect.x + brickRect.width)	
								{
									ballXdir1 = -ballXdir1;
								}
								// when ball hits top or bottom of brick
								else
								{
									ballYdir1 = -ballYdir1;				
								}
							}
							if(ballRect2.intersects(brickRect))
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
									//if(hit!=3) {
										hit=3;
										ballposX3 = ballposX2;
										ballposY3 = ballposY2;
										playerX+=40;
									//}
								}

								// when ball hit right or left of brick
								if(ballRect2.x <= brickRect.x - 20 || ballRect2.x  >= brickRect.x + brickRect.width)	
								{
									ballXdir2 = -ballXdir2;
								}
								// when ball hits top or bottom of brick
								else
								{
									ballYdir2 = -ballYdir2;				
								}

								break A;
							}
						}
					}
				}

				ballposX1 += ballXdir1;
				ballposY1 += ballYdir1;
				ballposX2 -= ballXdir2;
				ballposY2 -= ballYdir2;

				if(ballposX1 < 0)
				{
					ballXdir1 = -ballXdir1;
				}
				if(ballposY1 < 0)
				{
					ballYdir1 = -ballYdir1;
				}
				if(ballposX1 > x-20)
				{
					ballXdir1 = -ballXdir1;
				}

				if(ballposX2 < 0)
				{
					ballXdir2 = -ballXdir2;
				}
				if(ballposY2 < 0)
				{
					ballYdir2 = -ballYdir2;
				}
				if(ballposX2 > x-20)
				{
					ballXdir2 = -ballXdir2;
				}

				repaint();	
			}

		}
		
		else if(hit==3) {
			if(play)
			{	
				if(new Rectangle(ballposX1, ballposY1, 10, 10).intersects(new Rectangle(playerX-55, 9*y/10, 60, 8)))
				{
					ballYdir1 = -ballYdir1;
					if(ballXdir1>0)
						ballXdir1 = -ballXdir1;
				}
				else if(new Rectangle(ballposX1, ballposY1, 10, 10).intersects(new Rectangle(playerX + 55, 9*y/10, 40, 8)))
				{
					ballYdir1 = -ballYdir1;

				}
				else if(new Rectangle(ballposX1, ballposY1, 10, 10).intersects(new Rectangle(playerX + 95, 9*y/10, 60, 8)))
				{
					ballYdir1 = -ballYdir1;
					if(ballXdir1<0)
						ballXdir1 = -ballXdir1;
				}
				if(new Rectangle(ballposX2, ballposY2, 10, 10).intersects(new Rectangle(playerX-55, 9*y/10, 60, 8)))
				{
					ballYdir2 = -ballYdir2;
					if(ballXdir2>0)
						ballXdir2 = -ballXdir2;
				}
				else if(new Rectangle(ballposX2, ballposY2, 10, 10).intersects(new Rectangle(playerX + 55, 9*y/10, 40, 8)))
				{
					ballYdir2 = -ballYdir2;

				}
				else if(new Rectangle(ballposX2, ballposY2, 10, 10).intersects(new Rectangle(playerX + 95, 9*y/10, 60, 8)))
				{
					ballYdir2 = -ballYdir2;
					if(ballXdir2<0)
						ballXdir2 = -ballXdir2;
				}
				if(new Rectangle(ballposX3, ballposY3, 10, 10).intersects(new Rectangle(playerX-55, 9*y/10, 60, 8)))
				{
					ballYdir3 = -ballYdir3;
					if(ballXdir3>0)
						ballXdir3 = -ballXdir3;
				}
				else if(new Rectangle(ballposX3, ballposY3, 10, 10).intersects(new Rectangle(playerX + 55, 9*y/10, 40, 8)))
				{
					ballYdir3 = -ballYdir3;

				}
				else if(new Rectangle(ballposX3, ballposY3, 10, 10).intersects(new Rectangle(playerX + 95, 9*y/10, 60, 8)))
				{
					ballYdir3 = -ballYdir3;
					if(ballXdir3<0)
						ballXdir3 = -ballXdir3;
				}
				// check map collision with the ball		
				A: for(int i = 0; i<map.map.length; i++)
				{
					for(int j =0; j<map.map[0].length; j++)
					{				
						if(map.map[i][j] > 0)
						{
							//scores++;
							int brickX = j * map.brickWidth + k;
							int brickY = i * map.brickHeight + y/20;
							int brickWidth = map.brickWidth;
							int brickHeight = map.brickHeight;

							Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);					
							Rectangle ballRect1 = new Rectangle(ballposX1, ballposY1, 10, 10);
							Rectangle ballRect2 = new Rectangle(ballposX2, ballposY2, 10, 10);
							Rectangle ballRect3 = new Rectangle(ballposX3, ballposY3, 10, 10);
							Rectangle brickRect = rect;

							if(ballRect1.intersects(brickRect))
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
								if(ballRect1.x <= brickRect.x - 20 || ballRect1.x  >= brickRect.x + brickRect.width)	
								{
									ballXdir1 = -ballXdir1;
								}
								// when ball hits top or bottom of brick
								else
								{
									ballYdir1 = -ballYdir1;				
								}
							}
							if(ballRect2.intersects(brickRect))
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
								if(ballRect2.x <= brickRect.x - 20 || ballRect2.x  >= brickRect.x + brickRect.width)	
								{
									ballXdir2 = -ballXdir2;
								}
								// when ball hits top or bottom of brick
								else
								{
									ballYdir2 = -ballYdir2;				
								}

							}
							if(ballRect3.intersects(brickRect))
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
								if(ballRect3.x <= brickRect.x - 20 || ballRect3.x  >= brickRect.x + brickRect.width)	
								{
									ballXdir3 = -ballXdir3;
								}
								// when ball hits top or bottom of brick
								else
								{
									ballYdir3 = -ballYdir3;				
								}
								break A;
							}
						}
					}
				}

				ballposX1 += ballXdir1;
				ballposY1 += ballYdir1;
				ballposX2 -= ballXdir2;
				ballposY2 -= ballYdir2;
				ballposX3 += ballXdir3;
				ballposY3 += ballYdir3;

				if(ballposX1 < 0)
				{
					ballXdir1 = -ballXdir1;
				}
				if(ballposY1 < 0)
				{
					ballYdir1 = -ballYdir1;
				}
				if(ballposX1 > x-20)
				{
					ballXdir1 = -ballXdir1;
				}

				if(ballposX2 < 0)
				{
					ballXdir2 = -ballXdir2;
				}
				if(ballposY2 < 0)
				{
					ballYdir2 = -ballYdir2;
				}
				if(ballposX2 > x-20)
				{
					ballXdir2 = -ballXdir2;
				}
				
				if(ballposX3 < 0)
				{
					ballXdir3 = -ballXdir3;
				}
				if(ballposY3 < 0)
				{
					ballYdir3 = -ballYdir3;
				}
				if(ballposX3 > x-20)
				{
					ballXdir3 = -ballXdir3;
				}

				repaint();	
			}

		}


	}
}