package com.petstore.demo.reposiotry;

import com.petstore.demo.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet,Long> {
    @Query(nativeQuery = true,value = "select * from pet as p where p.status IN (:status)")
    List<Pet> findByStatus(String[] status);
}
