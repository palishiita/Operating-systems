package ProcessorSchedulingAlgorithms;
import java.util.Iterator;

public interface List<T> {
    void clear();
    int size();
    boolean isEmpty();
    T get(int index);
    T set(int index, T newValue);
    void add(T newValue);
    void add(int index, T newValue);
    void remove(int index);
    Iterator<T> iterator();
}
