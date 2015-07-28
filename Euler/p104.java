import java.math.BigInteger;
import java.util.HashSet;

class p104{

	static public void main(String args[]){
		BigInteger fn1 = new BigInteger("1");
		BigInteger fn2 = new BigInteger("1");
		BigInteger fn;
		final BigInteger ls = new BigInteger("1000000000");
		int k = 3;
		System.out.println("F(1) = " + fn1);
		System.out.println("F(2) = " + fn2);
		boolean bigEnough = false;
		boolean found = false;
		while(!found){
			fn = fn1.add(fn2);
			//System.out.println("F("+k+") = " + fn);
			fn2 = fn1;
			fn1 = fn;
			k++;
			BigInteger last = fn.mod(ls);
			if(bigEnough || fn.compareTo(ls) > 0){
				bigEnough = true;
				String first = fn.toString().substring(9);
				boolean c1 = isPandigital(first);
				boolean c2 = isPandigital(last.toString());
				if(c1){
					System.out.println("Pandigital first: " + k);
				}
				if(c2){
					System.out.println("Pandigital last: " + k);
				}
				if(c1 && c2) {
					System.out.println("Pandigital first and last: " + k);
					found = true;
				}
			}
			// if(isPandigital(last.toString())){
			// 	System.out.println("Pandigital last: " + last);
			// } 
			System.out.println("k: " + k);
		}
		System.out.println("final k: " + k);
	}

	static public boolean isPandigital(String str){
		if(str.length() != 9){
			return false;
		}
		HashSet<Character> pandigital = new HashSet<Character>();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if(pandigital.contains(c)){
				return false;
			} else {
				pandigital.add(c);
			}
		}
		return true;
	}

}
