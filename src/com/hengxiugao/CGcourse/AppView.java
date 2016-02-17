package com.hengxiugao.CGcourse;


import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class AppView extends JFrame implements ActionListener{
	/**
	 * 
	 */
	public JButton B_Quit = new JButton("Quit");
	public JButton B_About = new JButton("About");
	public JButton B_Debug = new JButton("Debug MODE");
	public JButton B_Setting = new JButton("Setting");
	public JLabel JL_speedfactor = new JLabel();
	public JLabel JL_scorefactor = new JLabel();
	public JLabel JL_rowsrequire = new JLabel();
	public int sendDataCount = 0;
	public int zoom=0;
	public int startX=0,startY=0;
	
	MainCanvas mc;	
	RightCanvas rc;
	float ChangeRatio=1;
	JPanel side_panel,sidepanel_text;
	JMenu mF, mV;
	JMenuItem open, exit, eyeUp, eyeDown, eyeLeft, eyeRight,incrDist, decrDist;;
	AppView appview;
	AppView()
	{
		mc = new MainCanvas(this);
		appview = this;
	}
	
	public void setSwing() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		int size = 100+PlayData.StageHeight;
		if(size<500) size = 500;
		setSize(300+PlayData.StageWidth, size);
		setLocation(300, 0);
		setVisible(true);
		setTitle("Game of Tetris");
		setLayout(null);
		
		JMenuBar mBar = new JMenuBar();
		  setJMenuBar(mBar);
		  mF = new JMenu("File");
		  mV = new JMenu("View");
		  mBar.add(mF); mBar.add(mV);

		  open = new JMenuItem("Open");
		  eyeDown = new JMenuItem("Viewpoint Down");
		  eyeUp = new JMenuItem("Viewpoint Up");
		  eyeLeft = new JMenuItem("Viewpoint to Left");
		  eyeRight = new JMenuItem("Viewpoint to Right");

		  incrDist = new JMenuItem("Increase viewing distance");
		  decrDist = new JMenuItem("Decrease viewing distance");
		  exit = new JMenuItem("Exit");
		  mF.add(open); mF.add(exit);
		  mV.add(eyeDown); mV.add(eyeUp);
		  mV.add(eyeLeft); mV.add(eyeRight);
		  mV.add(incrDist); mV.add(decrDist);
		  open.addActionListener(this);
		  exit.addActionListener(this);
		  eyeDown.addActionListener(this);
		  eyeUp.addActionListener(this);
		  eyeLeft.addActionListener(this);
		  eyeRight.addActionListener(this);
		  incrDist.addActionListener(this);
		  decrDist.addActionListener(this);
		
		
		
		mc.setPreferredSize(new Dimension(PlayData.StageWidth, PlayData.StageHeight));
		mc.setBounds(20, 0, PlayData.StageWidth, PlayData.StageHeight);
		add(mc);
		PlayData.gameStatus = 1;//Game Start
		
		rc = new RightCanvas(mc);
		rc.setPreferredSize(new Dimension(200, 250));
		rc.setBounds(PlayData.StageWidth+80, 50, 200, 250);
		add(rc);
		mc.addKeyListener(new KeyListener(){

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==38)
					mc.KeySataus = 5;
				else if(e.getKeyCode()==40)
					mc.KeySataus = 6;
				else if(e.getKeyCode()==39)
					mc.KeySataus = 7;
				else if(e.getKeyCode()==37)
					mc.KeySataus = 8;
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		this.addMouseWheelListener(new MouseWheelListener(){

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				if(e.getWheelRotation()>0) mc.KeySataus = 1;
				else mc.KeySataus = 2;
				
			}
			
		});
		this.addMouseListener(new MouseAdapter(){

			
			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getButton()==1)
					mc.KeySataus = 3;
				else if(e.getButton()==3)
					mc.KeySataus = 4;
				
			}

			
			
		});

		
		side_panel = new JPanel();
		side_panel.setLayout(new FlowLayout());
		side_panel.add(B_Setting);
		side_panel.add(B_Quit);
		side_panel.add(B_About);
		
		side_panel.setBounds(PlayData.StageWidth+25, 380, 300 ,30);
		
		JL_speedfactor.setBounds(PlayData.StageWidth+80, 300, 150 ,60);
		JL_scorefactor.setBounds(PlayData.StageWidth+80, 320, 150 ,60);
		JL_rowsrequire.setBounds(PlayData.StageWidth+80, 340, 200 ,60);
		B_Debug.setBounds(PlayData.StageWidth+70, 430, 120, 30);
		
	    add(side_panel);
	    add(JL_rowsrequire);
	    add(JL_scorefactor);
	    add(JL_speedfactor);
	    add(B_Debug);
	    
	    
	    Timer timerMain = new Timer(); 
	    TimerMain tm = new TimerMain(this);
	    timerMain.schedule(tm, 1,1);
	    
	    Thread PaintThread = new Thread(mc);
	    PaintThread.start();
	    
	    B_Setting.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Setting s = new Setting();
				s.setSWING();
				appview.dispose();
				
			}
	    	
	    });
	    B_Debug.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				mc.SwitchDebugMode();
			}
	    	
	    });
	    B_Quit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
				
			}});
	    
	    B_About.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				About a = new About();
				
			}});
		
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		  if (ae.getSource() instanceof JMenuItem)
		   {  JMenuItem mi = (JMenuItem)ae.getSource();
		      
		      if (mi == exit) System.exit(0); else
		      if (mi == eyeDown) mc.vp(0, .1F, 1); else
		      if (mi == eyeUp) mc.vp(0, -.1F, 1); else
		      if (mi == eyeLeft) mc.vp(-.1F, 0, 1); else
		      if (mi == eyeRight) mc.vp(.1F, 0, 1); else
		      if (mi == incrDist) mc.vp (0, 0, 2); else
		      if (mi == decrDist) mc.vp(0, 0, .5F);
		    }
		  
		
	}
	

}

class TimerMain extends TimerTask { 
	AppView app;
	
	TimerMain(AppView app)
	{
		this.app = app;
	}
      public void run() {
    	  app.rc.setString(PlayData.score, PlayData.lines, PlayData.level);
    	  app.JL_rowsrequire.setText("Rows required to level UP:"+PlayData.rowsRequired);
    	  app.JL_scorefactor.setText("Score Factor:"+PlayData.scoreFactor);
    	  app.JL_speedfactor.setText("Speed Factor:"+PlayData.speedFactor);
    	  /*
    	  wadapt.changeMC(rationW, rationH);
    	  wadapt.chageRC(rationW, rationH);
    	  PlayData.shapeSize = PlayData.MCw / 10;
    	  */
    	  app.mc.requestFocusInWindow();
    	  app.mc.repaint();
    	  app.rc.repaint();
    	  
  		  
      }  
  } 

class MouseCanvas extends Canvas
{
	MouseCanvas(final MainCanvas mc)
	{
		this.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				if(e.getButton()==1)
					mc.KeySataus = 3;
				else if(e.getButton()==3)
					mc.KeySataus = 4;
			}
		});
	}
}
