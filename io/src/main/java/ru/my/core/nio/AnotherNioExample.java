package ru.my.core.nio;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import static ru.my.core.io.file.FilesForExample.KAFKA_BOOK_PATH;

public class AnotherNioExample {

    /**
     * немного другой способ получить канал, в данном случае из {@link RandomAccessFile}
     *
     */
    public static void main(String[] args) throws Exception {
        RandomAccessFile accessFile = new RandomAccessFile(KAFKA_BOOK_PATH, "r");

        FileChannel channel = accessFile.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(48);
        int bytesRead = channel.read(buffer); // заполняется буффер и возвращается количество считанных байт

        while (bytesRead != -1) {
            buffer.flip(); // тк запись завершена, его надо перевести в чтение
            while (buffer.hasRemaining()) {
                System.out.println((char) buffer.get());
            }

            buffer.clear();
            bytesRead = channel.read(buffer);
        }

        accessFile.close();
        channel.close();
    }
}
