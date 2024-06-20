package com.example.test1.service;

import com.example.test1.Model.DTO.RoomDTO;
import com.example.test1.Model.Entity.*;
import com.example.test1.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private FloorRepository floorRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Autowired
    private BranchRepository branchRepository;

    public List<RoomDTO> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        return rooms.stream()
                .map(RoomDTO::toDTO)
                .collect(Collectors.toList());
    }


    public List<RoomDTO> getAllRoomsInBranch(Integer branchId) {
        List<Room> rooms = roomRepository.findByBranchId(branchId);
        return rooms.stream()
                .map(RoomDTO::toDTO)
                .collect(Collectors.toList());
    }


    public List<RoomDTO> getAllRoomsInBranchWithEmptyStatus(Integer branchId, String statusName) {
        List<Room> rooms = roomRepository.findByBranchIdAndStatus_StatusName(branchId, statusName);
        return rooms.stream()
                .map(RoomDTO::toDTO)
                .collect(Collectors.toList());
    }


    public RoomDTO getRoomById(Integer id) {
        Optional<Room> room = roomRepository.findById(id);
        return room.map(RoomDTO::toDTO).orElse(null);
    }

    public RoomDTO createRoom(RoomDTO roomDTO) {
        Room room = new Room();
        room.setFloor(floorRepository.findById(roomDTO.getFloorId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Floor ID")));
        room.setRoomNumber(roomDTO.getRoomNumber());
        room.setStatus(statusRepository.findById(roomDTO.getStatusId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Status ID")));
        room.setRoomType(roomTypeRepository.findById(roomDTO.getRoomTypeId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Room Type ID")));
        room.setBranch(branchRepository.findById(roomDTO.getBranch())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Branch ID")));

        Room savedRoom = roomRepository.save(room);
        return RoomDTO.toDTO(savedRoom);
    }

    public RoomDTO updateRoom(Integer id, RoomDTO roomDTO) {
        Optional<Room> optionalRoom = roomRepository.findById(id);

        if (optionalRoom.isPresent()) {
            Room room = optionalRoom.get();
            room.setFloor(floorRepository.findById(roomDTO.getFloorId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Floor ID")));
            room.setRoomNumber(roomDTO.getRoomNumber());
            room.setStatus(statusRepository.findById(roomDTO.getStatusId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Status ID")));
            room.setRoomType(roomTypeRepository.findById(roomDTO.getRoomTypeId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Room Type ID")));
            room.setBranch(branchRepository.findById(roomDTO.getBranch())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Branch ID")));

            Room updatedRoom = roomRepository.save(room);
            return RoomDTO.toDTO(updatedRoom);
        } else {
            return null;
        }
    }

    public boolean deleteRoom(Integer id) {
        if (roomRepository.existsById(id)) {
            roomRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}