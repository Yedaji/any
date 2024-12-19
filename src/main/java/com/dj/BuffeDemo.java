package com.dj;

import java.nio.IntBuffer;

/**
 * @author ydj
 * @date 2024/11/14 22:22
 **/
public class BuffeDemo {
    public static IntBuffer intBuffer = null;
    public static void main(String[] args) {
        Sn sn = new Sn();
        System.out.println(sn);
        intBuffer = IntBuffer.allocate(20);
        System.out.println("intBuffer = " + intBuffer);
        for (int i = 0; i < 15; i++) {
            intBuffer.put(i);

        }
        System.out.println(intBuffer);
        intBuffer.flip();

        System.out.println(intBuffer);

        intBuffer.clear();
    }

}
  class Sn{
    private char ch;

     public Sn() {
     }

     public Sn(char ch) {
         this.ch = ch;
     }

      public char getCh() {
          return ch;
      }

      public void setCh(char ch) {
          this.ch = ch;
      }

      @Override
      public String toString() {
          return "Sn{" +
                  "ch=" + ch +
                  '}';
      }
  }
