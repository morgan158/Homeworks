import java.lang.reflect.Field;
import java.util.Arrays;


public class Validator {


    public void validate(Object from) {
        Class<?> clazz = from.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {

            try {
                // По умолчанию считаю, что все перечисленные аннотации применяются к каждому полю
                int notEmpty = field.getAnnotation(NotEmpty.class).notEmpty();
                int min = field.getAnnotation(MinMaxLength.class).min().min();
                int max = field.getAnnotation(MinMaxLength.class).max().max();

                double digit; // использую double, чтобы охватить максимум значений без дополнительного кода
                              //также по умолчанию - что числа в аннотациях находятся в разумных пределах

                switch (field.getType().getSimpleName()) {
                    case "int", "double", "float", "short", "byte", "long", "char" -> //можно дополнить wrapper-классами
                            digit = Double.parseDouble(field.get(from).toString());
                    case "boolean" -> digit = Boolean.parseBoolean(field.get(from).toString()) ? 1 : 0;
                    case "String" -> digit = field.get(from).toString().length();
                    default -> {
                        System.out.println(Arrays.toString(field.get(from).getClass().getDeclaredFields()));
                        digit = field.get(from).getClass().getDeclaredFields().length;
                    }
                }

                if (digit == notEmpty
                    || digit < min
                    || digit > max) {
                    throw new IllegalArgumentException("Значение поля " + field.getName() + " не соответствует условию");
                }


            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        System.out.println("Значения полей соответствуют условиям");
    }


}

