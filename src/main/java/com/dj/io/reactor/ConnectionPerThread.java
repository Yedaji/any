package com.dj.io.reactor;

import jdk.internal.org.objectweb.asm.Handle;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 一个线程处理一个连接
 *
 * @author ydj
 * @date 2024/11/17 13:13
 **/
public class ConnectionPerThread {
    // 服务器监听的端口号
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is listening on port " + PORT);

            while (true) {
                // 接受客户端连接
                Socket clientSocket = serverSocket.accept();
                System.out.println("Accepted connection from " + clientSocket.getInetAddress().getHostAddress());

                // 为每个连接创建一个新的线程来处理
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 客户端处理器类，继承自Thread
    static class ClientHandler extends Thread {
        private Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try (
                    // 输入流，用于从客户端读取数据
                    InputStream input = clientSocket.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    // 输出流，用于向客户端发送数据
                    OutputStream output = clientSocket.getOutputStream();
                    PrintWriter writer = new PrintWriter(output, true)
            ) {
                String message;
                // 循环读取客户端发送的数据，直到连接关闭
                while ((message = reader.readLine()) != null) {
                    System.out.println("Received: " + message);
                    // 将接收到的消息回显给客户端
                    writer.println("Echo: " + message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}