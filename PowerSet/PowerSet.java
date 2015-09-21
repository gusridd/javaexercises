import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

class PowerSet {

  static public void main(String[] args){
    PowerSet ps = new PowerSet();
    ps.run();
  }

  public void run(){
    Set<Integer> set = new HashSet<Integer>();
    set.add(1);
    set.add(2);
    set.add(3);
    Set<Set<Integer>> pSet = getPowerSet(set);
    System.out.println(pSet.toString());
    PowerSetIterable psi = new PowerSetIterable(set);
    for(Set<Integer> s : psi){
      System.out.println(s.toString());
    }
  }

  public PowerSet(){}

  static public Set<Set<Integer>> getPowerSet(Set<Integer> set){
    Set<Set<Integer>> res = new HashSet<Set<Integer>>();
    for(int i = 0; i < (1 << set.size()); i++){
      res.add(getNSet(i,set));
    }
    return res;
  }

  static public Set<Integer> getNSet(int n, Set<Integer> set){
    Set<Integer> res = new HashSet<Integer>();
    int k = 0;
    for(Integer i : set){
      if(isKBitOne(k,n)){
        res.add(i);
      }
      k++;
    }
    return res;
  }

  static public boolean isKBitOne(int k, int n){
    return ((1 << k) & n) != 0;
  }

  class PowerSetIterable implements Iterable<Set<Integer>>{
    private Set<Integer> set;
    public PowerSetIterable(Set<Integer> s){
      this.set = s;
    }

    public Iterator<Set<Integer>> iterator(){
      return new PowerSetIterator(set);
    }
  }

  class PowerSetIterator implements Iterator<Set<Integer>>{
    private int counter;
    private Set<Integer> set;
    public PowerSetIterator(Set<Integer> s){
      this.counter = 0;
      this.set = s;
    }
    public boolean hasNext(){
      return counter < (1 << set.size());
    }
    public Set<Integer> next(){
      return getNSet(counter++,set);
    }
    public void remove(){}
  }

}
