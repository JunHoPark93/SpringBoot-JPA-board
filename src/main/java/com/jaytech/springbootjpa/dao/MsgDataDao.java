package com.jaytech.springbootjpa.dao;

import com.jaytech.springbootjpa.domain.MsgData;

import java.util.List;

public interface MsgDataDao<T> {
    List<MsgData> getAll();
    MsgData findById(long id);
}
