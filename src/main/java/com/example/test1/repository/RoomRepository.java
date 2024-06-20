package com.example.test1.repository;

import com.example.test1.Model.Entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {


    List<Room> findByBranchId(Integer branchId);


    List<Room> findByBranchIdAndStatus_StatusName(Integer branchId, String statusName);


}