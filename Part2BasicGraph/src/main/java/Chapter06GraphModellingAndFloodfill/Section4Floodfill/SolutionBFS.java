/***********************************************************
 * @Description : 岛屿的最大面积问题，等于1的点表示陆地，找到连接到一起的最多的点即表示最大的面积
 *                https://leetcode-cn.com/problems/max-area-of-island/
 *                优化：实际解决洪泛问题并不需要显示地构造图，栅格grid可以直接看做为一个图，直接看做图进行遍历，含节点做多的联通分量就是我们要求地最大岛面积
 *                注意栅格是屏幕坐标系，坐标点用(r, c)表示，第一个坐标是行(自上往下递增)，第二个坐标是列(自左往右递增)
 *                基于非递归的bfs实现
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-12-16 20:27
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter06GraphModellingAndFloodfill.Section4Floodfill;

import java.util.ArrayDeque;
import java.util.Queue;

public class SolutionBFS {
    /**
     * 四联通偏移分量
     */
    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    /**
     * 栅格的行数
     */
    private int R;
    /**
     * 栅格的列
     */
    private int C;

    /**
     * 栅格数据，这里可以直接看成一个图
     */
    private int[][] grid;

    /**
     * 记录节点是否被访问了的数组
     */
    private boolean[][] visited;

    /**
     * 计算最大的岛面积
     *
     * @param grid 栅格网络，这里也可以看做图
     * @return 最大的陆地，这里就是栅格的最大联通分量的节点数
     */
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null) {
            return 0;
        }
        this.R = grid.length;
        if (R == 0) {
            return 0;
        }
        this.C = grid[0].length;
        if (C == 0) {
            return 0;
        }
        this.grid = grid;
        this.visited = new boolean[R][C];
        // 栅格的最大联通分量的节点数
        int max = 0;
        // 要遍历所有的联通分量(每个点都要作为起始点试试)
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                // 点没被访问而且是陆地，才会进行bfs遍历
                if (!visited[r][c] && grid[r][c] == 1) {
                    // 把本次bfs的结果和前面已经得到的最大值进行比较，取较大值
                    max = Math.max(max, bfs(r, c));
                }
            }
        }
        return max;
    }

    /**
     * 遍历(r,c)所在的联通分量得到联通分量内点的个数
     *
     * @param r 遍历起点的行序号
     * @param c 遍历起点的列序号
     * @return (r,c)所在的联通分量内点的个数
     */
    private int bfs(int r, int c) {
        Queue<Integer> rQueue = new ArrayDeque<>();
        Queue<Integer> cQueue = new ArrayDeque<>();
        // 当前行和列加入对应的队列
        rQueue.add(r);
        cQueue.add(c);
        // 加入到队列后就认为是被访问过了
        visited[r][c] = true;
        // 当前遍历的起点起始深度就是1
        int maxCur = 1;
        // 行队列和列队列都不为空就接着沿着点进行深度遍历
        while (!rQueue.isEmpty() && !cQueue.isEmpty()) {
            // 从队列里弹出要遍历其邻接点的点
            // 当前行
            int rCur = rQueue.remove();
            // 当前列
            int cCur = cQueue.remove();
            // 遍历(r, c)的所有邻接点，顺时针上右下左四个点进行遍历
            for (int[] dir : dirs) {
                // dir代表相当于当前点的位移
                int rNext = rCur + dir[0];
                int cNext = cCur + dir[1];
                // 1.判断是否继续往下递归遍历,因为加了dir偏移，所以必须判断是否还在栅格内
                // 2和3.此外还需要没被访问并且是陆地(栅格的值为1)
                if (inGrid(rNext, cNext) && !visited[rNext][cNext] && grid[rNext][cNext] == 1) {
                    // 遍历一层就加1
                    maxCur++;
                    rQueue.add(rNext);
                    cQueue.add(cNext);
                    visited[rNext][cNext] = true;
                }
            }
        }

        return maxCur;
    }

    /**
     * 判断点(r, c)是否在栅格内
     *
     * @param r 行号
     * @param c 列号
     * @return 点(r, c)是否在栅格内
     */
    private boolean inGrid(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        };
        SolutionBFS solution = new SolutionBFS();
        System.out.println(solution.maxAreaOfIsland(grid));
    }
}
