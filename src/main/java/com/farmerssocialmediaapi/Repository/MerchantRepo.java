package com.farmerssocialmediaapi.Repository;

import com.farmerssocialmediaapi.Entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MerchantRepo extends JpaRepository<Merchant, Integer> {

    Optional<Merchant> findByEmail(String email);

    boolean existsByEmail(String email);
}
