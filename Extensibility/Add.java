public class Add extends Expr {
	private Expr l;
	private Expr r;

	public Add(Expr l, Expr r){
		this.l = l;
		this.r = r;
	}

	public int eval(){
		return l.eval() + r.eval();
	}

	public String toString(){
		return l.toString() + " + " + r.toString();
	}

}