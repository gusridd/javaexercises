

public class LinkedList<E> implements IList<E> {
  private Node<E> first;

  public E get(int index) throws Exception {
    if(first == null){
      throw new Exception("get on an empty list");
    }

    Node<E> curr = first;
    int i = 0;

    for(curr = first; curr.getNext() != null; curr = curr.getNext(),i++){
      if(i == index){
        return curr.getValue();
      }
    }
    if(i == index){
      return curr.getValue();
    }  else {
      throw new Exception("position " + index + " not found on list");  
    }
    
    }

    public void add(E e){

      Node<E> n = new Node<E>(e);
    // Empty case
      if(first == null){
        this.first = n;
        return;
      }

    // Non-Empty case
      Node<E> curr = first;
      for(curr = first; curr.getNext() != null; curr = curr.getNext());

        curr.setNext(n);
    }

    public void add(int index, E e){

    }

    public int size(){
      if(first == null)
        return 0;
      else {
        Node curr = first;
        int i = 1;
        for(curr = first; curr.getNext() != null; curr = curr.getNext(), i++);
          return i;
      }
    }




  }

  class Node<E> {

    private Node<E> next;
    private E value;

    public Node(E e){
      this.value = e;
    }

    public E getValue(){
      return this.value;
    }

    public Node getNext(){
      return this.next;
    }

    public void setNext(Node<E> n){
      this.next = n;
    }

  }