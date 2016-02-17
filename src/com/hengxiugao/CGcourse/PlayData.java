package com.hengxiugao.CGcourse;

import java.awt.Color;
/*
 * Store the game default data
 * 
 * */
public class PlayData {
	private static boolean[][][][] ShapeOfSquares;
	private static boolean[][][][] newShapeOfSquares;
	private static Color ColorArr[] = {Color.magenta,Color.red,Color.orange,
		Color.yellow,Color.green,Color.cyan,Color.pink,Color.gray};
	static int BaseColor[][];
	private static Color newColorArr[] = new Color[99];
	public static float stageSize = 1;
	public static float shapeSize = 20;
	public static float speedFactor = 0.5f;
	public static int scoreFactor = 5;
	public static int rowsRequired = 35;
	public static float fallingSpeed = 20;
	public static int score = 0, lines = 0, level=1;
	public static int gameStatus = -1;
	public static int numberOfTotalKind = 7;
	public static int numberOfNewKind = 0;
	public static int numberOfTotalColor = ColorArr.length;
	public static int numberOfNewColor = 0;
	public static int gamemode = 1;
	public static int StageWidth = 300;
	public static int StageHeight = 600;
	
	PlayData()
	{
		
	}
	public static void setDefaultColors()
	{
		BaseColor = new int[8][3];
		BaseColor[0][0] = 0; //magenta
		BaseColor[0][1] = -255;
		BaseColor[0][2] = 0;
		
		BaseColor[1][0] = 0; //red
		BaseColor[1][1] = -255;
		BaseColor[1][2] = -255;
		
		BaseColor[2][0] = 0; //orange
		BaseColor[2][1] = -90;
		BaseColor[2][2] = -255;
		
		BaseColor[3][0] = 0; //yellow
		BaseColor[3][1] = 0;
		BaseColor[3][2] = -255;
		
		BaseColor[4][0] = -131; //green
		BaseColor[4][1] = -3;
		BaseColor[4][2] = -255;
		
		BaseColor[5][0] = -255; //cyan
		BaseColor[5][1] = 0;
		BaseColor[5][2] = 0;
		
		BaseColor[6][0] = 255; //pink
		BaseColor[6][1] = -63;
		BaseColor[6][2] = -52;
		
		BaseColor[7][0] = -128; //gray
		BaseColor[7][1] = -128;
		BaseColor[7][2] = -128;
	}
	public static void setDefaultShapes()
	{
		newShapeOfSquares = new boolean[99][4][4][4];
		ShapeOfSquares = new boolean[7][4][4][4];
		ShapeOfSquares[0][0][0][0] = true;//Square 0
		ShapeOfSquares[0][0][0][1] = true;
		ShapeOfSquares[0][0][1][0] = true;
		ShapeOfSquares[0][0][1][1] = true;
		
		ShapeOfSquares[0][1][0][0] = true;//Square 1
		ShapeOfSquares[0][1][0][1] = true;
		ShapeOfSquares[0][1][1][0] = true;
		ShapeOfSquares[0][1][1][1] = true;
		
		ShapeOfSquares[0][2][0][0] = true;//Square 2
		ShapeOfSquares[0][2][0][1] = true;
		ShapeOfSquares[0][2][1][0] = true;
		ShapeOfSquares[0][2][1][1] = true;
		
		ShapeOfSquares[0][3][0][0] = true;//Square 3
		ShapeOfSquares[0][3][0][1] = true;
		ShapeOfSquares[0][3][1][0] = true;
		ShapeOfSquares[0][3][1][1] = true;
		
		ShapeOfSquares[1][0][0][2] = true;//Shape i 0
		ShapeOfSquares[1][0][1][2] = true;
		ShapeOfSquares[1][0][2][2] = true;
		ShapeOfSquares[1][0][3][2] = true;
		
		ShapeOfSquares[1][1][1][0] = true;//Shape i 1
		ShapeOfSquares[1][1][1][1] = true;
		ShapeOfSquares[1][1][1][2] = true;
		ShapeOfSquares[1][1][1][3] = true;
		
		ShapeOfSquares[1][2][0][2] = true;//Shape i 2
		ShapeOfSquares[1][2][1][2] = true;
		ShapeOfSquares[1][2][2][2] = true;
		ShapeOfSquares[1][2][3][2] = true;
		
		ShapeOfSquares[1][3][1][0] = true;//Shape i 3
		ShapeOfSquares[1][3][1][1] = true;
		ShapeOfSquares[1][3][1][2] = true;
		ShapeOfSquares[1][3][1][3] = true;
		
		ShapeOfSquares[2][0][0][0] = true;//Shape L 0
		ShapeOfSquares[2][0][0][1] = true;
		ShapeOfSquares[2][0][1][1] = true;
		ShapeOfSquares[2][0][2][1] = true;
		
		ShapeOfSquares[2][1][0][0] = true;//Shape L 1
		ShapeOfSquares[2][1][0][1] = true;
		ShapeOfSquares[2][1][0][2] = true;
		ShapeOfSquares[2][1][1][0] = true;
		
		ShapeOfSquares[2][2][0][0] = true;//Shape L 2
		ShapeOfSquares[2][2][1][0] = true;
		ShapeOfSquares[2][2][2][0] = true;
		ShapeOfSquares[2][2][2][1] = true;
		
		ShapeOfSquares[2][3][1][0] = true;//Shape L 3
		ShapeOfSquares[2][3][1][1] = true;
		ShapeOfSquares[2][3][1][2] = true;
		ShapeOfSquares[2][3][0][2] = true;
		
		ShapeOfSquares[3][0][0][0] = true;//Shape z 0
		ShapeOfSquares[3][0][0][1] = true;
		ShapeOfSquares[3][0][1][1] = true;
		ShapeOfSquares[3][0][1][2] = true;
		
		ShapeOfSquares[3][1][0][2] = true;//Shape z 1
		ShapeOfSquares[3][1][1][1] = true;
		ShapeOfSquares[3][1][1][2] = true;
		ShapeOfSquares[3][1][2][1] = true;
		
		ShapeOfSquares[3][2][0][0] = true;//Shape z 2
		ShapeOfSquares[3][2][0][1] = true;
		ShapeOfSquares[3][2][1][1] = true;
		ShapeOfSquares[3][2][1][2] = true;
		
		ShapeOfSquares[3][3][0][2] = true;//Shape z 3
		ShapeOfSquares[3][3][1][1] = true;
		ShapeOfSquares[3][3][1][2] = true;
		ShapeOfSquares[3][3][2][1] = true;
		
		ShapeOfSquares[4][0][0][1] = true;//Shape r z 0
		ShapeOfSquares[4][0][0][2] = true;
		ShapeOfSquares[4][0][1][0] = true;
		ShapeOfSquares[4][0][1][1] = true;
		
		ShapeOfSquares[4][1][0][0] = true;//Shape r z 1
		ShapeOfSquares[4][1][1][0] = true;
		ShapeOfSquares[4][1][1][1] = true;
		ShapeOfSquares[4][1][2][1] = true;
		
		ShapeOfSquares[4][2][0][1] = true;//Shape r z 2
		ShapeOfSquares[4][2][0][2] = true;
		ShapeOfSquares[4][2][1][0] = true;
		ShapeOfSquares[4][2][1][1] = true;
		
		ShapeOfSquares[4][3][0][0] = true;//Shape r z 3
		ShapeOfSquares[4][3][1][0] = true;
		ShapeOfSquares[4][3][1][1] = true;
		ShapeOfSquares[4][3][2][1] = true;

		ShapeOfSquares[5][0][0][1] = true;//Shape r L 0
		ShapeOfSquares[5][0][1][1] = true;
		ShapeOfSquares[5][0][2][1] = true;
		ShapeOfSquares[5][0][2][0] = true;
		
		ShapeOfSquares[5][1][1][2] = true;//Shape r L 1
		ShapeOfSquares[5][1][0][0] = true;
		ShapeOfSquares[5][1][0][1] = true;
		ShapeOfSquares[5][1][0][2] = true;
		
		ShapeOfSquares[5][2][0][0] = true;//Shape r L 2
		ShapeOfSquares[5][2][1][0] = true;
		ShapeOfSquares[5][2][2][0] = true;
		ShapeOfSquares[5][2][0][1] = true;
		
		ShapeOfSquares[5][3][0][0] = true;//Shape r L 3
		ShapeOfSquares[5][3][1][0] = true;
		ShapeOfSquares[5][3][1][1] = true;
		ShapeOfSquares[5][3][1][2] = true;
		
		ShapeOfSquares[6][0][0][1] = true;//Shape t
		ShapeOfSquares[6][0][1][1] = true;
		ShapeOfSquares[6][0][2][1] = true;
		ShapeOfSquares[6][0][1][0] = true;
		
		ShapeOfSquares[6][1][1][1] = true;//Shape t
		ShapeOfSquares[6][1][0][0] = true;
		ShapeOfSquares[6][1][0][1] = true;
		ShapeOfSquares[6][1][0][2] = true;
		
		ShapeOfSquares[6][2][0][0] = true;//Shape t
		ShapeOfSquares[6][2][1][0] = true;
		ShapeOfSquares[6][2][2][0] = true;
		ShapeOfSquares[6][2][1][1] = true;
		
		ShapeOfSquares[6][3][0][1] = true;//Shape t
		ShapeOfSquares[6][3][1][0] = true;
		ShapeOfSquares[6][3][1][1] = true;
		ShapeOfSquares[6][3][1][2] = true;

		/*
		for(int i=0;i<4;i++)
		{
			System.out.println("Shape No."+i);
			for(int j=0;j<4;j++)
			{
				for(int k=0;k<4;k++)
				{
					if(ShapeOfSquares[i][0][j][k])
						System.out.print("1");
					else
						System.out.print("0");
				}
				System.out.println();
					
			}
			System.out.println();
		}
		*/
		
	}
	public static void setStageSize(int stagesize)
	{
		switch(stagesize)
		{
		case 2: 
			StageWidth = 100;
			StageHeight = 200;
			stageSize = 2;
			break;
		case 1: 
			StageWidth = 200;
			StageHeight = 400;
			stageSize = 1;
			break;
		case 0: 
			StageWidth = 400;
			StageHeight = 800;
			stageSize = 0;
			break;
		}
	}
	public static void setShapeSize(int shapesize)
	{
		switch(shapesize)
		{
		case 0: 
			shapeSize = 40;
			break;
		case 1: 
			shapeSize = 20;
			break;
		case 2: 
			shapeSize = 10;
			break;
		}
	}
	public static void setGameMode(int gameMode)
	{
		gamemode = gameMode;
		switch(gameMode)
		{
		case 0: //hard
			speedFactor = 1f;
			scoreFactor = 10;
			rowsRequired = 50;
			break;
		case 1: //normal
			speedFactor = 0.5f;
			scoreFactor = 5;
			rowsRequired = 35;
			break;
		case 2: //easy
			speedFactor = 0.1f;
			scoreFactor = 1;
			rowsRequired = 20;
			break;
		}
		//rowsRequired = 2;
	}
	public static void addNewShape(boolean newShape0[][], boolean newShape1[][],
			boolean newShape2[][],boolean newShape3[][])
	{
		newShapeOfSquares[numberOfNewKind][0] = newShape0;
		newShapeOfSquares[numberOfNewKind][1] = newShape1;
		newShapeOfSquares[numberOfNewKind][2] = newShape2;
		newShapeOfSquares[numberOfNewKind][3] = newShape3;
		numberOfNewKind++;
		numberOfTotalKind++;
	}
	public static void addNewColor(Color c)
	{
		newColorArr[numberOfNewColor++] = c;
		numberOfTotalColor++;
	}
	public static Color getColor(int num)
	{
		if(num<ColorArr.length)
			return ColorArr[num];
		else
			return newColorArr[num - ColorArr.length];
	}
	public static boolean[][][] getShape(int i)
	{
		if(i<7)
			return ShapeOfSquares[i];
		else
			return newShapeOfSquares[i-7];
	}
}
