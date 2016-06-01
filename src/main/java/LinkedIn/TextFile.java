package LinkedIn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

/**
 * Problem
 * Implement a Java Iterable object that iterates lines one by one from a text file.
 * The candidate might well ask:
 * should the Iterator implement remove? A: no.
 * what should happen when an IOException occurs? A: throw an unchecked exception, of your choice.
 * what's Iterable? A: it's a little interface that simply declares the iterator() method, for example
 * java.util.List.iterator() and Set.iterator().
 * Common mistakes include:
 * attempting to read the entire file into memory. Ask the candidate to avoid this (which will require handling in version of control).
 * failing to close the FileReader when the iterator has been exhausted (which leaks a file descriptor).
 * incorrectly handling an empty file (in which case BufferedReader.readLine returns null immediately).
 * trying to re-implementBufferedReader. If they don't know about this class, give them a brief introduction or perhaps abandon this question.
 * creating the BufferedReader in the TextFile constructor instead of the Iterator implementation
 * Suggested hints:
 * Feel free to point candidates at the Javadoc for java.io.* there isn't any reason to expect them to know that off
 * the top of their head. Also, feel free to point them to the relevant classes if they describe what they want.
 * As with many of our iterator questions, reading the entire file at the beginning is an acceptable starting point for
 * them to improve on. If they are having trouble starting, you can guide them to that before asking for the line by line implementation.
 */

public class TextFile implements Iterable<String> {
    private final String fileName;

    public TextFile(String fileName) {
        this.fileName = fileName;
    }

    public static void main(String[] args) {
        for (String fileName : args) {
            for (String line : new TextFile(fileName)) {
                System.out.println(line);
            }
        }
    }

    /**
     * Begin reading the file, line by line. The returned Iterator.next() will return a line.
     * remove() will throw UnsupportedOperationException.
     */
    @Override
    public Iterator<String> iterator() {
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            return new LineIterator(bufferedReader);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static class LineIterator implements Iterator<String> {
        private final BufferedReader bufferedReader;
        private String nextLine;

        public LineIterator(BufferedReader bufferedReader) {
            this.bufferedReader = bufferedReader;
            nextLine = null;
        }

        @Override
        public boolean hasNext() {
            try {
                nextLine = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return nextLine != null;
        }

        @Override
        public String next() {
            if (!hasNext()) {
                return null;
            }
            String line = nextLine;
            nextLine = null;
            return line;
        }
    }
}