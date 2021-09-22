/***********************************************************
 * @Description : 使用广度优先遍历解决单源路径模式
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-08-09 15:30
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter05BreadthFirstTraversal.Section3GraphBFSSingleSourcePath;

import Chapter02GraphExpress.Graph;

import java.util.*;

public class GraphBFSSingleSourcePath {

    private Graph graph;
    /**
     * 顶点的访问情况的数组
     */
    private boolean[] visited;

    /**
     * 广度优先遍历的顺序结果(只有一种，不想DFS有前序、后序两种)
     */
    private List<Integer> orderList = new ArrayList<>();

    /**
     * 单源路径的起点
     */
    private int source;

    private int[] pre;

    public GraphBFSSingleSourcePath(Graph graph, int source) {
        graph.validateVertex(source);
        this.graph = graph;
        this.source = source;
        this.visited = new boolean[graph.V()];
        // 给存储单源路径的数组赋值
        pre = new int[graph.V()];
        Arrays.fill(pre, -1);
        // 因为单源路径问题是和连通分量无关的，所以dfs()要用最早没有考虑连通分量的那版
        bfs(source);
    }

    /**
     * 从source点开始进行广度优先遍历
     */
    private void bfs(int source) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(source);
        visited[source] = true;
        // 起点的上一个节点可以认为是自己
        pre[source] = source;
        while (!queue.isEmpty()) {
            int v = queue.remove();
            orderList.add(v);
            for (int w : graph.adj(v)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    pre[w] = v;
                }
            }
        }
    }

    /**
     * 判断图的遍历起点是否和target点连通，实际只需要看下visit[v]是否为true即可，为true表示在一个连通分量上，肯定是连通地
     *
     * @param target 目标定点索引
     * @return 判断图的遍历起点是否和target点连通
     */
    public boolean isSourceConnectedTo(int target) {
        graph.validateVertex(target);
        return visited[target];
    }

    /**
     * 找到起点source到目标定点target的路径
     *
     * @param target 目标定点
     * @return 可迭代的对象，一般是集合，用于存储source到target的完整路径
     */
    public Iterable<Integer> path(int target) {
        List<Integer> pathList = new ArrayList<>();
        // source到target有路径才进行路径查找
        if (isSourceConnectedTo(target)) {
            // 用pre数组从target一直找到source点，记录下中间经过的所有点，就是要求的单源路径
            int current = target;
            while (current != source) {
                pathList.add(current);
                current = pre[current];
            }
            // 起点要加上
            pathList.add(source);
            // 因为是从source到target的路径，所以要颠倒下
            Collections.reverse(pathList);
            return pathList;
        } else {
            // 没有路径就直接返回空集合
            return pathList;
        }
    }

    public Iterable<Integer> getOrderList() {
        return orderList;
    }
}
