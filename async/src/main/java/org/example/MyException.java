package org.example;

/*
    Это исключение хранит ранее вычисленное значение, своего рода контрольную точку ("checkpoint").
*/

public class MyException extends RuntimeException {

    private Integer checkpoint = null;

    public MyException(Integer checkpoint) {
        this.checkpoint = checkpoint;
    }

    public Integer getCheckpoint() {
        return checkpoint;
    }
}
