package LinkedIn;

public class CA2SquareRoot {
    public static void main(String[] argv) {
        CA2SquareRoot r = new CA2SquareRoot();
        System.out.println(r.sqrtMath(4));
        System.out.println(r.sqrtMath(Integer.MAX_VALUE));
    }

    public int sqrt(int x) {
        int res = -1;
        int start = 1, end = x;
        while (start <= end) {
            int mid = (start + end) / 2;
            int prod = mid * mid;
            if (prod < x) {
                start = mid + 1;
            } else if (prod > x) {
                end = mid - 1;
            } else {
                res = mid;
                break;
            }
        }
        return res;
    }

    public double sqrtMath(int x) {
        double result = x;
        while (Math.abs(result * result - x) > 0.0001) {
            result = (result + x / result) / 2;
        }
        return result;
    }
}
