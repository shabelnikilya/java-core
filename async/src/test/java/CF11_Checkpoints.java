import java.util.concurrent.CompletableFuture;

import org.example.CompletableFutureBase;
import org.example.MyException;
import org.junit.Test;

public class CF11_Checkpoints extends CompletableFutureBase {

    /*
        Можем сохранять контрольные точки (checkpoints).
        ------------------------------------------------
        Возможно, придется перезапуститься несколько раз.
     */
    @Test
    public void testCheckpoint() throws Exception {
        CompletableFuture<Integer> future
                = CompletableFuture.supplyAsync(this::slowInit)  // 1
                        .thenApply(this::slowIncrement)          // 2
                        .thenApply(this::slowIncrementException) // 3
                        .exceptionally(ex -> {
                            System.out.println("Exception 1 occured!");
                            return ex.getCause() instanceof MyException
                                ? ((MyException) ex.getCause()).getCheckpoint()
                                : 0;
                        })
                        .thenApply(this::slowIncrement)          // 4
                        .thenApply(this::slowIncrementException) // 5
                        .exceptionally(ex -> {
                            System.out.println("Exception 2 occured!");
                            return ex.getCause() instanceof MyException
                                ? ((MyException) ex.getCause()).getCheckpoint()
                                : 0;
                        })
                        .thenApply(this::slowIncrement);         // 6

        Integer result = future.get();  // вообще без исключений: 6
                                        // с единственным исключением: 5
                                        // с обоими исключениями: 4
        System.out.println(result);

    }

}
