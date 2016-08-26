package LinkedIn;

public interface DataSource<K, T> {
    T get(K key);
}