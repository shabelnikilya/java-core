package ru.my.core.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.nio.ByteBuffer;

import java.nio.channels.Channels;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;

import static ru.my.core.io.file.FilesForExample.FIRST_TXT_PATH;

/*
* Демонстрирует работу с каналами-(де)мультиплексорами (scattering / gathering channels).
* Файл с данными ('x.dat') частично считывается в 2 буфера, которые
* затем пишутся в файл 'y.dat' в транспонированном порядке.
* */

public class ScatterGatherChannelDemo {

    public static void main(String[] args) throws IOException {
        ScatteringByteChannel src;
        FileInputStream fis = new FileInputStream(FIRST_TXT_PATH);   // содержит '12345abcdefg'

        src = (ScatteringByteChannel) Channels.newChannel(fis);

        ByteBuffer buffer1 = ByteBuffer.allocateDirect(5);
        ByteBuffer buffer2 = ByteBuffer.allocateDirect(10);

        ByteBuffer[] buffers = { buffer1, buffer2 };

        src.read(buffers);      // в buffer1: 12345
                                // в buffer2: abc

        buffer1.flip();
        while (buffer1.hasRemaining())
            System.out.print((char)buffer1.get());

        System.out.println();
        buffer2.flip();
        while (buffer2.hasRemaining())
            System.out.print((char)buffer2.get());

        // обнуляем position
        buffer1.rewind();
        buffer2.rewind();

        GatheringByteChannel dest;
        FileOutputStream fos = new FileOutputStream("./io/src/main/resources/y.dat");   // должен содержить 'abc12345'
        dest = (GatheringByteChannel) Channels.newChannel(fos);
        buffers[0] = buffer2;
        buffers[1] = buffer1;
        dest.write(buffers);
    }
}