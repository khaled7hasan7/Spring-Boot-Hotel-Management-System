package com.example.test1.service;


import com.example.test1.Model.DTO.PositionDTO;
import com.example.test1.Model.Entity.Position;
import com.example.test1.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PositionService {


    @Autowired
    private PositionRepository repo;



    public List<PositionDTO> getAllCustomers() {
        List<Position> position = repo.findAll();
        return position.stream()
                .map(PositionDTO::toDTO)
                .collect(Collectors.toList());
    }



    public PositionDTO getPositionById(int id) {
        Optional<Position> position = repo.findById(id);
        return position.map(PositionDTO::toDTO).orElse(null);
//
    }

    public PositionDTO createposition(PositionDTO positionDTO) {
        Position position = new Position();
        position.setPositionName(positionDTO.getPositionName());
        position.setId(positionDTO.getId());
        Position save = repo.save(position);
        return PositionDTO.toDTO(save);
    }

    public PositionDTO updateCustomer(Integer id, PositionDTO positionDTO) {
        Optional<Position> optionalPosition = repo.findById(id);

        if (optionalPosition.isPresent()) {
            Position position = optionalPosition.get();
            position.setId(positionDTO.getId());
            position.setPositionName(positionDTO.getPositionName());


            Position updatedPosition = repo.save(position);
            return PositionDTO.toDTO(updatedPosition);
        } else {
            return null;
        }
    }

    public boolean deletePosition(Integer id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }




}
