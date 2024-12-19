package com.dj.io.reactor;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author ydj
 * @date 2024/11/17 13:37
 **/
public class SocketClient {
    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("127.0.0.1",12345);
        OutputStream outputStream = socket.getOutputStream();
        String name = "ggg";
        outputStream.write(name.getBytes());

    }
}
