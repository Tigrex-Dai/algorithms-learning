/***********************************************************
 * @Description : 统一接口
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/30 13:58
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter7GraphBasics.Section4ReadGraphOptimize;

public interface Graph {
    /**
     * 返回定点数
     */
    int V();

    /**
     * 返回边数
     */
    int E();

    /**
     * 确保v在有效范围
     *
     * @param v 定点编号
     */
    void validateVertex(int v);

    /**
     * 向邻接表/邻接矩阵中添加一条边
     *
     * @param v 起点
     * @param w 终点
     */
    void addEdge(int v, int w);

    /**
     * 判断定点v到定点w之间是否有路径连通
     *
     * @param v 起点
     * @param w 终点
     * @return 是否有路径连通
     */
    boolean hasEdge(int v, int w);

    /**
     * 顶点的度，即有多少条临边
     *
     * @param v 顶点
     * @return 多少条临边
     */
    int degree(int v);

    /**
     * 打印当前的图
     */
    void show();

    /**
     * 返回当前的图的迭代器，方便进行循环遍历
     *
     * @param v
     * @return
     */
    Iterable<Integer> adj(int v);
}
