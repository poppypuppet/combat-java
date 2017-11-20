package LeetCode;

import java.util.LinkedList;

/**
 * Convert a non-negative integer to its english words representation.
 * Given input is guaranteed to be less than 2^31 - 1.
 * <p>
 * For example,
 * 123 -> "One Hundred Twenty Three"
 * 12345 -> "Twelve Thousand Three Hundred Forty Five"
 * 1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 * Hint:
 * <p>
 * Did you see a pattern in dividing the number into chunk of words? For example, 123 and 123000.
 * Group the number by thousands (3 digits). You can write a helper function that takes a number less than 1000 and convert just that chunk to words.
 * There are many edge cases. What are some good test cases? Does your code work with input such as 0? Or 1000010? (middle chunk is zero and should not be printed out)
 * <p>
 * 题目中给足了提示,首先告诉我们要3个一组的进行处理,而且题目中限定了输入数字范围为0到231-1之间,
 * 最高只能到billion位,3个一组也只需处理四组即可,那么我们需要些一个处理三个一组数字的函数,
 * 我们需要把1到19的英文单词都列出来,放到一个数组里,还要把20,30到90的英文单词列出来放到另一个数组里,
 * 然后我们需要用写技巧,
 * 比如一个三位数n,百位数表示为n/100,后两位数一起表示为n%100,十位数表示为n%100/10,个位数表示为n%10,
 * 然后我们看后两位数是否小于20,
 * 小于的话直接从数组中取出单词,如果大于等于20的话,则分别将十位和个位数字的单词从两个数组中取出来.
 * 然后再来处理百位上的数字,还要记得加上Hundred.
 * 主函数中调用四次这个帮助函数,然后中间要插入"Thousand", "Million", "Billion"到对应的位置,
 * 最后check一下末尾是否有空格,把空格都删掉,返回的时候检查下输入是否为0,是的话要返回'Zero'.
 */
public class IntegerToEnglishWords {
    private final String[] v = {"Thousand", "Million", "Billion"};
    private final String[] v1 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] v2 = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    public static void main(String[] argv) {
        IntegerToEnglishWords i2w = new IntegerToEnglishWords();
        System.out.println(i2w.numberToWords(102938475));
    }

    public String numberToWords(int num) {
        LinkedList<String> ans = new LinkedList<>();
        int res = num % 1000;
        if (res > 0) {
            String ons = toHundreds(res);
            if (!"".equals(ons))
                ans.offerFirst(ons);
        }
        num /= 1000;
        for (int i = 0; i < 3 && num != 0; i++) {
            res = num % 1000;
            if (res > 0) {
                if (!"".equals(v[i])) {
                    ans.offerFirst(v[i]);
                }
                String hds = toHundreds(res);
                if (!"".equals(hds)) {
                    ans.offerFirst(hds);
                }
            }
            num /= 1000;
        }
        return ans.isEmpty() ? "Zero" : String.join(" ", ans);
    }

    public String toHundreds(int num) {
        LinkedList<String> ans = new LinkedList<>();
        int hundred = num / 100;
        if (hundred > 0) {
            ans.add(v1[hundred]);
            ans.add("Hundred");
        }
        int tens = num % 100;
        if (tens >= 20) {
            int ten = tens / 10;
            int ones = tens % 10;
            if (!"".equals(v2[ten])) {
                ans.add(v2[ten]);
            }
            if (!"".equals(v1[ones])) {
                ans.add(v1[ones]);
            }
        } else {
            if (!"".equals(v1[tens])) {
                ans.add(v1[tens]);
            }
        }
        return ans.isEmpty() ? "" : String.join(" ", ans);
    }
}
