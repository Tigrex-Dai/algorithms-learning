/***********************************************************
 * @Description : 图的DFS测试
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-08-07 21:49
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter7GraphBasics.Graph.test;

import Chapter7GraphBasics.Graph.Graph;
import Chapter7GraphBasics.Graph.GraphDFS;
import Chapter7GraphBasics.Graph.ReadGraph;

public class MainGraphDFS {
    public static void main(String[] args) {
        // DFS用于连通图
        String filePath = "src/main/java/Chapter7GraphBasics/Graph/data/graphDFS.txt";
        Graph graph = new Graph(7, false);
        ReadGraph.init(graph, filePath);
        GraphDFS graphDFS1 = new GraphDFS(graph);
        System.out.println("深度优先遍历的结果是：" + graphDFS1.getOrderList());
        System.out.println("连通分量的个数是：" + graphDFS1.getConnectedComponentCount());

        System.out.println();

        // DFS用于非连通图
        filePath = "src/main/java/Chapter7GraphBasics/Graph/data/graphDFSNotConnected.txt";
        graph = new Graph(7, false);
        ReadGraph.init(graph, filePath);
        GraphDFS graphDFS2 = new GraphDFS(graph);
        System.out.println("深度优先遍历的结果是：" + graphDFS2.getOrderList());
        System.out.println("连通分量的个数是：" + graphDFS2.getConnectedComponentCount());

    }
}
