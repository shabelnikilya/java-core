import java.util.concurrent.CompletableFuture;

import org.example.CompletableFutureBase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CF05_Combine_Problem extends CompletableFutureBase {

    /*
     * Вычисляем future3 как сумму результатов из future1 и future2 с помощью thenCombine();
     * убедиться, что исполнение этой задачи действительно заняло 2 секунды
     */
    @Test
    public void testThenCombine() throws Exception {

        CompletableFuture<Integer> future1
                = CompletableFuture.supplyAsync(this::slowInit)
                        .thenApply(this::slowIncrement); // 2 

        CompletableFuture<Integer> future2
                = CompletableFuture.supplyAsync(this::slowInit)
                        .thenApply(this::slowIncrement); // 2

        CompletableFuture<Integer> future3
                = future1.thenCombine(future2, Integer::sum);    // здесь ваше решение

        int result = future3.get();

        assertEquals(4, result);

        System.out.println("result: " + future3.get()); // result: 4
    }

}
