public class Main {

  static public void main(String args[]){
    IRobot robot = new RandomRobot();
    moveTwiceRight(robot);
    System.out.println("Robot final pos: " + robot.getPos());

    IRobot robot2 = new RandomRobot(0.3);
    moveTwiceRight(robot2);
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

}
