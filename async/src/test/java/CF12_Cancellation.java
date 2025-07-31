import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;

import org.example.CompletableFutureBase;
import org.junit.Test;

public class CF12_Cancellation extends CompletableFutureBase {

    /*
        Исполнение CompletableFuture можно отменить.
     */
    @Test
    public void testCancellation() throws Exception {
        CompletableFuture<Integer> future
                = CompletableFuture.supplyAsync(this::slowInit)
                        .thenApply(this::slowIncrement);

        future.cancel(new java.util.Random().nextBoolean());   // закомментировать, чтобы не бросало исключение
        // отметим, что булевый флаг работает в Future, но не в CompletableFuture, см.javadoc

        System.out.println(future.isCancelled());              // true
        System.out.println(future.isCompletedExceptionally()); // true

        try {
            Integer result = future.get();      // бросает CancellationException
            System.out.println(result);
        } catch (CancellationException ce) {
            System.out.println("Caught CE!");
        }

    }

}
