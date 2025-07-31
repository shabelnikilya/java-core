package ru.my.core.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Date;

/**
 * Доделать этот клиент, чтобы он получил и затем распечатал текстовое сообщение.
 */
public class NIO_10_SelectorClient_Problem {
    final static int DEFAULT_PORT = 9999;

    static ByteBuffer bb = ByteBuffer.allocateDirect(8);

    public static void main(String[] args) {
        int port = DEFAULT_PORT;

        try {
            SocketChannel sc = SocketChannel.open();
            sc.configureBlocking(true);
            InetSocketAddress addr = new InetSocketAddress("localhost", port);

            sc.connect(addr);

            long time = 0;
            while (sc.read(bb) != -1) {
                bb.flip();
                while (bb.hasRemaining()) {
                    time <<= 8;
                    time |= bb.get() & 255;
                }
                bb.clear();
            }
            System.out.println(new Date(time));

            sc.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.err.println("I/O error: " + ioe.getMessage());
        }
    }
}