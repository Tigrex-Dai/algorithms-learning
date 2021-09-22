/***********************************************************
 * @Description : 深度优先遍历DFS用于计算连通分量的个数
 *                实际就是在DFS递归的地方加一个计数器
 *                2019-12-22 从第4章第1节拷贝过来把Graph改成WeightedGraph就行了
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-12-22 20:24
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter11WeightedGraphAndMinimumSpanningTree.Section3to5Kruskal;

import Chapter11WeightedGraphAndMinimumSpanningTree.Section1To2WeightedGraph.WeightedGraph;

import java.util.ArrayList;
import java.util.List;

public class GraphDFS4ConnectedComponents {
    private WeightedGraph graph;

    /**
     * 存储顶点是否被访问的数组
     */
    private boolean[] visited;

    /**
     * 存放图的深度优先遍历的结果
     */
    private List<Integer> orderList = new ArrayList<>();


    /**
     * 连通分量计数器
     */
    private int connectedComponentCount;

    public GraphDFS4ConnectedComponents(WeightedGraph graph) {
        this.graph = graph;
        // 初始化访问数组，用图的顶点个数来访问
        visited = new boolean[graph.V()];
        // 从dfs(0)改成下面的代码，可以支持非连通的图
        for (int v = 0; v < graph.V(); v++) {
            if (!visited[v]) {
                dfs(v);
                // 当退出递归时，相当于结束了一个连通图的遍历，所以连通分量数加1
                connectedComponentCount++;
            }
        }
    }

    public Iterable<Integer> getOrderList() {
        return orderList;
    }

    public int getConnectedComponentCount() {
        return connectedComponentCount;
    }

    private void dfs(int v) {
        visited[v] = true;
        orderList.add(v);
        for (Integer w : graph.adj(v)) {
            if (!visited[w]) {
                // w点没被访问的话就递归接着访问
                dfs(w);
            }
        }
    }
}
