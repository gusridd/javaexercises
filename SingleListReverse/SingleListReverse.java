public class SingleListReverse{

  static public void main(String[] args){
    new SingleListReverse().run();
  }

  public SingleListReverse(){}

  public void run(){
    List l = new List();
    l.prepend(3);
    l.prepend(2);
    l.prepend(1);
    System.out.println(l.toString());
    l.reverse();
    System.out.println(l.toString());
  }

}

class List {
  Node head;
  Node last;
  int size = 0;
  public List(){}
  public String toString(){
    String res = "";
    String prefix = "";
    for(Node aux = head; aux != null; aux = aux.next){
      res += prefix + aux.toString();
      prefix = " ";
    }
    return res;
  }
  public boolean isEmpty(){
    return head == null;
  }
  public void prepend(int v){
    Node n = new Node(v);
    n.next = head;
    this.head = n;
    if(this.size() == 0){
      last = n;
    }
  }
  public void append(int v){
    Node n = new Node(v);
    if(this.size() == 0){
      last = n;
      head = n;
    } else {
      last.next = n;
    }
  }
  public int size(){
    return this.size;
  }
  public void reverse(){
    if(head == null){
      return;
    }
    Node prev = head;
    Node curr = prev.next;
    prev.next = null;
    while(curr != null){
      Node aux = curr.next;
      curr.next = prev;
      prev = curr;
      curr = aux;
    }
    head = prev;
  }
}

class Node{
  Node next;
  int value;
  public Node(int v){
    this.value = v;
  }
  public String toString(){
    return this.value+"";
  }
}
