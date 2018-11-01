package Brickbreaker;
import java.awt.BasicStroke;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JButton;
public class MapGenerator 
{
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int x = screenSize.width;
	private int y = screenSize.height;
	public int map[][];
	public int brickWidth;
	public int brickHeight;
	public MapGenerator (int row, int col)
	{
		map = new int[row][col];		
		for(int i = 0; i<map.length; i++)
		{
			for(int j =0; j<map[0].length; j++)
			{
				if(i==1 && j==3 || i==2 && j==9 || i==4 && j==11) {
					map[i][j] = 2;
				}
				else
					map[i][j]=1;
			}			
		}
		brickWidth = 540/col;
		brickHeight = 150/row;
	}	
	public void draw(Graphics2D g, int k )
	{
		for(int i = 0; i<map.length; i++)
		{
			for(int j =0; j<map[0].length; j++)
			{
				if(map[i][j] == 1)
				{
					g.setColor(Color.white);
					if(k + map[0].length * brickWidth <1500 && k + map[0].length * brickWidth > 0)
					{
						if(k==0)
						{
							g.fillRect(x/4 + j * brickWidth , y/20 + i * brickHeight  , brickWidth, brickHeight);
							g.setStroke(new BasicStroke(3));
							g.setColor(Color.black);
							g.drawRect( x/4 + j*brickWidth, y/20 + i * brickHeight , brickWidth, brickHeight);
						}

						else
						{
							g.fillRect(k + j * brickWidth , y/20 + i * brickHeight  , brickWidth, brickHeight);
							// this is just to show separate brick, game can still run without it
							g.setStroke(new BasicStroke(3));
							g.setColor(Color.black);
							g.drawRect(k + j*brickWidth, y/20 + i * brickHeight , brickWidth, brickHeight);
						}

					}
				}
				else if(map[i][j] == 2)
				{
					g.setColor(Color.green);
					if( k + map[0].length * brickWidth <1500 && k + map[0].length * brickWidth> 0)
					{
						if(k==0)
						{
							g.fillRect(x/4 + j * brickWidth , y/20 + i * brickHeight  , brickWidth, brickHeight);
							// this is just to show separate brick, game can still run without it
							g.setStroke(new BasicStroke(3));
							g.setColor(Color.black);
							g.drawRect(x/4+ j*brickWidth, y/20 + i * brickHeight , brickWidth, brickHeight);
						}
						else
						{
							g.fillRect(k + j * brickWidth , y/20 + i * brickHeight  , brickWidth, brickHeight);
							// this is just to show separate brick, game can still run without it
							g.setStroke(new BasicStroke(3));
							g.setColor(Color.black);
							g.drawRect(k + j*brickWidth, y/20 + i * brickHeight , brickWidth, brickHeight);
						}
					}
				}	
			}

		}
	}
	public void setBrickValue(int value, int row, int col)
	{
		map[row][col] = value;
	}

}