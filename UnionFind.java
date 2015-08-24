public class UnionFind{
  static public void main(String []args){
    System.out.println("Hello World!");

    UnionFind uf = new UnionFind(5);
    System.out.println(uf.toString());
    uf.unionSet(0,1);
    System.out.println(uf.toString());
    uf.unionSet(1,2);
    System.out.println(uf.toString());
    uf.unionSet(3,1);
    System.out.println(uf.toString());
    System.out.println("findSet(0): " + uf.findSet(0));
    System.out.println(uf.toString());
    System.out.println("isSameSet(0,4): " + uf.isSameSet(0,4));
    System.out.println(uf.toString());

  }

  class Node {
    int val;
    Node parent;
    public Node(int v){
      this.val = v;
      this.parent = this;
    }
  }

  int []parent;
   
  public UnionFind(int size){
    this.parent = new int[size];
    for(int i = 0 ; i<size; i++){
      // each element is its own representative element
      parent[i] = i;
    }    
  }

  public int findSet(int i){
    return (parent[i] == i) ? i : (parent[i] = findSet(parent[i])); 
  }

  public void unionSet(int i, int j){
    parent[findSet(i)] = findSet(j);
  }

  public boolean isSameSet(int i, int j){
    return findSet(i) == findSet(j);
  }

  public String toString(){
    return java.util.Arrays.toString(parent);
  }


}
