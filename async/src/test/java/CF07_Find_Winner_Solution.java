import static org.junit.Assert.assertEquals;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.example.CompletableFutureBase;
import org.junit.Test;

public class CF07_Find_Winner_Solution extends CompletableFutureBase {

    /*
     * Кто победитель забега? (должен занять 2 секудны и вернуть 2)
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

        CompletableFuture<?> winner
                = CompletableFuture.anyOf(future1, future2);

        System.out.println("result: " + winner.get());            // result: 2

        long elapsedTime = System.nanoTime() - start;
        System.out.printf("%d sec passed\n", TimeUnit.NANOSECONDS.toSeconds(elapsedTime));

        assertEquals(2, winner.get());
        assertEquals(2, TimeUnit.NANOSECONDS.toSeconds(elapsedTime));

    }

}
