import java.lang.*;
import java.util.*;
import java.io.*;

public class p59 {
  static public void main(String[] args) throws Exception {
    Scanner scan = new Scanner(new File("p059_cipher.txt"));
    scan.useDelimiter(",");
    ArrayList<Integer> list = new ArrayList<Integer>();
    int current;
    while(scan.hasNextInt()){
      current = scan.nextInt();
      list.add(current);
      //System.out.println(current);
    }
    //System.out.println(Arrays.toString(list.toArray()));

    for(char a = 'a'; a < 'z'; a++){
      for(char b = 'a'; b < 'z'; b++){
        for(char c = 'a'; c < 'z'; c++){
          for(int i = 0; i < list.size()-2; i++){
            char t = (char)((char)Character.toChars(list.get(i))[0] ^ (char)a);
            char h = (char)((char)Character.toChars(list.get(i+1))[0] ^ (char)b);
            char e = (char)((char)Character.toChars(list.get(i+2))[0] ^ (char)c);
            char[] data = {t,h,e};
            if((new String(data)).compareTo("the") == 0){
              System.out.println(a+"");
              System.out.println(b+"");
              System.out.println(c+"");
              System.out.println((a+b+c)+"");
              printText(list,a,b,c);
              System.out.println(sum(list,a,b,c)+"");
            }
          }
        }
      }
    }
  }

  static public void printText(ArrayList<Integer> list, char a, char b, char c){
    for(int i = 0; i < list.size()-2; i = i+3){
      char t = (char)((char)Character.toChars(list.get(i))[0] ^ (char)a);
      char h = (char)((char)Character.toChars(list.get(i+1))[0] ^ (char)b);
      char e = (char)((char)Character.toChars(list.get(i+2))[0] ^ (char)c);
      char[] data = {t,h,e};
      System.out.print(new String(data));
    }
    System.out.println();
  }

  static public int sum(ArrayList<Integer> list, char a, char b, char c){
    int sum = 0;
    for(int i = 0; i < list.size()-2; i = i + 2){
      char t = (char)((char)Character.toChars(list.get(i))[0] ^ (char)a);
      char h = (char)((char)Character.toChars(list.get(i+1))[0] ^ (char)b);
      char e = (char)((char)Character.toChars(list.get(i+2))[0] ^ (char)c);
      sum += t+h+e;
    }
    return sum;
  }
  
}
