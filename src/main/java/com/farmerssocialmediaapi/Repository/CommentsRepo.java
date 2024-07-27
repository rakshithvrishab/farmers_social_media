package com.farmerssocialmediaapi.Repository;

import com.farmerssocialmediaapi.Entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepo extends JpaRepository<Comments, Integer> {

    List<Comments> findByPostId(Integer id);
}
