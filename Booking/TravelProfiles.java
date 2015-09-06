import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class TravelProfiles {
	static public void main(String []args){
		Scanner s = new Scanner(System.in);
		int N = s.nextInt();
		Hotel []hotels = new Hotel[N];
		for(int i = 0; i<N;i++){
			int id = s.nextInt();
			int p = s.nextInt();
			HashSet<String> set = new HashSet<String>();
			while(!s.hasNextInt()){
				set.add(s.next());
			}
			hotels[i] = new Hotel(id,p,set);
			// System.out.println(hotels[i].toString());
		}
		int M = s.nextInt();
		for(int i = 0; i<M;i++){
			int max = s.nextInt();
			HashSet<String> set = new HashSet<String>();
			while(!s.hasNextInt() && s.hasNext()){
				set.add(s.next());
			}
			Need need = new Need(max,set);
			System.out.println(need.resolve(hotels));
		}
	}
}

class Need {
	int maxPrice;
	Set<String> facilities;
	public Need(int m, Set<String> f){
		this.maxPrice = m;
		this.facilities = f;
	}

	public String resolve(Hotel[] hotels){
		PriorityQueue<Hotel> res = new PriorityQueue<Hotel>();
		for(int i = 0; i<hotels.length; i++){
			Hotel h = hotels[i];
			if(h.price < maxPrice){
				boolean complies = true;
				for(String f : facilities){
					complies = complies && h.facilities.contains(f);
				}
				if(complies){
					res.add(h);
				}
			}
		}
		StringBuilder b = new StringBuilder();
		while(!res.isEmpty()){
			b.append(res.poll().id);
			b.append(" ");
		}
		return b.toString().trim();
	}
}

class Hotel implements Comparable { 
	int id;
	int price;
	Set facilities;
	public Hotel(int id, int p, Set f){
		this.id = id;
		this.price = p;
		this.facilities = f;
	}
	public String toString(){
		return id + " " + price + facilities.toString();
	}

	public int compareTo(Object o){
		if(o instanceof Hotel){
			Hotel ho = (Hotel)o;
			if(facilities.size() > ho.facilities.size()){
				return -1;
			} else if(facilities.size() < ho.facilities.size()){
				return 1;
			} else 	if(price > ho.price){
				return 1;
			} else 	if(price < ho.price){
				return -1;
			} else {
				return id-ho.id;
			}
		} else {
			return 0;
		}
	}
}