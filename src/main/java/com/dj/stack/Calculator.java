package com.dj.stack;

/**
 * 计算器实现
 *
 * @author ydj
 * @date 2024/09/04 11:38
 **/
public class Calculator {
    public static void main(String[] args) {
        // 表达式  7*((7+3)-(4-5))
        String expression = "78+2*6-2";
        ArrayStack numStack = new ArrayStack(10);
        ArrayStack operStack = new ArrayStack(10);
        // 定义变量
        int index = 0;//用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';// 将每次扫描的char保存到ch
        String keepNum = ""; //多位数拼接
        while (true) {
            //依次得到expression的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            if (operStack.isOper(ch)) {
                // 如果是运算符
                if (!operStack.isEmpty()) {
                    // 如果符号栈有操作符，进行比较，如果当前的操作符的优先级小于等于栈中的操作符，就需要从数栈
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        // 把运算结果入数栈
                        numStack.push(res);
                        // 然后把当前的操作符入符号栈
                        operStack.push(ch);
                    } else {
                        // 如果当前优先级大于栈中的操作符，直接入符号栈
                        operStack.push(ch);
                    }
                } else {
                    // 符号栈为空情况，直接入栈
                    operStack.push(ch);
                }
            } else {
                // 数字则直接入数栈 ch为字符
                // 考虑多位数情况
                keepNum += ch;
                // 判断下一位是否是运算符
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }
            // 让index+1，并判断是否扫描到最后
            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        // 当表达式扫描完毕，就顺序的从数栈中pop出相应的数和符号，并运行
        while (true) {
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        // 最后结果
        int result = numStack.pop();
        System.out.printf("表达式%s = %d ", expression, result);
    }

}

// 定义一个数组表示栈
class ArrayStack {
    private int maxSize; // 栈的大小
    private int[] stack; // 数组模拟栈
    private int top = -1;// top表示栈顶，初始化为-1

    // 构造器
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    // 栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    // 栈空
    public boolean isEmpty() {
        return top == -1;
    }

    // 入栈
    public void push(int value) {
        // 先判断栈是否满
        if (isFull()) {
            System.out.println("栈满了");
            return;
        }
        top++;
        stack[top] = value;
    }

    // 出栈
    public int pop() {
        // 先判断栈是否为空
        if (isEmpty()) {
            // 抛出异常
            throw new RuntimeException("栈空，没有数据～");
        }
        int value = stack[top];
        top--;
        return value;
    }

    public int peek() {
        return stack[top];
    }

    // 显示栈
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    // 返回运算符的优先级，使用数字表示，数字越大优先级越高
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    // 判断是否是运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    // 计算
    public int cal(int num1, int num2, int oper) {
        int res = 0;// 用于存放计算的结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 + num1;
                break;
            default:
                break;
        }
        return res;
    }
}