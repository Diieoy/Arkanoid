package home.arkanoid.dao;


import java.util.List;

public interface AbstractDAO<T> {

    void insert(T data);
    void update(T data);
    void delete(T data);
    T findByID(int id);
    List<T> getAll();
}
