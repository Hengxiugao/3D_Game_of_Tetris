package com.hengxiugao.CGcourse;

import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class About extends JFrame{
	int s;
	About()
	{
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setSize(400, 200);
		setLocation(400, 250);
		setVisible(true);
		setTitle("About");
		setLayout(new FlowLayout());
		JLabel l1 = new JLabel("Computer Graphics Homework #3, Game of Tetris");
		JLabel l2 = new JLabel("By Hengxiu Gao UTD ID: 2021229554");
		JLabel l3 = new JLabel("To meet the requirement,it will display a static demo first.");
		JLabel l4 = new JLabel("It can be of course played, though just a very simple game.");
		JLabel l5 = new JLabel("Using the arraw key to move, change the shape,");
		JLabel l6 = new JLabel("once one line is all painted, this line will be eliminated.");
		JButton jb_web = new JButton("My Webside");
		jb_web.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				URI uri = null;
				
				Desktop dtp = Desktop.getDesktop();
				if(dtp.isDesktopSupported()&&dtp.isSupported(Desktop.Action.BROWSE))
				{
					try {
						uri = new URI("https://sites.google.com/site/gaohengxiu/about-me");
						dtp.browse(uri);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
			
		});
		
		add(l1);
		add(l2);
		add(l3);
		add(l4);
		add(l5);
		add(l6);
		add(jb_web);
	}

}
