package LeetCode;

public class OneEditDistance {
    public static void main(String[] argv) {
        OneEditDistance oed = new OneEditDistance();
        System.out.println(oed.isOneEditDistance("a", "ac"));
    }

    /**
     * 两个字符串一样长的时候，说明有一个替换操作，我们只要看对应位置是不是只有一个字符不一样就行了
     * 一个字符串比另一个长1，说明有个增加或删除操作，我们就找到第一个对应位置不一样的那个字符，如果较长字符串在那个字符之后的部分和较短字符串那个字符及之后的部分是一样的，则符合要求
     * 如果两个字符串长度差距大于1，肯定不对
     */
    public boolean isOneEditDistance(String s, String t) {
        int m = s.length(), n = t.length();
        if (m == n) return isOneModified(s, t);
        if (m - n == 1) return isOneDeleted(s, t);
        if (n - m == 1) return isOneDeleted(t, s);
        // 长度差距大于2直接返回false
        return false;
    }

    private boolean isOneModified(String s, String t) {
        boolean modified = false;
        // 看是否只修改了一个字符
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (modified) return false;
                modified = true;
            }
        }
        return modified;
    }

    public boolean isOneDeleted(String longer, String shorter) {
        // 找到第一组不一样的字符，看后面是否一样
        for (int i = 0; i < shorter.length(); i++) {
            if (longer.charAt(i) != shorter.charAt(i)) {
                return longer.substring(i + 1).equals(shorter.substring(i));
            }
        }
        return true;
    }
}
