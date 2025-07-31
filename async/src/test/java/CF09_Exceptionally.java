import java.util.concurrent.CompletableFuture;

import org.example.CompletableFutureBase;
import org.junit.Test;

public class CF09_Exceptionally extends CompletableFutureBase {

    /*
        Обратный вызов exceptionally() позволяет восстановиться после ошибки, возникшей на
        исходном Future. Ошибку можно зажурналировать и вернуть дефолтное значение.
        -----------------------------------------------------------------------
        Возможно, придется перезапуститься несколько раз, пока не вылетит исключение.
     */
    @Test
    public void testExceptionally() throws Exception {

        CompletableFuture<Integer> future
                = CompletableFuture.supplyAsync(this::slowInit)
                        .thenApply(this::slowIncrementException)
                        .thenApply(this::slowIncrement)

                        // эта функция отработает только в случае ошибки
                        .exceptionally(ex -> {
                            System.out.println("exception occured!");
                            return 0;
                        })

                        .thenApply(this::slowIncrement);

        Integer result = future.get();
        System.out.println(result);

    }

}
