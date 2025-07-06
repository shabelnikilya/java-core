package ru.my.core.io.file;

import java.io.*;

import static ru.my.core.io.file.FilesForExample.KAFKA_BOOK_PATH;

/**
 * Считывание файла байтами, но используя обертку с буфферищацией.
 * Сам контракт API, как следовало догадаться симметричен InputStream, но в отличие
 * от FileInputStream реализация с буффером позволяет экономить на IO операциях.
 * в FileInputStream данные читаются по байтам и между файлом и конкретно методом read()  в
 * java нету буфферов, что приводит к частым IO походам на жесткий диск(хотя вроде как операционка
 * может прогружать часть файлов, но в любом случае IO операций прилично).
 * В то время как с буфферизацией часто чтение данных происходит из оперативной памяти и это исключает частые IO походы на жесткий,
 * и только есть буффер полностью прочитан, то происходит его наполнение и идет IO операция.
 *
 * Супер глубого в дебрии работы класса не уходил, могут быть неточности, но врехнеуровнево работает так.
 */
public class BufferedInputStreamExample {

    public static void main(String[] args) throws IOException {
        File kafka = new File(KAFKA_BOOK_PATH);

        InputStream streamInputFile = null;
        BufferedInputStream buffStream = null;
        try {
            streamInputFile = new FileInputStream(kafka);
            buffStream = new BufferedInputStream(streamInputFile, 500_000);
            System.out.println("Количество доступных байтов: " + buffStream.available());

            var inception = System.currentTimeMillis();
            int r;
            while ((r = buffStream.read()) != -1) {
                System.out.println(buffStream.available());
            }
            var end = System.currentTimeMillis();

            System.out.println("Время считывания файла: " + (end - inception));

        } finally {
            streamInputFile.close();
            buffStream.close();
        }
    }
}
