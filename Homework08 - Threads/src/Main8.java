import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main8 {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        service.submit(new Runnable() {
            @Override
            public void run() {
                try(BufferedReader reader = new BufferedReader(new FileReader("links.txt"))){

                } catch (IOException e) {
                    throw new ;
                }
                System.out.println("Идет поток");
            }
        });

    }
}
