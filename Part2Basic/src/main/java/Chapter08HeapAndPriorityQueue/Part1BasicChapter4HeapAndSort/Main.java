/***********************************************************
 * @Description : 测试Heapify和不heapify的效率差距,Heapify后的效率会提升些
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/18 00:31
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter08HeapAndPriorityQueue.Part1BasicChapter4HeapAndSort;

import java.util.Random;

public class Main {
    private static double testHeap(Integer[] testData, boolean isHeapify) {

        long startTime = System.nanoTime();

        MaxHeap<Integer> maxHeap;
        if (isHeapify) {
            maxHeap = new MaxHeap<>(testData);
        } else {
            maxHeap = new MaxHeap<>(testData.length);
            for (int num : testData) {
                maxHeap.insert(num);
            }
        }

        int[] arr = new int[testData.length];
        for (int i = 0; i < testData.length; i++) {
            arr[i] = maxHeap.popMax();
        }

        for (int i = 1; i < testData.length; i++) {
            if (arr[i - 1] < arr[i]) {
                throw new IllegalArgumentException("Error");
            }
        }
        System.out.println("Test MaxHeap completed.");

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {

        int n = 1000000;

        Random random = new Random();
        Integer[] testData = new Integer[n];
        for (int i = 0; i < n; i++) {
            testData[i] = random.nextInt(Integer.MAX_VALUE);
        }

        double time1 = testHeap(testData, false);
        System.out.println("Without heapify: " + time1 + " s");

        double time2 = testHeap(testData, true);
        System.out.println("With heapify: " + time2 + " s");
    }
}
