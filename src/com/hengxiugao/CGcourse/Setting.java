package com.hengxiugao.CGcourse;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

public class Setting extends JFrame implements ActionListener,ChangeListener{
	int s;
	ShapeListCanvas SLC;
	ColorListCanvas CLC;
	JPanel panel_m,panel_n,panel_s;
	AppView appview;
	JRadioButton buttonGM_1,buttonGM_2,buttonGM_3,buttonGM_4,buttonShS_1,buttonShS_2,buttonShS_3,buttonStS_1,buttonStS_2,buttonStS_3;
	JButton JB_cus,JB_cus_color,JB_ok;
	private int gameMode = 1,stageSize = 1, shapeSize = 1;
	JLabel jtf_m,jtf_n,jtf_s;
	JLabel jl_m,jl_n,jl_s;
	JSlider slider_M;
	JSlider slider_N = new JSlider();
	JSlider slider_S = new JSlider();
	Setting()
	{
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setSize(700, 600);
		setLocation(400, 50);
		setTitle("Game Setting");
		setLayout(null);
	}
	void SetSlider()
	{
		panel_m = new JPanel();
		panel_m.setLayout(new FlowLayout());
		jl_m = new JLabel("Scoring factor (M)=");
		jtf_m = new JLabel(String.valueOf(PlayData.scoreFactor));
		slider_M = new JSlider(1,11);
		slider_M.setPaintLabels(true);
		slider_M.setSnapToTicks(true);
		slider_M.setPaintTicks(true);
		slider_M.setInverted(true);
		slider_M.setExtent(1);
		slider_M.setValue(PlayData.scoreFactor);
		Dictionary<Integer, Component> labelTable1 = new Hashtable<Integer, Component>();  
		labelTable1.put(1, new JLabel("1"));  
		labelTable1.put(6, new JLabel("6"));  
		labelTable1.put(10, new JLabel("10"));
		slider_M.setLabelTable(labelTable1);
		slider_M.setMajorTickSpacing(5);  
		slider_M.setMinorTickSpacing(1); 
		slider_M.setValue(PlayData.scoreFactor);
		panel_m.add(jl_m);
		panel_m.add(jtf_m);
		panel_m.add(slider_M);
		panel_m.setBounds(85, 30, 540, 50);
		
		panel_n = new JPanel();
		panel_n.setLayout(new FlowLayout());
		jl_n = new JLabel("Rows required to level up (N)=");
		jtf_n = new JLabel(String.valueOf(PlayData.rowsRequired));
		slider_N = new JSlider(20,50);
		slider_N.setPaintLabels(true);
		slider_N.setSnapToTicks(true);
		slider_N.setPaintTicks(true);
		slider_N.setMajorTickSpacing(5);  
		slider_N.setMinorTickSpacing(1); 
		slider_N.setValue(PlayData.rowsRequired);
		panel_n.add(jl_n);
		panel_n.add(jtf_n);
		panel_n.add(slider_N);
		panel_n.setBounds(120, 75, 540, 50);
		
		panel_s = new JPanel();
		panel_s.setLayout(new FlowLayout());
		jl_s = new JLabel("Speed factor (S)=");
		jtf_s = new JLabel(String.valueOf(PlayData.speedFactor));
		slider_S = new JSlider(1,11);
		slider_S.setPaintLabels(true);
		slider_S.setSnapToTicks(true);
		slider_S.setPaintTicks(true);
		slider_S.setExtent(1);
		slider_S.setValue((int) (PlayData.speedFactor * 10.0f));
		Dictionary<Integer, Component> labelTable = new Hashtable<Integer, Component>();  
        labelTable.put(1, new JLabel("0.1"));  
        labelTable.put(6, new JLabel("0.6"));  
        labelTable.put(10, new JLabel("1.0"));
        slider_S.setLabelTable(labelTable);  
        slider_S.setMajorTickSpacing(5); 
		slider_S.setMinorTickSpacing(1); 
		panel_s.add(jl_s);
		panel_s.add(jtf_s);
		panel_s.add(slider_S);
		panel_s.setBounds(78, 120, 540, 50);
		
        add(panel_m);
        add(panel_n);
        add(panel_s);
        slider_M.addChangeListener(this);
        slider_S.addChangeListener(this);
        slider_N.addChangeListener(this);
        
		/*
        listener = new ChangeListener()  
        {    
            public void stateChanged(ChangeEvent event)  
            {    
                JSlider source = (JSlider) event.getSource();  
                System.out.println(source.getValue());
            }  
        };  
        */
	}
	public void setSWING()
	{
		SetSlider();
		JLabel J_GM = new JLabel("Game Mode:");
		JPanel panel_GM = new JPanel();
		panel_GM.setLayout(new FlowLayout());
		buttonGM_1 = new JRadioButton("Hard"); 
		buttonGM_2 = new JRadioButton("Normal");  
		buttonGM_3 = new JRadioButton("Easy"); 
		buttonGM_4 = new JRadioButton("Customized");
		ButtonGroup groupGM = new ButtonGroup();
		if(PlayData.gamemode==0)
		{
			buttonGM_1.setSelected(true);
			buttonGM_2.setSelected(false);
			buttonGM_3.setSelected(false);
			buttonGM_4.setSelected(false);
		}else if(PlayData.gamemode==1)
		{
			buttonGM_1.setSelected(false);
			buttonGM_2.setSelected(true);
			buttonGM_3.setSelected(false);
			buttonGM_4.setSelected(false);
		}
		else if(PlayData.gamemode==2)
		{
			buttonGM_1.setSelected(false);
			buttonGM_2.setSelected(false);
			buttonGM_3.setSelected(true);
			buttonGM_4.setSelected(false);
		}
		else if(PlayData.gamemode==3)
		{
			buttonGM_1.setSelected(false);
			buttonGM_2.setSelected(false);
			buttonGM_3.setSelected(false);
			buttonGM_4.setSelected(true);
		}
		
		groupGM.add(buttonGM_1);
		groupGM.add(buttonGM_2);
		groupGM.add(buttonGM_3);
		groupGM.add(buttonGM_4);
		panel_GM.add(J_GM);
		panel_GM.add(buttonGM_1);
		panel_GM.add(buttonGM_2);
		panel_GM.add(buttonGM_3);
		panel_GM.add(buttonGM_4);
		add(panel_GM);
		buttonGM_1.addActionListener(this);
		buttonGM_2.addActionListener(this);
		buttonGM_3.addActionListener(this);
		panel_GM.setBounds(28, 5, 590, 40);
		
		JLabel J_ShS = new JLabel("Shape Size:");
		JPanel panel_ShS = new JPanel();
		panel_ShS.setLayout(new FlowLayout());
		buttonShS_1 = new JRadioButton("Big"); 
		buttonShS_2 = new JRadioButton("Middle");  
		buttonShS_3 = new JRadioButton("Small"); 
		ButtonGroup groupShS = new ButtonGroup();
		if(PlayData.shapeSize==40)
		{
			buttonShS_1.setSelected(true);
			buttonShS_2.setSelected(false);
			buttonShS_3.setSelected(false);
		}else if(PlayData.shapeSize==20)
		{
			buttonShS_1.setSelected(false);
			buttonShS_2.setSelected(true);
			buttonShS_3.setSelected(false);
		}else if(PlayData.shapeSize==10)
		{
			buttonShS_1.setSelected(false);
			buttonShS_2.setSelected(false);
			buttonShS_3.setSelected(true);
		}
		
		groupShS.add(buttonShS_1);
		groupShS.add(buttonShS_2);
		groupShS.add(buttonShS_3);
		panel_ShS.add(J_ShS);
		panel_ShS.add(buttonShS_1);
		panel_ShS.add(buttonShS_2);
		panel_ShS.add(buttonShS_3);
		buttonShS_1.addActionListener(this);
		buttonShS_2.addActionListener(this);
		buttonShS_3.addActionListener(this);
		add(panel_ShS);
		panel_ShS.setBounds(85, 165, 350, 40);
		
		JLabel J_StS = new JLabel("Stage Size:");
		JPanel panel_StS = new JPanel();
		panel_StS.setLayout(new FlowLayout());
		buttonStS_1 = new JRadioButton("Big"); 
		buttonStS_2 = new JRadioButton("Middle");  
		buttonStS_3 = new JRadioButton("Small"); 
		ButtonGroup groupStS = new ButtonGroup();
		if(PlayData.stageSize==0)
		{
			buttonStS_1.setSelected(true);
			buttonStS_2.setSelected(false);
			buttonStS_3.setSelected(false);
		}else if(PlayData.stageSize==1)
		{
			buttonStS_1.setSelected(false);
			buttonStS_2.setSelected(true);
			buttonStS_3.setSelected(false);
		}else if(PlayData.stageSize==2)
		{
			buttonStS_1.setSelected(false);
			buttonStS_2.setSelected(false);
			buttonStS_3.setSelected(true);
		}
		
		groupStS.add(buttonStS_1);
		groupStS.add(buttonStS_2);
		groupStS.add(buttonStS_3);
		panel_StS.add(J_StS);
		panel_StS.add(buttonStS_1);
		panel_StS.add(buttonStS_2);
		panel_StS.add(buttonStS_3);
		buttonStS_1.addActionListener(this);
		buttonStS_2.addActionListener(this);
		buttonStS_3.addActionListener(this);
		add(panel_StS);
		panel_StS.setBounds(85, 200, 350, 40);
		
		JLabel J_CSL = new JLabel("Current Shape List:");
		J_CSL.setBounds(60, 235, 350, 40);
		add(J_CSL);
		JB_cus = new JButton("Customize shape");
		JB_cus.setBounds(180, 240, 140, 30);
		add(JB_cus);
		SLC = new ShapeListCanvas(this);
		SLC.setPreferredSize(new Dimension(400, 800));
		SLC.setBounds(60, 280, 320, 260);
		add(SLC);
		JB_cus.addActionListener(this);
		
		JLabel J_CCL = new JLabel("Current Color List:");
		J_CCL.setBounds(380, 235, 350, 40);
		add(J_CCL);
		JB_cus_color = new JButton("Add new color");
		JB_cus_color.setBounds(500, 240, 140, 30);
		add(JB_cus_color);
		CLC = new ColorListCanvas(this);
		CLC.setPreferredSize(new Dimension(400, 800));
		CLC.setBounds(360, 250, 350, 260);
		add(CLC);
		JB_cus_color.addActionListener(this);
		
		JB_ok = new JButton("OK");
		JB_ok.addActionListener(this);
		JB_ok.setBounds(325, 540, 60, 40);
		add(JB_ok);
		
		JButton JB_1 = new JButton("Add Color");
		JLabel J2 = new JLabel("Current Color List:");
		J2.setBounds(15, 215, 400, 40);
		JB_1.setBounds(135, 245, 100, 30);
		setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource().equals(buttonGM_1))//Hard
		{
			gameMode = 0;
			PlayData.setGameMode(gameMode);
			slider_M.setValue(PlayData.scoreFactor);
			slider_N.setValue(PlayData.rowsRequired);
			slider_S.setValue((int) (PlayData.speedFactor*10.0));
		}else if (e.getSource().equals(buttonGM_2))//Normal
		{
			gameMode = 1;
			PlayData.setGameMode(gameMode);
			slider_M.setValue(PlayData.scoreFactor);
			slider_N.setValue(PlayData.rowsRequired);
			slider_S.setValue((int) (PlayData.speedFactor*10.0));
		}
		else if (e.getSource().equals(buttonGM_3))//Easy
		{
			gameMode = 2;
			PlayData.setGameMode(gameMode);
			slider_M.setValue(PlayData.scoreFactor);
			slider_N.setValue(PlayData.rowsRequired);
			slider_S.setValue((int) (PlayData.speedFactor*10.0));
		}else if(e.getSource().equals(buttonGM_4))
		{
			gameMode = 3;
		}
		else if (e.getSource().equals(buttonShS_1))//Shape Size big
		{
			if(stageSize==2)
			{
				JOptionPane.showMessageDialog(null, "This particular group of stage size and shape size is not suppotted", "ERROR: unsupport", JOptionPane.ERROR_MESSAGE);
				buttonShS_2.setSelected(true);
			}
			else
				shapeSize = 0;
			PlayData.setShapeSize(shapeSize);
		}
		else if (e.getSource().equals(buttonShS_2))//Shape Size middle
		{
			shapeSize = 1;
			PlayData.setShapeSize(shapeSize);
		}
		else if (e.getSource().equals(buttonShS_3))//Shape Size small
		{
			shapeSize = 2;
			PlayData.setShapeSize(shapeSize);
		}
		else if (e.getSource().equals(buttonStS_1))//Stage Size big
		{
			stageSize = 0;
			PlayData.setStageSize(stageSize);
		}
		else if (e.getSource().equals(buttonStS_2))//Stage Size middle
		{
			stageSize = 1;
			PlayData.setStageSize(stageSize);
		}
		else if (e.getSource().equals(buttonStS_3))//Stage Size small
		{
			if(shapeSize==0)
			{
				buttonStS_2.setSelected(true);
				JOptionPane.showMessageDialog(null, "This particular group of stage size and shape size is not suppotted", "ERROR: unsupport", JOptionPane.ERROR_MESSAGE);
			}
			else
				stageSize = 2;
			PlayData.setStageSize(stageSize);
		}
		else if (e.getSource().equals(JB_cus))//Customize
		{
			Customize cm = new Customize(this);
			cm.setSwing();
		}
		else if (e.getSource().equals(JB_cus_color))//Customize
		{
			Color color = JColorChooser.showDialog(this, "Choose Color", Color.WHITE);
			if(color!=null)
				PlayData.addNewColor(color);
			CLC.repaint();
		}
		else if (e.getSource().equals(JB_ok))//OK
		{
			//PlayData.setGameFactor(gameMode, shapeSize, stageSize);
			this.setVisible(false);
			appview = new AppView();
		}
		
		//System.out.println("Game mode="+gameMode+",Shape Size="+shapeSize+",Stage Size="+stageSize);
		
	}
	
	int getGameMode()
	{
		return gameMode;
	}
	int getShapeSize()
	{
		return shapeSize;
	}
	int getStageSize()
	{
		return stageSize;
	}
	class ColorListCanvas extends Canvas
	{
		Setting s;
		ColorListCanvas(Setting s)
		{
			this.s = s;
		}
		@Override
		public void paint(Graphics g) {
			
			super.paint(g);
			
			int shapesize = 20;
			int LNcounter = -1;
			//
			if(PlayData.numberOfNewColor>PlayData.numberOfNewKind)
			{
				s.setSize(700, 630 + ((PlayData.numberOfTotalColor - 7) / 3)*(shapesize*4 + 5));
				s.CLC.setBounds(360, 250, 350, 260 + ((PlayData.numberOfTotalColor - 7) / 3)*(shapesize*4 + 5));
				s.SLC.setBounds(60, 280, 320, 260 + ((PlayData.numberOfTotalColor - 7) / 3)*(shapesize*4 + 5));
				s.JB_ok.setBounds(325, 540+((PlayData.numberOfTotalColor - 7) / 3)*(shapesize*4 + 5),  60, 40);
			}else
			{
				s.setSize(700, 630 + ((PlayData.numberOfTotalKind - 7) / 3)*(shapesize*4 + 5));
				s.SLC.setBounds(60, 280, 320, 260 + ((PlayData.numberOfTotalKind - 7) / 3)*(shapesize*4 + 5));
				s.CLC.setBounds(360, 250, 350, 260 + ((PlayData.numberOfTotalColor - 7) / 3)*(shapesize*4 + 5));
				s.JB_ok.setBounds(325, 540+((PlayData.numberOfTotalKind - 7) / 3)*(shapesize*4 + 5),  60, 40);
			}
			
			
			for(int k=0;k<PlayData.numberOfTotalColor;k++)
			{
				if((k)%3==0)
					LNcounter++;
				g.setColor(PlayData.getColor(k));
				g.fillRect(5+(k%3)*(shapesize*4+5)+shapesize,1+shapesize+LNcounter*(shapesize*4+5), shapesize*4, shapesize*4);
			}
			
		}
		
	}
	class ShapeListCanvas extends Canvas
	{
		Setting s;
		ShapeListCanvas(Setting s)
		{
			this.s = s;
		}
		@Override
		public void paint(Graphics g) {
			
			super.paint(g);
			
			int shapesize = 20;
			int LNcounter = -1;
			if(PlayData.numberOfNewColor>PlayData.numberOfNewKind)
			{
				s.setSize(700, 630 + ((PlayData.numberOfTotalColor - 7) / 3)*(shapesize*4 + 5));
				s.CLC.setBounds(360, 250, 350, 260 + ((PlayData.numberOfTotalColor - 7) / 3)*(shapesize*4 + 5));
				s.SLC.setBounds(60, 280, 320, 260 + ((PlayData.numberOfTotalColor - 7) / 3)*(shapesize*4 + 5));
				s.JB_ok.setBounds(325, 540+((PlayData.numberOfTotalColor - 7) / 3)*(shapesize*4 + 5),  60, 40);
			}else
			{
				s.setSize(700, 630 + ((PlayData.numberOfTotalKind - 7) / 3)*(shapesize*4 + 5));
				s.SLC.setBounds(60, 280, 320, 260 + ((PlayData.numberOfTotalKind - 7) / 3)*(shapesize*4 + 5));
				s.CLC.setBounds(360, 250, 350, 260 + ((PlayData.numberOfTotalColor - 7) / 3)*(shapesize*4 + 5));
				s.JB_ok.setBounds(325, 540+((PlayData.numberOfTotalKind - 7) / 3)*(shapesize*4 + 5),  60, 40);
			}
			
			for(int k=0;k<7+PlayData.numberOfNewKind;k++)
			{
				if((k)%3==0)
					LNcounter++;
				boolean shape[][][] = PlayData.getShape(k);
				g.setColor(Color.black);
				g.drawRect((k%3)*(shapesize*4+5),LNcounter*(shapesize*4+5), shapesize*4+5, shapesize*4+5);
				for(int i=3;i>=0;i--)
				{
					for(int j=0;j<4;j++)
					{
						
						if(shape[1][j][i])
						{
							g.setColor(Color.black);
							g.drawRect(5+(k%3)*(shapesize*4+5)+(j)*shapesize,1+(i)*shapesize+LNcounter*(shapesize*4+5), shapesize, shapesize);
							g.setColor(Color.green);
							g.fillRect(5+(k%3)*(shapesize*4+5)+(j)*shapesize+1,1+(i)*shapesize+LNcounter*(shapesize*4+5)+1, shapesize-1, shapesize-1);
						}
						
						//System.out.println("currentx="+(current_x+i*shapesize+KeyDirection)+"currenty="+
						  //       (current_y+j*shapesize));
					}
				}
			}
		}
		
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
		if(source==slider_M)
		{
			jtf_m.setText(String.valueOf(source.getValue()));
			PlayData.scoreFactor = source.getValue();
		}else if(source==slider_N)
		{
			jtf_n.setText(String.valueOf(source.getValue()));
			PlayData.rowsRequired = source.getValue();
		}
		else if(source==slider_S)
		{
			jtf_s.setText(String.valueOf(source.getValue()/10.0));
			PlayData.speedFactor = (float)source.getValue() / 10.0f;
		}
		if(slider_M.getValue()==10&&slider_N.getValue()==50&&slider_S.getValue()==10)
			buttonGM_1.setSelected(true);
		else if(slider_M.getValue()==5&&slider_N.getValue()==35&&slider_S.getValue()==5)
			buttonGM_2.setSelected(true);
		else if(slider_M.getValue()==1&&slider_N.getValue()==20&&slider_S.getValue()==1)
			buttonGM_3.setSelected(true);
		else
			buttonGM_4.setSelected(true);
		
	}

}
