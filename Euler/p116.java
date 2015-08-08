public class p116 {
	static public void main(String args[]){
		System.out.println(howMany(2,5));
	}

	static public int howMany(int blockSize, int space){

		if(blockSize > space){
			System.out.println("howMany("+blockSize+","+space+") = " + 0);
			return 0;
		}
		if(blockSize == space){
			System.out.println("howMany("+blockSize+","+space+") = " + 1);
			return 1;
		}
		/*if(blockSize == space + 1 ){
			return 2;
		}*/
		int sum = 0;
		for(int i = 1; i<space; i++){
			sum += howMany(blockSize,space-i) + 1;
		}
		System.out.println("howMany("+blockSize+","+space+") = " + sum);
		return sum;
	}
}