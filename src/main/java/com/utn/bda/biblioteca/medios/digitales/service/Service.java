package com.utn.bda.biblioteca.medios.digitales.service;

import java.util.List;

public interface Service<T,E> {
    List<T> getAll();

    T getById(E id);

    T add(T model);

    void update(E id , T model);

    void delete(E id);
}
