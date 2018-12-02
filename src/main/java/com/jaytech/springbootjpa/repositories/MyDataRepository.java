package com.jaytech.springbootjpa.repositories;

import com.jaytech.springbootjpa.domain.MyData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyDataRepository extends JpaRepository<MyData, Long> {
}
