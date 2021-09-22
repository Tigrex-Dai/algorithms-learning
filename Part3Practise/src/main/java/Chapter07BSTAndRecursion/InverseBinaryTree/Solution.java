/***********************************************************
 * @Description : 反转二叉树
 * 输入：
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 输出：
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/8/22 07:55
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter07BSTAndRecursion.InverseBinaryTree;

import Chapter07BSTAndRecursion.LeetCode297BST序列化和反序列化.Codec;
import Chapter07BSTAndRecursion.TreeNode;

public class Solution {

    public void swap(TreeNode root) {
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        invertTree(root.left);
        invertTree(root.right);
        swap(root);
        return root;
    }

    public static void main(String[] args) {
        String data = "3,9,20,null,null,15,7";
        Codec codec = new Codec();
        TreeNode root = codec.deserialize(data);
        String datatInit = codec.serialize(root);
        System.out.println(datatInit);
    }
}
