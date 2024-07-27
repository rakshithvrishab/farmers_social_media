package com.farmerssocialmediaapi.Repository;

import com.farmerssocialmediaapi.Entity.Biddings;
import com.farmerssocialmediaapi.Entity.Merchant;
import com.farmerssocialmediaapi.Entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BiddingsRepo extends JpaRepository<Biddings, Integer> {

    List<Biddings> findByMerchant(Merchant merchant);

    List<Biddings> findByProducts(Products products);

    boolean existsByMerchantIdAndProductsId(Integer merchantid, Integer productid);
}
