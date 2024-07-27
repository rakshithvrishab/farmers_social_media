package com.farmerssocialmediaapi.Repository;

import com.farmerssocialmediaapi.Entity.Messages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessagesRepo extends JpaRepository<Messages, Integer> {


    List<Messages> findByFarmerIdAndMerchantId(Integer farmerid, Integer merchantid);
}
