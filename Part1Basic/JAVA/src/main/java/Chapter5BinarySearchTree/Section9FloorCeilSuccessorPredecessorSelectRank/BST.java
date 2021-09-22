/***********************************************************
 * @Description : 二分搜索树
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/24 23:09
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter5BinarySearchTree.Section9FloorCeilSuccessorPredecessorSelectRank;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @param <Key>   作为树的索引关键词，必须是可比较地，所以需要继承Comparable
 * @param <Value> 不需要可比较
 */
public class BST<Key extends Comparable<Key>, Value> {

    /**
     * 节点类
     */
    private class Node {
        Key key;
        Value value;
        /**
         * 左子节点
         */
        Node left;
        /**
         * 右子节点
         */
        Node right;

        /**
         * 构造函数
         */
        Node(Key key, Value value) {
            // key是唯一地，不能重复
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }

        public Node(Node node) {
            // key是唯一地，不能重复
            this.key = node.key;
            this.value = node.value;
            this.left = node.left;
            this.right = node.right;
        }
    }

    /**
     * 根节点
     */
    private Node root;
    /**
     * 节点数
     */
    private int count;

    public BST() {
        root = null;
        count = 0;
    }


    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public void insert(Key key, Value value) {
        root = insert(root, key, value);
    }

    public boolean contain(Key key) {
        return contain(root, key);
    }

    public Value search(Key key) {
        return search(root, key);
    }


    /**
     * 寻找最小的键值
     */
    public Key min() {
        assert (count != 0);
        Node minNode = min(root);
        return minNode.key;
    }

    /**
     * 寻找最大的键值
     */
    public Key max() {
        assert (count != 0);
        Node maxNode = max(root);
        return maxNode.key;
    }

    /**
     * 删除最小值对应的节点
     */
    public void deleteMin() {
        if (root != null) {
            root = deleteMin(root);
        }
    }

    /**
     * 删除最大值对应的节点
     */
    public void deleteMax() {
        if (root != null) {
            root = deleteMax(root);
        }
    }

    /**
     * 从二叉树中删除键值为key的节点
     */
    public void deleteNode(Key key) {
        root = deleteNode(root, key);
    }

    /**
     * 寻找key的floor值(小于key的最大值)，递归算法
     * 如果不存在的话(key比BST中的最小值还小)，返回NULL
     */
    public Key floor(Key key) {
        // BST为空或者小于BST中的最小值，直接返回NULL，因为肯定不用找了
        if (count == 0 || key.compareTo(min()) < 0) {
            return null;
        }
        Node floorNode = floor(root, key);
        return floorNode.key;
    }

    /**
     * 寻找key的ceil值(大于key的最小值)，递归算法
     * 如果不存在的话(key比BST中的最大值还大)，返回NULL
     */
    public Key ceil(Key key) {
        // BST为空或者大于BST中的最大值，直接返回NULL，因为肯定不用找了
        if (count == 0 || key.compareTo(max()) > 0) {
            return null;
        }
        Node ceilNode = ceil(root, key);
        return ceilNode.key;
    }

    //***************************************************
    //* 二分搜索树的辅助函数
    //**************************************************

    /**
     * 向以node为根节点的二叉树搜索树种，插入节点(key,value)
     * 返回插入新节点后的二叉搜索树的根
     */
    private Node insert(Node node, Key key, Value value) {
        // 处理最基本事件，即可以退出递归的事件
        if (node == null) {
            // 递归到没有子树了，就在这个位置插入。
            count++; // 元素数先+1
            // 把当前要插入的节点作为根节点返回，一层层递归后会跟新树路径上的节点关系地
            return new Node(key, value);
        }
        if (key.compareTo(node.key) < 0) {
            // 小于根节点的键，往左边子树插入
            node.left = insert(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            // 大于根节点的键，往右边子树插入
            node.right = insert(node.right, key, value);
        } else {
            // 如果key相等就直接更新(注意二叉搜索树的键是唯一地)
            node.value = value;
        }

        return node;
    }

    /**
     * 被递归调用来判断是否包含指定key的函数
     */
    private boolean contain(Node node, Key key) {
        // 递归退出条件.最后到达树底，下面没节点了(NULL),返回false
        if (node == null) {
            return false;
        }
        if (key.compareTo(node.key) > 0) {
            // 大于就在右边找
            return contain(node.right, key);
        } else if (key.compareTo(node.key) < 0) {
            // 小于就在左边找
            return contain(node.left, key);
        } else {
            // 等于就返回true,代表找到含有这个key地节点了
            return true;
        }
    }

    /**
     * 在以node为根的二叉搜索树种查找key对应的value
     */
    private Value search(Node node, Key key) {
        // 递归退出条件.最后到达树底，返回NULL
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) > 0) {
            // 大于就在右边找
            return search(node.right, key);
        } else if (key.compareTo(node.key) < 0) {
            // 小于就在左边找
            return search(node.left, key);
        } else {
            // 等于就返回true,代表找到含有这个key地节点了,返回Value的地址
            return node.value;
        }
    }

    /*****************************************************************************
     * 前、中、后序遍历的私有函数：前中后序遍历的差别在于访问当前节点是在一开始、中间还是最后
     * ***************************************************************************/

    /**
     * 前序遍历
     */
    public void preOrder() {
        preOrder(root);
    }

    /**
     * 中序遍历
     */
    public void inOrder() {
        inOrder(root);
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        postOrder(root);
    }

    /**
     * 层序遍历(Level Order),又称广度优先遍历
     */
    public void levelOrder() {
        LinkedList<Node> queue = new LinkedList<>();
        // 先推入根节点
        queue.push(root);
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            System.out.println(node.key);
            if (node.left != null) {
                queue.push(node.left);
            }
            if (node.right != null) {
                queue.push(node.right);
            }
        }
    }


    /*****************************************************************************
     * 前、中、后序遍历的私有辅助函数：前中后序遍历的差别在于访问当前节点是在一开始、中间还是最后
     * ***************************************************************************/

    /**
     * 对以node为根的二叉搜索树进行前序遍历
     */
    private void preOrder(Node node) {
        if (node != null) {
            System.out.println(node.key);
            preOrder(node.left);
            preOrder(node.right);
        }
    }


    /**
     * 对以node为根的二叉搜索树进行中序遍历
     */
    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.println(node.key);
            inOrder(node.right);
        }
    }

    /**
     * 对以node为根的二叉搜索树进行后序遍历
     */
    private void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.println(node.key);
        }
    }

    /**
     * 在以node为根的二叉搜索树中，返回最小键值的点
     */
    private Node min(Node node) {
        // 没有左子节点的时候就到了最小点了
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    /**
     * 在以node为根的二叉搜索树中，返回最大键值的点
     */
    private Node max(Node node) {
        // 没有左子节点的时候就到了最小点了
        if (node.right == null) {
            return node;
        }
        return max(node.right);
    }

    /**
     * 删除以Node为根节点的子树中的最小值
     */
    private Node deleteMin(Node node) {
        if (node.left == null) {
            // 没有左节点了，就要看看右节点是否存在
            Node rightNode = node.right;
            // 删除最小节点node
            node.right = null;
            count--;
            // 不管rightNode是否为空，都可以直接返回地
            return rightNode;
        }
        // 好好体验递归
        node.left = deleteMin(node.left);
        return node;
    }

    /**
     * 删除以Node为根节点的子树中的最大值
     */
    private Node deleteMax(Node node) {
        if (node.right == null) {
            // 没有右节点了，就要看看左节点是否存在
            Node leftNode = node.left;
            // 删除最小节点node
            node.left = null;
            count--;
            // 不管rightNode是否为空，都可以直接返回地
            return leftNode;
        }
        // 好好体验递归
        node.right = deleteMax(node.right);
        return node;
    }


    /**
     * 删除指定节点作为树的根的二叉搜索树中键值为Key的节点
     * 返回删除节点后的新的二分搜索树的根。
     * 删除最小节点后的替换值实际从删除节点的右子树中找最小节点即可
     */
    private Node deleteNode(Node node, Key key) {
        // 到节点为NULL时，就可以退出了
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            node.left = deleteNode(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = deleteNode(node.right, key);
            return node;
        } else {
            // 关键之处，循环到某阶段key = node->key了，最为复杂
            if (node.left == null) {
                // 左子树为空，看右子树，删除节点后，所有右子树节点前提一位
                Node rightNode = node.right;
                count--;
                // 右子树也为空的话也不影响，会返回NULL，不影响地
                return rightNode;
            }
            if (node.right == null) {
                // 右子树为空，看左子树，删除节点后，所有左子树节点前提一位
                Node leftNode = node.left;
                count--;
                return leftNode;
            }

            // 左右子树都不为空，找右子树的最小节点即可，然后把左右子树更新一下
            // 找到右子树的最小值.注意要新建一个Node,不然下面的
            Node successor = new Node(min(node.right));
            // 这里++，在下面的deleteMin会--地
            count++;
            // 更新右子树指针,注意deleteMin返回的node->right作为根节点的树的最小值的父亲节点
            successor.right = deleteMin(node.right);
            successor.left = node.left;
            // 删除指定节点
            count--;
            // 返回新的二叉搜索树的根节点
            return successor;
        }
    }

    // 在以node为根的二叉搜索树中，寻找key的floor值所处的节点，递归算法
    private Node floor(Node node, Key key) {
        if (node == null) {
            return null;
        }

        // 如果node的key值与要找的key值相等，则node本身就是key的floor节点
        if (node.key == key) {
            return node;
        }
        // 如果node的值比要寻找的key值大，则要寻找的key的floor节点一定在node的左子树中
        if (node.key.compareTo(key) > 0) {
            return floor(node.left, key);
        }

        // 如果node->key < key，则node可能是key的floor节点，也有可能不是，因为node的右子树中可能还存在比node-key)
        // 大但是小于key的其余节点),需要尝试着在node的右子树中寻找一下
        Node tmpNode = floor(node.right, key);
        if (tmpNode != null) {
            return tmpNode;
        }
        return node;
    }


    // 在以node为根的二叉搜索树中，寻找key的ceil值所处的节点，递归算法
    private Node ceil(Node node, Key key) {
        if (node == null) {
            return null;
        }

        // 如果node的key值与要找的key值相等，则node本身就是key的ceil节点
        if (node.key == key) {
            return node;
        }
        // 如果node的值比要寻找的key值小，则要寻找的key的floor节点一定在node的右子树中
        if (node.key.compareTo(key) < 0) {
            return ceil(node.right, key);
        }

        // 如果node->key > key，则node可能是key的ceil节点，也有可能不是，因为node的左子树中可能还存在比node-key
        // 小但是大于key的其余节点),需要尝试着在node的左子树中寻找一下
        Node tmpNode = ceil(node.left, key);
        if (tmpNode != null) {
            return tmpNode;
        }
        return node;
    }

    /**
     * 打乱数组顺序
     */
    private static void shuffle(Object[] arr) {

        for (int i = arr.length - 1; i >= 0; i--) {
            int pos = (int) (Math.random() * (i + 1));
            Object t = arr[pos];
            arr[pos] = arr[i];
            arr[i] = t;
        }
    }

    // 打乱数组顺序
    private static void shuffle(ArrayList arr) {

        for (int i = arr.size() - 1; i >= 0; i--) {
            int pos = (int) (Math.random() * (i + 1));
            Object t = arr.get(pos);
            arr.set(pos, arr.get(i));
            arr.set(i, t);
        }
    }


    public static void main(String[] args) {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();

        //将[0, N)之间的偶数保存在nums中
        int N = 1000;
        ArrayList<Integer> nums = new ArrayList<Integer>();
        for (int i = 0; i < N; i += 2) {
            nums.add(i);
        }
        int minNum = nums.get(0);
        int maxNum = nums.get(nums.size() - 1);

        // 将nums乱序处理
        shuffle(nums);

        // 向二分搜索树中插入[0, N)之间的所有偶数
        for (Integer num : nums) {
            bst.insert(num, num);
        }

        // 对[0...N]区间里的N+1个数, 调用二分搜索树的floor和ceil, 查看其结果
        for (int i = 0; i < N; i++) {

            // 测试floor
            Integer floorKey = bst.floor(new Integer(i));
            if (i % 2 == 0) {
                if (i >= 0 && i < N) {
                    assert floorKey == i;
                } else if (i < 0) {
                    assert floorKey == null;
                } else {
                    assert floorKey == maxNum;
                }
            } else {
                if (i - 1 >= 0 && i - 1 < N) {
                    assert floorKey == i - 1;
                } else if (i - 1 < 0) {
                    assert floorKey == null;
                } else {
                    assert floorKey == maxNum;
                }
            }

            System.out.print("The floor of " + i + " is ");
            if (floorKey == null) {
                System.out.println("NULL");
            } else {
                System.out.println(floorKey);
            }


            // 测试ceil
            Integer ceilKey = bst.ceil(new Integer(i));
            if (i % 2 == 0) {
                if (i >= 0 && i < N) {
                    assert ceilKey == i;
                } else if (i < 0) {
                    assert ceilKey == minNum;
                } else {
                    assert ceilKey == null;
                }
            } else {
                if (i + 1 >= 0 && i + 1 < N) {
                    assert ceilKey == i + 1;
                } else if (i + 1 < 0) {
                    assert ceilKey == minNum;
                } else {
                    assert ceilKey == null;
                }
            }

            System.out.print("the ceil of " + i + " is ");
            if (ceilKey == null) {
                System.out.println("NULL");
            } else {
                System.out.println(ceilKey);
            }

            System.out.println();
        }
    }

}
