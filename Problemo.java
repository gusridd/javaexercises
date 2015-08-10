import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;

class Chopstick {
  private Lock lock;

  public Chopstick(){
  	lock = new ReentrantLock();
  }

  public void pickUp(){
  	lock.lock();
  }
  public void putDown(){
  	lock.unlock();
  }
}

class Philosopher extends Thread {
	private int bites = 1000;
	private Chopstick left;
	private Chopstick right;
	private String name;
	int amount = 0;

	public Philosopher(String name, Chopstick left, Chopstick right){
		this.left = left;
		this.right = right;
		this.name = name;
	}

	public void eat(){
		pickUp();
		chew();
		putDown();
	}

	public void pickUp(){
		left.pickUp();
		right.pickUp();
	}

	public void chew() { 
		System.out.println(name + " is chewing for the " + amount++);
		try {
			//this.sleep(100);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}

	public void putDown(){
		left.putDown();
		right.putDown();
	}

	public void run(){
		for(int i=0; i < bites; i++){
			eat();
		}
	}
}


public class Problemo {
	static public void main(String []args){
		Chopstick c1 = new Chopstick();
		Chopstick c2 = new Chopstick();
		//Chopstick c3 = new Chopstick();

		Philosopher p1 = new Philosopher("Alice",c1,c2);
		Philosopher p2 = new Philosopher("Bob",c2,c1);
		//Philosopher p3 = new Philosopher("Carol",c3,c1);

		p1.start();
		p2.start();
		//p3.start();
	}
}