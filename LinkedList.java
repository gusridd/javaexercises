public interface IList{

  public int get(int index);
  public void add(int e);
  public void add(int index, int e);
  public int size();
  

}

public class LinkedList implements IList{
  private Node first;

  

}

private class Node {
  Node next;
  int value;

  public Node(int i){
    this.value = i;
  }

  public int getValue(){
    return this.value;
  }

  public Node getNext(){
    return this.next;
  }
}
