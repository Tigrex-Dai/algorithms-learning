/***********************************************************
 * @Description : 直接利用外面的数组初始化一个堆
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/19 00:42
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter4Heap.Section8MaxIndexHeap;

import java.util.Arrays;

/**
 * 在堆的有关操作中，需要比较堆中元素的大小，所以Item需要extends Comparable
 */
public class IndexMaxHeap<Item extends Comparable> {
    // 数据内容
    protected Item[] data;
    // 索引数组,用于等效替代data中的元素来进行元素移动(上移和下移)
    protected int[] indexes;
    // 元素数量
    protected int count;
    // 数组容量
    protected int capacity;

    // 构造函数
    IndexMaxHeap(int capacity) {
        data = (Item[]) new Comparable[capacity + 1];
        indexes = new int[capacity + 1];
        count = 0;
        this.capacity = capacity;
    }

    // 获取最大堆的大小
    public int size() {
        return count;
    }

    // 判断最大堆是否为空
    public boolean isEmpty() {
        return count == 0;
    }

    // 向最大索引堆中插入一个新的元素, 新元素的索引为i, 元素为item
    // 传入的i对用户而言,是从0索引的
    public void insert(Item item, int i) {
        // 防止后面的++count越界
        assert (count + 1 <= capacity);
        // 传入的i对用户而言，是从0开始地，但是对堆内部而言是从1开始地,所以需要判断i+1是否越界
        assert (i + 1 >= 1 && i + 1 <= capacity);
        // 先加1，屏蔽外部数组和内部堆得差异
        i += 1;
        // 元素不用shiftUp
        data[i] = item;
        // 插入新元素，元素数加1,之所以用++count而不用count++是因为数组下标从1开始
        indexes[++count] = i;
        // 把新加入的元素的index(在indexes中的位置)向上浮动到合适位置
        shiftUp(count);
    }

    // 弹出最大值(根节点的对象)
    Item popMax() {
        // 保证堆不为空
        assert (count > 0);
        // 最大元素为第一个元素
        Item max = data[indexes[1]];
        // 移出最大元素后，需要把最下面的元素移到上面去
        swapIndexes(1, count);
        // 少了最后一个元素
        count--;
        // 将换上去(到最上面了,根元素，下标为1)的最后一个元素下移
        shiftDown(1);
        return max;
    }

    // 弹出最大值的数组的下标(根节点的对象).元素一样会被删除
    int popMaxIndex() {
        // 保证堆不为空
        assert (count > 0);
        // 最大元素为第一个元素，取其下标即可
        // Item max = data[indexes[1]];
        int maxIndex = indexes[1];
        // 移出最大元素后，需要把最下面的元素移到上面去
        swapIndexes(1, count);
        // 少了最后一个元素
        count--;
        // 将换上去(到最上面了,根元素，下标为1)的最后一个元素下移
        shiftDown(1);
        // 外面的用户数组索引是从0开始的，内部堆数组是从1开始地，所以需要减1
        return maxIndex - 1;
    }

    // 获取最大元素(堆中最上面的元素，即索引中为1的值)，但是并不删除
    public Item getMax() {
        assert (count > 0);
        return data[indexes[1]];
    }

    // 获取最大元素在数组中的索引。注意减去1
    int getMaxIndex() {
        assert (count > 0);
        return indexes[1] - 1;
    }


    public Item getItem(int i) {
        // 先确定好范围合适不合适
        assert (i + 1 >= 1 && i + 1 <= capacity);
        // 获取指定下标的元素。无关大小
        return data[i + 1];
    }

    // 更新数组中i下标位置的元素值
    public void update(int i, Item newItem) {
        // 外部数组下标从0开始，内部数组下表从1开始，需要平衡下
        i++;
        data[i] = newItem;
        // 找到indexes[j] = i, j表示data[i]在堆中的位置(结合堆得序号分配方式)，后面shiftUp和shiftDown都要用这个j
        // 之后进行shiftUp(j),再shiftDown(j)即可
        for (int j = 1; j <= count; j++) {
            if (indexes[j] == i) {
                // 因为难以知道上浮还是下沉，所以上下都做一下
                shiftUp(j);
                shiftDown(j);
                return;
            }
        }
    }

    // 交换索引堆中的索引i和j
    private void swapIndexes(int i, int j) {
        int t = indexes[i];
        indexes[i] = indexes[j];
        indexes[j] = t;
    }

    // 上浮的私有函数。注意这里的k是在indexes数组中的索引，indexes[k]才是在数组中的索引，shiftUp针对地是indexes
    private void shiftUp(int k) {
        // k > 1是因为到根节点时就没父节点了，不需要再比较了.注意需要套一层indexes，
        while (k > 1 && data[indexes[k / 2]].compareTo(data[indexes[k]]) < 0) {
            // 如果当前元素的父元素(在数组中的下表为整除2)比当前元素还小，
            // 则不符合最大堆的定义，那么就交换一下
            swapIndexes(k / 2, k);
            k /= 2;
        }
    }

    // 把移上来的最后一个元素下移到合适位置
    private void shiftDown(int k) {
        // 当当前k节点有子节点的时候(有左孩子不一定有右孩子，但有右孩子一定有左孩子。因为生成堆得时候是从左向右地)
        // 等于号别漏
        while (2 * k <= count) {
            // 获取子孩子下标
            int j = 2 * k;
            // 存在右孩子并且右孩子大于左孩子，那么右孩子有父节点交换
            if (j + 1 <= count && data[indexes[j + 1]].compareTo(data[indexes[j]]) > 0) {
                // 选择右节点作为下面与k处节点进行交换
                j++;
            }
            // 父节点比两个子节点的较大值还大，那么不需要交换
            if (data[indexes[k]].compareTo(data[indexes[j]]) >= 0) {
                break;
            }
            // 父节点小于孩子节点的较大值，那么就和较大值的子节点交换.注意交换是交换indexes
            swapIndexes(k, j);
            // 把换后的子节点作为父节点，接着往下走
            k = j;
        }
    }

    // 测试索引堆中的索引数组index
    // 注意:这个测试在向堆中插入元素以后, 不进行extract操作有效
    boolean testIndexes() {

        int[] copyIndexes = new int[count + 1];

        for (int i = 0; i <= count; i++) {
            copyIndexes[i] = indexes[i];
        }

        copyIndexes[0] = 0;
        Arrays.sort(copyIndexes);

        // 在对索引堆中的索引进行排序后, 应该正好是1...count这count个索引
        boolean res = true;
        for (int i = 1; i <= count; i++) {
            if (copyIndexes[i - 1] + 1 != copyIndexes[i]) {
                res = false;
                break;
            }
        }

        if (!res) {
            System.out.println("Error!");
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        int N = 1000;
        IndexMaxHeap<Integer> indexMaxHeap = new IndexMaxHeap<>(N);
        for (int i = 0; i < N; i++) {
            indexMaxHeap.insert(i, (int) (Math.random() * N));
        }
        assert indexMaxHeap.testIndexes();
    }
}
