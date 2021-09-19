import java.util.Iterator;


public interface IdGenerator extends Iterator<Integer> {
    void clear();  // этот метод нужен для обновления id в user_id.txt (при вызове update или delete)
}
