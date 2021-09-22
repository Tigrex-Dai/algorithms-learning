/***********************************************************
 * @Description : 比较基于数组的队列、循环队列进而基于链表的队列三种
 *                链表之间的性能
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/16 08:13
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter04LinkedList.Section7LinkedListQueue;

import Chapter03StackAndQueues.Section5ArrayQueue.ArrayQueue;
import Chapter03StackAndQueues.Section5ArrayQueue.Queue;
import Chapter03StackAndQueues.Section6LoopQueue.LoopQueue;

import java.util.Random;

public class Main {
    // 测试使用q运行opCount个enqueueu和dequeue操作所需要的时间，单位：秒
    private static double testQueue(Queue<Integer> q, int opCount) {

        long startTime = System.nanoTime();

        Random random = new Random();
        for (int i = 0; i < opCount; i++) {
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i++) {
            q.dequeue();
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {

        int opCount = 100000;

        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        double time1 = testQueue(arrayQueue, opCount);
        System.out.println("基于数组的队列ArrayQueue, time: " + time1 + " s");

        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        double time2 = testQueue(loopQueue, opCount);
        System.out.println("基于循环队列LoopQueue, time: " + time2 + " s");

        LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<>();
        double time3 = testQueue(linkedListQueue, opCount);
        System.out.println("基于自己实现的链表构造的队列LinkedListQueue, time: " + time3 + " s");

        JdkLinkedListQueue<Integer> jdkLinkedListQueue = new JdkLinkedListQueue<>();
        double time4 = testQueue(jdkLinkedListQueue, opCount);
        System.out.println("基于JDK自带的链表构造的队列JdkLinkedListQueue, time: " + time4 + " s");
    }
}
/**
 * 基于数组的队列ArrayQueue, time: 2.561748501 s
 * 基于循环队列LoopQueue, time: 0.0094739 s
 * 基于自己实现的链表构造的队列LinkedListQueue, time: 0.008525399 s
 * 基于JDK自带的链表构造的队列JdkLinkedListQueue, time: 0.0050826 s
 */