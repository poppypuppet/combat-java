package Basic;

public class BitOperations {
    public static void main(String[] argv) {

    }

    /**
     * 判断奇偶
     * 只要根据最未位是0还是1来决定,为0就是偶数,为1就是奇数.
     * 因此可以用if((a & 1) == 0) 代替if(a % 2 == 0) 来判断a是不是偶数.
     */
    boolean isEven(int num) {
        return (num & 1) == 0;
    }

    /**
     * 第一步  a^=b 即a=(a^b);
     * 第二步  b^=a 即b=b^(a^b),由于^运算满足交换律,b^(a^b)=b^b^a.由于一个数和自己异或的结果为0并且任何数与0异或都会不变的,所以此时b被赋上了a的值.
     * 第三步  a^=b 就是a=a^b,由于前面二步可知a=(a^b),b=a,所以a=a^b即a=(a^b)^a.故a会被赋上b的值.
     */
    void Swap(Integer a, Integer b) {
        if (a != b) {
            a ^= b;
            b ^= a;
            a ^= b;
        }
    }

    int rightOne(int a) {
        return a &= -a;
    }
}
