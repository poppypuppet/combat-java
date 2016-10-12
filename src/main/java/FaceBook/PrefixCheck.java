package FaceBook;

import java.util.List;

public abstract class PrefixCheck {
    public Integer[] offsetX = {0, -1, 0, 1};
    public Integer[] offsetY = {-1, 0, 1, 0};

    public void findPrefix(Character[][] board, StringBuilder candidate, List<String> ans, Integer x, Integer y) {
        int m = board.length;
        int n = board[0].length;
        if (x >= 0 && x < m && y >= 0 && y < n) {
            char c = board[x][y];
            if (c != '#') {
                candidate.append(c);
                String prefix = candidate.toString();
                if (isPrefix(prefix)) {
                    ans.add(prefix);
                } else {
                    return;
                }

                board[x][y] = '#';
                for (int i = 0; i < 4; i++) {
                    findPrefix(board, candidate, ans, x + offsetX[i], y + offsetY[i]);
                }
                board[x][y] = c;
                candidate.setLength(candidate.length() - 1);
            }
        }
    }

    public abstract Boolean isPrefix(String s);
}
