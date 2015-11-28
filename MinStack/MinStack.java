import java.util.Stack;

public class MinStack {
  static public void main(String[] args){
    new MinStack().run();
  }
  public MinStack(){}
  public void run(){
    System.out.println("MinStack!");
    MinimumStack s = new MinimumStack();
    s.push(5);
    System.out.println(s.toString());
    s.push(4);
    System.out.println(s.toString());
    s.push(10);
    System.out.println(s.toString());
    s.push(4);
    System.out.println(s.toString());
    s.push(6);
    System.out.println(s.toString());
    
    
  }
}

class MinimumStack {
  private Stack<Integer> actualStack;
  private Stack<Integer> minStack;
  public MinimumStack(){
    actualStack = new Stack<Integer>();
    minStack = new Stack<Integer>();
  }
  public int peekMin(){
    return minStack.peek();
  }
  public void push(int i){
    actualStack.push(i);
    // case: minStack is empty
    if(minStack.isEmpty()){
      minStack.push(i);
      return;
    }
    // case: there are actual values
    if(i <= peekMin()){
      minStack.push(i);
    }
  }
  public int pop(){
    int ret = actualStack.pop();
    if(ret == peekMin()){
      minStack.pop();
    }
    return ret;
  }
  public int peek(){
    return actualStack.peek();
  }
  public String toString(){
    return "as: " + actualStack.toString() + "\nms: " + minStack.toString() + " min: " + peekMin();
  }
}
