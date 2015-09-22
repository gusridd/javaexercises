import java.util.Vector;

public class SimpleHeap {
  static public void main(String[] args){
    new SimpleHeap().run();
  }
  public SimpleHeap(){}
  public void run(){
    System.out.println("SimpleHeap!");
    Heap<Integer> h = new Heap<Integer>();
    h.add(5);
    h.add(7);
    h.add(1);
    h.add(10);
    h.add(6);
    while(!h.isEmpty()){
      System.out.println(h.extract());
    }
  }

}

class Heap<E extends Comparable<E>>{
  Vector<E> arr;
  public Heap(){
    this.arr = new Vector<E>();
  }
  public void add(E e){
    arr.add(e);
    heapify();
    //System.out.println(arr.toString());
  }
  public E extract(){
    if(arr.size() == 0){
      return null;
    }
    E ret = arr.get(0);
    E nRoot = arr.lastElement();
    arr.set(0,nRoot);
    //System.out.println(arr.toString());
    arr.removeElementAt(arr.size()-1);
    int k = 0;
    int gmcp = getMaxChildrenPos(k);
    if(arr.size() == 0){
      return ret;
    }
    E curr = arr.get(k);
    while(gmcp != -1 && curr.compareTo(arr.get(gmcp)) > 0){
      E aux = curr;
      arr.set(k,arr.get(gmcp));
      arr.set(gmcp,aux);
      k = gmcp;
      curr = arr.get(k);
      gmcp = getMaxChildrenPos(k);
    }
    //System.out.println(arr.toString());
    return ret;
  }

  public boolean isEmpty(){
    return arr.isEmpty();
  }


  public void heapify(){
    int k = arr.size()-1;
    E curr = arr.get(k);
    E parent = getParent(k);
    while(k != 0 && curr.compareTo(parent) < 0){
      E aux = parent;
      arr.set(k/2, curr);
      arr.set(k,aux);
      k = k/2;
      curr = arr.get(k);
      parent = getParent(k);
    }
  }
  public int getParentPos(int n){
    return n/2;
  }
  public E getParent(int n){
    return arr.get(getParentPos(n));
  }
  public int getMaxChildrenPos(int k){
    if(k*2 >= arr.size() || k*2+1 >= arr.size()){
      return -1;
    }
    if(k*2+1 > arr.size()){
      return k*2;
    } else if(arr.get(k*2).compareTo(arr.get(k*2+1)) < 0){
      return k*2;
    } else {
      return k*2+1;
    }
  }
}
