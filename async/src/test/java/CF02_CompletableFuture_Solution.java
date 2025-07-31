import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.example.CompletableFutureBase;
import org.junit.Test;
import static org.junit.Assert.*;

public class CF02_CompletableFuture_Solution extends CompletableFutureBase {

    /*
     * Инициализируем CompletableFuture с помощью slowInit(),
     * затем дважды выполняем медленный инкремент.
     */
    @Test
    public void promiseTestInc() throws Exception {
        long start = System.currentTimeMillis();

        CompletableFuture<?> future
                = CompletableFuture.supplyAsync(this::slowInit)
                        .thenApply(this::slowIncrement)
                        .thenApply(this::slowIncrement)
                        .thenAccept(res -> System.out.println("Final result: " + res));

        future.get();
        long elapsedTime = System.currentTimeMillis() - start;
        System.out.printf("%d sec passed\n", TimeUnit.MILLISECONDS.toSeconds(elapsedTime));

        assertEquals(3, TimeUnit.MILLISECONDS.toSeconds(elapsedTime));
    }

}
