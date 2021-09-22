/***********************************************************
 * @Description : 稠密图--邻接矩阵
 *                邻接表只存储非零节点，而邻接矩阵则要把所有的节点信息(非零节点与零节点)都存储下来。
 *                稠密图的非零界点多，零节点少，选用邻接矩阵是最适合不过！ add on 2019-08-01 00:28
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/30 11:50
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter7GraphBasics.Section2GraphToShow;

public class DenseGraph {
    /**
     * 图的顶点数
     */
    private int vertices;
    /**
     * 图的边数
     */
    private int edges;
    /**
     * 当前图是有向图还是无向图
     */
    private boolean directed;
    /**
     * 邻接矩阵，采用vector套vector的形式
     */
    private boolean[][] adj;


    DenseGraph(int vertices, boolean directed) {
        this.vertices = vertices;
        this.edges = 0;
        this.directed = directed;
        // adj初始化为n*n的布尔矩阵，每一个g[i][j]均为false,表示没有任何边
        // false为boolean类型变量默认值
        adj = new boolean[vertices][vertices];
    }


    /**
     * 返回顶点数
     */
    public int V() {
        return vertices;
    }

    /**
     * 返回边的数目
     */
    public int E() {
        return edges;
    }

    /**
     * 添加边,在v和w之间建立一条边
     */
    public void addEdge(int v, int w) {
        // 先确保元素不越界
        assert (v >= 0 && v < vertices);
        assert (w >= 0 && w < vertices);
        // v和w之间是连接地,不需要再加一次，就直接退出
        if (hasEdge(v, w)) {
            return;
        }
        adj[v][w] = true;
        if (!directed) {
            // 无向图实际上是双向图，所以w到v也应该为true.如果是有向图这步就不用处理了
            adj[w][v] = true;
        }
        // 边加1
        edges++;
    }

    /**
     * v和w之间是否存在边
     */
    boolean hasEdge(int v, int w) {
        // 先确保元素不越界
        assert (v >= 0 && v < vertices);
        assert (w >= 0 && w < vertices);
        return adj[v][w];
    }
}
