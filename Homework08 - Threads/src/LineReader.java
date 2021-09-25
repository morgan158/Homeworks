/*
Класс для считывания файла со ссылками
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LineReader {
    String fileName;

    public LineReader(String fileName) {
        this.fileName = fileName;
    }

    public String readLine (int numberLine) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            for (int i = 0; i < 100; i++) {

                if (i == numberLine) {
                    return reader.readLine();
                } else {
                    reader.readLine();
                }
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return null;
    }
}
