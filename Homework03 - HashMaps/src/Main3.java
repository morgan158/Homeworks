import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        CountOfLetter countOfLetter = new CountOfLetter();
        countOfLetter.printCountOfLetter(string);
    }
}
