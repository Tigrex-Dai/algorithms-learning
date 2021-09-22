/***********************************************************
 * @Description : 两数之和之链表法
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/18 19:16
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter05LinkedList.LeetCode2AddTwoNumbers;

import Chapter05LinkedList.LinkedListTool;
import Chapter05LinkedList.ListNode;

import java.math.BigDecimal;

class Solution {

    private String add2Sum(String s1, String s2) {
        BigDecimal b1 = new BigDecimal(s1);
        BigDecimal b2 = new BigDecimal(s2);
        return b1.add(b2).toString();
    }

    /**
     * 两个数相加，要考虑大数相加的情况，所以数字都以字符串的形式来存储
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 用字符串来存储两个数
        StringBuilder num1 = new StringBuilder();
        StringBuilder num2 = new StringBuilder();
        while (l1 != null) {
            num1.append(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            num2.append(l2.val);
            l2 = l2.next;
        }
        String sum = add2Sum(num1.reverse().toString(), num2.reverse().toString());
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        for (int i = sum.length() - 1; i >= 0; i--) {
            cur.next = new ListNode(Integer.parseInt(sum.charAt(i) + ""));
            cur = cur.next;
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        int[] nums1 = {9};
        int[] nums2 = {1, 9, 9, 9, 9, 9, 9, 9, 9};
        ListNode l1 = LinkedListTool.create(nums1);
        ListNode l2 = LinkedListTool.create(nums2);
        ListNode newHead = new Solution().addTwoNumbers(l1, l2);
        LinkedListTool.show(newHead);
    }
}