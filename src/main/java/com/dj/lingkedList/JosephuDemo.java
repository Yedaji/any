package com.dj.lingkedList;

/**
 * 约瑟夫问题
 *
 * @author ydj
 * @date 2024/09/03 15:36
 **/
public class JosephuDemo {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.list();
        circleSingleLinkedList.countBoy(1,2,5);
    }
}

class CircleSingleLinkedList {
    private Boy first = null;

    public void addBoy(int nums) {
        if (nums < 1) {
            System.out.println("nums不能小于1");
            return;
        }
        Boy cur = null;
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                cur = first;
                first.setNext(first);
            } else {
                cur.setNext(boy);
                boy.setNext(first);
                cur = boy;
            }
        }
    }

    public void list() {
        if (first == null) {
            System.out.println("链表为空");
            return;
        }
        Boy cur = first;
        while (true) {
            System.out.printf("小孩编号 %d \n", cur.getNo());
            if (cur.getNext() == first) {
                break;
            }
            cur = cur.getNext();
        }
    }

    /**
     * 约瑟夫问题
     *
     * @param startNo 开始编号
     * @param countNum       跳过编号
     * @param nums    总人数
     */
    public void countBoy(int startNo, int countNum, int nums) {
        if (first ==null|| startNo<1||startNo>nums){
            System.out.println("参数输入有误");
            return;
        }
        // 创建一个辅助节点helper
        Boy help = first;
        while (true){
            if (help.getNext()==first){
                break;
            }
            help= help.getNext();
        }
        // 报数前移动startno-1
        for (int i = 0; i < startNo-1; i++) {
            first= first.getNext();
            help = help.getNext();
        }
        // 当小孩报数时，让first和help指针同时移动m-1,然后出圈
        //这里是一个循环操作，知道圈中只有一个节点
        while (true) {
            if (help==first) {
                break;
            }
            for (int i = 0; i < countNum-1; i++) {
                first = first.getNext();
                help=help.getNext();
            }
            System.out.printf("小孩%d出圈 \n", first.getNo());
            help.setNext(first.getNext());
            first =first.getNext();
        }
        System.out.printf("最后留在圈中的小孩编号%d",first.getNo());

    }
}

class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
