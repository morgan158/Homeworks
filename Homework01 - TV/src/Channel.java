public class Channel {
    String nameChannel;
    int countPrograms;
    Program [] programs;


    public Channel(String nameChannel) {
        this.nameChannel = nameChannel;
        this.programs = new Program[24];
        this.countPrograms = 0;

    }

    public int getCountPrograms(){
        return countPrograms;
    }

    public void addProgram(Program program){

        if (countPrograms >= programs.length){
            ErrorPrintMessage errorPrintMessage = new ErrorPrintMessage("Эфирное время заполнено полностью");
            return;
        }
        this.programs[countPrograms] = program;
        this.countPrograms++;
    }
}
