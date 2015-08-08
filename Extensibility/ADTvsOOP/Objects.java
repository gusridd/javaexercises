public class Objects {
	
	static public void main(String []args) throws Exception{
		Expr e = new Add(new Num(2), new Num(5));
		System.out.println(e.eval());

		PExpr p = new PAdd(new PNum(2), new PNum(5));
		System.out.println(p.prettify() + " = " + p.eval());
	}
}

interface Expr<T extends Expr<T>> {
	int eval() throws Exception;
}

class Num implements Expr<Num> {
	private int n;
	public Num(int n){
		this.n = n;
	}
	public int eval() throws Exception{
		return this.n;
	}
}

class Bool implements Expr {
	private boolean b;
	public Bool(boolean b){
		this.b = b;
	}
	public int eval() throws Exception {
		throw new Exception("Bool is not a Num");
	}
}

class Add implements Expr {
	private Expr l;
	private Expr r;
	public Add(Expr l, Expr r){
		this.l = l;
		this.r = r;
	}
	public int eval() throws Exception{
		return l.eval() + r.eval();
	}
}



/**
 *  EXTENSION 1: add another structure
 */

class Sub implements Expr {
	private Expr l;
	private Expr r;
	public Sub(Expr l, Expr r){
		this.l = l;
		this.r = r;
	}
	public int eval() throws Exception{
		return l.eval() - r.eval();
	}
}


/**
 *  EXTENSION 2: add functionality
 */ 

interface PExpr extends Expr {
	public String prettify();
}

class PNum extends Num implements PExpr {
	public PNum(int n){
		super(n);
	}
	public String prettify(){
		return x+"";
	}
}

class PBool extends Bool implements PExpr {
	public PBool(boolean b){
		super(b);
	}
	public String prettify(){
		return b+"";
	}
}

class PAdd extends Add implements PExpr {
	public PAdd(PExpr l, PExpr r){
		super(l,r);
	}
	public String prettify(){
		return this.l.prettify() + " + " + this.r.prettify();
	}
}

class PSub extends Sub implements PExpr {
	public PSub(PExpr l, PExpr r){
		super(l,r);
	}
	public String prettify(){
		return this.l.prettify() + " - " + this.r.prettify();
	}
}