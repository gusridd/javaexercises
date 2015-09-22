import java.util.Comparator;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Intervals {
  static public void main(String[] args){
    new Intervals().run();
  }
  public Intervals(){}
  public void run(){
    System.out.println("Intervals!");
    Set<Interval> set = new HashSet<Interval>();
    set.add(new Interval(-5,3));
    set.add(new Interval(-5,3));
    set.add(new Interval(5,8));
    set.add(new Interval(2,3));
    set.add(new Interval(2,2));
    try{
      System.out.println(getMaxCut(set));
    } catch (Exception e) {
      System.out.println(e.toString());
    }
  }

  public int getMaxCut(Set<Interval> set) throws Exception {
    if(set.isEmpty()){
      throw new Exception("Set is empty");
    }
    int maxCut = 0;
    int maxCutPos = 0;
    int currCut = 0;
    //  have two ordered arrays of intervals
    Interval[] opening = set.toArray(new Interval[set.size()]);
    Interval[] closing = set.toArray(new Interval[set.size()]);
    Arrays.sort(opening,new LowerIntervalComparator());
    Arrays.sort(closing,new UpperIntervalComparator());
    System.out.println(Arrays.toString(opening));
    System.out.println(Arrays.toString(closing));
    int o = 0;
    int c = 0;
    Interval oI = opening[0];
    Interval cI = closing[0];
    while(o < opening.length || c < closing.length){
      System.out.println("oI:"+oI.toString()+" cI:"+cI.toString() + " currCut:" + currCut);
      if(o < opening.length){
        oI = opening[o];
      } else {
        oI = null;
      }
      if(c < closing.length){
        cI = closing[c];
      } else {
        cI = null;
      }
      if(cI == null && oI == null){
        break;
      }
      if(cI == null || ( oI != null && oI.getLower() < cI.getUpper())){
        currCut++;
        o++;
      } else if(oI == null || (cI != null && oI.getLower() > cI.getUpper())) {
        currCut--;
        c++;
      } else {
        o++;
        c++;
      }
      //update
      if(currCut > maxCut){
        maxCut = currCut;
        //maxCutPos = oI.lower;
        if(oI != null){
          maxCutPos = oI.getLower();
        } else if(cI != null){
          maxCutPos = cI.getUpper();
        }
      }
    }
    return maxCutPos;
  }
}

class Interval{
  int lower;
  int upper;
  public Interval(int l, int u){
    this.lower = l;
    this.upper = u;
  }
  public String toString(){
    return "["+lower+","+upper+"]";
  }
  public int getLower(){return this.lower;}
  public int getUpper(){return this.upper;}
}

class LowerIntervalComparator implements Comparator<Interval>{
  public LowerIntervalComparator(){}
  public int compare(Interval a, Interval b){
    return a.lower - b.lower;
  }
}

class UpperIntervalComparator implements Comparator<Interval>{
  public UpperIntervalComparator(){}
  public int compare(Interval a, Interval b){
    return a.upper - b.upper;
  }
}


