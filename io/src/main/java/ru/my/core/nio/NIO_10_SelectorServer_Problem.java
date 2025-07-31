package ru.my.core.nio;

import java.io.IOException;

import java.net.InetSocketAddress;
import java.net.ServerSocket;

import java.nio.ByteBuffer;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import java.util.Iterator;

/**
 * Задание: послать сообщение от сервера с текстом "hello from server".
 * Использовать тот же самый селектор для приема и отправки сообщения; вот подсказка:
 *     1) когда соединение будет установлено, переводим канал в пишущий режим;
 *     2) задаем на канале неблокирующий режим при помощи configureBlocking()
 * Для упрощения примем, что данные в канале поместятся в буфер.
 */

public class NIO_10_SelectorServer_Problem {
    final static int DEFAULT_PORT = 9999;

    static ByteBuffer bb = ByteBuffer.allocateDirect(8);

    public static void main(String[] args) throws IOException {
        int port = DEFAULT_PORT;
        System.out.println("Server starting ... listening on port " + port);

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ServerSocket ss = ssc.socket();
        ss.bind(new InetSocketAddress(port));
        ssc.configureBlocking(false);

        Selector s = Selector.open();
        ssc.register(s, SelectionKey.OP_ACCEPT);

        while (true) {
            int n = s.select(); // выберем какой-то канал
            if (n == 0) continue; // не получилось выбрать канал
            Iterator it = s.selectedKeys().iterator();
            while (it.hasNext()) {
                SelectionKey key = (SelectionKey) it.next();
                if (key.isAcceptable()) {
                    SocketChannel sc;
                    // неблокирующий вызов accept()
                    sc = ((ServerSocketChannel) key.channel()).accept();
                    if (sc == null) continue; // очередь ожидающих соединений пуста
                    System.out.println("Receiving connection");
                    bb.clear(); // готовим буфер к записи, обнуляем position
                    bb.putLong(System.currentTimeMillis());
                    bb.flip(); // устанавливаем limit в значение position, затем обнуляем position
                    System.out.println("Writing current time");
                    // пишем из буфера в канал
                    while (bb.hasRemaining()) sc.write(bb);
                    sc.close();
                }
                it.remove(); // удаляем канал, если обмен данными завершен
            }
        }
    }
}