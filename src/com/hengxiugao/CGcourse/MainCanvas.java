package com.hengxiugao.CGcourse;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;
import java.util.Stack;
import java.util.Vector;


public class MainCanvas extends Canvas implements MouseMotionListener,Runnable
{
	/**
	 * Perform displaying the game.
	 */
	
	public boolean StatusChageFlag;
	public boolean CurrentReachBottom = false;
	public int ShapeDirection = 0;
	int kind = 0,nextkind=0,thisColori,nextColori;
	Color thisColor,nextColor;
	public ShapePaint[][][] ShapePainted = new ShapePaint[80][40][2];
	boolean shape[][][];
	float ratio = 1;
	private int DebugMode = 0;
	int KeySataus=0;
	AppView app;
	boolean ChangeShapeFlag = true;
	float ChangeRatio=1;
	int maxX, maxY, centerX, centerY, maxX0 = -1, maxY0 = -1;
    float buf[][];
    Obj3D obj_stage;
    ObjCube obj_cube;
    Point2D imgCenter;
    int xrightmost_globa,xrightmost_globamin,ymax,ymaxgl,xleftmost_globa,xleftmost_globamax;
    int currentx_3D=-2,currenty_3D=0,currentz_3D = 14;
    MainCanvas(AppView app)//Construction
	{
		//current_x = 2*PlayData.shapeSize;
		//current_y = -2*PlayData.shapeSize;
		this.app = app;
		for(int i=0; i<80;i++)
			for(int j=0;j<40;j++)
				for(int k=0;k<2;k++)
				{
					ShapePainted[i][j][k] = new ShapePaint(i,j,k,0);
					ShapePainted[i][j][k].SetPainted(false);
				}
			
		obj_cube = new ObjCube();
		obj_cube.read("data.txt");
		obj_stage = new Obj3D();
		obj_stage.read("datastage.txt");
		
		obj_cube.vp(this, 0, 0, 15f);
		obj_stage.vp(this, 0, 0, 15f);
		
		/*
		obj_cube.Setvp(this, 0, 0f, 360);
		obj_stage.Setvp(this, 0, 0f, 360);
		*/
		buf = new float[2000][2000];
		Random random = new Random(System.currentTimeMillis());
		kind = random.nextInt(PlayData.numberOfTotalKind);
		nextkind = random.nextInt(PlayData.numberOfTotalKind);
		thisColori = random.nextInt(PlayData.numberOfTotalColor);
		thisColor = PlayData.getColor(thisColori);
		nextColori = random.nextInt(PlayData.numberOfTotalColor);
		nextColor = PlayData.getColor(nextColori);
		addMouseMotionListener(this);
		addMouseListener(new MouseAdapter(){

			@Override
			public void mouseEntered(MouseEvent arg0) {
				
				super.mouseEntered(arg0);
				if(PlayData.gameStatus==1)
				{
					PlayData.gameStatus = 2;
					StatusChageFlag = true;
				}else if(PlayData.gameStatus==0)
				{
					PlayData.gameStatus = 3;
					StatusChageFlag = true;
				}
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				super.mouseExited(arg0);
				if(PlayData.gameStatus==2)
				{
					PlayData.gameStatus = 1;
					StatusChageFlag = true;
				}
			}
		});
	}

	void SwitchDebugMode()
	{
		if(DebugMode==0)
		{
			DebugMode = 1;
			System.out.println("Debug Mode Now Open");
		}
		else if(DebugMode==1)
			{
				DebugMode = 0;
				System.out.println("Debug Mode Now Close");
			}
	}
	int iX(float x){return Math.round(centerX + x - imgCenter.x);}
	int iY(float y){return Math.round(centerY - y + imgCenter.y);}
    void vp(float dTheta, float dPhi, float fRho) // Viewpoint
		{  
    		//obj_cube.printCoor();
		   if (obj_stage == null  ||   !obj_stage.vp(this, dTheta, dPhi, fRho))
		      Toolkit.getDefaultToolkit().beep();
		   if (obj_cube == null  ||   !obj_cube.vp(this, dTheta, dPhi, fRho))
			      Toolkit.getDefaultToolkit().beep();
		}
    
    
	int drawCube(Graphics g, int color)
	{
		Vector polyList = obj_cube.getPolyList();
		if (polyList == null) return -1;
		int nFaces = polyList.size();
		if (nFaces == 0) return - 1;
		float xe, ye, ze;

		Dimension dim = getSize();
		maxX = dim.width - 1; maxY = dim.height - 1;
		centerX = maxX/2; centerY = maxY/2;
		for (int iy=0; iy<500; iy++)
			for (int ix=0; ix<500; ix++)
				buf[ix][iy] = 1e30F;

		obj_cube.eyeAndScreen(dim,0);
		imgCenter = obj_cube.getImgCenter();
		obj_cube.planeCoeff(); // Compute a, b, c and h.
		Point3D[] e = obj_cube.getE();
		Point2D[] vScr = obj_cube.getVScr();
		xrightmost_globa = 0;
		xleftmost_globa = 1000;
		ymax = 0;
		for (int j=0; j<nFaces; j++)
		{
			Polygon3D pol = (Polygon3D)(polyList.elementAt(j));
			if (pol.getNrs().length < 3 || pol.getH() >= 0)
				continue;
			int cCode = obj_cube.colorCode(pol.getA(), pol.getB(), pol.getC());
			
			int base_color[] = PlayData.BaseColor[color];
			int tcolor[] = new int[3];
			for(int i=0;i<3;i++)
			{
				tcolor[i] = base_color[i] + cCode;
				if(tcolor[i]>255) tcolor[i] = 255;
					else if(tcolor[i]<0) tcolor[i] = 0;
			}
			
			g.setColor(new Color(tcolor[0], tcolor[1], tcolor[2]));
			
			pol.triangulate(obj_cube);
			Tria[] t = pol.getT();
			for (int i=0; i<t.length; i++)
			{
				Tria tri = t[i];
				int iA = tri.iA, iB = tri.iB, iC = tri.iC;
				Point2D a = vScr[iA], b = vScr[iB], c = vScr[iC];
				double zAi = 1/e[tri.iA].z, zBi = 1/e[tri.iB].z, zCi = 1/e[tri.iC].z;
				double u1 = b.x - a.x, v1 = c.x - a.x,
				       u2 = b.y - a.y, v2 = c.y - a.y,
				       cc=u1*v2-u2*v1;
				if (cc <= 0) continue;
				double xA = a.x, yA = a.y,
						xB = b.x, yB = b.y,
						xC = c.x, yC = c.y,
						xD = (xA + xB + xC)/3,
						yD = (yA + yB + yC)/3,
						zDi = (zAi + zBi + zCi)/3,
						u3 = zBi - zAi, v3 = zCi - zAi,
						aa=u2*v3-u3*v2,
						bb=u3*v1-u1*v3,
						dzdx = -aa/cc, dzdy = -bb/cc,
						yBottomR = Math.min(yA, Math.min(yB, yC)),
						yTopR = Math.max(yA, Math.max(yB, yC));
				int yBottom = (int)Math.ceil(yBottomR), yTop = (int)Math.floor(yTopR);
				for (int y=yBottom; y<=yTop; y++)
				{  
					// Compute horizontal line segment (xL, xR) 
					// for coordinate y:
					double xI, xJ, xK, xI1, xJ1, xK1, xL, xR;
					xI = xJ = xK = 1e30;
					xI1 = xJ1 = xK1 = -1e30;
					if((y-yB)*(y-yC)<=0&&yB!=yC)
						xI = xI1 = xC + (y - yC)/(yB - yC) * (xB - xC);
					if((y-yC)*(y-yA)<=0&&yC!=yA)
						xJ = xJ1 = xA + (y - yA)/(yC - yA) * (xC - xA);
					if((y-yA)*(y-yB)<=0&&yA!=yB)
						xK = xK1 = xB + (y - yB)/(yA - yB) * (xA - xB);
					// xL = xR = xI;
					xL = Math.min(xI, Math.min(xJ, xK));
					xR = Math.max(xI1, Math.max(xJ1, xK1));
					int iy = iY((float)y);
					int iXL = iX((float)(xL+0.5));
					int iXR = iX((float)(xR-0.5));
					double zi = 1.01 * zDi + (y - yD) * dzdy + (xL - xD) * dzdx;
					boolean leftmostValid = false;
					int xLeftmost = 0;
					//System.out.println(iXL+","+iXR);
					if(iXL<0) iXL = 0;
					if(iy<0) iy = 0;
					for (int ix=iXL; ix<=iXR; ix++)
					{
						if (zi < buf[ix][iy]) // < means nearer
						{  
							if (!leftmostValid)
							{  
								xLeftmost = ix;
								leftmostValid = true;
							}
							buf[ix][iy] = (float)zi;
				        }
						else if (leftmostValid)
				        {  
							g.drawLine(xLeftmost, iy, (ix-1), iy);
							leftmostValid = false;
				        } 
						zi += dzdx;
					}
					if (leftmostValid)
					{
						g.drawLine(xLeftmost, iy, iXR, iy);
					}
					// ---
					if(xLeftmost>0&&xleftmost_globa>xLeftmost)
						xleftmost_globa = xLeftmost;
					if(ymax<iy)
						ymax = iy;
					if(xrightmost_globa<iXR)
						xrightmost_globa = iXR;
				}
			}
		}
		//System.out.println(xleftmost_globa);
		return xrightmost_globa;
	   }
	
	void drawStage(Graphics g)
	   {
		Vector polyList = obj_stage.getPolyList();
		if (polyList == null) return;
		int nFaces = polyList.size();
		if (nFaces == 0) return;
		float xe, ye, ze;

		Dimension dim = getSize();
		maxX = dim.width - 1; maxY = dim.height - 1;
		centerX = maxX/2; centerY = maxY/2;
		      
		// ze-axis towards eye, so ze-coordinates of
		// object points are all negative. Since screen
		// coordinates x and y are used to interpolate for
		// the z-direction, we have to deal with 1/z instead
		// of z. With negative z, a small value of 1/z means
		// a small value of |z| for a nearby point. We there-
		// fore begin with large buffer values 1e30:
		
		for (int iy=0; iy<dim.height; iy++)
			for (int ix=0; ix<dim.width; ix++)
				buf[ix][iy] = 1e30F;

		obj_stage.eyeAndScreen(dim,0);
		imgCenter = obj_stage.getImgCenter();
		obj_stage.planeCoeff(); // Compute a, b, c and h.
		Point3D[] e = obj_stage.getE();
		Point2D[] vScr = obj_stage.getVScr();

		for (int j=0; j<nFaces; j++)
		{
			Polygon3D pol = (Polygon3D)(polyList.elementAt(j));
			if (pol.getNrs().length < 3 || pol.getH() >= 0)
				continue;
			int cCode = obj_stage.colorCode(pol.getA(), pol.getB(), pol.getC());
			if(cCode<100) cCode = 100;
			if(cCode>=255) cCode = 255;
			g.setColor(new Color(cCode, cCode, cCode));
			
			pol.triangulate(obj_stage);
			Tria[] t = pol.getT();
			for (int i=0; i<t.length; i++)
			{
				Tria tri = t[i];
				int iA = tri.iA, iB = tri.iB, iC = tri.iC;
				Point2D a = vScr[iA], b = vScr[iB], c = vScr[iC];
				double zAi = 1/e[tri.iA].z, zBi = 1/e[tri.iB].z, zCi = 1/e[tri.iC].z;
				// We now compute the coefficients a, b and c
				// (written here as aa, bb and cc)
				// of the imaginary plane ax + by + czi = h,
				// where zi is 1/z (and x, y and z are
				// eye coordinates. Then we compute
				// the partial derivatives dzdx and dzdy:
				double u1 = b.x - a.x, v1 = c.x - a.x,
				       u2 = b.y - a.y, v2 = c.y - a.y,
				       cc=u1*v2-u2*v1;
				if (cc <= 0) continue;
				double xA = a.x, yA = a.y,
						xB = b.x, yB = b.y,
						xC = c.x, yC = c.y,
						xD = (xA + xB + xC)/3,
						yD = (yA + yB + yC)/3,
						zDi = (zAi + zBi + zCi)/3,
						u3 = zBi - zAi, v3 = zCi - zAi,
						aa=u2*v3-u3*v2,
						bb=u3*v1-u1*v3,
						dzdx = -aa/cc, dzdy = -bb/cc,
						yBottomR = Math.min(yA, Math.min(yB, yC)),
						yTopR = Math.max(yA, Math.max(yB, yC));
				int yBottom = (int)Math.ceil(yBottomR), yTop = (int)Math.floor(yTopR);
				for (int y=yBottom; y<=yTop; y++)
				{  
					// Compute horizontal line segment (xL, xR) 
					// for coordinate y:
					double xI, xJ, xK, xI1, xJ1, xK1, xL, xR;
					xI = xJ = xK = 1e30;
					xI1 = xJ1 = xK1 = -1e30;
					if((y-yB)*(y-yC)<=0&&yB!=yC)
						xI = xI1 = xC + (y - yC)/(yB - yC) * (xB - xC);
					if((y-yC)*(y-yA)<=0&&yC!=yA)
						xJ = xJ1 = xA + (y - yA)/(yC - yA) * (xC - xA);
					if((y-yA)*(y-yB)<=0&&yA!=yB)
						xK = xK1 = xB + (y - yB)/(yA - yB) * (xA - xB);
					// xL = xR = xI;
					xL = Math.min(xI, Math.min(xJ, xK));
					xR = Math.max(xI1, Math.max(xJ1, xK1));
					int iy = iY((float)y), iXL = iX((float)(xL+0.5)), iXR = iX((float)(xR-0.5));
					double zi = 1.01 * zDi + (y - yD) * dzdy + (xL - xD) * dzdx;
					boolean leftmostValid = false;
					int xLeftmost = 0;
					for (int ix=iXL; ix<=iXR; ix++)
					{  
						if (zi < buf[ix][iy]) // < means nearer
						{  
							if (!leftmostValid)
							{  
								xLeftmost = ix;
								leftmostValid = true;
							}
							buf[ix][iy] = (float)zi;
				        }
						else if (leftmostValid)
				        {  
							g.drawLine(xLeftmost, iy, (ix-1), iy);
							leftmostValid = false;
				        } 
						zi += dzdx;
					}
					if (leftmostValid)
					{
						g.drawLine(xLeftmost, iy, iXR, iy);
					}
					// ---
				}
			}
		}
	   }
	
	public void paint(Graphics g) {
		
		
		Image iBuffer = null; //Double Buffer to avoid flash
		Graphics gBuffer = null;
		//ChangeRatio = 1;
		//app.ChangeRatio = 1;
		//PlayData.shapeSize = 20 * ChangeRatio;
		int shapesize = (int) PlayData.shapeSize;
		if(iBuffer==null)
		{
			iBuffer = createImage(this.getSize().width,this.getSize().height);
			gBuffer = iBuffer.getGraphics();
		}
		super.paint(gBuffer);
		
		drawStage(gBuffer);
		
		switch(KeySataus)
		{
		case 1:
			ShapeDirection += 1;
			ShapeDirection %= 4;
			break;
		case 2:
			ShapeDirection -= 1;
			if(ShapeDirection<0) ShapeDirection = 3;
			break;
		case 3:
			currenty_3D-=2;
			break;
		case 4:
			currenty_3D+=2;
			break;
		case 5:
			currentx_3D -=2;//Z
			break;
		case 6:
			currentx_3D +=2;
			break;
		case 7:
			currentz_3D -=2;//Y
			break;
		case 8:
			currentz_3D +=2;
			break;
		
		}
		shape = PlayData.getShape(kind);
		
		/*Draw shapes have already in*/
		for(int i=0;i<20;i++)
		{
			for(int j=0;j<10;j++)
			{
				int jj= j, ii = i;
				if(obj_cube.theta<0)
					jj = 9 - j;
				
				if(obj_cube.phi>=1.5)
					ii = 19 - i;
				
				for(int k=0;k<2;k++)
				{
					if(ShapePainted[ii][jj][k].isPainted())
					{
						obj_cube.ChangeWorldCoor(k*2-2, jj*2-8, ii*2-22);
						drawCube(gBuffer,ShapePainted[ii][jj][k].GetColor()); 
					}
				}
				
			}
		}
		/*Draw current down shape*/
		
		if(BorderDetection()) CurrentReachBottom = true;//If The Shape has been frozen
		
		if(IntersectionDetection())
		{
			if(KeySataus!=3&&KeySataus!=4)
			{
				CurrentReachBottom = true;
				currentz_3D += 2;
			}
			if(DebugMode==1) System.out.print(KeySataus);
			if(KeySataus==4)
				currenty_3D -= 2;
			else if(KeySataus==3)
				currenty_3D += 2;
				
		}
		else if(PlayData.gameStatus==1||PlayData.gameStatus==2||PlayData.gameStatus==3)
		{
			xrightmost_globamin = 0;
			ymaxgl = 0;
			xleftmost_globamax = 0;
			for(int i=0;i<4;i++)
			{
				
				for(int j=0;j<4;j++)
				{
					int ii= i, jj=j;
					if(obj_cube.theta<0)
						ii = 3 - i;
					
					if(obj_cube.phi>=1.5)
						jj = 3 - j;
					
					if(shape[ShapeDirection][jj][ii]) 
					{
						obj_cube.ChangeWorldCoor(currentx_3D, currenty_3D+ii*2, currentz_3D+jj*2);
						
						int right = drawCube(gBuffer,thisColori); 
						if(xleftmost_globamax<xleftmost_globa)
							xleftmost_globamax = xleftmost_globa;
						if(xrightmost_globamin<right)
							xrightmost_globamin = right;
						if(ymaxgl<ymax)
							ymaxgl = ymax;
					}
					
				}
			}
			//System.out.println(xleftmost_globamax+","+xrightmost_globamin+","+ymaxgl);
			gBuffer.setColor(Color.red);
			int base = xrightmost_globamin-xleftmost_globamax;
			//gBuffer.drawRect(xleftmost_globamax, ymaxgl, xrightmost_globamin-xleftmost_globamax, 80);
		}
		int start = xrightmost_globamin;
		//gBuffer.drawRect(xrightmost_globamin, xrightmost_globamin, width, height);
		//System.out.println("x="+currentx_3D+",y="+currenty_3D+",z="+currentz_3D);
		//obj_cube.printVP();
		if(CurrentReachBottom) // Current shape reach the bottom
		{
			//System.out.println("x="+currentx_3D+",y="+currenty_3D+",z="+currentz_3D);
			for(int ii=0;ii<4;ii++)
				for(int jj=0;jj<4;jj++)
					if(shape[ShapeDirection][ii][jj])
					{
						if((int)currentz_3D+ jj>12)
							PlayData.gameStatus = 8;
						else
							ShapePainted[(currentz_3D+22)/2 + ii][(currenty_3D+8)/2 + jj][currentx_3D/2+1].Draw(thisColori);
					}
			
			if(PlayData.gameStatus!=8)
			{
				
				Stack<Integer> ES = CheckEliminate();
				while(!ES.empty())
				{
					PlayData.lines += 1;
					if(PlayData.lines>=PlayData.rowsRequired * PlayData.level)
					{
						PlayData.level ++;
						PlayData.fallingSpeed *= (int)(1 + PlayData.level * PlayData.speedFactor);
					}
					PlayData.score += PlayData.level*PlayData.scoreFactor;
					int target = ES.pop();
					if(DebugMode==1) System.out.println("Eliminate target="+target);
					for(int j=0;j<10;j++)
						for(int i=target;i<19;i++)
						{
							ShapePainted[i][j][currentx_3D/2+1].Replace(ShapePainted[i+1][j][currentx_3D/2+1]);
						}
				}
				
				
				/* Prepare for next random shape*/
				
				Random random = new Random(System.currentTimeMillis());
				//System.out.println(kind);
				
				
				
				kind = nextkind;
				nextkind = random.nextInt(PlayData.numberOfTotalKind);
				
				thisColor = nextColor;
				thisColori = nextColori;
				nextColori = random.nextInt(PlayData.numberOfTotalColor);
				nextColor = PlayData.getColor(nextColori);
				
				
				currentx_3D=-2;
				currenty_3D=0;
				currentz_3D = 14;
				CurrentReachBottom = false;
			}
		}
		KeySataus = 0;
		//if(KeyDirection!=0) KeyDirection = 0;
		
		if(PlayData.gameStatus==2||PlayData.gameStatus==3) //Game pause
		{
			gBuffer.setColor(Color.black);
			gBuffer.drawRect((int)(ChangeRatio*85),(int)(ChangeRatio*250),(int)(ChangeRatio*120),(int)(ChangeRatio*30));
			gBuffer.setColor(Color.BLUE);
			gBuffer.setFont(new Font("SansSerif",Font.BOLD,(int)(ChangeRatio*22)));
			gBuffer.drawString("PAUSE", (int)(ChangeRatio*108), (int)(ChangeRatio*275));
			
			if(StatusChageFlag)
			{
				StatusChageFlag = false;
				this.repaint();
			}
			
		}else if(PlayData.gameStatus==8) //Game terminates
		{
			gBuffer.setColor(Color.black);
			gBuffer.drawRect((int)(ChangeRatio*40),(int)(ChangeRatio*200),(int)(ChangeRatio*120),(int)(ChangeRatio*30));
			gBuffer.setColor(Color.BLACK);
			gBuffer.setFont(new Font("SansSerif",Font.BOLD,(int)(ChangeRatio*19)));
			gBuffer.drawString("GAME OVER", (int)(ChangeRatio*42), (int)(ChangeRatio*225));
			this.repaint();
		}
		
		g.drawImage(iBuffer, 0, 0,this);
	
	}
	void putMap()
	{
		
		for(int i=19;i>=0;i--)
		{
			for(int j=0;j<10;j++)
				if(ShapePainted[i][j][currentx_3D/2+1].isPainted())
					System.out.print("1 ");
				else 
					System.out.print("0 ");
			System.out.println();
		}
		
	}
	
	public Stack<Integer> CheckEliminate() //Find the line which are full, prepare to eliminate.
	{
		//putMap();
		
		Stack<Integer> result = new Stack<Integer>();
		boolean EliminateFlag;
		for(int i=0;i<20;i++)
		{
			EliminateFlag = true;
			for(int j=0;j<10;j++)
			{
				if(!ShapePainted[i][j][currentx_3D/2+1].isPainted())
				{
					EliminateFlag = false;
					break;
				}
			}
			if(EliminateFlag)
			{
				//System.out.println("hahaha");
				result.push(i);
			}
		}
		return result;
	}
	
	public boolean BorderDetection()
	{
		int imin = 5,imax = -1;
		for(int i=3;i>=0;i--)
		{
			for(int j=3;j>=0;j--)
			{
				if(shape[ShapeDirection][j][i])
				{
					if(imin>i) imin = i;
					if(imax<i) imax = i;
					
					if((currentz_3D+j*2)<-22) // Bottom border detection
					{
						currentz_3D += 2;//400-20
						return true;
					}
					
					
				}
			}
		}
		if(currenty_3D+imin*2<-8)  //Left and right border detection.
			currenty_3D += 2;
		else if(currenty_3D+imax*2>10)
			currenty_3D -= 2;
		if(currentx_3D>0)
			currentx_3D = 0;
		else if(currentx_3D<-2)
			currentx_3D = -2;
		
		return false;
	}
	
	public boolean IntersectionDetection() //Avoid two shapes overlap
	{
		
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
			{
				if(shape[ShapeDirection][j][i])
				{
					if(ShapePainted[(currentz_3D+22)/2 + j][(currenty_3D+8)/2 + i][currentx_3D/2+1].isPainted())
					return true;
				}
				
				
			}
		}
		
		return false;
	}
	

	@Override
	public void mouseDragged(MouseEvent e) {
		
		
	}
	public Point2D[] getPointSet()//TODO could be optimized to reduce the overlap point.
	{
		Point2D[] temp = new Point2D[65];
		for(int i=0;i<64;i++)
			temp[i] = new Point2D(-999,-999);
		int count = 0;
		int current_x=0,current_y=0;
		try{
		
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++)
			{
				if(shape[ShapeDirection][j][i])
				{
					temp[count].x = current_x + j * PlayData.shapeSize;
					temp[count].y =   current_y + i * PlayData.shapeSize;
					temp[++count].x = current_x + (j +1) * PlayData.shapeSize;
					temp[count].y =   current_y + i * PlayData.shapeSize;
					temp[++count].x = current_x + j * PlayData.shapeSize;
					temp[count].y =   current_y + (i + 1) * PlayData.shapeSize;
					temp[++count].x = current_x + (j + 1) * PlayData.shapeSize;
					temp[count].y =   current_y + (i + 1) * PlayData.shapeSize;
					count++;
				}
			}
		}
		catch(Exception e){
			//System.out.println(e.getMessage());
		}
		Point2D[] result = new Point2D[count];
		for(int i=0;i<count;i++)
		{
			result[i] = new Point2D(temp[i].x,temp[i].y);
		}
		/*//Debug
		int i=0;
		while (result[i].x!=-999)
		{
			int base = 100;
			g.setColor(Color.red);
			g.drawLine(base+(int)result[i].x, (int)result[i].y, base+(int)result[i].x, (int)result[i].y);
			i++;
		}
		*/
		return result;
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		//System.out.println("current_x="+current_x+",current_y="+current_y+",X="+e.getX()+", Y="+e.getY());
		Point2D[] polygon = getPointSet();
		Point2D cusor = new Point2D(e.getX(),e.getY());
		
		boolean is_inside = Tools2D.insidePolygon(cusor, polygon);
		if(ChangeShapeFlag&&is_inside)
		{
			//System.out.println("caonima");
			/* Prepare for next random shape*/
			ChangeShapeFlag = false;
			Random random = new Random(System.currentTimeMillis());
			//System.out.println(kind);
			kind = nextkind;
			nextkind = random.nextInt(PlayData.numberOfTotalKind);
			thisColori = nextColori;
			nextColori = random.nextInt(PlayData.numberOfTotalColor);
			nextColor = PlayData.getColor(nextColori);
			PlayData.score -= PlayData.level * PlayData.scoreFactor;
		}else if(!is_inside)
		{
			ChangeShapeFlag = true;
		}
		

		
	}

	@Override
	public void run() {
		//System.out.println(111);
		while(PlayData.gameStatus!=8)
		{
			try {
				//Thread.sleep((long) (200/(PlayData.level*PlayData.speedFactor)));
				Thread.sleep((long) 200);
				if(PlayData.gameStatus==1)
		    	  {
					currentz_3D -= 2;
			    	  
		    	  }
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}