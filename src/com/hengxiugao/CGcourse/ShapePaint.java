package com.hengxiugao.CGcourse;

import java.awt.Color;
import java.awt.Point;

/*
 * Record the shapes that have been frozen in the stage
 * */
public class ShapePaint
{
    private Point3D p;
	private boolean ispainted;
	private int color;
	ShapePaint(int x,int y, int z, int c)
	{
		p = new Point3D(x,y,z);
		ispainted = false;
		color = c;
	}
	void ChangePointPostion(int x,int y,int z)
	{
		p.x = x;
		p.y = y;
		p.z = z;
	}
	void Draw(int c)
	{
		SetColor(c);
		SetPainted(true);
	}
	void Replace(ShapePaint next)
	{
		this.color = next.color;
		this.ispainted = next.ispainted;
	}
	boolean isPainted()
	{
		return ispainted;
	}
	void SetPainted(boolean b)
	{
		ispainted = b;
	}
	Point3D getStartPoint()
	{
		return p;
	}
	int GetColor()
	{
		return color;
		
	}
	void SetColor(int c)
	{
		color = c;
	}
}
