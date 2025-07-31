import static org.junit.Assert.assertEquals;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.example.CompletableFutureBase;
import org.junit.Test;

public class CF06A_Async_then_Sequent extends CompletableFutureBase {

    /**
     * Требует 3 секунды, т.к. после асинхронной стадии идет последовательная обработка
     */
    @Test
    public void testThenCombineSync() throws Exception {
        long start = System.nanoTime();

        CompletableFuture<Integer> initial
                = CompletableFuture.supplyAsync(this::slowInit);
        CompletableFuture<Integer> future1
                = initial.thenApply(this::slowIncrement);   // а вот и ошибочка!
        CompletableFuture<Integer> future2                  // надо было использовать
                = initial.thenApply(this::slowIncrement);   // thenApplyAsynch()
        CompletableFuture<Integer> future3
                = future1.thenCombine(future2, (x, y) -> x * y);

        int result = future3.get();
        System.out.println("result: " + result);

        long elapsedTime = System.nanoTime() - start;
        System.out.printf("%d sec passed",
                TimeUnit.NANOSECONDS.toSeconds(elapsedTime));

//******************** этот assert завалится ***********************************
        assertEquals(2, TimeUnit.NANOSECONDS.toSeconds(elapsedTime));
//******************************************************************************
    }

}
