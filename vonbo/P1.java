import java.lang.Math;
import java.util.Random;
import java.util.Arrays;

public class P1 {

	//final static int[] A = {1,5,9,13,15,25,50,100,200};
	//final static int[] B = {1,5,9,13,15,25,50,100,200};

	static public void main(String []args){
		Case c; 
		//int[][] p = generateCase(101);
		int[] A;
		int[] B; 

		// System.out.println(A.length);
		// System.out.println(B.length);
		// System.out.println(Arrays.toString(A));
		// System.out.println(Arrays.toString(B));
		// System.out.println(findPosK(A,B,10));

		int res = -1;
		int pos, val;
		while(true){
			c = new Case(101);
			A = c.A;
			B = c.B;
			pos = c.pos;
			res = findPosK(A,B,c.pos);
			if(res != c.val){
				System.out.println("The next case fails");
				System.out.println(c.toString());
			}
		}
	}

	static public int findPosK(int[] arr1, int[] arr2, int k){
		if(k < 0 || k >= arr1.length + arr2.length){
			return -1;
		}

		// reduce the size of the problem to 2k
		// if(arr1.length + arr2.length != 2*k){
		// 	int[] a1 = java.util.Arrays.copyOf(arr1,k);
		// 	int[] a2 = java.util.Arrays.copyOf(arr2,k);
		// 	findPosK(a1,a2,k);
		// }
		
		// Merged Binary Search

		int i1 = 0;
		int i2 = 0;
		int f1 = k;
		int f2 = k;

		int pos1 = (f1-i1)/2;
		int pos2 = (f2-i2)/2;

		int relativePos = pos1 + pos2;

		if(k > relativePos){
			// we need to search at the right of both positions
			i1 = pos1;
			i2 = pos2;
		} else if(k < relativePos){
			// we need to search at the left of both positions
			f1 = pos1;
			f2 = pos2;
		} else {
			// we need to search in between of both positions, this may depend on values

			// if elements in between are all equal
			if(arr1[pos1] == arr2[pos2]){
				return arr1[pos1];
			} else if(arr1[pos1] > arr2[pos2]){
				return arr2[pos2];
			} else {
				return arr1[pos1];
			}
		}

		int e1 = arr1[arr1.length/2];
		int e2 = arr2[arr2.length/2];

		// candidate pos
		if(e1 > e2){
			// we need to search to the 
		} else if (e2 > e1){
			//
		} else {

		}
		return 0;

	}

	private int[][] generateCase(int size){
		Random r = new Random();
		int arr[] = new int[size];
		int split = r.nextInt(size);
		for(int i = 0; i<size; i++){
			arr[i] = r.nextInt();
		}
		Arrays.sort(arr);
		int[] a = Arrays.copyOfRange(arr,0,split);
		int[] b = Arrays.copyOfRange(arr,split,size);
		int[][] res = {a,b};
		return res;
	}

}

class Case {
	int[] A;
	int[] B;
	int pos;
	int val;
	public Case(int size){
		Random r = new Random();
		int arr[] = new int[size];
		int split = r.nextInt(size);
		for(int i = 0; i<size; i++){
			arr[i] = r.nextInt();
		}
		this.pos = r.nextInt(size);
		this.val = arr[pos];
		Arrays.sort(arr);
		this.A = Arrays.copyOfRange(arr,0,split);
		this.B = Arrays.copyOfRange(arr,split,size);
	}

	public String toString(){
		return "Expected Value: " + val + "\nFor Position: " + pos + "A: " + Arrays.toString(A) + "\n" + "B: " + Arrays.toString(B) + "\n";
	}
}