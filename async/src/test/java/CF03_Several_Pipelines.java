import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.example.CompletableFutureBase;
import org.junit.Test;
import static org.junit.Assert.*;

public class CF03_Several_Pipelines extends CompletableFutureBase {

    @Test
    public void testRunSeveralPipelines() throws Exception {

        long start = System.currentTimeMillis();

        // факультативно: раскомментировать Thread.currentThread().getName() в slowInit()
        CompletableFuture<Integer> future1
                = CompletableFuture.supplyAsync(this::slowInit)
                                   .thenApplyAsync(this::slowIncrement);

        CompletableFuture<Integer> future2
                = CompletableFuture.supplyAsync(this::slowInit);

        Integer res0 = slowInit();    // здесь мы выполняем свою работу...

        Integer res1 = future1.get(); // ... а теперь извлекаем результаты...
        Integer res2 = future2.get();

        long elapsedTime = System.currentTimeMillis() - start;

        System.out.println("tasks finished with results "
                + res0 + ", " + res1 + " and " + res2);  // ... и комбинируем их.

        System.out.printf("%d sec passed\n", TimeUnit.MILLISECONDS.toSeconds(elapsedTime));

        assertEquals(2, TimeUnit.MILLISECONDS.toSeconds(elapsedTime));

    }

}
