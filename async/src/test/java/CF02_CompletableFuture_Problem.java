import static org.junit.Assert.assertEquals;

import org.example.CompletableFutureBase;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class CF02_CompletableFuture_Problem extends CompletableFutureBase {

    /*
     * Инициализируем CompletableFuture с помощью slowInit(),
     * затем дважды выполняем медленный инкремент.
     */
    @Test
    public void promiseTestInc() throws Exception {
        long start = System.currentTimeMillis();

        CompletableFuture<Void> calculate = CompletableFuture.supplyAsync(this::slowInit)
                .thenApply(this::slowIncrement)
                .thenApply(this::slowIncrement)
                .thenAccept(it -> System.out.println("Finish process: " + it));

        calculate.get();
        long elapsedTime = System.currentTimeMillis() - start;
        System.out.printf("%d sec passed",
                TimeUnit.MILLISECONDS.toSeconds(elapsedTime));

        assertEquals(3, TimeUnit.MILLISECONDS.toSeconds(elapsedTime));
    }

}
