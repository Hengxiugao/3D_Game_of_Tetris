package com.hengxiugao.CGcourse;



public class Tools2D {
	static boolean insideTriangle(Point2D A, Point2D B, Point2D C,
		      Point2D P) // ABC is assumed to be counter-clockwise
		   {  return
		        Tools2D.area2(A, B, P) >= 0 &&
		        Tools2D.area2(B, C, P) >= 0 &&
		        Tools2D.area2(C, A, P) >= 0;
		   }
	static boolean insidePolygon(Point2D p, Point2D[] pol)
	{  int n = pol.length, j = n - 1;
	   boolean b = false;
	   float x = p.x, y = p.y;
	   for (int i=0; i<n; i++)
	   { if (pol[j].y <= y && y < pol[i].y &&
	         Tools2D.area2(pol[j], pol[i], p) > 0 ||
	         pol[i].y <= y && y < pol[j].y &&
	         Tools2D.area2(pol[i], pol[j], p) > 0) b = !b;
	     j = i;

	    }
	    return b;
	}
	static float area2(Point2D A, Point2D B, Point2D C)
	   {  return (A.x - C.x) * (B.y - C.y) - (A.y - C.y) * (B.x - C.x);
	   }
}