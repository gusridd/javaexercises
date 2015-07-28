import java.util.HashSet;
import java.lang.StringBuffer;
import java.util.regex.Pattern;

public class regex {

  static public void main(String args[]){
    HashSet<String> dic = new HashSet<String>();

    dic.add("AB");
    dic.add("BC");
    dic.add("CD");
    dic.add("E");
    dic.add("CDEF");
    
    StringBuffer buff = new StringBuffer();
    buff.append("[");

    for(String e : dic){
	buff.append("(");
        buff.append(e);
	buff.append(")");
    }
    buff.append("]*");
    String res = buff.toString();
    System.out.println(res);
    
    //Pattern p = Pattern.compile(res);
    if(Pattern.matches(res,"ABCDEF")){
      System.out.println("Lo pille");
    } else {
      System.out.println("no lo pille");
    }

  }

}
