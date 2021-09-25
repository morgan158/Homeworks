import java.util.concurrent.*;

public class Main8 {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        LoaderProcessor processor = new LoaderProcessor();

        Callable<Long> task1 = () -> processor.toLoad(0, 10);
        Callable<Long> task2 = () -> processor.toLoad(10, 20);
        Callable<Long> task3 = () -> processor.toLoad(20, 30);
        Callable<Long> task4 = () -> processor.toLoad(30, 40);
        Callable<Long> task5 = () -> processor.toLoad(40, 50);
        Callable<Long> task6 = () -> processor.toLoad(50, 60);
        Callable<Long> task7 = () -> processor.toLoad(60, 70);
        Callable<Long> task8 = () -> processor.toLoad(70, 80);
        Callable<Long> task9 = () -> processor.toLoad(80, 90);
        Callable<Long> task10 = () -> processor.toLoad(90, 100);

        Future<Long> f1 = executorService.submit(task1);
        Future<Long> f2 = executorService.submit(task2);
        Future<Long> f3 = executorService.submit(task3);
        Future<Long> f4 = executorService.submit(task4);
        Future<Long> f5 = executorService.submit(task5);
        Future<Long> f6 = executorService.submit(task6);
        Future<Long> f7 = executorService.submit(task7);
        Future<Long> f8 = executorService.submit(task8);
        Future<Long> f9 = executorService.submit(task9);
        Future<Long> f10 = executorService.submit(task10);

        Future[] tasks = {f1, f2, f3, f4, f5, f6, f7, f8, f9, f10};

        long result = 0;

        while (true) {
            int count=0;
            for (Future<Long> future : tasks) {
                if (future.isDone()) {
                    count++;
                }
            }

            if (count == tasks.length) {
                for (Future<Long> task : tasks) {
                    try {
                        result = result + task.get();
                    } catch (InterruptedException | ExecutionException e) {
                        throw new IllegalArgumentException();
                    }
                }
                System.out.println(result);
                return;
            }
        }






//        Это работает только в однопоточном варианте
//        
//        ThreadLoad threadLoad = new ThreadLoad("thread1_");
//        ThreadLoad threadLoad1 = new ThreadLoad("thread2_");
//        ThreadLoad threadLoad2 = new ThreadLoad("thread3_");
//        ThreadLoad threadLoad3 = new ThreadLoad("thread4_");
//        ThreadLoad threadLoad4 = new ThreadLoad("thread5_");
//        ThreadLoad threadLoad5 = new ThreadLoad("thread6_");
//        ThreadLoad threadLoad6 = new ThreadLoad("thread7_");
//        ThreadLoad threadLoad7 = new ThreadLoad("thread8_");
//        ThreadLoad threadLoad8 = new ThreadLoad("thread9_");
//        ThreadLoad threadLoad9 = new ThreadLoad("thread10_");
//       
//        
//        threadLoad.start();
//        threadLoad1.start();
//        threadLoad2.start();
//        threadLoad3.start();
//        threadLoad4.start();
//        threadLoad5.start();
//        threadLoad6.start();
//        threadLoad7.start();
//        threadLoad8.start();
//        threadLoad9.start();

    }
}
