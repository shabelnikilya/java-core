import static org.junit.Assert.assertEquals;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.example.CompletableFutureBase;
import org.junit.Test;

public class CF08_Apply_to_Winner_Solution extends CompletableFutureBase {

    /*
     * Берем самого быстрого из future1 и future2, после чего инкрементируем результат
     */
    @Test
    public void testApplyToEither() throws Exception {
        long start = System.nanoTime();

        CompletableFuture<Integer> future1
                = CompletableFuture.supplyAsync(this::slowInit)
                        .thenApply(this::slowIncrement)
                        .thenApply(this::slowIncrement);

        CompletableFuture<Integer> future2
                = CompletableFuture.supplyAsync(this::slowInit)
                        .thenApply(this::slowIncrement);

        CompletableFuture<Integer> winner
                = future1.applyToEither(future2, this::slowIncrement);

        int result = winner.get();
        System.out.println("result: " + result);            // result: 3

        long elapsedTime = System.nanoTime() - start;
        System.out.printf("%d sec passed\n", TimeUnit.NANOSECONDS.toSeconds(elapsedTime));

        assertEquals(3, result);
        assertEquals(3, TimeUnit.NANOSECONDS.toSeconds(elapsedTime));

    }

}
