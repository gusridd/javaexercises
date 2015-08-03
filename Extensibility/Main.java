public class Main {
	static public void main(String args[]){
		Expr e = new Add(new Lit(1), new Add(new Lit(2), new Lit(3)));


		System.out.println(e.toString() + " = " + e.eval());
	}
}