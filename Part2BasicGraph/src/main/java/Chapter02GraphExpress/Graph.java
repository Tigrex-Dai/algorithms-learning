/***********************************************************
 * @Description : 图的最优表示(基于TreeSet的邻接表)，后面所有的图的操作都会基于这个去讲,
邻接矩阵方法多用于稠密图;邻接表多用于稀疏图。考虑到我们见到的大多数图论问题是稀疏图，
所以我们后面都用本文件中的基于TreeSet的邻接表实现
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019/08/02 07:53
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter02GraphExpress;

import java.util.TreeSet;

public class Graph implements IGraph, Cloneable {
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
     * 邻接表，采用vector套vector的形式
     */
    private TreeSet<Integer>[] adj;
    /**
     * 所有顶点的入度
     */
    private int[] inDegrees;
    /**
     * 所有顶点的出度
     */
    private int[] outDegrees;

    public Graph(boolean directed) {
        this(0, directed);
    }

    public Graph(int vertices, boolean directed) {
        this.vertices = vertices;
        this.edges = 0;
        this.directed = directed;
        inDegrees = new int[vertices];
        outDegrees = new int[vertices];
        // 泛型数组需要强制转换，可以认为是Java语言的缺陷
        adj = (TreeSet<Integer>[]) new TreeSet[vertices];
        for (int i = 0; i < vertices; i++) {
            // 每个顶点都有一组邻边组成邻接表，用TreeSet可以提高性能
            adj[i] = new TreeSet<>();
        }
    }


    /**
     * 返回顶点数
     */
    @Override
    public int V() {
        return vertices;
    }

    /**
     * 设置图的顶点数
     *
     * @param vertices 顶点数
     */
    public void setVertices(int vertices) {
        this.vertices = vertices;
        inDegrees = new int[vertices];
        outDegrees = new int[vertices];
        // 泛型数组需要强制转换，可以认为是Java语言的缺陷
        adj = (TreeSet<Integer>[]) new TreeSet[vertices];
        for (int i = 0; i < vertices; i++) {
            // 每个顶点都有一组邻边组成邻接表，用TreeSet可以提高性能
            adj[i] = new TreeSet<>();
        }
    }

    /**
     * 返回边的数目
     */
    @Override
    public int E() {
        return edges;
    }

    @Override
    public void validateVertex(int v) {
        assert (v >= 0 && v < vertices);
    }

    /**
     * 添加边,在顶点v和顶点w之间建立一条边
     */
    @Override
    public void addEdge(int v, int w) {
        // 先确保元素不越界
        validateVertex(v);
        validateVertex(w);
        // v和w之间是连接地,不需要再加一次，就直接退出。这样是为了防止平行边，但是从hasEdge实现可知成本较高，所以就by了
        // 平行边可以在所有边加完之后统一去掉，自己实现
        // if (hasEdge(v, w)) {
        //    return;
        // }

        // v=w会生成自环边
        if (v == w) {
            throw new IllegalArgumentException("Self Loop is Detected!");
        }
        adj[v].add(w);
        if (!directed) {
            // 无向图实际上是双向图，所以w到v也应该为true.如果是有向图这步就不用处理了
            adj[w].add(v);
        } else {
            // 有向图，入度和出度都要更新，因为是v-->w，所以v的出度+1，w的入度+1
            outDegrees[v]++;
            inDegrees[w]++;
        }
        // 边加1
        edges++;
    }

    /**
     * v和w之间是否存在边
     */
    @Override
    public boolean hasEdge(int v, int w) {
        // 先确保元素不越界
        validateVertex(v);
        validateVertex(w);
        // v的邻接表中是否有w
        return adj[v].contains(w);
    }

    @Override
    public int degree(int v) {
        // Todo:针对有向图的度比较麻烦，第13章最后讲
        if (directed) {
            throw new RuntimeException("degree()方法仅能用于无向图");
        }
        return adj[v].size();
    }

    /**
     * 顶点v的入度(其他边指向v的条数)
     */
    public int inDegree(int v) {
        if (!directed){
            throw new RuntimeException("inDegree()方法仅适用于有向图");
        }
        validateVertex(v);
        return inDegrees[v];
    }

    /**
     * 顶点v的出度(从v指向其他顶点的边数)
     */
    public int outDegree(int v) {
        if (!directed){
            throw new RuntimeException("outDegree()方法仅适用于有向图");
        }
        validateVertex(v);
        return outDegrees[v];
    }

    @Override
    public void removeEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        if (adj[v].contains(w)){
            // 边数-1
            edges--;
            // v的邻接点包含w才进行删除边
            adj[v].remove(w);
            if (!directed) {
                // 无向图才需要删除边w-v
                adj[w].remove(v);
            }else {
                // 有向图需要更新入度和出度.v->w所以v的出度-1，w的入度-1
                outDegrees[v]--;
                inDegrees[w]--;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("顶点数V = %d, 边数E = %d\n", vertices, edges));
        // 遍历所有顶点vertex(顶点都是按照编号顺序来地)，顶点是用从0开始的连续正整数表示时v才代表顶点，
        // 如果顶点不是用连续的正整数或者是用字符等形式来表示时，就要建立顶点数下标v和顶点实际含义的映射关系了，可以用map来表示
        // 参考 https://coding.imooc.com/learn/questiondetail/133447.html
        // vertices是vertex的复数形式，两者都是顶点的意思
        for (int v = 0; v < vertices; v++) {
            sb.append(String.format("vertex %d:\t", v));
            // 遍历顶点vertex的所有邻接点
            for (Integer w : adj[v]) {
                sb.append(String.format("%d\t", w));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public void show() {
        System.out.println(toString());
    }


    /**
     * 返回顶点v的所有临边
     * 由于java使用引用机制，返回一个Iterable对象不会带来额外开销,TreeSet、Vector、HashSet等都实现了Iterable接口
     */
    @Override
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        // 邻接表本身v处就是表达地v的所有邻接点
        return adj[v];
    }

    /**
     * 实现Graph对象的深拷贝(adj要完成拷贝过去)
     *
     * @return 深拷贝后的graph，在调用时不会改变原对象
     * @throws CloneNotSupportedException 不支持Clone的异常
     */
    @Override
    public Object clone() {
        // 实现深拷贝
        Graph cloned = null;
        try {
            cloned = (Graph) super.clone();
            cloned.adj = new TreeSet[vertices];
            for (int v = 0; v < vertices; v++) {
                cloned.adj[v] = new TreeSet<>();
                for (int w : adj[v]) {
                    cloned.adj[v].add(w);
                }
            }
            return cloned;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        // 出异常了就返回null
        return null;
    }

    /**
     * 返回图片是否是有向图
     */
    public boolean isDirected() {
        return directed;
    }
}
