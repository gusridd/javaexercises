import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import static java.lang.Math.*;

public class NearbyAttractions {
	static public void main(String []args){
		Scanner s = new Scanner(System.in);
		int N = s.nextInt();
		Attraction []attractions = new Attraction[N];
		for(int i = 0; i<N ;i++){
			attractions[i] = new Attraction(s.nextInt(),s.nextDouble(),s.nextDouble());
		}
		int M = s.nextInt();
		Case []cases = new Case[M];
		for(int i = 0; i<M; i++){
			cases[i] = new Case(s.nextDouble(),s.nextDouble(),s.next(),s.nextInt());
			resolve(attractions,cases[i]);
		}
		
	}
	static public void resolve(Attraction []attractions, Case c){
		// PriorityQueue<Tuple> res = new PriorityQueue<Tuple>();
		ArrayList<Tuple> res = new ArrayList<Tuple>();
		for(int i = 0; i<attractions.length; i++){
			Attraction a = attractions[i];
			Tuple t = c.getTuple(a);
			if(t.feasible){
				res.add(t);
			}
		}
		Tuple[] arr = res.toArray(new Tuple[res.size()]);
		Arrays.sort(arr);
		// Collections.sort(res);
		StringBuilder b = new StringBuilder();
		for(int i = 0;i<arr.length;i++){
			b.append(arr[i].attraction.id);
			b.append(" ");
		}
		// while(!res.isEmpty()){
		// 	b.append(res.poll().attraction.id);
		// 	b.append(" ");
		// }
		System.out.println(b.toString().trim());
	}
}

class Attraction {
	int id;
	Point p;
	public Attraction(int id, double lat, double lon){
		this.id = id;
		this.p = new Point(lat,lon);
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
			return Integer.compare(attraction.id, t.attraction.id);
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
	public Tuple getTuple(Attraction a){
		double d = p.distance(a.p);
		return new Tuple(d,a,getSpeed(transport) * maxMinutes/60.0 >= d);
	}

	// return speed in meters/min
	public double getSpeed(String s){
		if(s.compareTo("metro") == 0){
			return 20.0;
		}
		if(s.compareTo("foot") == 0){
			return 5.0;
		}
		else {
			return 15.0;
		}
	}
}

class Point {

	final static double PI=3.14159265359;
	final static double EARTH_RADIUS = 6371;// [m]

	double latitude;
	double longitude;

	public Point(double lat, double lon){
		this.latitude = lat;
		this.longitude = lon;
	}

	public double distance(Point p){
		double point1_lat_in_radians = degree2radians(this.latitude);
		double point2_lat_in_radians = degree2radians(p.latitude);
		double point1_long_in_radians = degree2radians(this.longitude);
		double point2_long_in_radians = degree2radians(p.longitude);

		double ans = acos( sin( point1_lat_in_radians ) * sin( point2_lat_in_radians ) +
			cos( point1_lat_in_radians ) * cos( point2_lat_in_radians ) *
			cos( point2_long_in_radians - point1_long_in_radians) ) * EARTH_RADIUS;
		return round(ans*100)/100;
	}

	static double degree2radians(double degrees){
		return PI/180*degrees;
	}
}