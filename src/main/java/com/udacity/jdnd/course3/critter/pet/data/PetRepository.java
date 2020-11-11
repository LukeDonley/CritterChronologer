package com.udacity.jdnd.course3.critter.pet.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<PetData, Long> {
    List<PetData> getAllByCustomerId(Long customerId);
}
