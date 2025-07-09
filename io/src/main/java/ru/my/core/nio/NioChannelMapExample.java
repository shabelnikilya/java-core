package ru.my.core.nio;

import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;

import static ru.my.core.io.file.FilesForExample.KAFKA_BOOK_PATH;

public class NioChannelMapExample {

    /**
     * Другой способ работы с буффером через отображение.
     * тут файл сразу отображается на буффер.
     *
     * Стоит отметить, что MappedByteBuffer вроде как управляется силами ОС, те его память
     * не аллоцируется в глоб. куче JVM
     */
    public static void main(String[] args) {
        var  path = Paths.get(KAFKA_BOOK_PATH);
        int count;

        var start = System.currentTimeMillis();

        try (var channel = (FileChannel) Files.newByteChannel(path)) {

            long size = channel.size();
            System.out.println("размер файла: " + size);

            MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, size);

            for (int i = 0; i < size; i++) {
                System.out.println(mappedByteBuffer.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        var end = System.currentTimeMillis();

        System.out.println(end - start);
    }
}
