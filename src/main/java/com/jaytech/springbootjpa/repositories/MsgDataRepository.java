package com.jaytech.springbootjpa.repositories;

import com.jaytech.springbootjpa.domain.MsgData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MsgDataRepository extends JpaRepository<MsgData, Long> {
}
