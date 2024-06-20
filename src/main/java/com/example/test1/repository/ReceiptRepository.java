package com.example.test1.repository;

import com.example.test1.Model.Entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {

//    @Query("SELECT r FROM Receipt r JOIN FETCH r.roomType WHERE r.id = :id")
//    Optional<Receipt> findReceiptWithDetailsById(@Param("id") Integer id);

}
