public class Lit extends Expr {
	
	private int x;

	public Lit(int x){
		this.x = x;
	}

	public int eval(){
		return this.x;
	}

	public String toString(){
		return x+"";
	}
}