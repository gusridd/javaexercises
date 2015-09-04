import java.util.Stack;
import java.lang.StringBuffer;
import java.lang.Thread;
import java.util.Random;

public class ReverseString {
	
	static public void main(String []args){
		if(args.length == 0){
			System.err.println("Missing argument");
			System.exit(1);
		}
		System.out.println("Original string: " + args[0]);
		System.out.println("reverseRecursive: " + reverseRecursive(args[0]));
		System.out.println("reverseWithStack: " + reverseWithStack(args[0]));
		System.out.println("reverseWithBuffer: " + reverseWithBuffer(args[0]));
		System.out.println("reverseIterative: " + reverseIterative(args[0]));
		System.out.println("reverseThread: " + reverseThread(args[0]));
		System.out.println("reverseRandom: " + reverseRandom(args[0]));
	}

	static public String reverseRecursive(String s){
		if(s.compareTo("") == 0){
			return "";
		} else {
			return reverseRecursive(s.substring(1)) + s.charAt(0);
		}
	}

	static public String reverseWithStack(String s){
		Stack<Character> stack = new Stack<Character>();
		StringBuffer buf = new StringBuffer(s.length());
		for(int i=0;i<s.length();i++){
			stack.push(s.charAt(i));
		}
		for(int i=0;i<s.length();i++){
			buf.append(stack.pop());
		}
		return buf.toString();
	}

	static public String reverseWithBuffer(String s){
		return (new StringBuffer(s).reverse().toString());
	}

	static public String reverseIterative(String s){
		StringBuffer buf = new StringBuffer(s.length());
		for(int i=s.length()-1; i>=0;i--){
			buf.append(s.charAt(i));
		}
		return buf.toString();
	}	

	static public String reverseThread(String s){
		char[] res = s.toCharArray();
		Thread[] threads = new Thread[s.length()/2];
		for(int i = 0; i<s.length()/2; i++){
			threads[i] = new Thread(new Inverter(res,i));
			threads[i].start();
		}
		// wait for all threads to finish
		for(int i = 0; i < threads.length; i++){
			try{
				threads[i].join();
			} catch (InterruptedException e) {
				return "";
			}
		}
		return new String(res);
	}

	static public String reverseRandom(String s){
		if(s.length() <= 1){
			return s;
		}
		char[] res = s.toCharArray();
		boolean reversed = false;
		Random r = new Random();
		while(!reversed){
			//System.out.println(new String(res));
			int pos1 = r.nextInt(s.length());
			int pos2 = r.nextInt(s.length());
			// swap
			char aux = res[pos1];
			res[pos1] = res[pos2];
			res[pos2] = aux;
			reversed = true;
			for(int i = 0; i<s.length(); i++){
				if(res[i] != s.charAt(s.length()-i-1)){
					reversed = false;
				}
			}
		}
		return new String(res);
	}
}

class Inverter implements Runnable {
	
	private final int i;
	private final char[] res;
	private final int length;

	public Inverter(char[] res, int i){
		this.res = res;
		this.i = i;
		this.length = res.length;
	}

	public void run(){
		char c = res[i];
		res[i] = res[length-i-1];
		res[length-i-1] = c;
	}
}