package com.example.databaseinitlize.repo;

import com.example.databaseinitlize.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusRepo extends JpaRepository<Status,Integer> {
    @Query("SELECT s.name FROM Status s WHERE s.name in :name")
    List<String> getAllStatusByName(@Param("name") List<String> list);
}
