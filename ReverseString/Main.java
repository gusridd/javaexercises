import java.lang.*;
import java.util.*;

class Main {
  
  static public void main(String[] args){
    println("ReverseString!");

    String str1 = "Hello World!";
    String exp1 = "!dlroW olleH";
    String res1 = reverseRecursive(str1);
    String res2 = reverseIterative(str1);
    String res3 = reverseLazy(str1);
    println(str1);    
    println(res1);
    println(res2);
    println(res3);
  }

  static public void println(String s){
    System.out.println(s);
  }


  // Function that reverses a String: example Hello -> olleH
  // Takes O(n), with n the size of the string
  static public String reverseRecursive(String s){
    if(s.compareTo("") == 0){
      return s;
    } else {
      return reverseRecursive(s.substring(1)) + s.charAt(0);
    }
  }

  static public String reverseRecursiveV2(String s){
    StringBuilder sb = new StringBuilder(s);
    reverseRecursiveV2Aux(s,sb);
    return sb.toString();
  }

  static public void reverseRecursiveV2Aux(String s, StringBuilder sb){
    if(s.compareTo("") == 0){
      return;
    } else {
      
      reverseRecursiveV2Aux(s.substring(1), sb) + s.charAt(0);
      sb.append(s.charAt(0));
    }
  }

  static public String reverseIterative(String s){
    char arr[] = s.toCharArray();
    char aux;
    for(int i = 0; i < s.length()/2; i++){
       aux = arr[i];
       arr[i] = arr[s.length()-i-1];
       arr[s.length()-i-1] = aux;
    }
    return new String(arr);
  }

  static public String reverseLazy(String s){
    return (new StringBuilder(s)).reverse().toString();
  }
  
}
