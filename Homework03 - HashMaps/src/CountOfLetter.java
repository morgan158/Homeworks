import java.util.HashMap;
import java.util.Map;

public class CountOfLetter {
    private HashMap<Character, Integer> mapLetter = new HashMap<>();

    /*
    приватный метод для создания словаря,
    вызывается из метода printCountOfLetter
     */

    private HashMap mapOfLetter(String string) {
        int value;
        for (char key : string.toCharArray()) {
            if (mapLetter.containsKey(key)) {   // если словарь содержит ключ,
                value = mapLetter.get(key);     // присваиваем value значение,
                value++;                        // увеличиваем его на 1
                mapLetter.put(key, value);      // вкладываем в словарь новую пару с тем же ключом и новым зн-м
            } else {
                value = 1;                     // иначе присваиваем 1 и
                mapLetter.put(key, value);     // вкладываем новую пару
            }
        }
        return mapLetter;
    }

    /*
    публичный метод, доступный для вызова из main,
    сразу после создания объекта можно вызвать printCountOfLetter c параметром string
     */

    public void printCountOfLetter(String string) {
        mapLetter = this.mapOfLetter(string);
        for (Map.Entry entry: mapLetter.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }
}
