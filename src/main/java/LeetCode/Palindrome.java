package LeetCode;

public class Palindrome {

    /**
     * Valid Palindrome
     * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
     * For example,
     * "A man, a plan, a canal: Panama" is a palindrome.
     * "race a car" is not a palindrome.
     */
    public boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            char head = s.charAt(i);
            char tail = s.charAt(j);
            if (!Character.isLetter(head) && !Character.isDigit(head)) {
                i++;
                continue;
            }
            if (!Character.isLetter(tail) && !Character.isDigit(tail)) {
                j--;
                continue;
            }
            if (Character.isLetter(tail)) {
                if (Character.toLowerCase(head) != Character.toLowerCase(tail)) {
                    return false;
                } else {
                    i++;
                    j--;
                }
            } else {
                if (head != tail) {
                    return false;
                } else {
                    i++;
                    j--;
                }
            }

        }
        return true;
    }

    /**
     * Determine whether an integer is a palindrome. Do this without extra space.
     * 解题思路: 这道题很明显是一道数学题,计算一个数字是否是回文数字,我们其实就是将这个数字除以10,保留他的余数,
     * 下次将余数乘以10,加上这个数字再除以10的余数.
     * <p>
     * 过程中利用分解x,反向构造一个y, 若x=12345, y=54321,最后判断是否相等.
     */
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        } else if (x < 10) {
            return true;
        } else {
            int temp = x;
            int y = 0;
            while (temp > 0) {
                y = y * 10 + temp % 10;
                temp = temp / 10;
                System.out.println(temp + " " + y);
            }
            return x == y;
        }
    }
}
