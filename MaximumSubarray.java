import static java.lang.Math.*;

public class MaximumSubarray{
	
	static public void main(String args[]) throws Exception{
		System.out.println("MaximumSubarray");
		int[] arr1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
		int[] arr2 = {2, 3, -2, 4,8,-2};
		System.out.println(subarray(arr1));
		System.out.println(subarraymult(arr2));

	}

	static public int subarray(int []arr) throws Exception{
		if(arr.length == 0){
			throw new Exception("subarray does not work over empty arrays");
		}
		int maxEndingHere = arr[0];
		int maxSoFar = arr[0];
		int start = 0;
		int end = 0;
		for(int i = 0; i<arr.length; i++){
			if(arr[i] > maxEndingHere+arr[i]){
				start = i;
			}
			maxEndingHere = max(arr[i],maxEndingHere+arr[i]);
			if(maxEndingHere > maxSoFar){
				end = i;
			}
			maxSoFar = max(maxSoFar,maxEndingHere);
			
		}
		System.out.println("["+start+", " +end+"]");
		return maxSoFar;
	}

	static public int subarraymult(int []arr) throws Exception{
		if(arr.length == 0){
			throw new Exception("subarray does not work over empty arrays");
		}
		int maxEndingHere = arr[0];
		int maxSoFar = arr[0];
		int maxAlt = arr[0];
		int start = 0;
		int end = 0;
		for(int i = 1; i<arr.length; i++){
			if(arr[i] > maxEndingHere*arr[i]){
				System.out.println("start: " + start);
				start = i;
			}
			maxEndingHere = max(arr[i],maxEndingHere*arr[i]);
			if(maxEndingHere > maxSoFar){
				System.out.println("end: " + end);
				end = i;
			}
			maxSoFar = max(maxSoFar,maxEndingHere);
			
		}
		System.out.println("["+start+", " +end+"]");
		return maxSoFar;
	}
}