import static org.junit.Assert.assertEquals;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.example.CompletableFutureBase;
import org.junit.Test;

public class CF06B_Async_then_Parallel extends CompletableFutureBase {

    /**
     * Модифицируем предыдущий пример, чтобы исполнение заняло только 2 секунды
     */
    @Test
    public void testThenCombineSync() throws Exception {
        long start = System.nanoTime();

        CompletableFuture<Integer> initial
                = CompletableFuture.supplyAsync(this::slowInit);
        CompletableFuture<Integer> future1
                = initial.thenApplyAsync(this::slowIncrement);       // меняем на thenApplyAsync
        CompletableFuture<Integer> future2
                = initial.thenApplyAsync(this::slowIncrement);       // аналогично
        CompletableFuture<Integer> future3
                = future1.thenCombine(future2, (x, y) -> x * y);

        int result = future3.get();
        System.out.println("result: " + result);

        long elapsedTime = System.nanoTime() - start;
        System.out.printf("%d sec passed\n", TimeUnit.NANOSECONDS.toSeconds(elapsedTime));

        assertEquals(2, TimeUnit.NANOSECONDS.toSeconds(elapsedTime));

    }

}
