import org.example.CompletableFutureBase;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
* Демонстрирует старомодные подходы;
* запускать по-очереди
* */

public class CF01_FutureJavaOld extends CompletableFutureBase {

    int result;

//---- ШАГ 1: с Java 1 по 4: изначальная многопоточность в Java --------------
//---- для запуска дважды щелкнуть testStartJoin()
    @Ignore
    @Test
    public void testStartJoin() throws InterruptedException {
        Thread t = new Thread() {
            @Override
            public void run() {
                result = slowInit();
            }
        };

        t.start();
        t.join();
        System.out.println("testStartJoin() is done: " + result);
    }


//---- ШАГ 2: с Java 5 по 7: используем Executor для вызова slowInit() --------------
//---- для запуска дважды щелкнуть futureTest()
    @Ignore
    @Test
    public void futureTest() throws InterruptedException, ExecutionException {

        Callable<Integer> r = this::slowInit;
        ExecutorService exec = Executors.newFixedThreadPool(10);
        Future<Integer> future = exec.submit(r);

        Integer res = future.get();

        System.out.println("futureTest() is done: " + res);
    }


//---- ШАГ 3: Java 8: используем CompletableFuture для вызова slowInit() --------------
//---- для запуска дважды щелкнуть promiseTest()
    @Ignore
    @Test
    public void promiseTest() throws InterruptedException, ExecutionException {

        CompletableFuture<Integer> future
                = CompletableFuture.supplyAsync(this::slowInit);
        Integer res = future.get();
        System.out.println("promiseTest() is done: " + res);
    }


//---- ШАГ 4: используем CompletableFuture для исполнения нескольких задач --------------
//---- для запуска дважды щелкнуть promiseTestNext()
    @Ignore
    @Test
    public void promiseTestNext() throws InterruptedException, ExecutionException {
        CompletableFuture<Void> future
                = CompletableFuture.supplyAsync(this::slowInit)
                        .thenAccept(res -> System.out.println("finished " + res))
                        .thenRun(() -> System.out.println("look at results"));

        while (!future.isDone()) {
            System.out.println("Completable future in process ...");
        }
        future.get();
        System.out.println("promiseTestNext() is done.");
    }

}
