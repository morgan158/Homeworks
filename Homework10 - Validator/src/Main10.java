import java.lang.annotation.Annotation;
import java.util.ArrayList;

public class Main10 {

    public static void main(String[] args) {
        Person person = new Person("uuuuu", 2, "programmer");
        Validator validator = new Validator();
        person.programmer = new Programmer(8, "kkk", true);
        validator.validate(person);




    }
}
