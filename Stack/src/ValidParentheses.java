import java.util.Stack;

class Solution {
    public boolean isValid(String s) {
        ArrayStack<Character>  stack = new ArrayStack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '('){
                stack.push(')');
            }else if(c == '{'){
                stack.push('}');
            }else if(c == '['){
                stack.push(']');
            }else if( stack.isEmpty() || stack.pop()!=c){
                return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isValid("()"));
        System.out.println(new Solution().isValid("()[]{}"));
        System.out.println(new Solution().isValid("([)]"));
        System.out.println(new Solution().isValid("(]"));
        System.out.println(new Solution().isValid("{[]}"));
    }

}