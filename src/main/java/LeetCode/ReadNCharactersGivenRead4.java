package LeetCode;

/**
 * The API: int read4(char *buf) reads 4 characters at a time from a file.
 * The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
 * By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
 * Note:
 * The read function may be called multiple times.
 */
public class ReadNCharactersGivenRead4 {
    private char[] buffer = new char[4];
    private int offset = 0;
    private int bufsize = 0;

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

    /**
     * 如果read函数可能被多次调用
     * [分析]
     * 跟之前的一道题目比，这一题要复杂不少。主要是因为read函数可以调用多次以后，有可能文件中的部分内容被读出来，但是暂时没有用到。因此需要额外的空间来缓存读出来的字符。
     * [注意事项]
     * 1）对于几个变量的解读：
     * － buffer 存储从文件中读出来的字符
     * － offset 上一次读取之后buffer中剩下字符的偏移量
     * － bufsize buffer中剩下字符的个数
     */
    public int readII(char[] buf, int n) {
        int total = 0;
        boolean eof = false;
        while (!eof && total < n) {
            int sz = (bufsize > 0) ? bufsize : read4(buffer);
            if (bufsize == 0 && sz < 4) eof = true;
            int read = Math.min(n - total, sz);
            System.arraycopy(buffer, offset, buf, total, read);
            offset = (offset + read) % 4;
            bufsize = sz - read;
            total += read;
        }
        return total;
    }

    int read4(char[] buf) {
        return 0;
    }
}
