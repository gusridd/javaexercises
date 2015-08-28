import java.util.Random;
import java.util.Arrays;

public class SearchWithCost {

	static public void main(String []args){
		(new SearchWithCost()).run();
	}

	public SearchWithCost(){ }

	public void run(){
		System.out.println("Hello World!");
		//Case c1 = new Case(10, new ConstantGenerator(8));
		//System.out.println(c1.toString());

		//int cost1 = binarySearch(c1,8);
		//System.out.println("Case1 binarySearch: " + cost1);

		Case c2 = new Case(10, new UniformGenerator(50));
		System.out.println(c2.toString());

		int costBin2 = binarySearch(c2,8);
		c2.reset();
		int costLinear2 = linearSearch(c2,8);
		System.out.println("Case2 binarySearch: " + costBin2);
		System.out.println("Case2 linearSearch: " + costLinear2);
	}


    public int binarySearch(Case c, int k){
    	int i = 0;
    	int j = c.getSize()-1;
    	int pos,e;
    	while(i<j){
    		pos = (j-i/2);
    		e = c.look(pos);
    		if(k == e){
    			System.out.println("Found!");
    			return c.getTotalCost();
    		} else if(k < e){
    			System.out.println("Lesser");
    			j = pos-1;
    		} else {
    			System.out.println("Greater");
    			i = pos+1;
    		}
    	}
    	System.out.println("Not Found!");
    	return c.getTotalCost();
    }

    public int linearSearch(Case c, int k){
    	int i = 0;
    	int j = c.getSize()-1;
    	int min = c.look(i);
    	int max = c.look(j);
    	int e;
    	int jmpSize, bestFit, bestPos;
    	
    	while(i+1<j){
    		// check out of range
    		if(k < min || k > max){
	    		System.out.println("Not Found!");
	    		return c.getTotalCost();
	    	}
	    	// check for found
	    	if(k == min || k == max){
	    		System.out.println("Found!");
	    		return c.getTotalCost();
	    	}

    		jmpSize = (max-min)/(j-i);
    		bestFit = k - min;
    		bestPos = min;
    		System.out.print("q: ");
    		for(int q = i; q<j;q++){
    			System.out.print(" " + q);
    			if(Math.abs(min+q*jmpSize-k) < bestFit){
    				bestFit = Math.abs(min+q*jmpSize-k);
    				bestPos = q;
    			}
    		}
    		// if(bestPos == i){
    		// 	bestPos++;
    		// } else if(bestPos == j){
    		// 	bestPos--;
    		// }
    		System.out.println("");
    		System.out.println("bestPos for " +k+" in [i:"+i+",j:"+j+"] with [min:"+min+",max:"+max+"] = " +bestPos);
    		e = c.look(bestPos);
    		//update
    		if(k == e){
    			System.out.println("Found!");
    			return c.getTotalCost();
    		} else if(k < e){
    			max = e;
    			j = bestPos;
    		} else {
    			min = e;
    			i = bestPos;
    		}
    	}
    	System.out.println("Not Found!");
    	return c.getTotalCost();
    }

	class Case {

		private int []arr;
		private int totalCost;
		private Random r;

		public Case(int n, Generator g){
			arr = new int[n];
			for(int i = 0; i<n;i++){
				arr[i] = g.generate();
			}
			// cases are ordered
			Arrays.sort(arr);
			this.totalCost = 0;
			r = new Random();
		}

		public String toString(){
			return Arrays.toString(arr);
		}

		public int getTotalCost(){
			return this.totalCost;
		}

		public int getSize(){
			return arr.length;
		}

		public int look(int pos){
			int e = arr[pos];
			totalCost += e;
			return e;
		}

		public void reset(){
			this.totalCost = 0;
		}

		// returns a number inside the array
		public int getInside(){
			return arr[r.nextInt(arr.length)];
		}
	}

	interface Generator{
		public int generate();
	}

	class ConstantGenerator implements Generator {
		private final int c;

		public ConstantGenerator(int c){
			this.c = c;
		}

		public int generate(){
			return c;
		}
	}

	class UniformGenerator implements Generator {
		
		Random r;
		int max;

		public UniformGenerator(int max){ 
			this.max = max;
			this.r = new Random();
		}

		public int generate(){
			return r.nextInt(max);
		}
	}
}

