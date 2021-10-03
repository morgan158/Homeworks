import java.util.ArrayList;

public class Person {
    @NotEmpty @MinMaxLength
    String name;
    @NotEmpty @Max
    int age;
    @NotEmpty
    String profession;

    @NotEmpty  @MinMaxLength(min = @Min(min = 3))
    Programmer programmer;

    public Person(String name, int age, String profession) {
        this.name = name;
        this.age = age;
        this.profession = profession;
    }

    public void setProfession(String newProfession) {
        this.profession = newProfession;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", profession='" + profession + '\'' +
                '}';
    }
}
