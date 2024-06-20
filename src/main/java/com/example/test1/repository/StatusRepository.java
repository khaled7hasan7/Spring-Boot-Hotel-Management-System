package com.example.test1.repository;


import com.example.test1.Model.Entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



import java.util.Optional;


@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {



    @Query("SELECT s FROM Status s WHERE s.statusName = :statusName")
    Optional<Status> findByStatusName(@Param("statusName") String statusName);


}