package com.lokasyon.lokasyon.repository;

import com.lokasyon.lokasyon.entity.Lokasyon;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LokasyonRepository extends CrudRepository<Lokasyon,Long> {

    @Query("select * from Lokasyon")
    List<Lokasyon> getAllLokasyon();



}
