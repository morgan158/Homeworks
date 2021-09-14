import java.util.Random;

public class Controller {
    TV tv;

    public Controller (TV tv){
        this.tv = tv;
    }

    public void on(int number){
        number--;
        String message = null;
        if (number > tv.getCountChannels() || number < 0) {
            message = "Такого канала не существует";
        }

        if (tv.channels[number].getCountPrograms() < 1){
            message = "На канале нет передач";
        }
        if (message != null){
            ErrorPrintMessage errorPrintMessage = new ErrorPrintMessage(message);
            return;
        }

        Random random = new Random();
        int randomProgram = random.nextInt(tv.channels[number].getCountPrograms());
        System.out.println(tv.channels[number].programs[randomProgram].nameProgram);
    }
}
