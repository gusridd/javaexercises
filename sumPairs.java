import java.util.HashSet;

public class sumPairs {
	static public void main(String args[]){
		System.out.println("Hello World!");
	}

	static public HashSet<Pair> getSumPairs(int []arr, int s){
		HashSet<Pair> res = new HashSet<Pair>();
		HashSet<Integer> nums = new HashSet<Integer>();
		for(int i = 0; i<arr.length;i++){
			nums.add(arr[i]);
		}
		for(int e : nums){
			if(nums.contains(s-e)){
				res.add(new Pair(e,s-e));
			}
		}
		return res;
	}
}

public Pair{
	int x,y;
	public Pair(int x, int y){
		this.x = x;
		this.y = y;
	}
	public int hashCode(){
		return x*y;
	}
	public String toString(){
		return "("+x+","+y+")";
	}
}
