public class Main {

  static public void main(String args[]){
    //IRobot robot = new RandomRobot();
    //moveTwiceRight(robot);
    //System.out.println("Robot final pos: " + robot.getPos());

    IRobot robot2 = new RandomRobot(0.1);
    moveRightWithTrampoline(robot2).execute();
    System.out.println("Robot final pos: " + robot2.getPos());
  }

  static public void moveTwiceRight(IRobot robot){
    moveRight(robot);
    moveRight(robot);
  }

  static public void moveRight(IRobot robot){
    if(robot.move()) return;
    else {
      moveRight(robot);
      moveRight(robot);
    }    
  }

  static public Trampoline<Object> moveRightWithTrampoline(IRobot robot){
    if(robot.move()) {
      return new Trampoline<Object>() {
        public Object get() { return new Object(); }
      };
    } else {
      return new Trampoline<Object>() {
        public Trampoline<Object> run() {
          moveRightWithTrampoline(robot).execute();
          return moveRightWithTrampoline(robot);
        }
      };
    }
  }

}


class Trampoline<T> 
{
    public T get() { return null; }
    public Trampoline<T>  run() { return null; }

    T execute() {
        Trampoline<T>  trampoline = this;

        while (trampoline.get() == null) {
            trampoline = trampoline.run();
        }

        return trampoline.get();
    }
}
