public class Main{

  static public void main(String[] args){
    System.out.println("Red-Black Tree!");

    RBTree rbt = new RBTree();

    rbt.insert(1,"One");
    rbt.insert(2,"Two");

    System.out.println(rbt.toString());

    rbt.insert(3,"Three");
    rbt.insert(4,"Four");

    System.out.println(rbt.toString());

    rbt.insert(5,"Five");
    rbt.insert(6,"Six");

    System.out.println(rbt.toString());

    System.out.println("Searching: " + 4);
    System.out.println(rbt.search(4));
  }

}

class RBTree{
  Node root;

  public RBTree(){

  }

  public void insert(int k, String v){
    Node n = new Node(k,v);

    // No root case
    if (root == null){

      // insert case 1
      insertCase1(n);
      root = n;
      return;
    }

    // root case
    Node aux = root;
    while(true){
      if(k < aux.key){
        if( aux.left == null){
          aux.left = n;
          n.parent = aux;
          break;
        } else {
          aux = aux.left;
        }
      } else if(k > aux.key) {
        if (aux.right == null){
          aux.right = n;
          n.parent = aux;
          break;
        } else {
          aux = aux.right;
        }
      }
    }
    insertCase2(n);
  }

  private void insertCase1(Node n){
    if(n.parent == null){
      n.red = false;
    } else {
      insertCase2(n);
    }
  }

  private void insertCase2(Node n){
    if(! n.parent.red){
      return;
    } else {
      insertCase3(n);
    }
  }

  private void insertCase3(Node n){
    Node u = n.getUncle();
    Node g;

    if( u != null && u.red){
      n.parent.red = false;
      u.red = false;
      g = n.getGrandparent();
      g.red = true;
      insertCase1(n);
    } else {
      insertCase4(n);
    }
  }

  private void insertCase4(Node n){

  }

  public void remove(int k){

  }

  public String search(int k){
    Node aux = root;
    while(aux != null){
      if(aux.key == k){
        return aux.value;
      }
      if(k < aux.key){
        aux = aux.left;
      } else {
        aux = aux.right;
      }
    }
    return null;
  }

  public String toString(){
    if (root != null){
      return root.toString();  
    } else {
      return "";
    }
  }
}

class Node {

  Node parent;
  Node left;
  Node right;
  boolean red;
  int key;
  String value;

  public Node(int k, String v){
    this.value = v;
    this.key = k;
    this.red = true;
  }

  public String toString(){
    String res = "";

    if(left != null){
      res += left.toString();
    }

    res += value;

    if(right != null){
      res += right.toString();
    }

    return res;
  }

  public Node getGrandparent(){
    if(this.parent != null){
      return this.parent.parent;
    } else {
      return null;
    }
  }

  public Node getUncle(){
    Node g = getGrandparent();
    if(g == null){
      return null;
    }
    if(this.parent == g.left){
      return g.right;
    } else {
      return g.left;
    }
  }

}