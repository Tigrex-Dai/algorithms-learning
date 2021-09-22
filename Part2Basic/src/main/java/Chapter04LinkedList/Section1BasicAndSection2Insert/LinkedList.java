/***********************************************************
 * @Description : 
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/14 07:56
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter04LinkedList.Section1BasicAndSection2Insert;

public class LinkedList<Element> {
    /**
     * 链表头节点
     */
    private Node head;
    /**
     * 链表的头节点
     */
    private int size;

    /**
     * 每个节点的封装
     */
    private class Node {
        /**
         * 节点元素值
         */
        Element element;
        /**
         * 指向的下一个节点
         */
        Node next;

        public Node(Element element, Node next) {
            this.element = element;
            this.next = next;
        }

        public Node(Element element) {
            this(element, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "element=" + element +
                    '}';
        }
    }

    public LinkedList() {
        head = null;
        size = 0;
    }

    /**
     * 获取链表节点数目
     */
    public int getSize() {
        return size;
    }

    /**
     * 判断链表是否已经为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 链表头添加元素
     */
    public void addFirst(Element element) {
        // 下面3行可用一行 head = new Node(element, head)来代替
        Node node = new Node(element);
        node.next = head;
        head = node;
        size++;
    }

    /**
     * 向链表尾加入元素
     *
     * @param element 要加入地元素
     */
    public void addLast(Element element) {
        insert(size, element);
    }

    /**
     * 在链表的index(从0开始)位置添加新的元素element
     * 在链表中这种操作很少用，仅仅练习下就行
     */
    public void insert(int index, Element element) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("插入失败！index范围违法！");
        }
        // 链表头比较特殊
        if (index == 0) {
            addFirst(element);
        } else {
            Node prev = head;
            for (int i = 0; i < index - 1; i++) {
                // 链表不停向后移动，直到移动到要插入位置的前一个位置
                prev = prev.next;
            }
            // 下面3行等效于prev.next = new Node(element, prev.next);
            Node node = new Node(element);
            node.next = prev.next;
            prev.next = node;

            size++;
        }
    }
}
