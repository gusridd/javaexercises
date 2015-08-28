import java.util.HashMap;

public class Ex5441 {

  static public void main(String []args){
    HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
    int sum;
    for(int c1 = 1; c1 <= 2; c1++){
      for(int c2 = 1; c2 <= 2 ; c2++){
        for(int d1 = 1; d1 <= 6; d1++){
          for(int d2 = 1; d2 <= 6; d2++){
            sum = c1+c2+d1+d2;
            if(map.containsKey(sum)){
              map.put(sum,map.get(sum)+1);
            } else {
              map.put(sum,1);
            }
          }
        }
      }
    }
    System.out.println(map.toString());
    System.out.println(map.size());
    ammount();
  }
  
  static public int ammount(){
    int []coinValue = {1,2};
    int []dizeValue = {1,2,3,4,5,6};
    HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
    int sum;
    for(int c1 : coinValue){
      for(int c2 : coinValue){
        for(int d1 : dizeValue){
          for(int d2 : dizeValue){
            sum = c1+c2+d1+d2;
            if(map.containsKey(sum)){
              map.put(sum,map.get(sum)+1);
            } else {
              map.put(sum,1);
            }
          }
        }
      }
    }
    System.out.println(map.toString());
    System.out.println(map.size());
    return map.size();
  }

}
