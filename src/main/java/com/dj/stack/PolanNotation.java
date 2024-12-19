package com.dj.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰后缀表达式
 *
 * @author ydj
 * @date 2024/09/04 16:51
 **/
public class PolanNotation {
    public static void main(String[] args) {
        // 定义一个逆波兰表达式
        //（30+4）* 5-6 =》30 4 + 5 * 6 == 164
        // 4 * 5 - 8 + 60 + 8 / 2 =》4 5 * 8 -60 + 8 2 / +
//        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";
        // 思路
        // 1. 先将 "3 4 + 5 * 6 - " =》放到ArrayList中
        // 2. 将ArrayList 传递给一个方法，遍历ArrayList 配合栈完成计算
//        int res = calculate(getListString(suffixExpression));
//        System.out.println("计算结果=" + res);

        //
        // String s = toPolanNotation("1+((2+3)*4)-5");
        //  System.out.println("s = " +s);//
        String s = "10+((2+3)*4)-5";
        System.out.println(s);
        List<String> toInfixExpressionList = toInfixExpressionList(s);
        System.out.println("中缀表达式 = " + toInfixExpressionList);
        List<String> suffixExpressionList = parseSuffixExpressionList(toInfixExpressionList);
        System.out.println("后缀表达式【1 2 3 + 4 * + 5 -】= " + suffixExpressionList);
        int result = calculate(suffixExpressionList);
        System.out.println("1+((2+3)*4)-5 = " + result);
    }

    public static List<String> getListString(String suffixExpression) {
        // 将suffixExpression分割
        String[] split = suffixExpression.split(" ");
        ArrayList<String> list = new ArrayList<>();
        for (String s : split) {
            list.add(s);
        }
        return list;
    }

    public static int calculate(List<String> list) {
        Stack<String> stack = new Stack<>();
        for (String item : list) {
            if (item.matches("\\d+")) {
                stack.push(item);
            } else {
                // pop出两个数并运算
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num2 + num1;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                stack.push(String.valueOf(res));
            }
        }
        return Integer.parseInt(stack.pop());
    }

    // 中缀表达式转后缀表达式
    public static List<String> parseSuffixExpressionList(List<String> ls) {
        /**
         * 例子 1+((2+3)*4)-5 ==> 将s2出栈为 - 5 + * 4 + 3 2 1 ==》后缀表达式为 1 2 3 + 4 * + 5 -
         *
         * 固定公式
         * 1,初始化两个栈：运算符栈s1和存储中间结果栈s2
         * 2,从左到右扫描中缀表达式
         * 3,遇到操作数时，将其压s2
         * 4,遇到运算符时，比较其与s1栈顶运算符的优先级：
         *    1,如果s1为空，或栈顶运算符为左括号(，则直接将此运算符入栈
         *    2,否则，若优先级比栈顶运算符的高，也将运算符压入s1
         *    3,否则，将s1栈顶的运算符弹出并压入到s2中，再次将4.1与s1中新的栈顶运算符相比较；
         *  5,遇到括号时：
         *      1,如果是左括号(，则直接压入s1
         *      2,如果是右括号)，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号位置，此时将这一对括号丢弃
         *  6,重复2至5，直到表达式的最右边
         *  7,将s1中剩余的运算符依次弹出并压入s2
         *  8,依次弹出s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
         */
        //  s1保存运算符
        Stack<String> s1 = new Stack<>();
        // s2保存中间结果
        ArrayList<String> s2 = new ArrayList<>();
        for (String item : ls) {
            if (item.equals("(")) {
                s1.add(item);
            } else if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals(")")) {
                //如果是右括号)，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号位置，此时将这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();
            } else {
                /**
                 *  4,遇到运算符时，比较其与s1栈顶运算符的优先级：
                 *   1,如果s1为空，或栈顶运算符为左括号(，则直接将此运算符入栈
                 *   2,否则，若优先级比栈顶运算符的高，也将运算符压入s1
                 *   3,否则，将s1栈顶的运算符弹出并压入到s2中，再次将4.1与s1中新的栈顶运算符相比较；
                 */
               while (s1.size()!=0 && priority(s1.peek()) >= priority(item)) {
                   s2.add(s1.pop());
               }
               s1.push(item);
            }
        }
        while (!s1.isEmpty()){
            s2.add(s1.pop());
        }
        return s2;
    }


    // 返回运算符的优先级，使用数字表示，数字越大优先级越高
    public static int priority(String oper) {
        if (oper.equals("+") || oper.equals("-")) {
            return 1;
        } else if (oper.equals("*") || oper.equals("/")) {
            return 2;
        } else {
            return 0;
        }
    }

    public static List<String> toInfixExpressionList(String s) {
        ArrayList<String> list = new ArrayList<>();
        int i = 0;
        String str;
        char c;
        do {
            // 非数字
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                list.add(c + "");
                i++;
            } else {
                str = "";
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;
                    i++;
                }
                list.add(str);
            }
        } while (i < s.length());
        return list;
    }

}
