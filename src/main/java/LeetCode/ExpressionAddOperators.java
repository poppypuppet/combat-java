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

    private void operator(String num, int target, List<String> res, long cursum, long presum, String tmp) {
        if (num.length() == 0 && cursum == target) {
            res.add(tmp);
            return;
        }
        for (int i = 1; i <= num.length(); i++) {
            String front = num.substring(0, i); // [)
            if (front.length() > 1 && front.charAt(0) == '0') {
                // 除去多位数字以0开头的情况
                return;
            }
            long value = Long.parseLong(front);
            String back = num.substring(i);
            if ("".equals(tmp)) {
                // 第一个数字,不需要考虑之前的情况
                operator(back, target, res, cursum + value, cursum, front);
            } else {
                operator(back, target, res, cursum + value, cursum, tmp + "+" + front);
                operator(back, target, res, cursum - value, cursum, tmp + "-" + front);
                operator(back, target, res, (cursum - presum) * value + presum, cursum, tmp + "*" + front);
            }
        }
    }
}
