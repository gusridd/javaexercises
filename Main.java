

public class Main {
	
	static public void main(String[] args) throws Exception {
	  	System.out.println("Hello World!");

	  	IList<Integer> list = new LinkedList<Integer>();

	  	System.out.println("size: " + list.size());

	  	list.add(8);
	  	list.add(3);
	  	list.add(5);

	  	String a = "Hi there";
	  	System.out.println(a.length());

	  	System.out.println("size: " + list.size());

	  	System.out.println("pos(0) " + list.get(0));
	  	System.out.println("pos(1) " + list.get(1));
	  	System.out.println("pos(2) " + list.get(2));
	  	System.out.println("pos(4) " + list.get(4));



	}
}