package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string that contains only digits 0-9 and a target value,
 * return all possibilities to add binary operators (not unary) +, -, or * between the digits
 * so they evaluate to the target value.
 * <p>
 * Examples:
 * "123", 6 -> ["1+2+3", "1*2*3"]
 * "232", 8 -> ["2*3+2", "2+3*2"]
 * "105", 5 -> ["1*0+5","10-5"]
 * "00", 0 -> ["0+0", "0-0", "0*0"]
 * "3456237490", 9191 -> []
 */
public class ExpressionAddOperators {
    public static void main(String[] argv) {
        ExpressionAddOperators eao = new ExpressionAddOperators();
        System.out.println(eao.addOperators("123", 6));
    }

    /**
     * ...........................123
     * ......................./....|....\
     * 第一个数................1....12...123
     * ...................../..\.....\
     * 第二个数.............2...23.....3
     * ................../
     * 第三个数..........3
     */
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        operator(num, target, res, 0, 0, "");
        return res;
    }

    private void operator(String num, int target, List<String> result, long curSum, long preSum, String temp) {
        if (num.length() == 0 && curSum == target) {
            result.add(temp);
            return;
        }
        for (int i = 1; i <= num.length(); i++) { //此处是<=，而不是<
            String front = num.substring(0, i);
            if (front.length() > 1 && front.charAt(0) == '0') {
                return;
            }
            long value = Long.parseLong(front);
            String back = num.substring(i);
            if (!"".equals(temp)) {
                operator(back, target, result, curSum + value, curSum, temp + "+" + front);
                operator(back, target, result, curSum - value, curSum, temp + "-" + front);
                operator(back, target, result, (curSum - preSum) * value + preSum, preSum, temp + "*" + front);
            } else {
                operator(back, target, result, curSum + value, curSum, front);
            }
        }
    }
}
