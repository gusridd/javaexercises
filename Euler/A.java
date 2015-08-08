class A {
	private int x = 3;

	public void setX(int nx){
		this.x = nx;
	}

	public int getX(){
		return this.x;
	}

	static public void main(String []args){
		A a = new A();
		System.out.println(a.x);
		a.setX(10);
		System.out.println(a.x);
		B b = new B();
		System.out.println(b.x);
		b.setX(11);
		System.out.println(b.x);
		C c = new C();
		System.out.println(c.x);
		c.setX(12);
		System.out.println(c.x);
		D d = new D();
		System.out.println(d.x);
		System.out.println(d.getX());
		System.out.println(d.getoX());
		d.setX(13);
		d.x = 14;
		System.out.println(d.x);
		System.out.println(d.getX());
		System.out.println(d.getoX());
		System.out.println(d.getsX());
		d.setsX(20);
		System.out.println(d.getsX());
	}
}

class B extends A{
	final public int x = 8;
}

class C extends B {

}

class D extends C {
	int x = 2;

	public int getoX(){
		return x;
	}

	public int getsX(){
		return super.x;
	}

	public void setsX(int nx){
		super.x = nx;
	}
}