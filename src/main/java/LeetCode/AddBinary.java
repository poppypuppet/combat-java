package LeetCode;

public class AddBinary {
    /**
     * Given two binary strings, return their sum (also a binary string).
     * <p>
     * For example,
     * a = "11"
     * b = "1"
     * Return "100".
     */
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0 || carry != 0; i--, j--) {
            int vi = 0, vj = 0;
            if (i >= 0) {
                vi = a.charAt(i) - '0';
            }
            if (j >= 0) {
                vj = b.charAt(j) - '0';
            }
            int sum = vi + vj + carry;
            if (sum == 0) {
                carry = 0;
                sb.insert(0, '0');
            } else if (sum == 1) {
                carry = 0;
                sb.insert(0, '1');
            } else if (sum == 2) {
                carry = 1;
                sb.insert(0, '0');
            } else {
                carry = 1;
                sb.insert(0, '1');
            }
        }
        return sb.toString();
    }
}
