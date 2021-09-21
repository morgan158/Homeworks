import java.util.Comparator;
import java.util.List;

public class Main7 {
    public static void main(String[] args) {
        CarsRepository carsRepository = new CarsRepository("models.txt");

        List<Car> cars = carsRepository.findAll();

        /*
        Возвращаем номера всех автомобилей, имеющих черный цвет или нулевой пробег
         */

        cars.stream()
                .filter((car)->car.getColor().equals("black")||car.getMileage()==0)
                .map(Car::getNumber)
                .forEach(System.out::println);

        /*
        Возвращаем количество уникальных моделей в ценовом диапазоне от 700 до 800 тысяч
         */

        System.out.println(cars.stream()
                .filter((car) -> car.getPrice() >= 700000 && car.getPrice() <= 800000) //фильтруем
                .map(Car::getModel) //вызываем map по модели
                .distinct()        // отсеиваем неуникальные элементы
                .count());

        /*
        Возвращаем цвет автомобиля с минимальной стоимостью
         */

        // Тут я отсортировала, затем вызвала map, затем вывела первое значение
        // как использовать min и при этом вывести цвет модели с минимальной ценой, не поняла
        System.out.println(cars.stream()
                .sorted(Comparator.comparingInt(Car::getPrice))
                .map(Car::getColor)
                .findFirst()
                .orElse("0"));

        /*
        Возвращаем среднюю стоимость Camry
         */
        System.out.println(cars.stream()
                .filter((car)->car.getModel().equals("Camry"))
                .mapToInt(Car::getPrice)
                .average()
                .getAsDouble());
    }
}
