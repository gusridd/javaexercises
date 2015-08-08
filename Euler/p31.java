public class p31 {

	static public void main(String []args){
		long start = 0;
		long elapsedTime = 0;
		int total = 0;

		// start = System.nanoTime();
		// total = amount();
		// elapsedTime = System.nanoTime() - start;
		// System.out.println("Ammount = " + total + " in " + elapsedTime + " [ns]");
		// // Ammount = 73682 in 29962862885 [ns]

		// start = System.nanoTime();
		// total = amount2();
		// elapsedTime = System.nanoTime() - start;
		// System.out.println("Ammount = " + total + " in " + elapsedTime + " [ns]");
		// Ammount = 73682 in 18189984052 [ns]

		start = System.nanoTime();
		total = amount3(200);
		elapsedTime = System.nanoTime() - start;
		System.out.println("Ammount = " + total + " in " + elapsedTime + " [ns]");
		// Ammount = 73682 in 64613 [ns]

	}

	static public int amount(){
		int total = 0;
		for(int ones = 0; ones <= 200; ones++){
			for(int twos = 0; twos <= 100; twos++){
				for(int fives = 0; fives <= 40; fives++){
					for(int tens = 0; tens <= 20; tens++){
						for(int twenties = 0; twenties <= 10; twenties++){
							for(int fifties = 0; fifties <= 4; fifties++){
								for(int hundreds = 0; hundreds <= 2; hundreds++){
									for(int twohundreds = 0; twohundreds<=1; twohundreds++){
										if(ones*1+twos*2+fives*5+tens*10+twenties*20+fifties*50+hundreds*100+twohundreds*200 == 200){
											total++;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return total;
	}

	static public int amount2(){
		int total = 0;
		int oneSum, twoSum, fiveSum, tenSum, twentySum, fiftieSum, hundredSum, twohundredSum;
		for(int ones = 0; ones <= 200; ones++){
			oneSum = ones*1;
			for(int twos = 0; twos <= 100; twos++){
				twoSum = twos*2;
				for(int fives = 0; fives <= 40; fives++){
					fiveSum = fives*5;
					for(int tens = 0; tens <= 20; tens++){
						tenSum = tens*10;
						for(int twenties = 0; twenties <= 10; twenties++){
							twentySum = twenties*20;
							for(int fifties = 0; fifties <= 4; fifties++){
								fiftieSum = fifties*50;
								for(int hundreds = 0; hundreds <= 2; hundreds++){
									hundredSum = hundreds*100;
									for(int twohundreds = 0; twohundreds<=1; twohundreds++){
										twohundredSum = twohundreds*200;
										if(oneSum+twoSum+fiveSum+tenSum+twentySum+fiftieSum+hundredSum+twohundredSum == 200){
											total++;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return total;
	}


	static final int[] coinValue = {1,2,5,10,20,50,100,200};
	static final int[] maxAmount = {200,100,40,20,10,4,2,1};

	static public int amount3(int objectValue){
		int[] res = new int[objectValue+1];
		res[0] = 1;
		for(int i = 0; i<coinValue.length; i++){
			int val = coinValue[i];
			for(int pos = val-1; pos<objectValue+1; pos++){
				if(pos-val >= 0){
					res[pos] += res[pos-val];
				}
			}
		}
		System.out.println(java.util.Arrays.toString(res));
		return res[objectValue];
	}
}
