import java.lang.Math;

public class Balanced {

  static public void main(String []args){
    Balanced b = new Balanced();
    b.run();                                           
  }
  
  public void run(){
    Node r = new Node(new Node(null,
                               null), 
                      new Node(null, 
                               new Node(null,
                                        new Node(null,
                                                 new Node(null,
                                                          null)))));
    System.out.println("isBalanced: " + this.isBalanced(r)); 
  }
  
  
  public Balanced(){ }
  
  public boolean isBalanced(Node root){
    try{
      return isBalancedRec(root).balanced;
    } catch (Exception e) {
      return false;
    }
  }
  
  private Pair isBalancedRec(Node root) throws Exception {
    if(root == null){
      return new Pair(true,0);
    }
    Pair lp = isBalancedRec(root.l);
    Pair rp = isBalancedRec(root.r);
    System.out.println(lp.toString());
    System.out.println(rp.toString());
    if(!lp.balanced || !rp.balanced){
      throw new Exception("Shortcut");
    }
    return new Pair(Math.abs(lp.depth-rp.depth) <= 1, Math.max(lp.depth,rp.depth)+1);
  }
  
  class Pair {
    boolean balanced;
    int depth;
    public Pair(boolean b, int d){
      this.balanced = b;
      this.depth = d;
    }
    public String toString(){
      return "("+balanced+", "+depth+")";
    }
  }

  class Node {
   Node l,r;
   public Node(Node l,Node r){
    this.l = l;
    this.r = r;
   }
  }
}
