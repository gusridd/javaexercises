import java.util.Scanner;
import java.util.HashMap;
import java.lang.Math;

class Main {

	static private HashMap<Long,Integer> mem = new HashMap<Long,Integer>();

	static public void main(String []args){
		Scanner s = new Scanner(System.in);
		boolean first = true;
		while(s.hasNext()){
			if(first){
				first = false;
			} else {
				System.out.println("");
			}
			int i = s.nextInt();
			int j = s.nextInt();
			int max = 0;
			int start = Math.min(i,j);
			int end = Math.max(i,j);
			for(long k = start; k <= end;k++){
				int calc = cycleLenght(k);
				mem.put(k,calc);
				max = (calc > max) ? calc : max;
			}
			System.out.print(i+" "+j+" "+max);
		}
	}

	// static public int cycleLenght(int n, int length){
	// 	int r;
	// 	if(n == 1){
	// 		return length;
	// 	}else if(mem.containsKey(n)){
	// 		return mem.get(n);
	// 	} else if(n%2 == 0){
	// 		r = cycleLenght(n/2,++length) + 1;
	// 		mem.put(n,r);
	// 		return r;
	// 	} else {
	// 		r = cycleLenght(3*n+1,++length) + 1;
	// 		mem.put(n,r);
	// 		return r;
	// 	}
	// }

	static public int cycleLenght(long i){
		if(mem.containsKey(i)){
			return mem.get(i);
		}
		int length = 1;
		long curr = i;
		while(curr != 1){
			if(curr%2 == 0){
				curr = curr >> 1;
			} else {
				curr = 3*curr+1;
			}
			length++;
		}
		return length;
	}
}