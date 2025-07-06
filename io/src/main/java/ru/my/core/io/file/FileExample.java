package ru.my.core.io.file;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * В java пакет io главным образом является абстракцией для потоковой работы с различными io операции, но класс File
 * выбивается из этого определения и он главным образом предназначен для сборка мета данных о файлах и файловой системы в целом.
 *
 */

public class FileExample {

    public static void main(String[] args) throws IOException {
        File kafkaBook = new File("/Users/ilyashabelnik/IdeaProjects/java-core/io/src/main/resources/Apache_Kafka_Potokovaya_obr_i_an_dannykh_2019 1.pdf");

        System.out.println("__________Служебная информация о файле!__________");

        System.out.println("Является директорией ? - " + kafkaBook.isDirectory());
        System.out.println("Является файлом ? - " + kafkaBook.isFile());
        System.out.println("Путь до файла: " + kafkaBook.getPath());

        System.out.println("________Конец служебной информации!__________________");

        File javaClassDir = new File("/Users/ilyashabelnik/IdeaProjects/java-core/io/src/main", "java/ru/my/core/io/file");
        System.out.println("_____Данные по директории с классами_____________");
        System.out.println("Директория существует: " + javaClassDir.exists());
        System.out.println("Действительно считается директорией ?: " + javaClassDir.isDirectory());
        System.out.println("Список файлов: ");
        Stream.of(Objects.requireNonNull(javaClassDir.listFiles()))
                .map(File::getName)
                .forEach(System.out::println);

        System.out.println("_____Данные по директории закончены_____");
    }
}
