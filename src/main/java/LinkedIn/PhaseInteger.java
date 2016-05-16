package LinkedIn;

public class PhaseInteger {
    public static int parseInt(String s) {
        if (s == null || s.length() <= 0) {
            throw new NumberFormatException("Not a number");
        }
        int result = 0;
        int walker = 0;
        boolean isNegative = false;
        if (s.charAt(0) == '-') {
            if (s.length() <= 1) {
                throw new NumberFormatException("Not a number");
            }
            isNegative = true;
            walker++;
        }
        while (walker < s.length()) {
            char c = s.charAt(walker++);
            if (c < '0' || c > '9') {
                throw new NumberFormatException("Not a number");
            }
            result = result * 10 + (c - '0');
            System.out.println(result);
        }
        if (isNegative) {
            result = -result;
        }
        return result;
    }

    public static void main(String argv[]) {
        System.out.println(PhaseInteger.parseInt("29837424"));
        System.out.println(PhaseInteger.parseInt("-29837424"));
    }
}
