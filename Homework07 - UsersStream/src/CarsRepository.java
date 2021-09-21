import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CarsRepository {
    private String fileName;

    private List<Car> cars;

    public CarsRepository(String fileName) {
        this.fileName = fileName;
    }

    private final static Function<String, Car> carMapFunction = line -> {
        String[] parts = line.split("|");

        String number = parts[0];
        String model = parts[1];
        String color = parts[2];
        int mileage = Integer.parseInt(parts[3]);
        int price = Integer.parseInt(parts[4]);

        return new Car(number, model, color, mileage, price);
    };

    public List<Car> findAll() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            return reader.lines().map(carMapFunction).collect(Collectors.toList());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
