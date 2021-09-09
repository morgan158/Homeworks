public class TV {
    String model;
    Channel[] channels;
    int countChannels;

    public TV(String model){
        this.model = model;
        this.countChannels = 0;
        this.channels = new Channel[100];
    }

    public int getCountChannels(){
        return this.countChannels;
    }

    public void addChannel (Channel channel){
        if (countChannels >= channels.length){
            ErrorPrintMessage errorPrintMessage = new ErrorPrintMessage("Список каналов переполнен");
            return;
        }
        this.channels[countChannels] = channel;
        this.countChannels++;
    }

}
