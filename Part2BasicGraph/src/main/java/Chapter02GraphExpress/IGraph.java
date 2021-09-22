/***********************************************************
 * @Description : 图的统一接口
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/08/02 07:52
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter02GraphExpress;

public interface IGraph {
    /**
     * 返回顶点数
     */
    int V();

    /**
     * 返回边数
     */
    int E();

    /**
     * 确保v在有效范围
     *
     * @param v 顶点编号
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
     * 判断顶点v到顶点w之间是否有路径连通
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
     * 删除边v-w
     *
     * @param v 顶点v
     * @param w 顶点w
     */
    void removeEdge(int v, int w);

    /**
     * 打印当前的图
     */
    void show();

    /**
     * 返回顶点v的邻接表的迭代器，方便进行循环遍历v的所有邻接点
     *
     * @param v
     * @return
     */
    Iterable<Integer> adj(int v);
}
