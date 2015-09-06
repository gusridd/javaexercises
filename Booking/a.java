import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.lang.Math;

public class NearbyAttractions {
	static public void main(String []args){
		Scanner s = new Scanner(System.in);
		int N = s.nextInt();
		Attraction []attractions = new Attraction[N];
		for(int i = 0; i<N ;i++){
			attractions[i] = new Attraction(s.nextInt(),s.nextDouble(),s.nextDouble());
			// System.out.println(attractions[i].toString());
		}
		int M = s.nextInt();
		Case []cases = new Case[M];
		for(int i = 0; i<M; i++){
			cases[i] = new Case(s.nextDouble(),s.nextDouble(),s.next(),s.nextInt());
			resolve(attractions,cases[i]);
		}
		
	}
	static public void resolve(Attraction []attractions, Case c){
		// System.out.println(c.toString());
		PriorityQueue<Tuple> res = new PriorityQueue<Tuple>();
		for(int i = 0; i<attractions.length; i++){
			Attraction a = attractions[i];
			Tuple t = c.getTuple(a);
			if(t.feasible){
				res.add(t);
			}
		}
		StringBuilder b = new StringBuilder();
		while(!res.isEmpty()){
			b.append(res.poll().attraction.id);
			b.append(" ");
			// System.out.print(res.poll().attraction.id + " ");
		}
		System.out.println(b.toString().trim());
	}
}

class Attraction {
	int id;
	Point p;
	double latitude;
	double longitude;
	public Attraction(int id, double lat, double lon){
		this.id = id;
		this.p = new Point(lat,lon);
	}
	public String toString(){
		return "[" + id + "] " + p.toString();
	}
}

class Tuple implements Comparable<Tuple>{
	double distance;
	Attraction attraction;
	boolean feasible;
	public Tuple(double d, Attraction a, boolean f){
		this.distance = d;
		this.attraction = a;
		this.feasible = f;
	}
	public int compareTo(Tuple t){
		if(Double.compare(distance,t.distance) != 0){
			return Double.compare(distance,t.distance);
		} else {
			return attraction.id - t.attraction.id;
		}
	}
}

class Case {
	Point p;
	String transport;
	int maxMinutes;
	public Case(double lat, double lon, String tr, int max){
		this.p = new Point(lat,lon);
		this.transport = tr;
		this.maxMinutes = max;
	}
	public String toString(){
		return p.toString() + " " + transport + " " + maxMinutes;
	}
	public Tuple getTuple(Attraction a){
		double d = p.distance(a.p);
		return new Tuple(d,a, getSpeed() >= d / maxMinutes);
	}

	// return speed in meters/min
	public double getSpeed() {
		if(transport.compareTo("metro") == 0){
			return 20;
		}
		if(transport.compareTo("foot") == 0){
			return 5;
		} else {
			return 15;
		}
	}
}

class Point {

	final static double PI=3.14159265359;
	final static double EARTH_RADIUS = 6371; // km

	double latitude;
	double longitude;

	public Point(double lat, double lon){
		this.latitude = lat;
		this.longitude = lon;
	}
	public String toString(){
		return "(" + latitude + ", " + longitude + ")";
	}

	public double distance(Point p){
		double point1_lat_in_radians = degree2radians(this.latitude);
		double point2_lat_in_radians = degree2radians(p.latitude);
		double point1_long_in_radians = degree2radians(this.longitude);
		double point2_long_in_radians = degree2radians(p.longitude);

		double ans = Math.acos( Math.sin( point1_lat_in_radians ) * Math.sin( point2_lat_in_radians ) +
                 Math.cos( point1_lat_in_radians ) * Math.cos( point2_lat_in_radians ) *
                 Math.cos( point2_long_in_radians - point1_long_in_radians) ) * EARTH_RADIUS;
		ans = Math.round(ans*100);
		return ans/100;
	}

	static double degree2radians(double degrees){
		return PI/180*degrees;
	}
}