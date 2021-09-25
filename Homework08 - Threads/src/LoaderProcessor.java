public class LoaderProcessor {
    long size = 0;

    long toLoad(int begin, int end){
        LineReader lineReader = new LineReader("links.txt");
        for (int i = begin; i < end; i++) {
            String loadFileName = lineReader.readLine(i); // тут он должен считывать нужный файл с помощью лайнридера
            size += LoadFile.toLoadFile(loadFileName,  i + ".jpg");// и вызывать метод класса для скачивания
        }
        return size;
    }
}
