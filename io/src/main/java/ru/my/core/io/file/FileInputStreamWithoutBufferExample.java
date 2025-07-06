package ru.my.core.io.file;

import java.io.*;

import static ru.my.core.io.file.FilesForExample.KAFKA_BOOK_PATH;

/**
 * В целом про FileInputStream нечего рассказать, помимо того, что эта реализация позволяет
 * прочесть байты из файла.
 */
public class FileInputStreamWithoutBufferExample {

    public static void main(String[] args) throws IOException {
        File kafka = new File(KAFKA_BOOK_PATH);

        InputStream streamInputFile = null;
        try {
            streamInputFile = new FileInputStream(kafka);
            System.out.println("Количество доступных байтов: " + streamInputFile.available());

            var inception = System.currentTimeMillis();
            int r;
            while ((r = streamInputFile.read()) != -1) {
                System.out.println(streamInputFile.available());
            }
            var end = System.currentTimeMillis();

            System.out.println("Время считывания файла: " + (end - inception));

        } finally {
            streamInputFile.close();
        }

    }
}
