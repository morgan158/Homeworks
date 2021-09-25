import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class LoadFile {
    static long toLoadFile(String loadFileName, String newFileName) {
        URL website = null;
        try {
            website = new URL(loadFileName);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try (InputStream in = website.openStream()) {
            Path target = Path.of("C:\\Users\\333\\Desktop\\test\\pictures\\" + newFileName);
            return Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
