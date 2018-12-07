package com.jaytech.springbootjpa.dao;

import com.jaytech.springbootjpa.domain.MyData;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class MyDataDaoImpl implements MyDataDao<MyData> {
    private static final long serialVersionUID = 1L;

    private EntityManager entityManager;

    public MyDataDaoImpl() {

    }

    public MyDataDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<MyData> getAll() {
        Query query = entityManager.createQuery("from MyData");
        List<MyData> list = query.getResultList();
        entityManager.close();

        return list;
    }

    @Override
    public MyData findById(long id) {
        return (MyData) entityManager.createQuery("from MyData where id = " + id)
                .getSingleResult();
    }

    @Override
    public List<MyData> findByName(String name) {
        return (List<MyData>)entityManager.createQuery("from MyData where name = " + name)
                .getResultList();
    }
}
