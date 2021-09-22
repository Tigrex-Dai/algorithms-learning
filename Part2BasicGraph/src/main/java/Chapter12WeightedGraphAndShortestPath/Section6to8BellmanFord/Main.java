/***********************************************************
 * @Description : Bellman-Ford算法测试
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/23 21:11
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter12WeightedGraphAndShortestPath.Section6to8BellmanFord;

import Chapter11WeightedGraphAndMinimumSpanningTree.Section1To2WeightedGraph.ReadWeightedGraph;
import Chapter11WeightedGraphAndMinimumSpanningTree.Section1To2WeightedGraph.WeightedGraph;

public class Main {
    public static void main(String[] args) {
        // 1.不存在负权边
        String filepath = "src/main/java/Chapter12WeightedGraphAndShortestPath/Section1to3Dijkstra/graph.txt";
        WeightedGraph graph = new WeightedGraph(false);
        ReadWeightedGraph.init(graph, filepath);
        int start = 0;
        ShortestPathBellmanFord bellmanFord = new ShortestPathBellmanFord(graph, 0);
        if (bellmanFord.hasNegativeCycle()) {
            System.out.println("图中存在负权环！");
        } else {
            for (int v = 0; v < graph.V(); v++) {
                System.out.println(start + "到" + v + "的最短距离为：" + bellmanFord.shortestDistanceTo(v)+ ", 路径详情是：" + bellmanFord.getPath(v));
            }
        }

        // 2.存在负权边
        filepath = "src/main/java/Chapter12WeightedGraphAndShortestPath/Section6to8BellmanFord/graph.txt";
        graph = new WeightedGraph(false);
        ReadWeightedGraph.init(graph, filepath);
        start = 0;
        bellmanFord = new ShortestPathBellmanFord(graph, 0);
        if (bellmanFord.hasNegativeCycle()) {
            System.out.println("图中存在负权环！");
        } else {
            for (int v = 0; v < graph.V(); v++) {
                System.out.println(start + "到" + v + "的最短距离为：" + bellmanFord.shortestDistanceTo(v)+ ", 路径详情是：" + bellmanFord.getPath(v));
            }
        }
    }
}
/**
 * 顶点数V = 5, 边数E = 8
 * 0到0的最短距离为：0, 路径详情是：[0]
 * 0到1的最短距离为：3, 路径详情是：[0, 2, 1]
 * 0到2的最短距离为：2, 路径详情是：[0, 2]
 * 0到3的最短距离为：5, 路径详情是：[0, 2, 1, 3]
 * 0到4的最短距离为：6, 路径详情是：[0, 2, 1, 4]
 * 顶点数V = 5, 边数E = 8
 * 图中存在负权环！
 */