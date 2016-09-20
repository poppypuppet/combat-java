package LeetCode;

/**
 * Given a non negative integer number num.
 * For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their
 * binary representation and return them as an array.
 * Example:
 * For num = 5 you should return [0,1,1,2,1,2].
 * Follow up:
 * It is very easy to come up with a solution with run time O(n*sizeof(integer)).
 * But can you do it in linear time O(n) /possibly in a single pass?
 * Space complexity should be O(n).
 * 想一想,当一个数为2的整数幂的时候,1的个数为1,比如2（10) 和4(100),8(1000)
 * 在这之后就是前一个序列的数+1 比如 9(1001) = 1(1) + 8 (1) = 2
 * 就是把一个数分解为小于它的最大2的整数幂 + x
 */
public class CountingBits {
    /**
     * 方法一
     * 想一想,当一个数为2的整数幂的时候,1的个数为1,比如2（10) 和4(100),8(1000)
     * 在这之后就是前一个序列的数+1 比如 9(1001) = 1(1) + 8 (1) = 2
     * 就是把一个数分解为小于它的最大2的整数幂 + x
     * 方法二
     * i&(i - 1),判断一个数是否是2的指数的快捷方法,是0则是2的指数.
     * 倒过来想,一个数 * 2 就是把它的二进制全部左移一位,也就是说 1的个数是相等的.
     * 那么我们可以利用这个结论来做.
     * res[i /2] 然后看看最低位是否为1即可（上面*2一定是偶数,这边比如15和14除以2都是7,但是15时通过7左移一位并且+1得到,14则是直接左移）
     * 所以res[i] = res[i >>1] + (i&1)
     */
    public int[] countBitsI(int num) {
        int[] result = new int[num + 1];
        int p = 1; //p tracks the index for number x
        int pow = 1;
        for (int i = 1; i <= num; i++) {
            if (i == pow) {
                result[i] = 1;
                pow <<= 1;
                p = 1;
            } else {
                result[i] = result[p] + 1;
                p++;
            }
        }
        return result;
    }

    public int[] countBitsII(int num) {
        int[] res = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            res[i] = res[i >> 1] + (i & 1);
        }
        return res;
    }
}
