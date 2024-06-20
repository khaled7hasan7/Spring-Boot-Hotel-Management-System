package com.example.test1.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.test1.Model.DTO.RoomDTO;
import com.example.test1.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class RoomController {



    @Autowired
    private RoomService roomService;

    @GetMapping("/rooms")
    public List<RoomDTO> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/room/{id}")
    public ResponseEntity<RoomDTO> getRoomById(@PathVariable Integer id) {
        RoomDTO roomDTO = roomService.getRoomById(id);
        if (roomDTO != null) {
            return ResponseEntity.ok(roomDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/rooms/{branchId}/{status}")
    public ResponseEntity<List<RoomDTO>> getAllRoomsInBranchWithEmptyStatus(@PathVariable Integer branchId , @PathVariable String status) {
        String StatusName = status; // Replace with your actual status name for "empty"
        List<RoomDTO> roomsInBranchWithEmptyStatus = roomService.getAllRoomsInBranchWithEmptyStatus(branchId, StatusName);
        if (!roomsInBranchWithEmptyStatus.isEmpty()) {
            return ResponseEntity.ok(roomsInBranchWithEmptyStatus);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/rooms/{branchId}")
    public ResponseEntity<List<RoomDTO>> getAllRoomsInBranch(@PathVariable Integer branchId) {
        List<RoomDTO> roomsInBranch = roomService.getAllRoomsInBranch(branchId);
        if (!roomsInBranch.isEmpty()) {
            return ResponseEntity.ok(roomsInBranch);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/rooms")
    public ResponseEntity<RoomDTO> createRoom(@RequestBody RoomDTO roomDTO) {
        RoomDTO createdRoom = roomService.createRoom(roomDTO);
        return ResponseEntity.ok(createdRoom);
    }

    @PutMapping("/rooms/{id}")
    public ResponseEntity<RoomDTO> updateRoom(@PathVariable Integer id, @RequestBody RoomDTO roomDTO) {
        RoomDTO updatedRoom = roomService.updateRoom(id, roomDTO);
        if (updatedRoom != null) {
            return ResponseEntity.ok(updatedRoom);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/rooms/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Integer id) {
        if (roomService.deleteRoom(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
