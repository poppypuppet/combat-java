package LeetCode;

/**
 * Related to question Excel Sheet Column Title
 * Given a column title as appear in an Excel sheet',' return its corresponding column number.
 * For example:
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * Credits:
 * Special thanks to @ts for adding this problem and creating all test cases.
 */
public class ExcelSheetColumnNumber {
    public int titleToNumber(String s) {
        int res = 0;
        for (char c : s.toCharArray()) {
            res *= 26;
            res += c - 'A' + 1;
        }
        return res;
    }
}
