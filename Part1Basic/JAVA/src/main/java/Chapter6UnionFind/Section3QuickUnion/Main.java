/***********************************************************
 * @Description : 测试并查集的执行类
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/29 22:58
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter6UnionFind.Section3QuickUnion;

public class Main {
    // 测试UF1
    public static void main(String[] args) {

        // 使用10000的数据规模
        int n = 100000;

        // 虽然isConnected只需要O(n)的时间, 但由于union操作需要O(logn)的时间
        // 总体测试过程的算法复杂度是O(nlogn)的,提升不大
        UnionFindTestHelper.testUF(n);
    }
}
