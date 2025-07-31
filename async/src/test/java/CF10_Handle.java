import java.util.concurrent.CompletableFuture;

import org.example.CompletableFutureBase;
import org.junit.Test;

public class CF10_Handle extends CompletableFutureBase {

    /*
        В API также имеется более генеральный метод handle() для восстановления после ошибки.
        Он вызывается вне зависимости от того, вылетело ли исключение.
        ----------------------------------------------------------------------
        Возможно, придется перезапуститься несколько раз.
     */
    @Test
    public void testHandle() throws Exception {
        CompletableFuture<Integer> future
                = CompletableFuture.supplyAsync(this::slowInit)
                        .thenApply(this::slowIncrementException)
                        .handle((ok, ex) -> {
                            if (ex != null) {
                                System.out.println("exception occured");
                            }
                            // 0 может служить условием для запуска специфической обработки ниже по потоку
                            return ok == null ? 0 : ok;    // вернет 0 при исключении, поэтому будет 1
                        })
                        .thenApply(this::slowIncrement);

        Integer result = future.get();  // без исключения 3, с вылетом исключением 1
        System.out.println(result);

    }

}
