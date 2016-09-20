package LeetCode;

/**
 * Aline Dictionary
 * There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you.
 * You receive a list of words from the dictionary, where words are sorted lexicographically by
 * the rules of this new language. Derive the order of letters in this language.
 * <p>
 * For example, Given the following words in dictionary,
 * [
 * "wrt",
 * "wrf",
 * "er",
 * "ett",
 * "rftt"
 * ]
 * The correct order is: "wertf".
 * Note: You may assume all letters are in lowercase.
 * If the order is invalid, return an empty string.
 * There may be multiple valid order of letters, return any one of them is fine.
 * <p>
 * 这道题让给了我们一些按“字母顺序”排列的单词,但是这个字母顺序不是我们熟知的顺序,而是另类的顺序,让我们根据这些“有序”的单词来找出新的字母顺序,
 * 这实际上是一道有向图的问题,跟之前的那两道 Course Schedule II 和 Course Schedule 的解法很类似.
 * <p>
 * BFS
 * 我们需要一个set来保存我们可以推测出来的顺序关系,比如题目中给的例子,我们可以推出的顺序关系有:
 * t->f
 * w->e
 * r->t
 * e->r
 * 那么set就用来保存这些pair,我们还需要另一个set来保存所有出现过的字母,需要一个一维数组in来保存每个字母的入度,另外还要一个queue来辅助拓扑遍历.
 * 我们先遍历单词集,把所有字母先存入ch,然后我们每两个相邻的单词比较,找出顺序pair,然后我们根据这些pair来赋度,我们把ch中入度为0的字母都排入queue中,
 * 然后开始遍历,如果字母在set中存在,则将其pair中对应的字母的入度减1,若此时入度减为0了,则将对应的字母排入queue中并且加入结果res中,直到遍历完成,
 * 我们看结果res和ch中的元素个数是否相同,若不相同则说明可能有环存在,返回空字符串.
 * <p>
 * DFS
 * 我们需要建立一个二维的bool数组g,然后把出现过的字母的对应的位置都赋为true,然后我们把可以推出的顺序的对应位置也赋为true,然后我们就开始递归调用,
 * 如果递归函数有返回false的,说明有冲突,则返回false,递归调用结束后结果res中存了入度不为0的字母,最后把入度为0的字母加到最后面,最后把结果res翻转一下即可
 * <p>
 * <p>
 * 拓展到拓扑排序中,结果具有唯一性的条件也是其所有顶点之间都具有全序关系.如果没有这一层全序关系,那么拓扑排序的结果也就不是唯一的了.在后面会谈到,如果拓扑排序的结果唯一,那么该拓扑排序的结果同时也代表了一条哈密顿路径.
 * <p>
 * 典型实现算法:
 * Kahn算法:
 * 摘一段维基百科上关于Kahn算法的伪码描述:
 * L ← Empty list that will contain the sorted elements
 * S ← Set of all nodes with no incoming edges
 * while S is non-empty do
 * remove a node n from S
 * insert n into L
 * foreach node m with an edge e from n to m do
 * remove edge e from the graph
 * ifm has no other incoming edges then
 * insert m into S
 * if graph has edges then
 * return error (graph has at least one cycle)
 * else
 * return L (a topologically sorted order)
 * <p>
 * 不难看出该算法的实现十分直观,关键在于需要维护一个入度为0的顶点的集合:
 * 每次从该集合中取出(没有特殊的取出规则,随机取出也行,使用队列/栈也行,下同)一个顶点,将该顶点放入保存结果的List中.
 * 紧接着循环遍历由该顶点引出的所有边,从图中移除这条边,同时获取该边的另外一个顶点.
 * 如果该顶点的入度在减去本条边之后为0,那么也将这个顶点放到入度为0的集合中.然后继续从集合中取出一个定点...
 * <p>
 * 当集合为空之后,检查图中是否还存在任何边,如果存在的话,说明图中至少存在一条环路.不存在的话则返回结果List,此List中的顺序就是对图进行拓扑排序的结果.
 */
public class AlineDictionary {
}
