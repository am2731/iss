package repository;

public interface Repository<T, Tid> {
    void add(T elem);

    Iterable<T> findAll();

    void delete(Integer id);
    void update(T elem, Integer id);
    T findById(Integer id);


}
