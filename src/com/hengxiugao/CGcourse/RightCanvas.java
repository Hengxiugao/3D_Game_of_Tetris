package com.hengxiugao.CGcourse;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class RightCanvas extends MouseCanvas
{
	/**
	 * Perform displaying the game status.
	 */
	
	private int level, lines, score;
	public float ChangeRatio = 1;
	MainCanvas mc = null;
	RightCanvas(MainCanvas mc)
	{
		super(mc);
		level = 1;
		lines = 0;
		score = 0;
		this.mc = mc;
	}
	public void setString(int score,int lines, int level)
	{
		this.level = level;
		this.score = score;
		this.lines = lines;
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.drawRect((int)(ChangeRatio*15), (int)(ChangeRatio*5), (int)(ChangeRatio*180), (int)(ChangeRatio*100));
		
		g.setFont(new Font("SansSerif",Font.BOLD,(int)(ChangeRatio*20)));
		g.drawString("Level:", (int)(ChangeRatio*15), (int)(ChangeRatio*150));
		g.drawString("Lines:", (int)(ChangeRatio*15), (int)(ChangeRatio*200));
		g.drawString("Score:", (int)(ChangeRatio*15), (int)(ChangeRatio*250));
		
		g.setFont(new Font("SansSerif",Font.ITALIC,(int)(ChangeRatio*20)));
		g.drawString(String.valueOf(level), (int)(ChangeRatio*100), (int)(ChangeRatio*150));
		g.drawString(String.valueOf(lines), (int)(ChangeRatio*100), (int)(ChangeRatio*200));
		g.drawString(String.valueOf(score), (int)(ChangeRatio*100), (int)(ChangeRatio*250));
		
		int shapesize = (int)PlayData.shapeSize;
		boolean shape[][][] = PlayData.getShape(mc.nextkind);
		for(int i=3;i>=0;i--)
		{
			for(int j=0;j<4;j++)
			{
				if(shape[0][j][i])
				{
					g.setColor(Color.black);
					g.drawRect(65+(j)*shapesize,15+(i)*shapesize, shapesize, shapesize);
					g.setColor(mc.nextColor);
					g.fillRect(65+(j)*shapesize,15+(i)*shapesize, shapesize, shapesize);
				}
				
				//System.out.println("currentx="+(current_x+i*shapesize+KeyDirection)+"currenty="+
				  //       (current_y+j*shapesize));
			}
		}
	
	}
}