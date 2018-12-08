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
    public List<MyData> findByName(String n) {
        return (List<MyData>)entityManager.createQuery("from MyData where name = :name")
                .setParameter("name", n)
                .getResultList();
    }

    @Override
    public List<MyData> find(String str) {
        String queryStr = "from MyData where id = ?1 or name like ?2 or mail like ?3";
        Long fid = 0L;

        try {
            fid = Long.parseLong(str);
        } catch (NumberFormatException e) {
            // handle
        }

        Query query = entityManager
                    .createNamedQuery("findWithName")
                    .setParameter("fname", "%" + str + "%");

//        Query query = entityManager.createQuery(queryStr).setParameter(1, fid)
//                                .setParameter(2, "%" + str + "%")
//                                .setParameter(3, str + "@%");

        List<MyData> list = query.getResultList();
        return list;
    }
}
