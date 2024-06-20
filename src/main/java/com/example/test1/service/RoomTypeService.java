package com.example.test1.service;

import com.example.test1.Model.DTO.RoomTypeDTO;
import com.example.test1.Model.Entity.RoomType;
import com.example.test1.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomTypeService {

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    public List<RoomTypeDTO> getAllRoomTypes() {
        List<RoomType> roomTypes = roomTypeRepository.findAll();
        return roomTypes.stream()
                .map(RoomTypeDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public RoomTypeDTO getRoomTypeById(int id) {
        Optional<RoomType> roomType = roomTypeRepository.findById(id);
        return roomType.map(RoomTypeDTO::fromEntity).orElse(null);
    }

    public RoomTypeDTO createRoomType(RoomTypeDTO roomTypeDTO) {
        RoomType roomType = new RoomType();
        roomType.setId(roomTypeDTO.getId());
        roomType.setClassPrice(roomTypeDTO.getClassPrice());
        roomType.setClassName(roomTypeDTO.getClassName());
        roomType.setCapacity(roomTypeDTO.getCapacity());
        roomType.setDescription(roomTypeDTO.getDescription());

        RoomType savedRoomType = roomTypeRepository.save(roomType);
        return RoomTypeDTO.fromEntity(savedRoomType);
    }

    public RoomTypeDTO updateRoomType(Integer id, RoomTypeDTO roomTypeDTO) {
        Optional<RoomType> optionalRoomType = roomTypeRepository.findById(id);

        if (optionalRoomType.isPresent()) {
            RoomType roomType = optionalRoomType.get();
            roomType.setClassPrice(roomTypeDTO.getClassPrice());
            roomType.setClassName(roomTypeDTO.getClassName());
            roomType.setCapacity(roomTypeDTO.getCapacity());
            roomType.setDescription(roomTypeDTO.getDescription());

            RoomType updatedRoomType = roomTypeRepository.save(roomType);
            return RoomTypeDTO.fromEntity(updatedRoomType);
        } else {
            return null;
        }
    }

    public boolean deleteRoomType(Integer id) {
        if (roomTypeRepository.existsById(id)) {
            roomTypeRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}