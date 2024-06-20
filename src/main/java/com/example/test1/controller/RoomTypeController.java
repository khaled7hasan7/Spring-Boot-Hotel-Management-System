package com.example.test1.controller;


import com.example.test1.Model.DTO.RoomTypeDTO;
import com.example.test1.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class RoomTypeController {

    @Autowired
    private RoomTypeService roomTypeService;

    @GetMapping("/roomTypes")
    public ResponseEntity<List<RoomTypeDTO>> getAllRoomTypes() {
        List<RoomTypeDTO> roomTypes = roomTypeService.getAllRoomTypes();
        return ResponseEntity.ok(roomTypes);
    }

    @GetMapping("/roomTypes{id}")
    public ResponseEntity<RoomTypeDTO> getRoomTypeById(@PathVariable int id) {
        RoomTypeDTO roomType = roomTypeService.getRoomTypeById(id);
        if (roomType != null) {
            return ResponseEntity.ok(roomType);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/roomTypes")
    public ResponseEntity<RoomTypeDTO> createRoomType(@RequestBody RoomTypeDTO roomTypeDTO) {
        RoomTypeDTO createdRoomType = roomTypeService.createRoomType(roomTypeDTO);
        return ResponseEntity.ok(createdRoomType);
    }

    @PutMapping("/roomTypes/{id}")
    public ResponseEntity<RoomTypeDTO> updateRoomType(@PathVariable int id, @RequestBody RoomTypeDTO roomTypeDTO) {
        RoomTypeDTO updatedRoomType = roomTypeService.updateRoomType(id, roomTypeDTO);
        if (updatedRoomType != null) {
            return ResponseEntity.ok(updatedRoomType);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/roomTypes/{id}")
    public ResponseEntity<Void> deleteRoomType(@PathVariable int id) {
        boolean isDeleted = roomTypeService.deleteRoomType(id);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}