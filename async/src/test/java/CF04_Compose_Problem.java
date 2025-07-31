import java.util.concurrent.CompletableFuture;
import static org.junit.Assert.*;

import org.example.CompletableFutureBase;
import org.junit.Test;

public class CF04_Compose_Problem extends CompletableFutureBase {

    /*
     * 1) определяем thenCompose как CompletableFuture;
     * 2) вызываем thenCompose(), чтобы поместить результат из future1 в thenCompose();
     * 3) выполняем slowIncrement() в thenCompose().
     *
     */
    @Test
    public void promiseTestCompose2() throws Exception {
        CompletableFuture<Integer> future1
                = CompletableFuture.supplyAsync(this::slowInit)      // 1 
                                   .thenApply(this::slowIncrement);  // 2

        CompletableFuture<Integer> thenCompose
                = future1.thenApply(this::slowIncrement);   // здесь ваше решение

        int result = thenCompose.get();

        System.out.println(result);

        assertEquals(3, result);
    }

}
