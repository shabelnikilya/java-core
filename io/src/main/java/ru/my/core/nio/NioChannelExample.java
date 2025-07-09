package ru.my.core.nio;

import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;

import static ru.my.core.io.file.FilesForExample.KAFKA_BOOK_PATH;

public class NioChannelExample {

    /**
     * Path - абстракция над путем к файлу (неожиданно) в ОС.
     *
     * В целом все как по учебнику, открывается файл с помощью создания канала.
     * Далее алоцируется в оперативку память под этот канал.
     * далее в него пишутся данные.
     * далее его переводят в режим чтения (rewind по сути индекс в массиве в начало возвращает после записи)
     * и читаем данные и все
     *
     */
    public static void main(String[] args) {
        var  path = Paths.get(KAFKA_BOOK_PATH);
        int count;

        var start = System.currentTimeMillis();

        try (var channel = Files.newByteChannel(path)) {
            ByteBuffer buffer = ByteBuffer.allocate(512);
            do {
                count = channel.read(buffer);
                if (count != -1) {
                    buffer.rewind();

                    for (int i = 0; i < count; i++) {
                        System.out.println(buffer.get());
                    }
                }
            } while (count != - 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        var end = System.currentTimeMillis();

        System.out.println(end - start);
    }
}
