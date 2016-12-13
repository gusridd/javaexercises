import java.lang.*;
import java.util.*;

class Main {

  static public void main(String[] args){
    println("ManagerOf!");

    Scanner s = new Scanner(System.in);
    String name1, name2;    

    Employee a,b;

    // Keep a HashMap of name -> Employee
    HashMap<String,Employee> map = new HashMap<String,Employee>();
     
    // Read the input and create a tree
    while(s.hasNext()){
      name1 = s.next();
      s.next();
      name2 = s.next();
      a = findInHashOrCreate(name1,map);
      b = findInHashOrCreate(name2,map);
      b.manager = a;
    }

    String emp1 = "Bob";
    String emp2 = "Pete";

    // After the creation of the tree we go up and keep track of visited Employees by 

    a = findInHashOrCreate(emp1,map);
    b = findInHashOrCreate(emp2,map);

    HashSet<String> set = new HashSet<String>();

    a = a.manager;

    while(a != null){
      set.add(a.name);
      a = a.manager;
    }

    b = b.manager;

    println("The common manager between:" + emp1 + " and " + emp2);

    while(b.manager != null){
      if(set.contains(b.name)){
        println("is: " + b.name);
        return;
      }
      b = b.manager;
    }
    
    println("No common ancestor");
  }
 
  static public Employee findInHashOrCreate(String name, HashMap<String,Employee> map){
    if(map.containsKey(name)){
      return map.get(name);
    } else {
      Employee e = new Employee(name);
      map.put(name,e);
      return e;
    }
  }

  static public void println(String s){
    System.out.println(s);
  }
}


// Assumptions, each employee is unique by name
class Employee {
  Employee manager;
  String name;
  

  public Employee(String n){
    this.name = n;
  }
}
