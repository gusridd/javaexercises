public class Main {
	static public void main(String []args){
		Point p1 = new Point(1,2);
		Point p2 = new Point(1,2);
		Point p3 = new Point(1,2);
		ColorPoint cp1 = new ColorPoint(1,2,"red");
		ColorPoint cp2 = new ColorPoint(1,2,"red");
		ColorPoint cp3 = new ColorPoint(1,2,"red");
		ColorPoint cp4 = new ColorPoint(1,2,"blue");

		// if(p1.equals(p1)){
		// 	System.out.println("[Point] equals reflexive");
		// } else {
		// 	System.out.println("[Point] equals NOT reflexive");
		// }

		// if(p1.equals(p2) && p2.equals(p1)){
		// 	System.out.println("[Point] equals simmetric");
		// } else {
		// 	System.out.println("[Point] equals NOT simmetric");
		// }

		if( (p1.equals(p2) && p2.equals(p3) && p1.equals(p3))
		   || (!p1.equals(p2) && !p2.equals(p3) && !p1.equals(p3))){
			System.out.println("[Point] equals transitive");
		} else {
			System.out.println("[Point] equals NOT transitive");
		}

		// if(cp1.equals(cp1)){
		// 	System.out.println("[ColorPoint] equals reflexive");
		// } else {
		// 	System.out.println("[ColorPoint] equals NOT reflexive");
		// }

		// if(cp1.equals(cp2) && cp2.equals(cp1)){
		// 	System.out.println("[ColorPoint] equals simmetric");
		// } else {
		// 	System.out.println("[ColorPoint] equals NOT simmetric");
		// }

		if( (cp1.equals(cp2) && cp2.equals(cp3) && cp1.equals(cp3))
		   || (!cp1.equals(cp2) && !cp2.equals(cp3) && !cp1.equals(cp3))){
			System.out.println("[ColorPoint] equals transitive");
		} else {
			System.out.println("[ColorPoint] equals NOT transitive");
		}

		testCrossSymmetry(p1,cp1);
		testCrossSymmetry(p2,cp4);
		testCrossTransitivity(p1,cp1,p2);
		testCrossTransitivity(p1,cp1,p2);
		testCrossTransitivity2(cp1,p1,cp2);
		testCrossTransitivity2(cp1,p1,cp4);


		// if(p1.equals(cp2) && cp2.equals(p3) && p1.equals(p3)){
		// 	System.out.println("[Point&ColorPoint] equals transitive");
		// } else {
		// 	System.out.println("[Point&ColorPoint] equals NOT transitive");
		// }


	}
	static public void testCrossSymmetry(Point p, ColorPoint cp){
		if ( (p.equals(cp) && cp.equals(p))
			|| (!p.equals(cp) && !cp.equals(p))) {
			System.out.println("[Point&ColorPoint] equals simmetric");
		} else {
			System.out.println("[Point&ColorPoint] equals NOT simmetric");
		}
	}

	static public void testCrossTransitivity(Point p1, ColorPoint cp, Point p2){
		if( (p1.equals(cp) && cp.equals(p2) && p1.equals(p2))
		  || (!p1.equals(cp) && !cp.equals(p2) && !p1.equals(p2)) ){
			System.out.println("[Point&ColorPoint] equals transitive");
		} else {
			System.out.println("[Point&ColorPoint] equals NOT transitive");
		}
	}

	static public void testCrossTransitivity2(ColorPoint p1, Point cp, ColorPoint p2){
		if( (p1.equals(cp) && cp.equals(p2) && p1.equals(p2))
		  || (!p1.equals(cp) && !cp.equals(p2) && !p1.equals(p2)) ){
			System.out.println("[Point&ColorPoint] equals transitive");
		} else {
			System.out.println("[Point&ColorPoint] equals NOT transitive");
		}
	}
}

abstract class AbstractPoint{
	int x;
	int y;

	abstract public boolean compareToPoint(AbstractPoint p);

	public boolean equals(Object o){
		if(!(o instanceof AbstractPoint)){
			return false;
		} else {
			// delay implementation
			return ((AbstractPoint) o).compareToPoint(this);
		}
		
	}
	public int getX(){
		return this.x;
	}
	public int getY(){
		return this.y;
	}
}

class Point extends AbstractPoint {
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	// A point knows how to compare itself with another point
	public boolean compareToPoint(AbstractPoint ap){
		Point p = (Point)ap;
		return this.getX() == p.getX() && this.getY() == p.getY();
	}
}

class ColorPoint extends Point {
	String color;
	public ColorPoint(int x, int y, String c){
		super(x,y);
		this.color = c;
	}
	// A point knows how to compare itself with another point
	public boolean compareToPoint(AbstractPoint p){
		if(p instanceof ColorPoint){
			ColorPoint cp = (ColorPoint)p;
			return this.getX() == cp.getX() 
				&& this.getY() == cp.getY() 
				&& this.getColor().compareTo(cp.getColor()) == 0;
		} else {
			// compare as a regular Point would
			return super.compareToPoint(p);
		}
	}
	public String getColor(){
		return this.color;
	}
}