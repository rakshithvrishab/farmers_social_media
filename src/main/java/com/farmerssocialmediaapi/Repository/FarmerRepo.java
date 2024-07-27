package com.farmerssocialmediaapi.Repository;

import com.farmerssocialmediaapi.Entity.Farmer;
import com.farmerssocialmediaapi.Entity.Post;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FarmerRepo extends JpaRepository<Farmer, Integer> {

    Optional<Farmer> findByEmail(String email);

    boolean existsByEmail(String email);

}
