package com.jaytech.springbootjpa.dao;

import java.io.Serializable;
import java.util.List;

public interface MyDataDao <T> extends Serializable {
    List<T> getAll();
    T findById(long id);
    List<T> findByName(String name);
    List <T> find(String str);
    List <T> findByAge(int min, int max);
}
