package com.farmerssocialmediaapi.Repository;

import com.farmerssocialmediaapi.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {

    List<Post> findByFarmerId(Integer farmerid);
}
