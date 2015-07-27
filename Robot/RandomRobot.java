import java.util.Random;

public class RandomRobot implements IRobot {

  private int pos;
  private Random r;
  private double p;

  public RandomRobot(){
    this(0.5);
  }

  public RandomRobot(double p){
    this.r = new Random();
    this.p = p;
    this.pos = 0;
  }

  public boolean move(){
    double c = r.nextDouble();
    boolean res = c <= p;
    System.out.println("InternalPos: " + getPos());
    if(res) {
      pos++;
    } else {
      pos--;
    }
    return res;
  }

  public int getPos(){
    return this.pos;
  }
}
