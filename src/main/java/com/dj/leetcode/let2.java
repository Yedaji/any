package com.dj.leetcode;

/**
 * 逆序两数相加
 *
 * @author ydj
 * @date 2024/11/22 23:04
 **/
public class let2 {
    public static void main(String[] args) {
        // -2147483648
        //true
        //0
        //1073741824
        //true
        //0
        System.out.println(4&1);
        for (int i = 31; i >=0; i--) {
            System.out.println((1<<i));
            System.out.println((4&(1<<i)) ==0 );
            System.out.println((4&(1<<i)) ==0 ?"0":"1");
        }
        System.out.println();
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        /**
         *  2->4->3
         *  5->6->4
         *  7->0->8
         *
         *  342+465 = 807
         */
        int in = 0;
        ListNode res = new ListNode();
        res.val = l1.val + l2.val;
        ListNode next = new ListNode();

        ListNode l1next = l1.next;
        ListNode l2next = l2.next;
        res.next = next;

        while (l1next != null && l2next != null) {
            int sum = l1next.val + l2next.val + in;
            in = sum % 10;
            int nextval = sum / 10;
            // 当前的数
            next = new ListNode(nextval);
            next.next=next;

            l1next = l1next.next;
            l2next = l2next.next;
        }

        return res;
    }


    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
