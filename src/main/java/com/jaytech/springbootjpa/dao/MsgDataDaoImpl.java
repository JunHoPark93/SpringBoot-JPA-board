package com.jaytech.springbootjpa.dao;

import com.jaytech.springbootjpa.domain.MsgData;

import javax.persistence.EntityManager;
import java.util.List;

public class MsgDataDaoImpl implements MsgDataDao<MsgData> {

    private EntityManager entityManager;

    public MsgDataDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<MsgData> getAll() {
        List<MsgData> list = entityManager.createQuery("from MsgData").getResultList();
        return list;
    }

    @Override
    public MsgData findById(long id) {
        return (MsgData) entityManager.createQuery("from MsgData where id :id")
                .setParameter("id", id).getSingleResult();
    }
}
