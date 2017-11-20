package LeetCode;

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
    private String digits[] = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private String tens[] = {"Zero", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    String int2word(int num) {
        if (num >= 1000000000)
            return int2word(num / 1000000000) + " Billion" + int2word(num % 1000000000);
        else if (num >= 1000000)
            return int2word(num / 1000000) + " Million" + int2word(num % 1000000);
        else if (num >= 1000)
            return int2word(num / 1000) + " Thousand" + int2word(num % 1000);
        else if (num >= 100)
            return int2word(num / 100) + " Hundred" + int2word(num % 100);
        else if (num >= 20)
            return " " + tens[num / 10] + int2word(num % 10);
        else if (num > 0)
            return " " + digits[num];
        else
            return "";
    }

    String numberToWords(int num) {
        if (num == 0)
            return "Zero";
        else {
            return int2word(num).trim();
        }
    }
}
