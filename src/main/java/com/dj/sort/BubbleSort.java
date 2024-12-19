package com.dj.sort;

/**
 * 1冒泡排序
 *
 * @author ydj
 * @date 2024/09/05 14:33
 **/
public class BubbleSort {

    public static void main(String[] args) {
//        int[] arr = {6, 2, 4, 3, 8, 5, 8};
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        long start = System.currentTimeMillis();
        sort(arr);
        System.out.println("时间消耗= " + (System.currentTimeMillis() - start)); // 8734左右

//        System.out.println(Arrays.toString(arr));
    }

    /**
     * 从小到大排序
     * <p>
     * 优化：如果在某一趟排序中，没有发生一次交换，可以提前结束冒泡排序
     */
    private static void sort(int[] arr) {
        // 每一个大的循环确认一个数的排序位置，只需要确定arr.length-1次
        int swapNum = 0;
        for (int i = 0; i < arr.length - 1; i++) {
//            System.out.printf("第%d趟处理%s\n", i+1, Arrays.toString(arr));
            // 比较
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tem = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = tem;
                    swapNum++;
//                    System.out.printf("  第%d次交换%s \n", swapNum,Arrays.toString(arr));
                }
            }
            if (swapNum == 0) {
//                return;
            }
            swapNum = 0;
        }
    }
}


