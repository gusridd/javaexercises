public class Solution {
    public String shortestPalindrome(String s) {
        // Border cases: ""
        if(s.length() == 0) return s;
        String head = "";
        for(int i = s.length()-1; i>=0;i--){
            String aux = s.substring(0,i);
            if(isPalindrome(aux)){
                return head + s;
            } else {
                head = s.charAt(i) + head;
            }
        }
        return head + s;
    }
    
    public boolean isPalindrome(String s){
        if(s.length() == 0) return true;
        for(int i=0; i<s.length()/2; i++){
            if(s.charAt(i) != s.charAt(s.length()-1-i)) return false;
        }
        return true;
    }
}