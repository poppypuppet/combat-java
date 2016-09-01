package LeetCode;

import java.util.PriorityQueue;

public class UglyNumber {
    public static void main(String[] argv) {
        UglyNumber un = new UglyNumber();
        System.out.println(un.nthUglyNumber(11));
    }

    /**
     * Ugly Number  QuestionEditorial Solution  My Submissions
     * Write a program to check whether a given number is an ugly number.
     * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
     * For example, 6, 8 are ugly while 14 is not ugly since it includes another prime factor 7.
     * Note that 1 is typically treated as an ugly number.
     */
    public boolean isUgly(int num) {
        while (num > 1) {
            if (num % 2 == 0) num /= 2;
            else if (num % 3 == 0) num /= 3;
            else if (num % 5 == 0) num /= 5;
            else return false;
        }
        return num == 1;
    }

    /**
     * Write a program to find the n-th ugly number.
     * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
     * For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
     * Note that 1 is typically treated as an ugly number.
     */
    public int nthUglyNumber(int n) {
        if (n > 1) {
            PriorityQueue<Ugly> heap = new PriorityQueue<Ugly>(3, (a, b) -> a.prime * a.count - b.prime * b.count);
            heap.offer(new Ugly(2, 1));
            heap.offer(new Ugly(3, 1));
            heap.offer(new Ugly(5, 1));
            int ans = 1;
            for (int i = 1; i < n; i++) {
                Ugly u = heap.poll();
                heap.offer(new Ugly(u.prime, u.count + 1));
                if (ans == u.prime * u.count) {
                    i--;
                } else {
                    ans = u.prime * u.count;
                    System.out.println(i + " " + ans);
                }
                System.out.println(heap);
            }
            return ans;
        } else {
            return n;
        }
    }

    public class Ugly {
        public int prime;
        public int count;

        public Ugly(int prime, int count) {
            this.prime = prime;
            this.count = count;
        }

        public String toString() {
            return String.format("(%d,%d)", prime, count);
        }
    }
}
