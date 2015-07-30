public class p112 {
	
	static public void main(String args[]){

		int bouncyCounter = 0;
		double ratio = 0.0;
		int i;
		String b = "";
		for(i = 0; ratio!=0.99; i++){
			b = "";
			if(isBouncy(i)){
				b = "bouncy";
				bouncyCounter++;
			}
			ratio = (double)bouncyCounter/(double)i;
		}
		System.out.println("ratio: " + ratio);
		System.out.println(--i + " " + b);
	}

	static private boolean isBouncy(int n) {
		String str = String.valueOf(n);
		char curr = str.charAt(0);
		boolean inc = true;
		boolean dec = true;
		for(int i = 0; i<str.length(); i++) {
			char c = str.charAt(i);
			if(curr < c) {
				dec = false;
			} else if (curr > c) {
				inc = false;
			}
			curr = c;
		}
		return !inc && !dec;
	}
}
