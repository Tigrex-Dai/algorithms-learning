# 第06章_二分搜索树BST
## 6.1 为什么要研究树结构
### 树结构的应用
+ 文件目录树
+ 公司人员组织架构
### 将数据使用树结构存储后，出奇的高效
+ 二分搜索树BST(Binary Search Tree)
+ 平衡二叉树：AVL、红黑树
+ 堆、并查集
+ 线段树、Trie(字典树、前缀树)

## 6.2 二分搜索树基础
> 从二叉树到二分搜索树
### 二叉树的基本结构
+ 和链表一样，是动态数据结构，动态即不需要自己维护容量，能自动扩缩容
+ 二叉树具有唯一的根节点
+ 每个父节点
  + 左边的子节点叫左孩子
  + 右边的子节点叫右孩子
+ 二叉树每个节点最多有两个子节点，可以只有一个左孩子或右孩子，也可以一个孩子节点也没有
+ `一个子节点也没有(即左右子节点均为null)`的节点叫做`叶子节点`，就像现实世界中树的叶子上不会长枝杈一样。
+ 每个节点最多有一个父亲节点。实际除了根节点没有父亲节点，其他节点都有且只有一个父亲节点。
![二叉树的基本概念](images/第06章_二分搜索树BST/二叉树的基本概念.png)
+ 和链表一样，二叉树有天然的递归结构
  + 每个节点的左子树也是二叉树
  + 每个节点的右子树也是二叉树
  + 满二叉树：除了叶子节点，每个节点都有左右两个孩子节点，如下图
    ![每个父亲节点的左右子树也是二叉树](images/第06章_二分搜索树BST/每个父亲节点的左右子树也是二叉树.png)
+ 二叉树不一定都是满二叉树，下面都是例子
  > ![非满二叉树的例子](images/第06章_二分搜索树BST/非满二叉树的例子.png)

### 二叉树的一个关键特点
> `度为2的节点数 = 度为0的节点数(即叶子节点) - 1`，度表示该节点的子节点的个数

大体证明过程：https://blog.csdn.net/abe_abd/article/details/69378904
![度为0的叶子节点和度为2的节点的关系](images/第06章_二分搜索树BST/度为0的叶子节点和度为2的节点的关系.png)
比如上面度为2的节点是0、1、2，度为0的节点是3、4、5、6，显然个数满足上面的关系

n0:叶子结点个数，S结点总数

结点方面考虑：n0+n1+n2 = S
树枝方面考虑：0*n0 +1*n1 +2*n2 = S-1(分十块豆腐砍九刀)

即 
n0 + n1 + n2 - 1 = 1 * n1 + 2 * n2   ==>  n0 = n2 + 1
一般题目还会给出n0或者n2，这样就能计算各个度的节点数和总的节点数了

### 二叉树到二分搜索树
> 二分搜索树也被称为二分查找树，它是基于二叉树的一种树形结构，它有着很鲜明的特点：

+ 任意一个节点的左子树中的所有节点都小于这个节点
+ 任意一个节点的右子树中的所有节点都大于这个节点

即`二分搜索树是满足上述两个条件的一种特殊的二叉树`
![二叉树到二分搜索树](images/第06章_二分搜索树BST/二叉树到二分搜索树.png)

### 二分搜索树更多的性质

+ 每一颗子树也是二分搜索树
+ 存储的元素必须具有**可比性**(`实现Comparable接口`)

![二分搜索树更多的性质](images/第06章_二分搜索树BST/二分搜索树更多的性质.png)

### 二分搜索树的基本代码结构

[二分搜索树的基本代码结构](src/main/java/Chapter06BST/BST.java)

## 6.3 向二分搜索树中添加节点

复习递归的组成部分
+ 1.递归终止条件
+ 2.递归组成逻辑

向二分搜索树添加新元素
+ 若二叉树为空，直接把要插入的节点作为根节点
+ 若该节点要想插入到一个完整的二分搜索树中，我们需要从根节点出发与其比较，
  + 若比当前节点大则往右走，然后与下一个节点进行比较，
  + 若比当前节点小则往左走，然后与下一个节点进行比较，
  + 找到一个节点的左子节点或右子节点为null，则把要插入的节点插入这个位置
  + 不允许插入重复元素(节点值E相等)，如果遍历过程中发现要插入的节点和已有节点相等，不做任何处理直接返回
    + 但是如果想让其包含重复元素的话也可以实现，只需要对其进行重定义：左子树小于等于节点；或者右子树大于等于节点
    + 我们前两章讲地数组和链表是可以有重复元素的

![二分搜索树插入节点](images/第06章_二分搜索树BST/二分搜索树插入节点.png)


实现代码如下：
```java
/**
 * 向以节点Node为根节点的二分搜索树树中添加新的元素e，递归实现
 *
 * @param node 二分搜索树的根节点
 * @param e    要加入地元素e
 */
private void add(Node node, E e) {
    if (e.compareTo(node.e) < 0) {
        // e小于根节点，往node的左子树继续遍历
        if (node.left == null) {
            // 执行完节点插入操作递归就会回退到上一层递归
            node.left = new Node(e);
            size++;
        } else {
            // 没找到插入位置就继续往左侧递归
            add(node.left, e);
        }
    } else if (e.compareTo(node.e) > 0) {
        // e大于根节点，往node的右子树继续遍历
        if (node.right == null) {
            // 执行完节点插入操作递归就会回退到上一层递归
            node.right = new Node(e);
            size++;
        } else {
            // 没找到插入位置就继续往右侧递归
            add(node.right, e);
        }
    }
    // 如果和遍历到的节点相等即e.compareTo(node.e)==0，不做插入操作。因为我们实现的二分搜索树不允许有重复元素。
    // 这里我们直接不处理这种情况，递归会直接回退到上一层递归
}

/**
 * 向二分搜索树中添加元素e
 */
public void add(E e) {
    if (root == null) {
        // 如果二分搜索树为空，直接把要加入的节点作为新节点
        root = new Node(e);
        size++;
    } else {
        add(root, e);
    }
}
```
本课程关于二分搜索树的注意点：
+ 我们的课程中实现的二分搜索树不包含重复元素(不会有两个节点的E相等)
+ 二分搜索树添加元素的非递归写法。和链表很像
+ 本课程在二分搜索树方面的实现，关注递归实现
+ 二分搜索树一些方法的非递归实现自己练习下，老师的代码仓库里有参考实现
+ 在二分搜索树方面，递归实现要比非递归实现简单地多


## 6.4 优化添加操作
> 优化后的代码如下(把多种为null的情况合一了，简洁但是不太易懂，好好看下)：

```java
/**
 * 向以节点Node为根节点的二分搜索树树中添加新的元素e，递归实现
 *
 * @param node 二分搜索树的根节点
 * @param e    要加入地元素e
 */
private Node add(Node node, E e) {
    // 只要碰到了为空的node，就一定要把我们的e作为节点添加到这里的，具体是作为左子树、右子树还是根节点到下面再进行设置
    if (node == null) {
        return new Node(e);
    }
    if (e.compareTo(node.e) < 0) {
        // e小于根节点，往node的左子树继续遍历
        node.left = add(node.left, e);
    } else if (e.compareTo(node.e) > 0) {
        // e大于根节点，往node的右子树继续遍历
        node.right = add(node.right, e);
    }
    // 如果和遍历到的节点相等即e.compareTo(node.e)==0则直接跳过，不做任何处理，因为我们实现的二分搜索树不允许有重复元素。
    
    // 当这个node是把e给new出来地就设置到子节点为空的上面去；如果不是new出来地相当于把已有的二分搜索树中的节点关系又设置一次
    return node;
}

/**
 * 向二分搜索树中添加元素e
 */
public void add(E e) {
    root = add(root, e);
}
```

## 6.5 查询操作
> 和上一节添加元素的逻辑很相似

```java
/**
 * 在以节点node为根节点的二分搜索树树中查找是否包含元素e
 */
private boolean contains(Node node, E e) {
    if (node == null) {
        // 遍历到到二叉树最底部了，还没找到，二分搜索树中肯定没有这个这个元素了
        return false;
    }
    if (e.compareTo(node.e) < 0) {
        // e小于当前节点，向左递归
        return contains(node.left, e);
    } else if (e.compareTo(node.e) > 0) {
        // e大于当前节点，向右递归
        return contains(node.right, e);
    } else {
        // e等于当前节点，返回true
        return true;
    }
}

/**
 * 看二分搜索树种是否包含元素e
 */
public boolean contains(E e) {
    return contains(root, e);
}
```

## 6.6 二分搜索树的前序遍历
> 遍历操作就是把所有节点都访问一遍。前中后序遍历其实都相当于图的深度优先遍历DFS，想象着有一个栈在不断压入和弹出树的子节点即可。

### 什么是遍历操作
![什么是遍历操作](images/第06章_二分搜索树BST/什么是遍历操作.png)

### 遍历操作的通用伪代码
> 第1步访问节点的顺序在相对于2和3的位置决定了是前、中、后序遍历的哪一种
![二分搜索树遍历的伪代码](images/第06章_二分搜索树BST/二分搜索树遍历的伪代码.png)

实现代码如下：
```java
/**
 * 遍历以node作为根节点的二分搜索树
 */
private void preOrder(Node node) {
    // 递归终止条件
    if (node == null) {
        // 遍历到null节点就返回上一层递归
        return;
    }

    // 递归组成逻辑
    // 1.访问当前节点。需要存储时可以放到list中
    System.out.print(node.e + " ");
    // 2.遍历左子树
    preOrder(node.left);
    // 3.遍历右子树
    preOrder(node.right);
}

/**
 * 前序遍历
 */
public void preOrder() {
    preOrder(root);
}
```

## 6.7 中序遍历和后续遍历
> 上一节有提"访问当前节点"步骤的位置决定是了前序、中序还是后序遍历，中序和后序的伪代码如下：

### 中序遍历
![中序遍历](images/第06章_二分搜索树BST/中序遍历伪代码.png)
> 中序遍历有个特殊性质：中序遍历的结果是从小到大排序地~~
![中序遍历的结果是从小到大排好序地](images/第06章_二分搜索树BST/中序遍历的结果是从小到大排好序地.png)

代码实现：

```java
/**
 * 中序遍历以node作为根节点的二分搜索树
 */
private void inOrder(Node node) {
    // 递归终止条件
    if (node == null) {
        // 遍历到null节点就返回上一层递归
        return;
    }

    // 递归组成逻辑
    // 2.遍历左子树
    inOrder(node.left);
    // 1.访问当前节点。需要存储时可以放到list中
    System.out.print(node.e + " ");
    // 3.遍历右子树
    inOrder(node.right);
}

/**
 * 中序遍历
 */
public void inOrder() {
    inOrder(root);
}
```
### 后序遍历
![后序遍历](images/第06章_二分搜索树BST/后序遍历伪代码.png)

后序遍历应用
> 为二分搜索树释放内存

代码实现：
```java
/**
 * 后序遍历以node作为根节点的二分搜索树
 */
private void postOrder(Node node) {
    // 递归终止条件
    if (node == null) {
        // 遍历到null节点就返回上一层递归
        return;
    }

    // 递归组成逻辑
    // 2.遍历左子树
    postOrder(node.left);
    // 3.遍历右子树
    postOrder(node.right);
    // 1.访问当前节点。需要存储时可以放到list中
    System.out.print(node.e + " ");
}

/**
 * 后序遍历
 */
public void postOrder() {
    postOrder(root);
}
```

## 6.8 理解前、中、后序遍历的简单方法
> 可以给二分搜索树每个节点标记上左中右三个紫色小点，然后沿着二分搜索树的边沿从左往右走：
+ 遇到节点左侧的紫色小点就输出对应节点的元素值--前序遍历
+ 遇到节点中间的紫色小点就输出对应节点的元素值--中序遍历
+ 遇到节点右侧的紫色小点就输出对应节点的元素值--后序遍历
![紫色小点标记法理解前中后序遍历](images/第06章_二分搜索树BST/紫色小点标记法理解前中后序遍历.png)

用上述方法，可以很容易得到前、中、后序遍历的结果：
+ 前序遍历：28 16 13 22 30 29 42
+ 中序遍历：13 16 22 28 29 30 42
+ 后序遍历：13 22 16 29 42 30 28

## 6.9 前序遍历的非递归实现
> 参考图的DFS递归和非递归实现。前中后序遍历实际都是深度优先遍历DFS的思想
+ 递归中我们是有一个系统调用栈来决定节点的访问顺序
+ 非递归中我们自己维护一个栈。通过把节点和子节点压栈和入栈来实现节点的访问顺序控制

## 6.10 层序遍历
> 和图的BFS类似，参考[图中的BFS遍历](../Part2BasicGraph/第05章_图的广度优先遍历.md#51-从树的广度优先遍历到图的广度优先遍历)和[dfs和bfs的神奇联系](../Part2BasicGraph/第05章_图的广度优先遍历.md#510-dfs和bfs的神奇联系)，一般用地是基于队列的非递归实现。
![层序遍历](images/第06章_二分搜索树BST/层序遍历.png)

### 代码实现如下：

```java
 /**
  * 层序遍历
  */
public void levelOrder() {
    levelOrder(root);
}

/**
  * 层序遍历以node作为根节点的二分搜索树
  *
  * @param root 二分搜索树的根节点
  */
private void levelOrder(Node root) {
    Queue<Node> queue = new ArrayDeque<>();
    queue.add(root);
    while (!queue.isEmpty()) {
        Node node = queue.remove();
        // 每次弹出一个元素就打印下
        System.out.print(node.e + " ");
        if (node.left != null) {
            queue.add(node.left);
        }
        if (node.right != null) {
            queue.add(node.right);
        }
    }
}
```

### 层序遍历(也称为广度优先遍历的应用)

+ 当目标存在图的靠上位置时，可以更快地找到问题的解
+ 常用语算法设计中--比如图的最短路径
+ 图的`深度优先遍历DFS(实际就是前中后序遍历)`和`广度优先遍历BFS(实际就是层序遍历)`

## 6.11 删除最大元素和最小元素
> 为下一节删除任意一个二分搜索树中的元素做准备

### 二分搜索树的最小值和最大值非常容易找到：
+ 1.左子树的终点就是元素的最小值；（不一定是叶节点）
+ 2.右子树的终点就是元素的最大值。（不一定是叶节点）
+ 3.最小值和最大值都不一定是叶子节点
  > ![最大值和最小值都不一定是叶子节点](images/第06章_二分搜索树BST/最大值和最小值都不一定是叶子节点.png)
二者操作类似于左子树链表和右子树链表（相较而言，非递归算法更简单一些）

### 二分搜索树删除最小或最大节点的情况：
+ 1、极值位于叶子节点上，直接删除叶子节点；
+ 2、最小值位于内部节点上，其后是一个右子树。此时删除节点后将剩余子树接到删除节点父节点位置作为他的左子树。（删除最大值同理）
+ 3、最大值位于内部节点上，其后是一个左子树。此时删除节点后将剩余子树接到删除节点父节点位置作为他的右子树。（删除最小值同理）
![最小值或最大值位于内部节点上](images/第06章_二分搜索树BST/最小值或最大值位于内部节点上.png)

```java
 /**
 * 从二分搜索树种删除最小元素并返回
 *
 * @return 删除的最小元素
 */
public E removeMin() {
    // 这里先记录下最小值，防止下面removeMin()后树结构变了
    E min = minimum();
    // 这个递归函数返回地是新子树的根节点，不是我们要找地最小值
    root = removeMin(root);
    // 直接用我们前面找到的最小元素来返回
    return min;
}

/**
 * 删除以node作为根节点的二分搜索树中的最小节点，并返回删除节点后的新的二分搜索树的根节点
 *
 * @param node 根节点
 */
private Node removeMin(Node node) {
    // 递归终止条件
    if (node.left == null) {
        // 递归遍历到左子树为空，说明找到了最小节点node
        // node.right是否为空都可以正常返回给上一级的父节点来设置父节点的左节点直接指向当前节点的右子树
        Node rightNode = node.right;
        node.right = null;
        size--;
        return rightNode;
    }

    // 递归组成逻辑
    // 当左节点不是null时就正常往下递归，返回当前节点给上一层节点设置下自己的左节点
    node.left = removeMin(node.left);
    return node;
}

/**
 * 从二分搜索树种删除最大元素并返回
 *
 * @return 删除的最大元素
 */
public E removeMax() {
    // 这里先记录下最大值，removeMax()后树结构变了
    E max = maximum();
    // 这个递归函数返回地是新子树的根节点，不是我们要找地最大值
    root = removeMax(root);
    // 直接用我们前面找到的最大元素来返回
    return max;
}

/**
 * 删除以node作为根节点的二分搜索树中的最大节点，并返回删除节点后的新的二分搜索树的根节点
 *
 * @param node 根节点
 */
private Node removeMax(Node node) {
    // 递归终止条件
    if (node.right == null) {
        // 递归遍历到右子树为空，说明找到了最大节点node
        // node.left是否为空都可以正常返回给上一级的父节点来设置父节点的右节点直接指向当前节点的左子树
        Node leftNode = node.left;
        node.left = null;
        size--;
        return leftNode;
    }

    // 递归组成逻辑
    // 当右节点不是null时就正常往下递归，返回当前节点给上一层节点设置下自己的右节点
    node.right = removeMax(node.right);
    return node;
}
```

## 6.12 删除任意元素
> 二分搜索树删除节点操作的逻辑

+ 1、删除的节点只有右孩子
  > 删除当前节点，然后把右孩子方到当前节点的位置上
+ 2、删除的节点只有左孩子
  > 删除当前节点，然后把左孩子放到当前节点的位置上

1和2的逻辑包含删除叶子节点（既可以理解为只有左孩子也可以理解只有右孩子的节点，只不过孩子节点时null）
+ 3、待删除的节点左右孩子都有
  > 可以立即为找待删除结点右子树中的最小节点来替换删除节点
  + 3.1 首先找到当前节点右子树的最小节点，记录这个节点为successor，删除此节点
    > ![删除任意值的元素](images/第06章_二分搜索树BST/删除任意值的元素.png)
  + 3.2 然后把successor的右指针指向当前节点的右孩子，左指针指向左孩子
    > ![删除任意值的元素2](images/第06章_二分搜索树BST/删除任意值的元素2.png)
  + 3.3 最后当前节点的父节点指向successor
    > ![删除任意值的元素3](images/第06章_二分搜索树BST/删除任意值的元素3.png)
  
3中的算法是在1962年由Hibbard提出的，名称叫`Hibbard Deletion`

```java
/**
 * 删除指定值的结点
 *
 * @param e 节点的值
 */
public void remove(E e) {
    root = remove(root, e);
}

/**
 * 删除
 *
 * @param node 二分搜索树的根节点
 * @param e    待删除节点的值
 * @return 要挂载到当前节点父节点的子树
 */
private Node remove(Node node, E e) {
    // 递归终止条件
    if (node == null) {
        return null;
    }

    // 递归组成逻辑
    // 还没找到就接着往下找
    if (e.compareTo(node.e) < 0) {
        // 要找的值比当前节点小，向左递归
        node.left = remove(node.left, e);
        return node;
    } else if (e.compareTo(node.e) > 0) {
        // 要找的值比当前节点大，向右递归
        node.right = remove(node.right, e);
        return node;
    } else {
        // node.e == e 找到相等的节点了，下面删除指定值的节点
        if (node.left == null) {
            Node rightNode = node.right;
            // 释放引用
            node.right = null;
            size--;
            // 左节点为空，把node的右子树挂接到node的父亲节点即可
            return rightNode;
        }
        if (node.right == null) {
            Node leftNode = node.left;
            // 释放引用
            node.left = null;
            size--;
            // 右节点为空，把node的左子树挂接到node的父亲节点即可
            return leftNode;
        }
        // node的左右子树都不为空，就找node的右子树的最小值来代替node
        Node minimumRight = minimum(node.right);
        // 警告：下面两行代码一定不要颠倒，一定要先设置right再设置left，否则会出现迭代引用！
        // 选出node右子树最小元素来代替node，那么右子树最小元素就要从原来位置删掉
        minimumRight.right = removeMin(node.right);
        // 替换当前节点node的左右子树
        minimumRight.left = node.left;
        // 释放node的引用
        node.left = node.right = null;
        // 返回给上一级来设置父节点
        return minimumRight;
    }
}
```

## 6.13 BST的更多操作
> 自己尝试写一下~

+ floor：小于目标值的最大值
+ ceil：大于目标值的最小值
+ rank：在BST中的排名
+ select：选出指定排名的元素(引入size用于统计每个子树的节点个数)
+ 支持重复元素的二分搜索树，引入count(相同元素存了几个)
+ [LeetCode上树相关的问题](https://leetcode-cn.com/tag/tree/)
+ [node的节点从值改为键值对BSTKV.java](src/main/java/Chapter06BST/BSTKV.java)，对应的测试类问[MainKV.java](src/main/java/Chapter06BST/MainKV.java)