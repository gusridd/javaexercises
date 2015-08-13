import java.util.HashSet;

public class p26 {
	static public void main(String []args){
		String res = "";
		int resD = 0;
		for(int d = 2; d < 10; d++){
			String curr = recurringCycle(1,d);
			if(curr.length() > res.length()){
				res = curr;
				resD = d;
			}
		}
		System.out.println("Maximum recurring cycle with: 1/"+resD + " = " + res + "(" + res.length() + ")");
	}

	static private String recurringCycle(int a, int b){
		System.out.println("recurringCycle("+a+","+b+")");
		int curr = a;
		int res = curr/b;
		int r = curr%b;
		String decimalPart = "";
		HashSet<Integer> hash = new HashSet<Integer>();
		while(r != 0 && !hash.contains(curr)){
			hash.add(curr);			
			System.out.println("decimalPart: " + decimalPart);
			if(res == 0){
				curr*=10;	
			} else {
				curr -= res*b;
				decimalPart += res + "";
			}
			
			res = curr/b;
			r = curr%b;
		}
		decimalPart += res + "";
		System.out.println("decimalPart: " + decimalPart);
		System.out.println("res: " + res);
		System.out.println("r: " + r);
		if(r == 0){
			return "";
		} else {
			decimalPart.substring(decimalPart.length()-1);
		}
		return "";
	}
}