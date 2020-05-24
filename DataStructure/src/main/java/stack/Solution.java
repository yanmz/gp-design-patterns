package stack;

import java.util.Stack;

public class Solution {
    public static void main(String[] args) {
        boolean valid = isValid("({[]})");
        System.out.println(valid);
    }
    public static boolean isValid(String str){
         Stack<Character> stack = new Stack<Character>();
         for (int i = 0;i<str.length();i++){
            char c= str.charAt(i);
            if(c=='(' || c=='[' ||c=='{'){
                stack.push(c);
            }else{
                if(stack.isEmpty()){
                    return  false;
                }
                char topc = stack.pop();
                if(c==')' && topc!='('){
                        return false;
                }
                if(c==']' && topc!='['){
                    return  false;
                }
                if(c=='}' && topc!='{'){
                    return false;
                }
            }
         }
        return stack.isEmpty();
    }
}
