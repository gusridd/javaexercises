public class Sieve {
  static public void main(String []args) throws Exception {
    System.out.println("Sieve");
    boolean []sieve1 = sieve(10000);
    for(int p = 0; p< sieve1.length; p++){
      if(sieve1[p]){
        System.out.println(p);
      }
    }
  }
  
  static public boolean[] sieve(int size) throws Exception {
    boolean arr[] = new boolean[size];
    if(size < 2){
      throw new Exception("Dude plz!");
    }
    java.util.Arrays.fill(arr,true);
    arr[0] = false;
    arr[1] = false;
    for(int p = 2; p< arr.length; p++){
      for(int i = 2; i*p < arr.length; i++){
        arr[i*p] = false;
      }
    }
    
    return arr;
  }
}
