import java.util.HashMap;

public class Prototype {
  
  static public void main(String []args){
    System.out.println("Hello World!");
    Prototype p = new Prototype();
    
    System.out.println(p.toString());
    p.setProperty("A", "B");
    System.out.println(p.toString());
    
    System.out.println(p.getProperty("A"));
    System.out.println(p.getProperty("C"));
    
    Prototype pp = new Prototype();
    pp.setProperty("C","D");
    System.out.println(pp.getProperty("C"));
    
    p.setProto(pp);
    
    System.out.println(pp.getProperty("C"));
  }
  
  private HashMap<String,Object> properties;
  private Prototype proto;
  
  public Prototype(){
    this.properties = new HashMap<String,Object>();
    this.proto = this;
  }
  
  public void setProperty(String name, Object o){
    this.properties.put(name,o);
  }
  
  public Object getProperty(String key){
    // try to get it locally
    if(properties.containsKey(key)){
      return properties.get(key);
    // try to get it with the parent
    } else if (proto != this){
      return proto.getProperty(key);
    // fail
    } else {
      return null;
    }
  }
  
  public void setProto(Prototype nProto){
    this.proto = nProto;
  }
  
  @Override
  public String toString(){
    return properties.toString();
  }

}

