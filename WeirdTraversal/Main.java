import java.lang.*;
import java.util.*;

class Main {
  
  static public void main(String[] args){
    System.out.println("Weird Traversal!");

    Node n = new Node(4, new Node(2, new Node(1), new Node(3)), new Node(6, new Node(5), new Node(7)));

    System.out.println(traversal(n));

  }

  static public String traversal(Node n){
    LinkedList<Node> toTraverse = new LinkedList<Node>();
    toTraverse.add(n);

    LinkedList<Node> res = new LinkedList<Node>();

    LinkedList<Node> nextTraverse = new LinkedList<Node>();

    LinkedList<Node> currentTraverse = new LinkedList<Node>();    

    while(toTraverse.size() != 0){
      Node current = toTraverse.poll();
      currentTraverse.add(current);

      // Add elements of the next row
      if(current.left != null)
        nextTraverse.add(current.left);

      if(current.right != null)
        nextTraverse.add(current.right);

      // When done with the current row
      // add all elements to the beggining of the resulting list
      if(toTraverse.size() == 0){
        res.addAll(0,currentTraverse);
        toTraverse = nextTraverse;
        currentTraverse = new LinkedList<Node>();
        nextTraverse = new LinkedList<Node>();
      }

      
    }
    
    return listToString(res);
  }

  static public String listToString(LinkedList<Node> l){
    StringBuilder sb = new StringBuilder();
    for(Node it : l){
      sb.append(it.value);
      sb.append(",");
    }
    if(sb.length() > 0)
      sb.deleteCharAt(sb.length()-1);
    return sb.toString();
  }

}

class Node {
  Node left;
  Node right;
  int value;

  public Node(int v, Node l, Node r){
    this.value = v;
    this.left = l;
    this.right = r;
  }

  public Node(int v){
    this.value = v;
  }
}
