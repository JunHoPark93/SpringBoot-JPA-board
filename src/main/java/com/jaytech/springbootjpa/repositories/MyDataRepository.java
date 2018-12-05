package com.jaytech.springbootjpa.repositories;

import com.jaytech.springbootjpa.domain.MyData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MyDataRepository extends JpaRepository<MyData, Long> {
    Optional<MyData> findById(Long name);

    List<MyData> findByNameLike(String name);
    List<MyData> findByIdIsNotNullOrderByIdDesc();
    List<MyData> findByAgeGreaterThan(Integer age);
    List<MyData> findByAgeBetween(Integer age1, Integer age2);
}
