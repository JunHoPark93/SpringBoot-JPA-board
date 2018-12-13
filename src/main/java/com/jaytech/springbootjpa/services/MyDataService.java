package com.jaytech.springbootjpa.services;

import com.jaytech.springbootjpa.domain.MyData;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class MyDataService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<MyData> getAll() {
        return entityManager.createQuery("from MyData").getResultList();
    }

    public MyData get(int id) {
        return (MyData)entityManager.createQuery("from MyData where id :id")
                .setParameter("id", id)
                .getSingleResult();
    }

    public List<MyData> find(String str) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MyData> query = builder.createQuery(MyData.class);
        Root<MyData> root = query.from(MyData.class);
        query.select(root).where(builder.equal(root.get("name"), str));

        List<MyData> list = entityManager.createQuery(query).getResultList();
        return list;
    }
}
