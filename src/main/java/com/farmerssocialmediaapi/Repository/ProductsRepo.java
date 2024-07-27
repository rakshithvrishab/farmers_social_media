package com.farmerssocialmediaapi.Repository;

import com.farmerssocialmediaapi.Entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsRepo extends JpaRepository<Products, Integer> {

    List<Products> findByFarmerId(Integer id);
}
