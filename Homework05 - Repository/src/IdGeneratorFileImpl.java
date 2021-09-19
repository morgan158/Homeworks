import java.io.*;
import java.util.Scanner;


public class IdGeneratorFileImpl implements IdGenerator {

    private final String fileName;

    public IdGeneratorFileImpl(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Integer next() {
        try (Scanner scanner = new Scanner(new FileInputStream(fileName))) {
            int lastId = scanner.nextInt();
            lastId++;
            scanner.close();
            try (PrintWriter printWriter = new PrintWriter(new FileWriter(fileName))) {
                printWriter.print(lastId);
            }
            return lastId;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void clear(){
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(fileName))) {
            printWriter.print(0);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
