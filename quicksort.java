import java.util.Random;
import java.util.Arrays;

public class quicksort {

  static Random rand = new Random();

  static public void main(String args[]){
    int []arr1 = {1,5,7,9,1,-4,3,3,4,5,9,0,2,4};
    System.out.println(Arrays.toString(arr1));
    System.out.println(Arrays.toString(qs1(arr1)));
  }

  static int[] qs1(int []arr){
    int aux[] = new int[arr.length];
    
    int randomNum = rand.nextInt(arr.length);
    int pivot = arr[randomNum];
    System.out.println("pivot: " + pivot);
    boolean seenPivot = false;
    int l = 0;
    int g = arr.length - 1;
    for(int i = 0; i<arr.length; i++){
      if(arr[i] < pivot){
        aux[l++] = arr[i];
      } else {
        aux[g--] = arr[i];
      }
    }
    aux[l] = pivot;   

    return aux;
  }

}
