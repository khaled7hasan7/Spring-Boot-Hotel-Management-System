package com.example.test1.service;
import com.example.test1.Model.DTO.BranchDTO;
import com.example.test1.Model.Entity.Branch;
import com.example.test1.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    public List<BranchDTO> getAllBranches() {
        List<Branch> branches = branchRepository.findAll();
        return branches.stream()
                .map(BranchDTO::toDTO)
                .collect(Collectors.toList());
    }

    public BranchDTO getBranchById(Integer id) {
        Optional<Branch> branch = branchRepository.findById(id);
        return branch.map(BranchDTO::toDTO).orElse(null);
    }

    public BranchDTO createBranch(BranchDTO branchDTO) {
        Branch branch = new Branch();
        branch.setName(branchDTO.getName());
        branch.setPhone(branchDTO.getPhone());
        branch.setLocation(branchDTO.getLocation());
        branch.setDescription(branchDTO.getDescription());

        Branch savedBranch = branchRepository.save(branch);
        return BranchDTO.toDTO(savedBranch);
    }

    public BranchDTO updateBranch(Integer id, BranchDTO branchDTO) {
        Optional<Branch> optionalBranch = branchRepository.findById(id);

        if (optionalBranch.isPresent()) {
            Branch branch = optionalBranch.get();
            branch.setName(branchDTO.getName());
            branch.setPhone(branchDTO.getPhone());
            branch.setLocation(branchDTO.getLocation());
            branch.setDescription(branchDTO.getDescription());

            Branch updatedBranch = branchRepository.save(branch);
            return BranchDTO.toDTO(updatedBranch);
        } else {
            return null;
        }
    }

    public boolean deleteBranch(Integer id) {
        if (branchRepository.existsById(id)) {
            branchRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}