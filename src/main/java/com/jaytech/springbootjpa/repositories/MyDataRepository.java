package com.jaytech.springbootjpa.repositories;

import com.jaytech.springbootjpa.domain.MyData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MyDataRepository extends JpaRepository<MyData, Long> {
    Optional<MyData> findById(Long name);

    List<MyData> findByNameLike(String name);

    @Query("SELECT d FROM MyData d ORDER BY d.name")
    List<MyData> findAllOrderByName();

    @Query("FROM MyData where age between :min and :max")
    List<MyData> findByAge(@Param("min") int min, @Param("max") int max);

    List<MyData> findByIdIsNotNullOrderByIdDesc();
    List<MyData> findByAgeGreaterThan(Integer age);
    List<MyData> findByAgeBetween(Integer age1, Integer age2);
}
