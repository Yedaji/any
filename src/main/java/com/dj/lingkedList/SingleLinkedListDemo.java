package com.dj.lingkedList;

/**
 * 单链表
 *
 * @author ydj
 * @date 2024/07/28 10:38
 **/
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        // 创建节点
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode11 = new HeroNode(5, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "卢骏义", "玉麒麟");
        HeroNode heroNode22 = new HeroNode(6, "卢骏义", "玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");
        HeroNode heroNode33 = new HeroNode(7, "吴用", "智多星");
        HeroNode heroNode4 = new HeroNode(4, "林冲", "豹子头");
        HeroNode heroNode44 = new HeroNode(8, "林冲", "豹子头");
        // 创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
//        singleLinkedList.addLink(heroNode1);
//        singleLinkedList.addLink(heroNode2);
//        singleLinkedList.addLink(heroNode3);
//        singleLinkedList.addLink(heroNode4);

        singleLinkedList.addByOrder(heroNode1);
        singleLinkedList2.addByOrder(heroNode11);
        singleLinkedList.addByOrder(heroNode4);
        singleLinkedList2.addByOrder(heroNode44);
        singleLinkedList.addByOrder(heroNode2);
        singleLinkedList2.addByOrder(heroNode22);
        singleLinkedList.addByOrder(heroNode3);
        singleLinkedList2.addByOrder(heroNode33);

        singleLinkedList.list();
        System.out.println("~~~");
        singleLinkedList2.list();
        System.out.println("----");
        SingleLinkedList mergelink = merge(singleLinkedList.head, singleLinkedList2.head);
        mergelink.list();
//        singleLinkedList.delete(new HeroNode(3, "小吴", "智多星～～"));
//        System.out.println(getLength(singleLinkedList.getHead()));
//        System.out.println(findLastIndexNode(singleLinkedList.getHead(), 0));
//        reverse(singleLinkedList.getHead());
//        singleLinkedList.list();

    }

    /**
     * 合并两个有序的单链表，合并之后的链表依然有序
     * <p>
     * 1，初始化：创建一个新的链表作为合并后的链表，并设置两个指针分别指向两个输入链表的头部。
     * 2， 比较和合并：
     * 比较两个链表的当前节点。选择数据较小的节点添加到新的链表中，并将该节点的指针移动到下一个节点。
     * 重复此过程，直到一个链表为空。
     * 处理剩余节点：
     * 3，如果一个链表已经遍历完，将另一个链表的剩余部分直接连接到新链表的尾部。
     * 结果：最终得到的新链表将包含所有原始链表的元素，并且保持有序。
     * 具体实现时，可以使用一个临时节点来保存当前应该添加到新链表的节点，以及使用一个尾指针来跟踪新链表的尾部，以便进行尾插操作。‌12
     */
    public static SingleLinkedList merge(HeroNode head1, HeroNode head2) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        if (head1.next == null) {
            singleLinkedList.addLink(head2);
            return singleLinkedList;
        }
        if (head2.next == null) {
            singleLinkedList.addLink(head1);
            return singleLinkedList;
        }
        // 1 3 5 7 9  head1
        // 2 4 6 8    head2
        HeroNode cur1 = head1.next;
        HeroNode cur2 = head2.next;

        while (cur1 != null && cur2 != null) {
            if (cur1.no > cur2.no) {
                singleLinkedList.addLink(new HeroNode(cur2.no,cur2.name, cur2.nickName));
                cur2 = cur2.next;
                System.out.println(cur2);
            } else {
                singleLinkedList.addLink(new HeroNode(cur1.no,cur1.name, cur1.nickName));
                cur1 = cur1.next;
                System.out.println(cur1);
            }
        }
        if (cur1 != null) {
            singleLinkedList.addLink(cur1);
        }
        if (cur2 != null) {
            singleLinkedList.addLink(cur2);
        }

        return singleLinkedList;
    }

    /**
     * 统计节点数
     *
     * @param head
     * @return
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        HeroNode temp = head.next;
        int length = 0;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

    /**
     * 查找单链表中的倒数第k个节点（新浪面试题）
     */
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        if (head.next == null) {
            return null;
        }
        int size = getLength(head);
        if (index <= 0 || size < index) {
            System.out.println("index超出长度");
            return null;
        }
        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    /**
     * 单链表的反转（腾讯面试题）
     */
    public static void reverse(HeroNode head) {
        if (head.next == null || head.next.next == null) {
            return;
        }
        // head->a->b->c
        HeroNode cur = head.next; // 当前节点
        HeroNode next = null; // 指向当前节点的下一个节点
        HeroNode reverseHead = new HeroNode(0, "", "");
        // 遍历链表
        while (cur != null) {
            next = cur.next; // 先暂时保存当前节点的下一个节点，后面需要使用
            cur.next = reverseHead.next;
            reverseHead.next = cur;
            cur = next;
        }
        head.next = reverseHead.next;
    }

    static class SingleLinkedList {
        // 初始化头节点，头节点不要动，不存放具体的数据
        private final HeroNode head = new HeroNode(0, "", "");

        public HeroNode getHead() {
            return head;
        }

        /**
         * 添加节点到单向链表
         * 不考虑编号
         */
        public void addLink(HeroNode heroNode) {
            HeroNode temp = head;
            while (true) {
                if (temp.next == null) {
                    break;
                }
                temp = temp.next;
            }
            temp.next = heroNode;
        }

        /**
         * 按编号添加节点
         */
        public void addByOrder(HeroNode heroNode) {
            HeroNode temp = head;
            boolean flag = false;
            while (true) {
                if (temp.next == null) {
                    break;
                }
                if (temp.next.no > heroNode.no) {
                    break;
                } else if (temp.next.no == heroNode.no) {
                    flag = true; // 编号已经存在
                    break;
                }
                temp = temp.next;
            }
            if (flag) {
                System.out.println("插入的编号 " + heroNode.no + " 已经存在, 不能加入\n");
            } else {
                heroNode.next = temp.next;
                temp.next = heroNode;
            }
        }

        /**
         * 根据编号修改
         */
        public void editLink(HeroNode newHeroNode) {
            if (head.next == null) {
                System.out.println("链表为空");
                return;
            }
            HeroNode temp = head.next;
            boolean flag = false;// 表示找到该节点
            while (true) {
                if (temp.next == null) {
                    break; // 链表遍历完
                }
                if (temp.next.no == newHeroNode.no) {
                    flag = true;
                    break;
                }
                temp = temp.next;
            }
            if (flag) {
                newHeroNode.next = temp.next.next;
                temp.next = newHeroNode;
            } else {
                System.out.println("没找到编号为" + newHeroNode.no + "的节点");
            }
        }

        /**
         * 删除节点
         */
        public void delete(HeroNode heroNode) {
            if (head.next == null) {
                System.out.println("链表为空");
            }
            HeroNode temp = head.next;
            boolean flag = false;
            while (true) {
                if (temp == null) {
                    break;// 遍历完
                }
                if (temp.next.no == heroNode.no) {
                    flag = true;
                    break;
                }
                temp = temp.next;
            }
            if (flag) {
                // 找到节点，删除节点
                temp.next = temp.next.next;
            } else {
                System.out.println("找不到编号为" + head.no + "的节点");
            }
        }

        /**
         * 显示列表
         */
        public void list() {
            if (head.next == null) {
                System.out.println("链表为空");
                return;
            }
            HeroNode temp = head.next;
            while (true) {
                if (temp == null) {
                    break;
                }
                System.out.println(temp);
                temp = temp.next;
            }
        }
    }

    static class HeroNode {
        public int no;
        public String name;
        public String nickName;
        public HeroNode next;

        public HeroNode() {
        }

        public HeroNode(int no, String name, String nickName) {
            this.no = no;
            this.name = name;
            this.nickName = nickName;
        }

        @Override
        public String toString() {
            return "HeroNode{" + "no=" + no + ", name='" + name + '\'' + ", nickName='" + nickName + '\'' + '}';
        }
    }
}
