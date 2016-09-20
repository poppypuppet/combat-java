package LeetCode;

import java.util.Stack;

public class BasicCalculator {
    public static void main(String[] argv) {
        BasicCalculator bc = new BasicCalculator();
        System.out.println(bc.calculate(" 3 + 2 * 2 "));
    }

    /**
     * Implement a basic calculator to evaluate a simple expression string.
     * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces.
     * The integer division should truncate toward zero.
     * You may assume that the given expression is always valid.
     * Some examples:
     * "3+2*2" = 7
     * " 3/2 " = 1
     * " 3+5 / 2 " = 5
     * Note: Do not use the eval built-in library function.
     * 思路就是两个stack,一个存数字一个存符号.如果遇到数字直接存到数字stack；如果遇到符号,有几种情况:
     * 1.当前符号比上一个符号优先级高,比如* 高于+,那么直接进栈
     * 2.当前符号低于上一个,那么就要把所有已经在stack里面优先于当前符号的全算完,再推进当前符号
     * 3.当前符号是“（”,直接push
     * 4.当前符号是“）”,就要把所有“（”以前的符号全部算完
     * 这道题只有“+”号与“-”号,不用判断符号的优先级.
     * <p>
     * 由于存在运算优先级,我们采取的措施是使用一个栈保存数字,
     * 如果该数字之前的符号是加或减,那么把当前数字压入栈中,
     * 注意如果是减号,则加入当前数字的相反数,因为减法相当于加上一个相反数.
     * 如果之前的符号是乘或除,那么从栈顶取出一个数字和当前数字进行乘或除的运算,再把结果压入栈中,
     * 那么完成一遍遍历后,所有的乘或除都运算完了,再把栈中所有的数字都加起来就是最终结果了.
     */
    public int calculate(String s) {
        Stack<Integer> nums = new Stack();
        int start = 0;
        Character op = '+';
        int n = s.length();
        for (int i = 0; i < n + 1; i++) {
            Character c = null;
            if (i < n) {
                c = s.charAt(i);
            }
            if (c == null || !Character.isDigit(c)) {
                if (c != null && c == ' ') {
                    continue;
                }
                // 得出上次数字
                Integer num = Integer.valueOf(s.substring(start, i).trim());
                start = i + 1;

                if (op == '+') {
                    nums.add(num);
                } else if (op == '-') {
                    nums.add(-num);
                } else if (op == '*') {
                    Integer pre = nums.pop();
                    nums.add(pre * num);
                } else if (op == '/') {
                    Integer pre = nums.pop();
                    nums.add(pre / num);
                }
                op = c;
            }
        }
        return nums.stream().reduce(0, Integer::sum);
    }
}
