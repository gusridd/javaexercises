import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class p102 {

	static public void main(String args[]) throws FileNotFoundException{
		 Scanner s = new Scanner(new File("p102_triangles.txt"));
		 s.useDelimiter("[,\n\r\n\r]");
		 int i = 0;
		 Point p1,p2,p3,p;
		 p = new Point(0,0);
		 int amount = 0;
		 int x1,x2,x3,y1,y2,y3;
		 while(s.hasNextInt()){
		 	x1 = s.nextInt();
		 	y1 = s.nextInt();
		 	x2 = s.nextInt();
		 	y2 = s.nextInt();
		 	x3 = s.nextInt();
		 	y3 = s.nextInt();
		 	p1 = new Point(x1,y1);
		 	p2 = new Point(x2,y2);
		 	p3 = new Point(x3,y3);
		 	// System.out.println("x1: " + x1 + " y1: " + y1);
		 	double alpha = ((p2.y - p3.y)*(p.x - p3.x) + (p3.x - p2.x)*(p.y - p3.y)) /
        		((p2.y - p3.y)*(p1.x - p3.x) + (p3.x - p2.x)*(p1.y - p3.y));
			double beta = ((p3.y - p1.y)*(p.x - p3.x) + (p1.x - p3.x)*(p.y - p3.y)) /
       			((p2.y - p3.y)*(p1.x - p3.x) + (p3.x - p2.x)*(p1.y - p3.y));
			double gamma = 1.0f - alpha - beta;
			amount += (alpha >= 0.0 && beta >= 0.0 && gamma >= 0.0) ? 1 : 0;
		 }
		 System.out.println("Numbers of triangles containing the origin: " + amount);
	}
}

class Point{
	public double x;
	public double y;
	public Point(double x, double y){
		this.x = x;
		this.y = y;
	}
}