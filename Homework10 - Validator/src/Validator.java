import java.lang.reflect.Field;
import java.util.Arrays;


public class Validator {


    public void validate(Object from) {
        Class<?> clazz = from.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {

            try {

                double digit; // использую double, чтобы охватить максимум значений без дополнительного кода
                //также по умолчанию - что числа в аннотациях находятся в разумных пределах

                switch (field.getType().getSimpleName()) {
                    case "int":
                    case "char":
                    case "double":
                    case "float":
                    case "short":
                    case "byte":
                    case "long":
                        digit = Double.parseDouble(field.get(from).toString());
                        break;
                    case "boolean":
                        digit = Boolean.parseBoolean(field.get(from).toString()) ? 1 : 0;
                    case "String":
                        digit = field.get(from).toString().length();
                    default:
                        digit = field.get(from).getClass().getDeclaredFields().length;
                }

                // По умолчанию считаю, что все перечисленные аннотации применяются к каждому полю
                if (field.getAnnotation(NotEmpty.class) != null) {
                    int notEmpty = field.getAnnotation(NotEmpty.class).notEmpty();
                    if (digit == notEmpty) {
                        throw new IllegalArgumentException("Значение поля " + field.getName() + " равно нулю или null");
                    }
                }

                if (field.getAnnotation(MinMaxLength.class) != null) {
                    int min = field.getAnnotation(MinMaxLength.class).min().min();
                    int max = field.getAnnotation(MinMaxLength.class).max().max();
                    if (digit < min || digit > max) {
                        throw new IllegalArgumentException("Значение поля " + field.getName() + " выходит за установленные пределы");
                    }
                }

                // на случай, если используются только @Min или @Max

                if (field.getAnnotation(Min.class) != null) {
                    int min = field.getAnnotation(Min.class).min();
                    if (digit < min) {
                        throw new IllegalArgumentException("Значение поля " + field.getName() + " меньше минимального минимального значения," +
                                "установленного в аннотации");
                    }
                }

                if (field.getAnnotation(Max.class) != null) {
                    int max = field.getAnnotation(Max.class).max();
                    if (digit > max) {
                        throw new IllegalArgumentException("Значение поля " + field.getName() + " больше" +
                                " максимального значения, установленного в аннотации");
                    }
                }


            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        System.out.println("Значения полей соответствуют условиям");
    }


}

