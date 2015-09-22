import java.util.LinkedList;
import java.lang.StringBuilder;

public class BinTreeMerge {
  static public void main(String[] args){
    new BinTreeMerge().run();
  }
  public BinTreeMerge(){}
  public void run(){
    System.out.println("BinTreeMerge!");
    BinTree bt = new BinTree();
    bt.insert(10);
    bt.insert(12);
    bt.insert(5);
    bt.insert(4);
    bt.insert(7);
    System.out.println(bt.toString());

    BinTree bt2 = new BinTree();
    bt2.insert(11);
    bt2.insert(6);
    bt2.insert(13);
    System.out.println(bt2.toString());
  }
}

class BinTree {
  Node head;
  public BinTree(){}
  public void insert(int v){
    if(head == null){
      head = new Node(v);
      return;
    }
    Node curr = head;
    while(true){
      if(v < curr.val && curr.l == null){
        curr.l = new Node(v);
        break;
      }
      if(v > curr.val && curr.r == null){
        curr.r = new Node(v);
        break;
      }
      if(v < curr.val){
        curr = curr.l;
      } else {
        curr = curr.r;
      }
    }
  }
  public String toString(){
    if(head == null) return "";
    LinkedList<Node> l = new LinkedList<Node>();
    l.add(head);
    Node curr;
    int count = 0;
    int levelSize = 1;
    int nextLevelSize = 0;
    StringBuilder sb = new StringBuilder();
    String prefix = "";
    while(!l.isEmpty()){
      curr = l.poll();
      if(curr.l != null) {
        l.add(curr.l);
        nextLevelSize++;
      }
      if(curr.r != null) {
        l.add(curr.r);
        nextLevelSize++;
      }
      count++;
      sb.append(prefix + curr.val);
      prefix = " ";
      if(count == levelSize){
        prefix = "";
        sb.append("\n");
        levelSize = nextLevelSize;
        nextLevelSize = 0;
        count = 0;
      }
    }
    return sb.toString();
  }
}

class Node {
  int val;
  Node l,r;
  public Node(int v){
    this.val = v;
  }
}
