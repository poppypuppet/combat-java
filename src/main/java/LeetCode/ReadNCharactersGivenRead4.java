package LeetCode;

/**
 * The API: int read4(char *buf) reads 4 characters at a time from a file.
 * The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
 * By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
 * Note:
 * The read function may be called multiple times.
 */
public class ReadNCharactersGivenRead4 {
    /**
     * 由于每次只能读进4个字符，而n未必是4的倍数。所以一直读到len+4>n时停止。
     * 剩下m=n-len<4个字符。建立一个新的缓存remain读入4个字符，然后将其中的m个写入buff尾部。
     * 要注意在整个读入过程中的每个阶段判断是否已经读完文件。
     *
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return The number of characters read
     */
    public int read(char[] buf, int n) {
        boolean eof = false; //end of file
        char[] temp = new char[4];
        int total = 0;
        while (!eof && total < n) {
            int count = read4(temp);
            if (count < 4) {
                eof = true;
            }
            // get the actual count
            count = Math.min(count, n - total);
            for (int i = 0; i < count; i++) {
                buf[total++] = temp[i];
            }
        }
        return total;
    }

    int read4(char[] buf) {
        return 0;
    }
}
