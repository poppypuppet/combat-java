package LeetCode;

/**
 * Given an integer, convert it to a roman numeral.
 * Input is guaranteed to be within the range from 1 to 3999.
 */
public class IntegerToRoman {
    public static int[] nums = new int[]{1000, 500, 100, 50, 10, 5, 1};
    public static char[] romans = new char[]{'M', 'D', 'C', 'L', 'X', 'V', 'I'};

    public String intToRoman(int num) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < romans.length; i += 2) {
            int bit = num / nums[i];
            if (bit < 4) {
                for (int j = bit; j > 0; j--) {
                    ans.append(romans[i]);
                }
            } else if (bit == 4) {
                ans.append(romans[i]).append(romans[i - 1]);
            } else if (bit < 9) {
                ans.append(romans[i - 1]);
                for (int j = bit - 5; j > 0; j--) {
                    ans.append(romans[i]);
                }
            } else {
                ans.append(romans[i]).append(romans[i - 2]);
            }
            num = num % nums[i];
        }
        return ans.toString();
    }
}
