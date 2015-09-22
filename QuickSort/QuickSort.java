import java.util.Arrays;

public class QuickSort {
  static public void main(String[] args){
    new QuickSort().run();
  }
  public QuickSort(){}
  public void run(){
    System.out.println("QuickSort!");
    int[] arr = {7,2,3,7,15,-8,9,3,3,50,100};
    System.out.println(Arrays.toString(arr));
    quicksort(arr);
    System.out.println(Arrays.toString(arr));
  }

  public void quicksort(int[] arr){
    qs(arr,0,arr.length-1);
  }

  private void qs(int[] arr, int l, int h){
    int p;
    //System.out.println("qs:"+Arrays.toString(arr)+" l:"+l+" h:"+h);
    if((h-l) > 0){
      p = partition(arr,l,h);
      qs(arr,l,p-1);
      qs(arr,p+1,h);
    }
  }

  private int partition(int[] arr, int l, int h){
    int p = h;
    int firsthigh = l;
    //System.out.println("partition:" + Arrays.toString(arr) + " l: " + l +" h:" + h);
    for(int i = l; i < h; i++){
      if(arr[i] < arr[p]){
        swap(arr,i,firsthigh);
        firsthigh++;
      }
    }
    swap(arr,p,firsthigh);
    return firsthigh;
  }

  private void swap(int[] arr, int i, int j){
    int aux = arr[i];
    arr[i] = arr[j];
    arr[j] = aux;
  }
}
