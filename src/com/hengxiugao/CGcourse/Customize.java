package com.hengxiugao.CGcourse;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Customize extends JFrame{
	Setting s;
	boolean newshape[][] = new boolean[4][4];
	CustomizeCanvas CC = new CustomizeCanvas();
	Customize(Setting s)
	{
		this.s = s;
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setSize(320, 300);
		setLocation(750, 50);
		setTitle("Customize Shape");
		setLayout(null);
		
	}
	void setSwing()
	{
		JLabel J1 = new JLabel("Click each square to create your own shapes:");
		J1.setBounds(15, 5, 400, 40);
		
		
		CC.setPreferredSize(new Dimension(400, 800));
		CC.setBounds(85, 45, 40*4, 40*4);
		
		JButton JB_1 = new JButton("Add this new shape");
		JB_1.setBounds(115, 225, 160, 30);
		add(JB_1);
		add(CC);
		add(J1);
		
		CC.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if(!newshape[e.getX()/40][e.getY()/40])
					newshape[e.getX()/40][e.getY()/40]= true;
				else
					newshape[e.getX()/40][e.getY()/40] = false;
				CC.repaint();
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		JB_1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean newshape0[][] = new boolean[4][4];
				boolean newshape1[][] = new boolean[4][4];
				boolean newshape2[][] = new boolean[4][4];
				boolean newshape3[][] = new boolean[4][4];
				for(int i=0;i<4;i++)
					for(int j=0;j<4;j++)
						newshape0[i][j] = newshape[i][j];
				for(int i=0;i<4;i++)
					for(int j=0;j<4;j++)
						newshape1[i][j] = newshape[j][3-i];
				for(int i=0;i<4;i++)
					for(int j=0;j<4;j++)
						newshape2[i][j] = newshape[3-i][3-j];
				for(int i=0;i<4;i++)
					for(int j=0;j<4;j++)
						newshape3[i][j] = newshape[3-j][i];
				PlayData.addNewShape(newshape0, newshape1, newshape2, newshape3);
				
				/*
				newshape = PlayData.getShape(7)[1];
				for(int i=0;i<4;i++)
				{
					for(int j=0;j<4;j++)
						if(newshape[3-j][i])
							System.out.print("1 ");
						else
							System.out.print("0 ");
					System.out.println();
				}
				System.out.println();
				*/
				s.SLC.repaint();
			}
			
		});
		this.setVisible(true);
	}
	class CustomizeCanvas extends Canvas
	{
		CustomizeCanvas()
		{
			for(int i=0;i<4;i++)
				for(int j=0;j<4;j++)
					newshape[i][j] = false;
		}
		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
			for(int i=0;i<4;i++)
				for(int j=0;j<4;j++)
				{
					g.setColor(Color.black);
					g.drawRect(i*40, j*40, 40, 40);
					if(newshape[i][j])
					{
						g.setColor(Color.green);
						g.fillRect(i*40+1, j*40+1, 40-1, 40-1);
					}
				}
			
			
		}
	}
	
}

